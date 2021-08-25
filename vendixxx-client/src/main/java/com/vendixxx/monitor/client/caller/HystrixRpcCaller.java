package com.vendixxx.monitor.client.caller;

import com.vendixxxx.monitor.client.hystrix.AsyncFuture;
import com.vendixxxx.monitor.client.hystrix.HytrixGetCommand;
import com.vendixxxx.monitor.client.hystrix.HytrixPostCommand;
import com.vendixxx.monitor.common.exception.ServiceNotAvaibleException;
import com.vendixxx.monitor.common.exception.ServiceNotFoundException;
import com.vendixxx.monitor.common.exception.VendixxxServiceException;
import com.vendixxx.monitor.registry.ServiceFinder;
import org.apache.http.client.HttpClient;

import java.util.Map;
import java.util.concurrent.Future;

/**
 * 服务调用者：提供下面的特性：包装了netflix hystrix
 * 1. 服务降级
 * 2. 线程隔离：相同类别的服务公用线程池
 */
public class HystrixRpcCaller implements RpcCaller {

    private String env;
    private HttpClient httpClient;
    private ServiceFinder serviceFinder;

    @Override
    public <T> AsyncFuture<T> asyncCall(String serviceName, Object request, Class<T> responseType, T fallbackRsp) throws VendixxxServiceException, ServiceNotAvaibleException, ServiceNotFoundException {
        HytrixPostCommand<T> command = new HytrixPostCommand<>(this.env, httpClient, serviceFinder, serviceName, request, responseType);
        command.setFallback(fallbackRsp);
        Future<T> future = command.queue();
        return new AsyncFuture<>(future, serviceName, fallbackRsp);
    }

    @Override
    public <T> AsyncFuture<T> asyncCall(String serviceName, Object request, Class<T> responseType)
            throws VendixxxServiceException, ServiceNotAvaibleException, ServiceNotFoundException {
        return this.asyncCall(serviceName, request, responseType, null);
    }

    @Override
    public <T> T call(String serviceName, Object request, Class<T> responseType, T fallbackRsp) throws VendixxxServiceException, ServiceNotAvaibleException, ServiceNotFoundException {
        try {
            return this.asyncCall(serviceName, request, responseType, fallbackRsp).get();
        } finally {
        }

    }

    @Override
    public <T> T call(String serviceName, Object request, Class<T> responseType)
            throws VendixxxServiceException, ServiceNotAvaibleException, ServiceNotFoundException {
        return this.call(serviceName, request, responseType, null);
    }

    @Override
    public <T> T call(String serviceName, Object request, Class<T> responseType, int timeout)
            throws VendixxxServiceException, ServiceNotAvaibleException, ServiceNotFoundException {
        try {
            return this.asyncCall(serviceName, request, responseType, null).get(timeout);
        } finally {

        }
    }

    @Override
    public <T> AsyncFuture<T> asyncGetcall(String serviceName, Map<String, ?> requestParams, Class<T> responseType) throws VendixxxServiceException, ServiceNotAvaibleException, ServiceNotFoundException {
        HytrixGetCommand<T> command = new HytrixGetCommand<>(this.env, httpClient, serviceFinder, serviceName, requestParams, responseType);
        Future<T> future = command.queue();
        return new AsyncFuture<>(future, serviceName, null);
    }

    @Override
    public <T> T getcall(String serviceName, Map<String, ?> requestParams, Class<T> responseType) throws VendixxxServiceException, ServiceNotAvaibleException, ServiceNotFoundException {
        return this.asyncGetcall(serviceName, requestParams, responseType).get();
    }


    @Override
    public <T> T getcall(String serviceName, Map<String, ?> requestParams, Class<T> responseType, int timeout)
            throws VendixxxServiceException, ServiceNotAvaibleException, ServiceNotFoundException {
        return this.asyncGetcall(serviceName, requestParams, responseType).get(timeout);
    }

    // get and set. 支持原生的 http 调用
    @Override
    public <T> AsyncFuture<T> get(String serviceName, String url, Map<String, ?> requestParams, Class<T> responseType, T fallback) throws VendixxxServiceException, ServiceNotAvaibleException, ServiceNotFoundException {
        HytrixGetCommand<T> command = new HytrixGetCommand<>(this.env, httpClient, url, serviceName, requestParams, responseType);
        command.setFallback(fallback);
        Future<T> future = command.queue();
        return new AsyncFuture<>(future, serviceName, fallback);
    }

    @Override
    public <T> AsyncFuture<T> post(String serviceName, String url, Object request, Class<T> responseType, T fallback) throws VendixxxServiceException, ServiceNotAvaibleException, ServiceNotFoundException {
        HytrixPostCommand<T> command = new HytrixPostCommand<>(this.env, httpClient, url, serviceName, request, responseType);
        command.setFallback(fallback);
        Future<T> future = command.queue();
        return new AsyncFuture<>(future, serviceName, fallback);
    }

    public void setHttpClient(HttpClient httpClient) {
        this.httpClient = httpClient;
    }

    public void setServiceFinder(ServiceFinder serviceFinder) {
        this.serviceFinder = serviceFinder;
    }

    public void setEnv(String env) {
        this.env = env;
    }
}
