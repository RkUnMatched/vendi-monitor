package com.vendixxx.monitor.registry.listener;

import com.vendixxx.monitor.common.config.MonitorCommonProperties;

/**
 * bean 周期接口
 * @author liuzheng
 * @date 2021-01-13
 * @since 2021
 */
public interface BeanInit {


    /**
     * 初始化前置处理
     * @throws Exception
     */
    void processBeforeInitialization(MonitorCommonProperties monitorCommonProperties) throws Exception;

    /**
     * class type
     * @return
     */
    Class<?> getClassType();

    /**
     * like BeanPostProcesser
     * 初始化 bean
     * @return
     * @throws Exception
     */
    void init(MonitorCommonProperties monitorCommonProperties, String... packages) throws Exception;

    /**
     * like BeanPostProcesser
     * 初始化后置处理
     * @throws Exception
     */
    void procesAfterInitialization(MonitorCommonProperties monitorCommonProperties) throws Exception;

    /**
     * 销毁
     * @throws Exception
     */
    void destroy() throws Exception;

    /**
     * 初始化排序用
     * @return
     */
    default Integer ordered(){
        return Integer.MAX_VALUE;
    }

    /**
     * 是否选中初始化
     * @return
     */
    default boolean selected(){
        return true;
    }
}
