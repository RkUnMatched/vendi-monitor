package com.vendixxx.monitor.springboot.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * nacos外部化配置
 *
 * @author liuzheng
 * @date 2021-01-27
 * @since 2021
 */
@ConfigurationProperties(MonitorPropertiesDoor.MONITOR_REGISTRY)
@Data
public class RegistryProperties {

    /**
     * 注册中心地址
     */
    private String connectString = "";

}
