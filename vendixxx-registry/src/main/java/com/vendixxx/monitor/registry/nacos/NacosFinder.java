package com.vendixxx.monitor.registry.nacos;

import com.alibaba.nacos.api.naming.NamingService;
import com.vendixxx.monitor.common.exception.ServiceNotFoundException;
import com.vendixxx.monitor.common.function.ThrowableFunction;
import com.vendixxx.monitor.common.rpc.InstanceDetail;
import com.vendixxx.monitor.registry.ServiceFinder;
import com.vendixxx.monitor.registry.strategy.Strategy;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.stream.Collectors;

/**
 * nacos服务发现
 *
 * @author liuzheng
 * @date 2021-01-18
 * @since 2021
 */
@Slf4j
public class NacosFinder implements ServiceFinder {

    private NamingService namingService;

    //setter
    private Strategy strategy;

    @Override
    public InstanceDetail getService(String env, String serviceName) throws ServiceNotFoundException {
        List<InstanceDetail> list = ThrowableFunction.execute(namingService, service ->
                service.selectInstances(serviceName, env, true)
                        .stream()
                        //多环境,过滤掉非env数据
                        .filter(clusterService -> clusterService.getClusterName().equals(env))
                        .map(NacosNamingServiceUtils::fromRemote)
                        .collect(Collectors.toList())
        );
        InstanceDetail instanceDetail = strategy.getInstance(list);
        return instanceDetail;
    }

    public void setStrategy(Strategy strategy) {
        this.strategy = strategy;
    }

    public void setNamingService(NamingService namingService) {
        this.namingService = namingService;
    }
}
