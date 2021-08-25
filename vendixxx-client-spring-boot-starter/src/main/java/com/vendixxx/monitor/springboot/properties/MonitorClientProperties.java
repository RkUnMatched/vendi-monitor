package com.vendixxx.monitor.springboot.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 客户端版本号
 *
 * @author liuzheng
 * @date 2021-01-20
 * @since 2021
 */
@ConfigurationProperties(prefix = MonitorPropertiesDoor.MONITOR_CLIENT)
@Data
public class MonitorClientProperties {

    /**
     * 版本号
     */
    private String version;

    /**
     * 应用名
     */
    private String application;

    /**
     * 应用描述
     */
    private String applcationDesc;

    private Integer nettyPort;

    /**
     * 应用token,这个需要在任务引擎系统中申请
     */
    private String token;
}
