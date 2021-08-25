package com.vendixxx.monitor.registry.model;

import lombok.Data;

/**
 * @author liuzheng
 * @date 2021-01-27
 * @since 2021
 */
@Data
public class RemoteModel {

    String env;

    Integer port;

    String ip;

    /**
     * 协议
     */
    String protocol;

    public RemoteModel() {
    }

    public RemoteModel(String env, String ip) {
        this.env = env;
        this.ip = ip;
    }


    public RemoteModel(String env, String ip, String protocol) {
        this.env = env;
        this.protocol = protocol;
        this.ip = ip;
    }

    public RemoteModel(String env, String ip, Integer port, String protocol) {
        this.env = env;
        this.port = port;
        this.ip = ip;
        this.protocol = protocol;
    }
}
