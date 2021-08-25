package com.vendixxx.monitor.server.container.netty;

import com.google.common.collect.Lists;
import org.jboss.netty.bootstrap.Bootstrap;
import org.jboss.netty.bootstrap.ServerBootstrap;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.group.ChannelGroup;
import org.jboss.netty.channel.group.DefaultChannelGroup;
import org.jboss.netty.channel.socket.nio.NioServerSocketChannelFactory;
import org.jboss.resteasy.core.SynchronousDispatcher;
import org.jboss.resteasy.plugins.server.netty.HttpServerPipelineFactory;
import org.jboss.resteasy.plugins.server.netty.HttpsServerPipelineFactory;
import org.jboss.resteasy.plugins.server.netty.NettyJaxrsServer;
import org.jboss.resteasy.plugins.server.netty.RequestDispatcher;

import javax.net.ssl.SSLContext;
import java.net.InetSocketAddress;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * monitor netty server for jax-rs
 *
 * @author liuzheng
 * @date 2021-01-29
 * @since 2021
 */
public class MonitorNettyJaxrsServer extends NettyJaxrsServer {

    public static final int PORT = 19900;

    private SSLContext sslContext;

    static final ChannelGroup allChannels = new DefaultChannelGroup("NettyJaxrsServer");

    /**
     * expose bootstrap to be able to config
     *
     * @return
     */
    public Bootstrap initBootstrap() {
        this.bootstrap = new ServerBootstrap(new NioServerSocketChannelFactory(
                new ThreadPoolExecutor(0, Integer.MAX_VALUE, 60L, TimeUnit.SECONDS, new SynchronousQueue<Runnable>()),
                new ThreadPoolExecutor(0, Integer.MAX_VALUE, 60L, TimeUnit.SECONDS, new SynchronousQueue<Runnable>()),
                NettyJaxrsConfig.ioWorkerCount));
        return bootstrap;
    }

    public void setBootstrap(ServerBootstrap bootstrap) {
        this.bootstrap = bootstrap;
    }

    @Override
    public void start() {
        deployment.start();
        RequestDispatcher dispatcher = new RequestDispatcher((SynchronousDispatcher) deployment.getDispatcher(), deployment.getProviderFactory(), domain);

        // Configure the server
        if (bootstrap == null) {
            initBootstrap();
        }

        ChannelPipelineFactory factory = null;
        if (sslContext == null) {
            factory = new HttpServerPipelineFactory(dispatcher, root, NettyJaxrsConfig.executorThreadCount, NettyJaxrsConfig.maxRequestSize, false, Lists.newArrayList());
        } else {
            factory = new HttpsServerPipelineFactory(dispatcher, root, NettyJaxrsConfig.executorThreadCount, NettyJaxrsConfig.maxRequestSize, false, Lists.newArrayList(), sslContext);
        }
        // Set up the event pipeline factory.
        bootstrap.setPipelineFactory(factory);

        // Bind and start to accept incoming connections.
        channel = bootstrap.bind(new InetSocketAddress(PORT));
        allChannels.add(channel);
    }
}
