package com.vendixxx.monitor.client.boot;

import com.vendixxx.monitor.common.config.MonitorCommonProperties;

/**
 * @author liuzheng
 * @date 2021-01-14
 * @since 2021
 */
public class ClientBootStrap {

    public static void run(MonitorCommonProperties monitorCommonProperties, String ...packageName) throws Exception {
        ClientLoadRunner.doInit(monitorCommonProperties,packageName);
    }

    public static void close(String... packageName) {
        ClientLoadRunner.doClose(packageName);
    }
}
