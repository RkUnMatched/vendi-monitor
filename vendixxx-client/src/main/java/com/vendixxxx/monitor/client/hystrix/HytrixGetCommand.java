package com.vendixxxx.monitor.client.hystrix;

import com.vendixxx.monitor.client.caller.HttpUtils;
import com.vendixxx.monitor.registry.ServiceFinder;
import org.apache.http.client.HttpClient;

import java.util.Map;


/**
 * @author liuzheng
 * get command
 * @param <T>
 */
public class HytrixGetCommand<T> extends HytrixBaseCommand<T> {

    public HytrixGetCommand(String env, HttpClient httpClient, ServiceFinder serviceFinder, String serviceName, Object request, Class<T> responseType) {
        super(env, httpClient, serviceFinder, serviceName, request, responseType);
    }


    /**
     * 直接传入URL
     *
     * @param env
     * @param httpClient
     * @param url
     * @param serviceName
     * @param request
     * @param responseType
     */
    public HytrixGetCommand(String env, HttpClient httpClient, String url, String serviceName, Object request, Class<T> responseType) {
        super(env, httpClient, url, serviceName, request, responseType);
    }

    @Override
    T getOrPost(String path, Object request, Class<T> responseType, Map<String, String> extraHeaders) throws Exception {
        return HttpUtils.clientGet(super.httpClient, path, request, responseType, extraHeaders);
    }


}