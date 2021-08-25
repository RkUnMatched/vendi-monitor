package com.vendixxxx.monitor.client.hystrix;

import com.vendixxx.monitor.client.caller.HttpUtils;
import com.vendixxx.monitor.registry.ServiceFinder;
import org.apache.http.client.HttpClient;

import java.util.Map;

/**
 * @author liuzheng
 * post command
 * @param <T>
 */
public class HytrixPostCommand<T> extends HytrixBaseCommand<T> {

    public HytrixPostCommand(String env, HttpClient httpClient, ServiceFinder serviceFinder, String serviceName, Object request, Class<T> responseType) {
        super(env, httpClient, serviceFinder, serviceName, request, responseType);
    }

    public HytrixPostCommand(String env, HttpClient httpClient, String url, String serviceName, Object request, Class<T> responseType) {
        super(env, httpClient, url, serviceName, request, responseType);
    }

    @Override
    T getOrPost(String path, Object request, Class<T> responseType, Map<String, String> extraHeaders) throws Exception {
        return HttpUtils.clientPost(super.httpClient, path, request, responseType, extraHeaders);
    }

}