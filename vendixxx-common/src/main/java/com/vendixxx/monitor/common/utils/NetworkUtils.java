package com.vendixxx.monitor.common.utils;

import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.net.*;
import java.util.Enumeration;
import java.util.regex.Pattern;

/**
 * 通过UDP获取本机的IP地址
 * @author liuzheng
 * @date 2021-01-13
 * @since 2021
 */
public class NetworkUtils {


    private static final String LOCALHOST = "127.0.0.1";
    private static final String ANYHOST = "0.0.0.0";
    private static final int MAX_PORT = 65535;
    private static final Pattern IP_PATTERN = Pattern.compile("\\d{1,3}(\\.\\d{1,3}){3,5}$");

    private static volatile String ip = null;
    private static volatile Integer pid = null;

    private static volatile InetAddress LOCAL_ADDRESS = null;

    static {
        ip = getLocalAddress().getHostAddress();
        pid = initGetPid();
        System.out.println("本机ip:" + ip + ", pid:" + pid);
    }

    private static String fetchLocalIP() {
        String localIP = "127.0.0.1";
        DatagramSocket sock = null;
        try {
            SocketAddress socket_addr = new InetSocketAddress(InetAddress.getByName("1.2.3.4"), 1);
            sock = new DatagramSocket();
            sock.connect(socket_addr);

            localIP = sock.getLocalAddress().getHostAddress();
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            sock.disconnect();
            sock.close();
            sock = null;
        }
        return localIP;
    }


    public static String getIp() {
        return ip;
    }

    public static Integer getPid() {
        return pid;
    }

    /**
     * 获取当前JVM实例的进程ID
     */
    private static int initGetPid() {
        RuntimeMXBean runtime = ManagementFactory.getRuntimeMXBean();
        String name = runtime.getName();
        try {
            return Integer.parseInt(name.substring(0, name.indexOf('@')));
        } catch (Exception e) {
            return -1;
        }
    }

    private static boolean isValidAddress(InetAddress address) {
        if (address != null && !address.isLoopbackAddress()) {
            String name = address.getHostAddress();
            return name != null && !ANYHOST.equals(name) && !LOCALHOST.equals(name) && IP_PATTERN.matcher(name).matches();
        } else {
            return false;
        }
    }

    private static InetAddress getLocalAddress() {
        if (LOCAL_ADDRESS != null) {
            return LOCAL_ADDRESS;
        } else {
            InetAddress localAddress = getLocalAddress0();
            LOCAL_ADDRESS = localAddress;
            return localAddress;
        }
    }

    private static InetAddress getLocalAddress0() {
        InetAddress localAddress = null;

        try {
            localAddress = InetAddress.getLocalHost();
            if (isValidAddress(localAddress)) {
                return localAddress;
            }
        } catch (Exception var6) {

        }

        Enumeration interfaces = null;

        try {
            interfaces = NetworkInterface.getNetworkInterfaces();
        } catch (SocketException var5) {
            return localAddress;
        }

        if (interfaces == null) {
            return localAddress;
        } else {
            while (interfaces.hasMoreElements()) {
                NetworkInterface network = (NetworkInterface) interfaces.nextElement();
                Enumeration addresses = network.getInetAddresses();

                while (addresses.hasMoreElements()) {
                    InetAddress address = (InetAddress) addresses.nextElement();
                    if (isValidAddress(address)) {
                        return address;
                    }
                }
            }
            return localAddress;
        }
    }


    /**
     * 判断端口是否被占用
     */
    public static boolean isPortUsing(String host, int port) throws UnknownHostException {
        boolean flag = false;
        InetAddress theAddress = InetAddress.getByName(host);
        Socket socket = null;
        try {
            socket = new Socket(theAddress, port);
            flag = true;
        } catch (IOException e) {
            //如果所测试端口号没有被占用，那么会抛出异常，这里利用这个机制来判断
            //所以，这里在捕获异常后，什么也不用做
        } finally {
            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException ioe) {
                    ioe.printStackTrace();
                }
            }
        }
        return flag;
    }

    public static int getAvailablePort(InetAddress hostAddress, int port) {
        if (port <= 0) {
            throw new IllegalArgumentException("port must big than 0");
        } else {
            int i = port;

            while (i < MAX_PORT) {
                try {
                    ServerSocket ss = new ServerSocket(i, 50, hostAddress);

                    int var4;
                    try {
                        var4 = i;
                    } catch (Throwable var7) {
                        try {
                            ss.close();
                        } catch (Throwable var6) {
                            var7.addSuppressed(var6);
                        }

                        throw var7;
                    }

                    ss.close();
                    return var4;
                } catch (IOException var8) {
                    ++i;
                }
            }
            return port;
        }
    }

    public static void main(String[] args) {
        System.out.println(getIp());
    }


}

