package com.vendixxx.monitor.server.container.netty;

/**
 * 配置项
 *
 * @author liuzheng
 * @date 2021-01-29
 * @since 2021
 */
public interface NettyJaxrsConfig {

    int maxRequestSize = 1024 * 1024 * 60;

    int executorThreadCount = 16;

    int ioWorkerCount = Runtime.getRuntime().availableProcessors() * 2;
}
