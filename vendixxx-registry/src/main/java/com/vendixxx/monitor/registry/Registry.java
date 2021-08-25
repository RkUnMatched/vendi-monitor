package com.vendixxx.monitor.registry;

import com.vendixxx.monitor.common.annotation.ServiceMeta;
import com.vendixxx.monitor.registry.model.RemoteModel;

/**
 * 注册服务
 *
 * @author liuzheng
 * @date 2021-01-18
 * @since 2021
 */
public interface Registry {

    /**
     * 注册一个服务
     * @param address               暴露地址，例如：127.0.0.1:8081
     * @param methodName            方法的名称，取controller上{@link ServiceMeta}标签传入的参数, 如果没传，默认是 方法名称
     * @param methodMapping         method上的requestMapping标记的URL, 可能为空
     * @param controllerMapping     controller类上的requestMapping标记的URL, 可能为空
     * @param serviceMeta           方法上的 {@link ServiceMeta} 服务单元注解内容
     */
    void register(RemoteModel remoteModel, ServiceMeta serviceMeta);

    /**
     * 删除服务
     * @param address
     * @param methodName
     * @param methodMapping
     * @param controllerMapping
     * @param serviceMeta
     */
    void unregister(RemoteModel remoteModel, ServiceMeta serviceMeta);

}
