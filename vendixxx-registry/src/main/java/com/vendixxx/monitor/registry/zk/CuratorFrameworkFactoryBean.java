package com.vendixxx.monitor.registry.zk;

import com.vendixxx.monitor.common.config.MonitorCommonProperties;
import com.vendixxx.monitor.common.exception.MonitorSystemException;
import com.vendixxx.monitor.registry.listener.BeanInit;
import lombok.extern.slf4j.Slf4j;
import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;

/**
 *
 * curator 客户端
 * @author liuzheng
 * @date 2021-01-13
 * @since 2021
 */
@Slf4j
public class CuratorFrameworkFactoryBean implements BeanInit {

    public static volatile CuratorFramework curator;

    private String connectString = "127.0.0.1:2181";
    private RetryPolicy retryPolicy;
    private Integer sessionTimeout;
    private String namespace;

    @Override
    public void processBeforeInitialization(MonitorCommonProperties monitorCommonProperties) throws Exception {
        //do nothing
    }

    public CuratorFramework getObject(){
        if(curator == null){
            try {
                init(null);
            } catch (Exception e) {
                //记录日志
                return null;
            }
        }
        return curator;
    }

    @Override
    public void init(MonitorCommonProperties monitorCommonProperties,String ... packages) throws Exception {
        if (connectString == null) {
            throw new MonitorSystemException("need connectString");
        }
        if (curator == null) {
            synchronized (CuratorFrameworkFactoryBean.class) {
                if (curator == null) {
                    CuratorFrameworkFactory.Builder builder = CuratorFrameworkFactory.builder();
                    builder.connectString(connectString);

                    if (retryPolicy == null) {
                        retryPolicy = new ExponentialBackoffRetry(1000, 3);
                    }
                    builder.retryPolicy(retryPolicy);

                    if (sessionTimeout != null) {
                        builder.sessionTimeoutMs(sessionTimeout);
                    }

                    if (namespace != null) {
                        builder.namespace(namespace);
                    }
                    curator = builder.build();
                    curator.start();
                }
            }
        }
        log.info("start zookeeper client success. connected to {}", connectString);
        return;
    }

    @Override
    public void procesAfterInitialization(MonitorCommonProperties monitorCommonProperties) throws Exception {

    }

    @Override
    public void destroy() throws Exception {
        curator.close();
    }

    @Override
    public Class<CuratorFramework> getClassType() {
        return CuratorFramework.class;
    }

    @Override
    public Integer ordered(){
        return 1;
    }

    public String getConnectString() {
        return connectString;
    }

    public void setConnectString(String connectString) {
        this.connectString = connectString;
    }

    public Integer getSessionTimeout() {
        return sessionTimeout;
    }

    public void setSessionTimeout(Integer sessionTimeout) {
        this.sessionTimeout = sessionTimeout;
    }

    public RetryPolicy getRetryPolicy() {
        return retryPolicy;
    }

    public void setRetryPolicy(RetryPolicy retryPolicy) {
        this.retryPolicy = retryPolicy;
    }

    public String getNamespace() {
        return namespace;
    }

    public void setNamespace(String namespace) {
        this.namespace = namespace;
    }

}