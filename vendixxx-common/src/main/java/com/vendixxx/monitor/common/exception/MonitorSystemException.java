package com.vendixxx.monitor.common.exception;

/**
 * @author liuzheng
 * @date 2021-01-12
 * @since 2021
 */
public class MonitorSystemException extends RuntimeException {

    private Integer code;

    private String desc;

    public MonitorSystemException() {

    }

    /**
     * 系统异常
     *
     * @param desc 系统错误描述
     */
    public MonitorSystemException(String desc) {
        this.desc = desc;
    }

    /**
     * 系统异常
     * @param code
     * @param desc 系统错误描述
     */
    public MonitorSystemException(Integer code,String desc) {
        this.code = code;
        this.desc = desc;
    }

    @Override
    public String getMessage() {
        return "monitor system: " + desc + "has some failure";
    }


}


