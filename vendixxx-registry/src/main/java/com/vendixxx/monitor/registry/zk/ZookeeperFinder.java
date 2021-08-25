package com.vendixxx.monitor.registry.zk;

import com.vendixxx.monitor.common.exception.ServiceNotFoundException;
import com.vendixxx.monitor.common.rpc.InstanceDetail;
import com.vendixxx.monitor.registry.ServiceFinder;
import com.vendixxx.monitor.registry.strategy.Strategy;
import org.apache.commons.lang3.StringUtils;
import org.apache.curator.x.discovery.ServiceInstance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * zk 服务发现
 *
 * @author liuzheng
 * @date 2021-01-27
 * @since 2021
 */
public class ZookeeperFinder implements ServiceFinder {

    private final Logger logger = LoggerFactory.getLogger(ZookeeperFinder.class);

    //setter
    private Strategy strategry;

    private CuratorXDiscoveryClientWrapper curatorXDiscoveryClientWrapper;

    @Override
    public InstanceDetail getService(String env, String serviceName) throws ServiceNotFoundException {

        try {
            List<ServiceInstance<InstanceDetail>> all = this.curatorXDiscoveryClientWrapper.getInstancesByName(serviceName);
            ServiceInstance<InstanceDetail> serviceInstance = strategry.getInstance(all);
            if (serviceInstance != null && serviceInstance.getPayload() != null && !StringUtils.isEmpty(serviceInstance.getPayload().getRequestUrl())) {
                logger.debug("find instance: {} by service name: {}", serviceInstance.getPayload(), serviceName);
                return serviceInstance.getPayload();
            } else {
                throw new ServiceNotFoundException(serviceName);
            }
        } catch (Exception e) {
            logger.error("find instance from zookeeper exception happened.", e);
            throw new ServiceNotFoundException(serviceName);
        }

    }

    public void setCuratorXDiscoveryClientWrapper(CuratorXDiscoveryClientWrapper curatorXDiscoveryClientWrapper) {
        this.curatorXDiscoveryClientWrapper = curatorXDiscoveryClientWrapper;
    }

    public void setStrategry(Strategy strategry) {
        this.strategry = strategry;
    }
}
