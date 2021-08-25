package com.vendixxx.monitor.common.config;

import java.util.EventListener;

/**
 * 事件监听器
 *
 * @author liuzheng
 * @date 2021-01-05
 * @since 2021
 */
public interface RefreshListener <E extends RefreshEvent> extends EventListener {

    /**
     * 监控触发方法
     * @param e
     */
    void onRefreshEvent(E e);
}
