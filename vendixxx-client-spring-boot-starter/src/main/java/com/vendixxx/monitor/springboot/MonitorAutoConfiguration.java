package com.vendixxx.monitor.springboot;

import com.google.common.base.Preconditions;
import com.vendixxx.monitor.client.boot.ClientBootStrap;
import com.vendixxx.monitor.client.caller.HystrixRpcCaller;
import com.vendixxx.monitor.client.caller.RpcCaller;
import com.vendixxx.monitor.client.http.HttpClientsFactoryBean;
import com.vendixxx.monitor.client.wrapper.ServiceFinderAdaptor;
import com.vendixxx.monitor.common.annotation.JsxrsBeanMeta;
import com.vendixxx.monitor.common.config.MonitorCommonProperties;
import com.vendixxx.monitor.common.config.MonitorHttpProperties;
import com.vendixxx.monitor.common.utils.CollectionUtils;
import com.vendixxx.monitor.registry.ServiceFinder;
import com.vendixxx.monitor.registry.nacos.NacosFinder;
import com.vendixxx.monitor.registry.nacos.NacosNamingServiceFactoryBean;
import com.vendixxx.monitor.registry.strategy.RoundRobinLocalFirstStrategy;
import com.vendixxx.monitor.registry.strategy.Strategy;
import com.vendixxx.monitor.server.container.netty.MonitorNettyJaxrsServer;
import com.vendixxx.monitor.springboot.annotation.EnableVendixxxMonitor;
import com.vendixxx.monitor.springboot.properties.HttpClientProperties;
import com.vendixxx.monitor.springboot.properties.MonitorClientProperties;
import com.vendixxx.monitor.springboot.properties.MonitorPropertiesDoor;
import com.vendixxx.monitor.springboot.properties.RegistryProperties;
import com.vendixxx.monitor.springboot.util.PropertyUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.jboss.resteasy.spi.ResteasyDeployment;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportAware;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotationMetadata;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ws.rs.ext.Provider;
import java.util.Collection;
import java.util.Locale;
import java.util.Optional;

/**
 * 配置引导
 *
 * @author liuzheng
 * @date 2021-01-13
 * @since 2021
 */
@Configuration
@ComponentScan
@EnableConfigurationProperties({RegistryProperties.class, HttpClientProperties.class, MonitorClientProperties.class})
@Slf4j
public class MonitorAutoConfiguration implements ImportAware, EnvironmentAware, ApplicationContextAware {

    public ApplicationContext applicationContext;

    public Environment environment;

    protected AnnotationAttributes enablevendixxxMonitor;

    public RegistryProperties registryProperties;

    public HttpClientProperties httpClientProperties;

    public MonitorClientProperties monitorClientProperties;

    public MonitorAutoConfiguration(RegistryProperties registryProperties, HttpClientProperties httpClientProperties, MonitorClientProperties monitorClientProperties) {
        this.registryProperties = registryProperties;
        this.httpClientProperties = httpClientProperties;
        this.monitorClientProperties = monitorClientProperties;
    }

    /**
     * 注册netty服务
     *
     * @return
     */
    @Bean
    public MonitorNettyJaxrsServer monitorNettyJaxrsServer() {
        MonitorNettyJaxrsServer netty = new MonitorNettyJaxrsServer();
        ResteasyDeployment resteasyDeployment = new ResteasyDeployment();
        Collection<Object> providers = applicationContext.getBeansWithAnnotation(Provider.class).values();
        Collection<Object> controllers = applicationContext.getBeansWithAnnotation(JsxrsBeanMeta.class).values();
        // extract providers
        if (CollectionUtils.isNotEmpty(providers)) {
            resteasyDeployment.getProviders().addAll(providers);
        }
        //extract controller beans
        resteasyDeployment.getResources().addAll(controllers);
        resteasyDeployment.getMediaTypeMappings().put("json", "application/json");
        resteasyDeployment.getMediaTypeMappings().put("xml", "text/xml");
        netty.initBootstrap().setOption("reuseAddress", true);
        netty.setDeployment(resteasyDeployment);
        netty.setPort(Optional.ofNullable(monitorClientProperties).map(MonitorClientProperties::getNettyPort).orElse(MonitorNettyJaxrsServer.PORT));
        netty.setRootResourcePath("");
        netty.setSecurityDomain(null);
        netty.start();
        log.info("netty jaxrs server successfully to export!");
        return netty;
    }

