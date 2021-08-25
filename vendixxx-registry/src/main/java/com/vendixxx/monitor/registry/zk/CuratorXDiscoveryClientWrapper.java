package com.vendixxx.monitor.registry.zk;

import com.google.common.collect.Lists;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.vendixxx.monitor.common.rpc.InstanceDetail;
import com.vendixxx.monitor.common.rpc.VendixxxJsonInstanceSerializer;
import com.vendixxx.monitor.common.utils.CollectionUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.utils.CloseableUtils;
import org.apache.curator.utils.ZKPaths;
import org.apache.curator.x.discovery.*;

import java.io.Closeable;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

/**
 * 服务发现客户端
 */
@Slf4j
public class CuratorXDiscoveryClientWrapper{

    ThreadFactory namedThreadFactory = new ThreadFactoryBuilder().setNameFormat("curator-serviceCache-thread-%d").build();
    ExecutorService serviceCacheExecutors;

    private ServiceDiscovery<InstanceDetail> serviceDiscovery;

    private Map<String, ServiceCache<InstanceDetail>> cacheMap = new ConcurrentHashMap<>();

    private List<Closeable> closeableList = Lists.newArrayList();
    private Object lock = new Object();
    private String basePath;
    private String zkAddress;
    private CuratorFramework client;

    /**
     * 构造函数
     *
     * @param client   zookeeper客户端
     * @param basePath zk上服务注册的基本路径
     * @throws Exception 异常
     */
    public CuratorXDiscoveryClientWrapper(CuratorFramework client, String basePath) throws Exception{
        this.serviceCacheExecutors = Executors.newFixedThreadPool(30, namedThreadFactory);
        this.basePath = basePath;
        this.client = client;
        this.zkAddress = client.getZookeeperClient().getCurrentConnectionString();
        VendixxxJsonInstanceSerializer<InstanceDetail> serializer = new VendixxxJsonInstanceSerializer<InstanceDetail>(InstanceDetail.class);
        this.serviceDiscovery = ServiceDiscoveryBuilder.builder(InstanceDetail.class)
                .client(client)
                .basePath(basePath)
                .serializer(serializer)
                .build();
        serviceDiscovery.start();
    }


    /**
     * 获取一个服务的所有地址
     *
     * @param serviceName 服务名称
     * @return 所有地址
     * @throws Exception 异常
     */
    public List<String> getAllRequestUrl(String serviceName) throws Exception {
        List<String> path = new LinkedList<>();
        List<ServiceInstance<InstanceDetail>> all = getInstancesByName(serviceName);
        for (ServiceInstance<InstanceDetail> one : all) {
            path.add(one.getPayload().getRequestUrl());
        }
        return path;
    }


    /**
     * 注销这个IP下的这个context下的所有服务实例 用于内部管理
     *
     * @param ip      IP
     * @param application application
     */
    public void unRegister(String ip, String application) throws Exception {

        if (StringUtils.isEmpty(ip) || StringUtils.isEmpty(application)) {
            return;
        }

        Collection<String> serviceNames = serviceDiscovery.queryForNames();
        if (CollectionUtils.isEmpty(serviceNames)) {
            log.error("can not find any services from zookeeper:{}", zkAddress);
            return;
        }

        int count = 0;
        for (String serviceName : serviceNames) {

            Collection<ServiceInstance<InstanceDetail>> instances = serviceDiscovery.queryForInstances(serviceName);
            if (CollectionUtils.isEmpty(instances)) {
                continue;
            }

            for (ServiceInstance<InstanceDetail> serviceInstance : instances) {
                //增加application
                if (serviceInstance.getAddress().equals(ip) && serviceInstance.getPayload().getApplication().equals(application)) {
                    //注销
                    this.deleteZkPath(serviceInstance.getName(), serviceInstance.getId());
                    count++;
                    log.debug("unregister: {} success", serviceInstance);
                }
            }
        }

        log.info("unregister total:{} service instances for ip:{} context:{} success.", count, ip, application);
    }


