package com.vendixxx.monitor.registry.nacos;

import com.alibaba.nacos.api.naming.NamingService;
import com.alibaba.nacos.api.naming.pojo.Instance;
import com.vendixxx.monitor.common.annotation.ServiceMeta;
import com.vendixxx.monitor.common.config.CuratorVendixxxConfig;
import com.vendixxx.monitor.common.config.MonitorCommonProperties;
import com.vendixxx.monitor.common.rpc.InstanceDetail;
import com.vendixxx.monitor.registry.AbstractHttpRegistry;
import com.vendixxx.monitor.registry.Registry;
import com.vendixxx.monitor.registry.listener.BeanInit;
import com.vendixxx.monitor.registry.model.HttpRemoteModel;
import com.vendixxx.monitor.registry.model.RemoteModel;
import lombok.extern.slf4j.Slf4j;

/**
 * ncaos注册中心
 * @author liuzheng
 * @date 2021-01-18
 * @since 2021
 */
@Slf4j
public class NcaosRegistry extends AbstractHttpRegistry implements BeanInit {

    public static volatile Registry registry;

    private NamingService namingService;

    /**
     * 无参构造函数在 ServiceLoader加载时需要调用这个方法
     */
    public NcaosRegistry() {
    }

    public NcaosRegistry(NamingService namingService){
        this.namingService = NacosNamingServiceFactoryBean.namingService;
    }

    @Override
    public void processBeforeInitialization(MonitorCommonProperties monitorCommonProperties) throws Exception {

    }

    @Override
    public Class<?> getClassType() {
        return NcaosRegistry.class;
    }

    @Override
    public void init(MonitorCommonProperties monitorCommonProperties,String... packages) throws Exception {
        if(!selected()){
            log.info("don't need to initialize nacos registry");
            return;
        }
        if (registry == null) {
            synchronized (NcaosRegistry.class) {
                if (registry == null) {
                    registry = new NcaosRegistry(namingService);
                }
            }
        }
        return;
    }

    @Override
    public void procesAfterInitialization(MonitorCommonProperties monitorCommonProperties) throws Exception {

    }

    @Override
    public void destroy() throws Exception {
        registry = null;
        namingService = null;
    }

    @Override
    public Integer ordered() {
        return CuratorVendixxxConfig.INIT_DIRECT_REF;
    }

    @Override
    public boolean selected() {
        return true;
    }

    @Override
    public void doRegister(RemoteModel remoteModel, ServiceMeta serviceMeta, InstanceDetail instanceDetail) {
        Instance instance = NacosNamingServiceUtils.toInstance(instanceDetail);
        try {
            HttpRemoteModel httpRemoteModel = (HttpRemoteModel)remoteModel;
            //groupName用env表示环境隔离
            namingService.registerInstance(httpRemoteModel.getMethodName(),httpRemoteModel.getEnv(),instance);
        } catch (Exception e) {
            log.error("register nacos service : {} error.", e.getMessage());
        }
    }

    @Override
    public void doUnregister(RemoteModel remoteModel, ServiceMeta serviceMeta, InstanceDetail instanceDetail) {
        Instance instance = NacosNamingServiceUtils.toInstance(instanceDetail);
        try {
            HttpRemoteModel httpRemoteModel = (HttpRemoteModel)remoteModel;
            namingService.deregisterInstance(httpRemoteModel.getMethodName(),httpRemoteModel.getEnv(),instance);
        } catch (Exception e) {
            log.error("deregister nacos service : {} error.", e.getMessage());
        }
    }
}