    @Bean
    public ServiceFinder serviceFinder() {
        ServiceFinderAdaptor serviceFinder = new ServiceFinderAdaptor();
        NacosFinder nacosFinder = new NacosFinder();
        Strategy strategy = new RoundRobinLocalFirstStrategy();
        nacosFinder.setStrategy(strategy);
        nacosFinder.setNamingService(NacosNamingServiceFactoryBean.namingService);
        serviceFinder.setNacosFinder(nacosFinder);
        return serviceFinder;
    }

    @Bean
    public RpcCaller rpcCaller(ServiceFinder serviceFinder) {
        String env = environment.getProperty("spring.profiles.active");
        HystrixRpcCaller rpcCaller = new HystrixRpcCaller();
        rpcCaller.setHttpClient(HttpClientsFactoryBean.closeableHttpClient);
        rpcCaller.setServiceFinder(serviceFinder);
        rpcCaller.setEnv(env);
        return rpcCaller;
    }

    @PostConstruct
    public void start() throws Exception {
        String[] basePackage = this.enablevendixxxMonitor.getStringArray("scanPackage");
        if (basePackage == null || basePackage.length == 0) {
            return;
        }
        String registryUrl = registryProperties.getConnectString();
        String env = environment.getProperty("spring.profiles.active");
        if (StringUtils.isEmpty(registryUrl)) {
            throw new IllegalArgumentException(
                    "@Monitor.Nacos.Registry can not be Empty!");
        }
        MonitorHttpProperties monitorHttpProperties = new MonitorHttpProperties();
        BeanUtils.copyProperties(this.httpClientProperties, monitorHttpProperties);
        MonitorCommonProperties monitorCommonProperties = new MonitorCommonProperties(registryUrl, env, monitorClientProperties.getNettyPort(), monitorHttpProperties);
        ClientBootStrap.run(monitorCommonProperties, basePackage);
    }

    @PreDestroy
    public void destroy() {
        String[] basePackage = this.enablevendixxxMonitor.getStringArray("scanPackage");
        ClientBootStrap.close(basePackage);
    }

    public static void main(String[] args) {
        String name = System.getProperty("os.name").toLowerCase(Locale.UK).trim();
        System.out.println(name);
    }

    @Override
    public void setImportMetadata(AnnotationMetadata importMetadata) {
        if (importMetadata == null) {
            return;
        }
        this.enablevendixxxMonitor = AnnotationAttributes.fromMap(
                importMetadata.getAnnotationAttributes(EnableVendixxxMonitor.class.getName(), false));
        if (this.enablevendixxxMonitor == null) {
            throw new IllegalArgumentException(
                    "@EnablevendixxxMonitor is not present on importing class " + importMetadata.getClassName());
        }
    }

    @Override
    public void setEnvironment(Environment environment) {
        httpClientProperties = PropertyUtil.handle(environment, MonitorPropertiesDoor.MONITOR_HTTP, HttpClientProperties.class);
        registryProperties = PropertyUtil.handle(environment, MonitorPropertiesDoor.MONITOR_REGISTRY, RegistryProperties.class);
        monitorClientProperties = PropertyUtil.handle(environment, MonitorPropertiesDoor.MONITOR_CLIENT, MonitorClientProperties.class);
        Preconditions.checkState(httpClientProperties != null, "wrong httpClientProperties!");
        Preconditions.checkState(registryProperties != null, "wrong registryProperties!");
        Preconditions.checkState(monitorClientProperties != null, "wrong monitorClientProperties!");
        this.environment = environment;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