    private void deleteZkPath(String serviceName, String id) {
        String path = ZKPaths.makePath(this.basePath, serviceName);
        String full_path = ZKPaths.makePath(path, id);
        try {
            this.client.delete().guaranteed().forPath(full_path);
        } catch (Exception ex) {
            log.error("can not delete path.", ex);
        }
    }


    /**
     * 从zookeeper中获取所有的服务配置
     * monitor:
     * servers:
     * - http://192.168.102.205:8080/monitor
     * services:
     * - test:/monitor/test
     *
     * @return
     * @throws Exception
     */
    public Map<String, Object> getAllService() throws Exception {

        Map<String, Object> yaml = new HashMap<>();
        //get all service names
        Collection<String> serviceNames = serviceDiscovery.queryForNames();

        if (CollectionUtils.isEmpty(serviceNames)) {
            log.error("can not find any services from zookeeper:{}", zkAddress);
            return yaml;
        }

        for (String serviceName : serviceNames) {

            Collection<ServiceInstance<InstanceDetail>> instances;
            try {
                instances = serviceDiscovery.queryForInstances(serviceName);
                if (CollectionUtils.isEmpty(instances)) {
                    log.debug("can not find any service instance by name:{}", serviceName);
                    continue;
                }
            } catch (Exception e) {
                log.warn("exception happened when query instances for: {}", serviceName, e);
                continue;
            }

            String type = serviceName.substring(0, serviceName.indexOf(".")); //promotion: type
            String name = serviceName.substring(serviceName.indexOf(".") + 1); //method
            Map<String, List<String>> serviceInfoMap;
            if (yaml.containsKey(type)) {
                serviceInfoMap = (Map<String, List<String>>) yaml.get(type);
            } else {
                serviceInfoMap = new HashMap<>();
                yaml.put(type, serviceInfoMap);
            }

            //init servers
            List<String> servers;
            if (serviceInfoMap.containsKey("servers")) {
                servers = serviceInfoMap.get("servers");
            } else {
                servers = new LinkedList<>();
                serviceInfoMap.put("servers", servers);
            }

            //init servers
            List<String> services;
            if (serviceInfoMap.containsKey("META-INF")) {
                services = serviceInfoMap.get("META-INF");
            } else {
                services = new LinkedList<>();
                serviceInfoMap.put("META-INF", services);
            }

            for (ServiceInstance<InstanceDetail> serviceInstance : instances) {
                InstanceDetail instanceDetail = serviceInstance.getPayload();
                /**
                 *   添加server http://ip:port/context/xxxx
                 */
                String serverUrl = instanceDetail.fetchContextUrl();
                if (!servers.contains(serverUrl)) {
                    servers.add(serverUrl);
                }
                /**
                 *   添加service：cancelOrderCouponUse:/coupon/cancelOrderCouponUse
                 */
                String serviceUrl = instanceDetail.fetchSubUrl();
                String service = name + ":" + serviceUrl;
                if (!services.contains(service)) {
                    services.add(service);
                }
            }

        }
        return yaml;
    }


    /**
     * 根据服务名称，获取服务
     *
     * @param serviceName 服务的名称
     * @return 服务实例
     * @throws Exception 异常
     */
    public List<ServiceInstance<InstanceDetail>> getInstancesByName(String serviceName) throws Exception {
        ServiceCache<InstanceDetail> cache = cacheMap.get(serviceName);
        if (cache == null) {
            synchronized (lock) {
                cache = cacheMap.get(serviceName);
                if (cache == null) {
                    ServiceCacheBuilder<InstanceDetail> cacheBuilder = serviceDiscovery.serviceCacheBuilder();
                    cache = cacheBuilder.name(serviceName).executorService(this.serviceCacheExecutors).build();
                    cache.start();
                    closeableList.add(cache);
                    cacheMap.put(serviceName, cache);
                }
            }
        }


        return cache.getInstances();
    }

    public synchronized void close() {
        for (Closeable closeable : closeableList) {
            CloseableUtils.closeQuietly(closeable);
        }
    }


}