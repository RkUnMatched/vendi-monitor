package com.vendixxx.monitor.common.exception;

/**
 * 服务查找不到异常
 * @author liuzheng
 * @date 2021-01-12
 * @since 2021
 */
public class ServiceNotFoundException extends  RuntimeException {

    private String serviceName;

    @Override
    public String getMessage() {
        return "can not find path for service name:" + serviceName;
    }

    public ServiceNotFoundException(String serviceName) {
        this.serviceName = serviceName;
    }

    public ServiceNotFoundException(String message, String serviceName) {
        super(message);
        this.serviceName = serviceName;
    }



}
