package com.vendixxx.monitor.common.config;

/**
 * @author liuzheng
 * @date 2021-01-05
 * @since 2021
 */
public class RefreshContextEvent extends RefreshEvent{

    private Object context;

    public RefreshContextEvent(Object source, Object context) {
        super(source);
        this.context = context;
    }
}
