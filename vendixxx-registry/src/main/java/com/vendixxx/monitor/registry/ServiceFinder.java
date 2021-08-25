package com.vendixxx.monitor.registry;

import com.vendixxx.monitor.common.exception.ServiceNotFoundException;
import com.vendixxx.monitor.common.rpc.InstanceDetail;

/**
 * 服务发现者
 *
 * @author liuzheng
 * @date 2021-01-20
 */
public interface ServiceFinder {

    /**
     * @param env          环境
     * @param serviceName  服务名称
     * @return  服务
     */
     InstanceDetail getService(String env, String serviceName) throws ServiceNotFoundException;
}
