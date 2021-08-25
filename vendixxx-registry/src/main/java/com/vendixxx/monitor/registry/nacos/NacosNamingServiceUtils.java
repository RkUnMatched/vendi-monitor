package com.vendixxx.monitor.registry.nacos;

import com.alibaba.nacos.api.NacosFactory;
import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.api.naming.NamingService;
import com.alibaba.nacos.api.naming.pojo.Instance;
import com.google.common.collect.Maps;
import com.vendixxx.monitor.common.config.CuratorVendixxxConfig;
import com.vendixxx.monitor.common.rpc.InstanceDetail;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.Properties;

import static com.alibaba.nacos.api.PropertyKeyConst.SERVER_ADDR;

/**
 * Nacos NamingService 管理类
 *
 * @author liuzheng
 * @date 2021-01-18
 * @since 2021
 */
@Slf4j
public class NacosNamingServiceUtils {

    public static Instance toInstance(InstanceDetail instanceDetail) {
        Instance instance = new Instance();
        instance.setServiceName(instanceDetail.getServiceName());
        instance.setIp(instanceDetail.getAddress().split(":")[0]);
        instance.setPort(Integer.parseInt(instanceDetail.getAddress().split(":")[1]));
        instance.setEnabled(true);
        instance.setHealthy(true);
        //设置clusterName,查找服务时候用到,用来过滤
        instance.setClusterName(instanceDetail.getEnv());
        Map<String, String> metadata = Maps.newHashMap();
        metadata.put(CuratorVendixxxConfig.env,String.valueOf(instanceDetail.getEnv()));
        metadata.put(CuratorVendixxxConfig.degrade,String.valueOf(instanceDetail.isDegrade()));
        metadata.put(CuratorVendixxxConfig.controllerMapping,instanceDetail.getControllerRequestMapping());
        metadata.put(CuratorVendixxxConfig.methodMapping,instanceDetail.getMethodRequestMapping());
        instance.setMetadata(metadata);
        return instance;
    }

    public static InstanceDetail fromRemote(Instance instance) {
        InstanceDetail.InstanceDetailBuilder builder = new InstanceDetail.InstanceDetailBuilder();
        builder.address(instance.getIp()+":"+instance.getPort())
                .env(getParams(instance.getMetadata(), CuratorVendixxxConfig.env,"dev"))
                .methodName(instance.getServiceName())
                .controllerRequestMapping(getParams(instance.getMetadata(), CuratorVendixxxConfig.controllerMapping,""))
                .methodReuqestMapping(getParams(instance.getMetadata(), CuratorVendixxxConfig.methodMapping,""))
                .degrade(Boolean.parseBoolean(getParams(instance.getMetadata(), CuratorVendixxxConfig.degrade,"false")));
        InstanceDetail instanceDetail = builder.build();
        return instanceDetail;
    }

    public static String getParams(Map<String,String> metadata,String key,String defaultValue){
        if(metadata == null||metadata.isEmpty()){
            return defaultValue;
        }
        return metadata.get(key);
    }

    public static NamingService createNamingService(String connectString) {
        Properties nacosProperties = buildNacosProperties(connectString);
        NamingService namingService;
        try {
            namingService = NacosFactory.createNamingService(nacosProperties);
        } catch (NacosException e) {
            if (log.isErrorEnabled()) {
                log.error(e.getErrMsg(), e);
            }
            throw new IllegalStateException(e);
        }
        return namingService;
    }

    private static Properties buildNacosProperties(String connectString) {
        Properties properties = new Properties();
        setServerAddr(connectString, properties);
        return properties;
    }

    private static void setServerAddr(String connectString, Properties properties) {
        properties.put(SERVER_ADDR, connectString);
    }

}
