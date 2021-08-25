package com.vendixxx.monitor.registry.nacos;

import com.alibaba.nacos.api.naming.pojo.Instance;
import com.google.common.collect.Maps;

import java.util.List;
import java.util.Map;

/**
 * Nacos instance管理类
 *
 * @author liuzheng
 * @date 2021-01-18
 * @since 2021
 */
public class NacosInstanceUtils {

    /**
     * serviceName -> refreshed instance list
     */
    private static final Map<String, List<Instance>> SERVICE_INSTANCE_LIST_MAP = Maps.newConcurrentMap();

    public static void initOrRefreshServiceInstanceList(String serviceName, List<Instance> instanceList) {
        SERVICE_INSTANCE_LIST_MAP.put(serviceName, instanceList);
    }

}
