package com.vendixxx.monitor.registry.nacos;

import com.alibaba.nacos.api.naming.NamingService;
import com.vendixxx.monitor.common.config.CuratorVendixxxConfig;
import com.vendixxx.monitor.common.config.MonitorCommonProperties;
import com.vendixxx.monitor.registry.listener.BeanInit;
import lombok.extern.slf4j.Slf4j;

/**
 * Nacos naming Service FactoryBean
 *
 * @author liuzheng
 * @date 2021-01-19
 * @since 2021
 */
@Slf4j
public class NacosNamingServiceFactoryBean implements BeanInit {

    public static volatile NamingService namingService;

    public NamingService getObject() throws Exception {
        if (namingService == null) {
            init(null);
        }
        return namingService;
    }

    @Override
    public void processBeforeInitialization(MonitorCommonProperties monitorCommonProperties) throws Exception {
        log.info("before init namingService");
    }

    @Override
    public Class<?> getClassType() {
        return NamingService.class;
    }

    @Override
    public void init(MonitorCommonProperties monitorCommonProperties, String... packages) throws Exception {
        if (namingService == null) {
            synchronized (NacosNamingServiceFactoryBean.class) {
                if (namingService == null) {
                    namingService = NacosNamingServiceUtils.createNamingService(monitorCommonProperties.getRegistryConnectString());
                }
            }
        }
        return;
    }

    @Override
    public void procesAfterInitialization(MonitorCommonProperties monitorCommonProperties) throws Exception {
        log.info("after init namingService");
    }

    @Override
    public void destroy() throws Exception {
        namingService = null;
    }

    @Override
    public Integer ordered() {
        //优先初始化
        return CuratorVendixxxConfig.INIT_ONE;
    }


}
