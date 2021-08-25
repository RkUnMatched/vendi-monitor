package com.vendixxx.monitor.common.config;

import java.util.EventObject;

/**
 * 事件
 *
 * @author liuzheng
 * @date 2021-01-05
 * @since 2021
 */
public abstract class RefreshEvent extends EventObject {

    private static final long serialVersionUID = 1L;

    /**
     * System time when the event happened
     */
    private final long timestamp;


    /**
     * Create a new RefreshEvent.
     * @param source the object on which the event initially occurred (never {@code null})
     */
    public RefreshEvent(Object source) {
        super(source);
        this.timestamp = System.currentTimeMillis();
    }


    /**
     * Return the system time in milliseconds when the event happened.
     */
    public final long getTimestamp() {
        return this.timestamp;
    }

}

