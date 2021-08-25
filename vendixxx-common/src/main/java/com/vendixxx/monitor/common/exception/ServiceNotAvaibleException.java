package com.vendixxx.monitor.common.exception;

/**
 * 服务不可用异常
 * @author liuzheng
 * @date 2021-01-12
 * @since 2021
 */
public class ServiceNotAvaibleException extends RuntimeException {

    private String serviceName;

    private Throwable originException;

    /**
     * 服务不可用异常
     *
     * @param serviceName 服务名称
     * @param e:          异常
     */
    public ServiceNotAvaibleException(String serviceName, Throwable e) {
        this.serviceName = serviceName;
        this.originException = e;
    }

    /**
     * 服务不可用异常
     *
     * @param serviceName 服务名称
     */
    public ServiceNotAvaibleException(String serviceName) {
        this.serviceName = serviceName;
    }

    @Override
    public String getMessage() {
        return "current service: " + serviceName + " is not available";
    }


}

