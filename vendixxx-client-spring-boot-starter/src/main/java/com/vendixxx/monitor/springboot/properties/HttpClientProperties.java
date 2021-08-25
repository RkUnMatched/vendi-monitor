package com.vendixxx.monitor.springboot.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * http配置
 *
 * @author liuzheng
 * @date 2021-01-20
 * @since 2021
 */
@ConfigurationProperties(prefix = MonitorPropertiesDoor.MONITOR_HTTP)
@Data
public class HttpClientProperties {

    private int connectionRequestTimeout;
    private int connectionTimeout;

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
