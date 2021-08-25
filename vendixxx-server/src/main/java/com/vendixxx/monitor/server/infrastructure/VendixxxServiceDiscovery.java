package com.vendixxx.monitor.server.infrastructure;

import com.vendixxx.monitor.common.annotation.ServiceMeta;
import com.vendixxx.monitor.common.config.MonitorCommonProperties;
import com.vendixxx.monitor.common.utils.NetworkUtils;
import com.vendixxx.monitor.registry.Registry;
import com.vendixxx.monitor.registry.listener.BeanInit;
import com.vendixxx.monitor.registry.model.HttpRemoteModel;
import com.vendixxx.monitor.registry.nacos.NcaosRegistry;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.reflections.Reflections;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Controller;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.Path;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 服务发现
 *
 * @author liuzheng
 * @date 2021-01-27
 * @since 2021
 */
@Slf4j
public class VendixxxServiceDiscovery implements BeanInit {

    private static final int PORT = 19900;

    private Registry registry;

    /**
     * 标记了 ServiceMeta.
     */
    private final Map<String, Object> serviceBeanMap = new ConcurrentHashMap<>();

    private String ip;

    //监听地址： 127.0.0.1:8081
    private String listenAddress;

    @Override
    public void processBeforeInitialization(MonitorCommonProperties monitorCommonProperties) throws Exception {
        //do nothing
    }

    @Override
    public Class getClassType() {
        return null;
    }

    @Override
    public void init(MonitorCommonProperties monitorCommonProperties, String... packages) throws Exception {
        String env = monitorCommonProperties.getEnv();
        this.registry = NcaosRegistry.registry;
        //反射工具扫描
        Reflections reflections = new Reflections(packages);
        Set<Class<?>> controllerClazzs = reflections.getTypesAnnotatedWith(Controller.class);
        Set<Class<?>> restControllerClazzs = reflections.getTypesAnnotatedWith(RestController.class);
        this.ip = NetworkUtils.getIp();
        this.listenAddress = ip + ":" + monitorCommonProperties.getPort();
        handleAnnotation(controllerClazzs, env);
        handleAnnotation(restControllerClazzs, env);
        return;
    }

    private void handleAnnotation(Set<Class<?>> targetClazzs, String env) {
        for (Class clazz : targetClazzs) {

            //获取service serviceType 如果有标签，则取标签的值，否则取context

            //获取controller上的request mapping. @RequestMapping("/value")
            Path classRequestMappingAnno = AnnotationUtils.findAnnotation(clazz, Path.class);
            String classPath = getPath(classRequestMappingAnno);
            classPath = classPath.replaceAll("/", "");

            //获取每一个方法上的request mapping的value，可能为空
            Method[] methods = ReflectionUtils.getAllDeclaredMethods(clazz);
            for (Method method : methods) {
                Path methodRequestMappingAnno = method.getAnnotation(Path.class);
                if (methodRequestMappingAnno != null) {
                    String methodPath = getPath(methodRequestMappingAnno);
                    //把所有 '/test/'转为'test'
                    methodPath = methodPath.replaceAll("/", "");
                    //method name. 如果有YHService的标签，则取标签的值，否则取方法名字
                    ServiceMeta methodAnno = method.getAnnotation(ServiceMeta.class);
                    String methodName = getServiceDescValue(methodAnno, method.getName());
                    //方法上的注解为空,说明不用注册到服务中
                    if (methodAnno != null) {
                        //判断methodName是否带应用名
                        if (!methodName.contains(".")) {
                            methodName = methodAnno.application() + "." + methodName;
                        }
                        this.registry.register(new HttpRemoteModel(env, this.ip, this.listenAddress, methodName, methodPath, classPath), methodAnno);
                    }
                }
            }
        }
    }

    @Override
    public void procesAfterInitialization(MonitorCommonProperties monitorCommonProperties) throws Exception {
        //do nothing
    }

    @Override
    public void destroy() throws Exception {

    }

    private static String getServiceDescValue(ServiceMeta serviceMeta, String defaultValue) {
        if (serviceMeta == null) {
            return defaultValue;
        }

        String serviceName = serviceMeta.service();
        if (StringUtils.isNotEmpty(serviceName)) {
            return serviceName;
        }

        String value = serviceMeta.value();
        if (StringUtils.isNotEmpty(value)) {
            return value;
        }

        return defaultValue;
    }


    private static String getPath(Path path) {
        if (path == null) {
            return "";
        }
        String value = path.value();
        if (StringUtils.isNotEmpty(value)) {
            return value;
        }
        return "";
    }

    public void setRegistry(Registry registry) {
        this.registry = registry;
    }
}
