package com.vendixxx.monitor.client.wrapper;

import com.vendixxx.monitor.common.exception.ServiceNotFoundException;
import com.vendixxx.monitor.common.rpc.InstanceDetail;
import com.vendixxx.monitor.registry.ServiceFinder;
import com.vendixxx.monitor.registry.nacos.NacosFinder;
import lombok.extern.slf4j.Slf4j;

/**
 * adaptor
 */
@Slf4j
public class ServiceFinderAdaptor implements ServiceFinder {

    /**
     * 这里以后需要改成 SPI机制自发现
     */
    private NacosFinder nacosFinder;


    @Override
    public InstanceDetail getService(String env, String serviceName) throws ServiceNotFoundException {

        InstanceDetail instanceDetail = null;
        try {
            instanceDetail = this.nacosFinder.getService(env, serviceName);
        } catch (Exception e) {
            log.warn("find service:{} from zk exception.", serviceName, e);
        }

        if (log.isDebugEnabled()) {
            log.debug("find service:{} with name:{} success", instanceDetail, serviceName);
        }
        return instanceDetail;

    }

    public void setNacosFinder(NacosFinder nacosFinder) {
        this.nacosFinder = nacosFinder;
    }

}
