package com.vendixxx.monitor.registry.model;

import lombok.Data;

/**
 * httpRemote
 *
 * @author liuzheng
 * @date 2021-01-27
 * @since 2021
 */
@Data
public class HttpRemoteModel extends RemoteModel{

    String address;

    String methodName;

    String methodMapping;

    String controllerMapping;

    public HttpRemoteModel() {
        super();
    }

    public HttpRemoteModel(String env, String ip, String address, String methodName, String methodMapping, String controllerMapping) {
        super(env, ip, RemoteProtocol.HTTP);
        this.address = address;
        this.methodName = methodName;
        this.methodMapping = methodMapping;
        this.controllerMapping = controllerMapping;
    }

    public HttpRemoteModel(String env, String ip, Integer port, String address, String methodName, String methodMapping, String controllerMapping) {
        super(env, ip, port, RemoteProtocol.HTTP);
        this.address = address;
        this.methodName = methodName;
        this.methodMapping = methodMapping;
        this.controllerMapping = controllerMapping;
    }
}
