package com.vendixxx.monitor.registry.zk;

import com.vendixxx.monitor.common.annotation.ServiceMeta;
import com.vendixxx.monitor.common.config.CuratorVendixxxConfig;
import com.vendixxx.monitor.common.config.MonitorCommonProperties;
import com.vendixxx.monitor.common.rpc.InstanceDetail;
import com.vendixxx.monitor.common.rpc.VendixxxJsonInstanceSerializer;
import com.vendixxx.monitor.registry.AbstractHttpRegistry;
import com.vendixxx.monitor.registry.Registry;
import com.vendixxx.monitor.registry.listener.BeanInit;
import com.vendixxx.monitor.registry.model.HttpRemoteModel;
import com.vendixxx.monitor.registry.model.RemoteModel;
import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.x.discovery.ServiceDiscovery;
import org.apache.curator.x.discovery.ServiceDiscoveryBuilder;
import org.apache.curator.x.discovery.ServiceInstance;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * zk注册中心
 *
 * @author liuzheng
 * @date 2021-01-18
 * @since 2021
 */
@Slf4j
public class ZookeeperRegistry extends AbstractHttpRegistry implements BeanInit {

    public static volatile Registry registry;

    private ServiceDiscovery<InstanceDetail> serviceDiscovery;

    private Map<String, List<String>> map = new ConcurrentHashMap<>();

    @Override
    public void processBeforeInitialization(MonitorCommonProperties monitorCommonProperties) throws Exception {

    }

    public Registry getObject() throws Exception{
        if (registry == null) {
            synchronized (ZookeeperRegistry.class) {
                if (registry == null) {
                    registry = new ZookeeperRegistry(new CuratorFrameworkFactoryBean().getObject(), CuratorVendixxxConfig.basePath);
                }
            }
        }
        return registry;
    }

    @Override
    public Class<Registry> getClassType() {
        return Registry.class;
    }

    @Override
    public void init(MonitorCommonProperties monitorCommonProperties,String... packages) throws Exception {
        if(!selected()){
            log.info("don't need to initialize zk registry");
            return;
        }
        if (registry == null) {
            synchronized (ZookeeperRegistry.class) {
                if (registry == null) {
                    registry = new ZookeeperRegistry(new CuratorFrameworkFactoryBean().getObject(), CuratorVendixxxConfig.basePath);
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
        this.serviceDiscovery = null;
        registry = null;
        map.clear();
    }

    @Override
    public Integer ordered(){
        return 1;
    }

    @Override
    public boolean selected() {
        return false;
    }

    /**
     * serviceLoad加载时需要调用这个无参构造方法！
     */
    public ZookeeperRegistry() {

    }

    /**
     * 服务注册
     * @param client curator客户端
     * @param basePath zk的path前缀
     * @throws Exception
     */
    public ZookeeperRegistry(CuratorFramework client, String basePath) throws Exception {
        //JSON序列化
        VendixxxJsonInstanceSerializer<InstanceDetail> serializer = new VendixxxJsonInstanceSerializer<InstanceDetail>(InstanceDetail.class);
        serviceDiscovery = ServiceDiscoveryBuilder.builder(InstanceDetail.class)
                .client(client)
                .serializer(serializer)
                .basePath(basePath)
                .build();
        serviceDiscovery.start();

        log.info("zookeeper ServiceRegister start success");
    }

    @Override
    protected void doRegister(RemoteModel remoteModel, ServiceMeta serviceMeta, InstanceDetail instanceDetail) {
        HttpRemoteModel httpRemoteModel = (HttpRemoteModel)remoteModel;
        String address = httpRemoteModel.getAddress();
        try {
            ServiceInstance<InstanceDetail> serviceInstance = ServiceInstance.<InstanceDetail>builder()
                    .name(instanceDetail.getServiceName())
                    .address(address.split(":")[0])
                    .port(Integer.parseInt(address.split(":")[1]))
                    .payload(instanceDetail)
                    .build();
            this.serviceDiscovery.registerService(serviceInstance);
        } catch (Exception e) {
            log.error("register zk service : {} error.", e.getMessage());
        }
    }

    @Override
    protected void doUnregister(RemoteModel remoteModel, ServiceMeta serviceMeta, InstanceDetail instanceDetail) {
        HttpRemoteModel httpRemoteModel = (HttpRemoteModel)remoteModel;
        String address = httpRemoteModel.getAddress();
        try {
            ServiceInstance<InstanceDetail> serviceInstance = ServiceInstance.<InstanceDetail>builder()
                    .name(instanceDetail.getServiceName())
                    .address(address.split(":")[0])
                    .port(Integer.parseInt(address.split(":")[1]))
                    .payload(instanceDetail)
                    .build();
            this.serviceDiscovery.unregisterService(serviceInstance);
        } catch (Exception e) {
            log.error("unregister zk service : {} error.", e.getMessage());
        }
    }

}
