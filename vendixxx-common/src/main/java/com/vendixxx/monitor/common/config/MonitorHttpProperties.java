package com.vendixxx.monitor.common.config;

import lombok.Data;

/**
 * @author liuzheng
 * @date 2021-01-20
 * @since 2021
 */
@Data
public class MonitorHttpProperties {

    private int connectionRequestTimeout;
    private int connectionTimeout ;
    private int socketTimeout;
    /**
     * 最大总数
     */
    private int maxTotal;

    /**
     * 默认并发数
     */
    private int defaultMaxPerRoute;
}
