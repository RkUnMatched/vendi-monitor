package com.vendixxxx.monitor.client.hystrix;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;
import com.netflix.hystrix.exception.HystrixBadRequestException;
import com.vendixxx.monitor.common.exception.ServiceNotAvaibleException;
import com.vendixxx.monitor.common.exception.ServiceNotFoundException;
import com.vendixxx.monitor.common.exception.VendixxxServiceException;
import com.vendixxx.monitor.common.rpc.InstanceDetail;
import com.vendixxx.monitor.registry.ServiceFinder;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.client.HttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * @author liuzheng
 * base command
 */
public abstract class HytrixBaseCommand<T> extends HystrixCommand<T> {
    protected final Logger log = LoggerFactory.getLogger(getClass());

    protected final String env;
    protected final Object request;
    protected final Class<T> responseType;
    protected final HttpClient httpClient;
    private final String serviceName;
    private final ServiceFinder serviceFinder;
    private final String url;

    private T fallback;

    /**
     * 源自那个服务
     */
    private String srcServiceName;

    /**
     * circuitBreaker
     * circuitBreaker.requestVolumeThreshold: 100,  10s窗口最少这么多失败才会触发open
     * circuitBreaker.sleepWindowInMilliseconds: 5000， 5000毫秒之后再去尝试
     * circuitBreaker.errorThresholdPercentage: 50. This property sets the error percentage at or above which the circuit should trip open and start short-circuiting requests to fallback logic.
     */
    public HytrixBaseCommand(String env, HttpClient httpClient, ServiceFinder serviceFinder, String serviceName, Object request, Class<T> responseType) {

        this(env, httpClient, null, serviceFinder, serviceName, request, responseType);

    }

    /**
     * circuitBreaker
     * circuitBreaker.requestVolumeThreshold: 100,  10s窗口最少这么多失败才会触发open
     * circuitBreaker.sleepWindowInMilliseconds: 5000， 5000毫秒之后再去尝试
     * circuitBreaker.errorThresholdPercentage: 50. This property sets the error percentage at or above which the circuit should trip open and start short-circuiting requests to fallback logic.
     */
    public HytrixBaseCommand(String env, HttpClient httpClient, String url, ServiceFinder serviceFinder, String serviceName, Object request, Class<T> responseType) {

        /**groupkey: serviceType
         * commandkey: serviceType.serviceMethod
         */
        super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey(serviceName.split("\\.", 2)[0]))
                .andCommandKey(HystrixCommandKey.Factory.asKey(serviceName))
        );
        this.env = env;
        this.url = url;
        this.serviceFinder = serviceFinder;
        this.httpClient = httpClient;
        this.serviceName = serviceName;
        this.request = request;
        this.responseType = responseType;
    }

    /**
     * circuitBreaker
     * circuitBreaker.requestVolumeThreshold: 100,  10s窗口最少这么多失败才会触发open
     * circuitBreaker.sleepWindowInMilliseconds: 5000， 5000毫秒之后再去尝试
     * circuitBreaker.errorThresholdPercentage: 50. This property sets the error percentage at or above which the circuit should trip open and start short-circuiting requests to fallback logic.
     */
    public HytrixBaseCommand(String env, HttpClient httpClient, String url, String serviceName, Object request, Class<T> responseType) {
        this(env, httpClient, url, null, serviceName, request, responseType);
    }


    /**
     * fallback逻辑：再找一个服务执行一次
     */
    @Override
    public T getFallback() {
        if (this.fallback != null) {
            if (log.isInfoEnabled()) {
                log.info("fallback happens for service: {}", serviceName);
            }
            return this.fallback;
        }
        log.warn("fallback happens for service: {}   throw ServiceNotAvaibleException exception", serviceName, fallback);
        throw new ServiceNotAvaibleException(serviceName);
    }


    /**
     * run in hystrix thread
     *
     * @return
     * @throws Exception
     */
    @Override
    protected T run() throws Exception {

        /*** 1. 先找出服务地址, 是否降级等信息 **/
        boolean degrade = false;
        String path = this.url;
        //如果没传入url，则需要从服务注册中心中查找服务
        if (StringUtils.isEmpty(path)) {
            InstanceDetail instanceDetail = this.serviceFinder.getService(env, serviceName);
            if (instanceDetail == null) {
                log.warn("can not find path for service:{}", serviceName);
                ServiceNotFoundException ex = new ServiceNotFoundException(serviceName);
                throw ex;
            }
            degrade = instanceDetail.isDegrade();
            path = instanceDetail.getRequestUrl();

            //path = "http://127.0.0.1:8080/test/getCompany";
        }

        /*** 2. 如果降级，并且传入了默认值，则直接返回默认值**/
        if (degrade && this.fallback != null) {
            return this.fallback;
        }

        /*** 3. 发起调用**/
        //额外的消息头，用来传递原始的消息
        long start = System.currentTimeMillis();
        Map<String, String> extraHeaders = new HashMap<>();
        T t;
        try {
            t = this.getOrPost(path, request, responseType, extraHeaders);
            long cost = System.currentTimeMillis() - start;
        } catch (Exception e) {

            if (e instanceof VendixxxServiceException) {
                throw new HystrixBadRequestException(e.getMessage(), e);
            } else {
                log.warn("call service:{} at url:{}  failed with exception.", serviceName, path, e);
                throw e;
            }
        }
        return t;
    }

    /**
     * http get or post
     *
     * @param path         请求路径
     * @param request      请求消息体
     * @param responseType 响应类型
     * @param extraHeaders 额外的头
     * @return 响应结果
     * @throws Exception
     */
    abstract T getOrPost(String path, Object request, Class<T> responseType, Map<String, String> extraHeaders) throws Exception;


    public void setFallback(T fallback) {
        this.fallback = fallback;
    }

    public void setSrcServiceName(String srcServiceName) {
        this.srcServiceName = srcServiceName;
    }


}
