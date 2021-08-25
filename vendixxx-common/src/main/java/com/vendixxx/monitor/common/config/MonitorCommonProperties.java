package com.vendixxx.monitor.common.config;

import lombok.Data;

/**
 * 配置
 *
 * @author liuzheng
 * @date 2021-01-05
 * @since 2021
 */
@Data
public class MonitorCommonProperties {

    private String registryConnectString;
    private String env;
    private Integer port;
    private MonitorHttpProperties monitorHttpProperties;

    public MonitorCommonProperties(String registryConnectString, String env, Integer port, MonitorHttpProperties monitorHttpProperties) {
        this.registryConnectString = registryConnectString;
        this.env = env;
        this.port = port;
        this.monitorHttpProperties = monitorHttpProperties;
    }
}
