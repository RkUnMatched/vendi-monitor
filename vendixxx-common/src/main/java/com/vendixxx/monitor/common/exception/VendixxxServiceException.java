package com.vendixxx.monitor.common.exception;

/**
 * @author liuzheng
 * @date 2021-01-12
 * @since 2021
 */
public class VendixxxServiceException extends RuntimeException {

    private String serviceName;

    private Throwable originException;

    /**
     * 业务异常
     *
     * @param serviceName 服务名称
     * @param e:          异常
     */
    public VendixxxServiceException(String serviceName, Throwable e) {
        this.serviceName = serviceName;
        this.originException = e;
    }

    /**
     * 业务异常
     *
     * @param serviceName 服务名称
     */
    public VendixxxServiceException(String serviceName) {
        this.serviceName = serviceName;
    }

    @Override
    public String getMessage() {
        return "current service: " + serviceName + " is not available";
    }


}


