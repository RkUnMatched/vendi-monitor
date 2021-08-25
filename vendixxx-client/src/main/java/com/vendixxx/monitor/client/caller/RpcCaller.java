package com.vendixxx.monitor.client.caller;

import com.vendixxxx.monitor.client.hystrix.AsyncFuture;
import com.vendixxx.monitor.common.exception.ServiceNotAvaibleException;
import com.vendixxx.monitor.common.exception.ServiceNotFoundException;
import com.vendixxx.monitor.common.exception.VendixxxServiceException;

import java.util.Map;

public interface RpcCaller {

    /**
     * 异步服务调用
     *
     * @param serviceName  服务方法
     * @param request      请求消息体. 可以为空
     * @param responseType 响应类型
     * @param <T>          类型
     * @return 响应对象 Future
     * @throws VendixxxServiceException       服务返回的结果异常
     * @throws ServiceNotAvaibleException 服务不可用
     * @throws ServiceNotFoundException   找不到服务
     */
    <T> AsyncFuture<T> asyncCall(String serviceName, Object request, Class<T> responseType)
            throws VendixxxServiceException, ServiceNotAvaibleException, ServiceNotFoundException;

    /**
     * 异步服务调用, 支持降级
     *
     * @param serviceName  服务方法
     * @param request      请求消息体. 可以为空
     * @param responseType 响应类型
     * @param <T>          类型
     * @param fallbackRsp  降级响应
     * @return 响应对象 Future
     * @throws VendixxxServiceException       服务返回的结果异常
     * @throws ServiceNotAvaibleException 服务不可用
     * @throws ServiceNotFoundException   找不到服务
     */
    <T> AsyncFuture<T> asyncCall(String serviceName, Object request, Class<T> responseType, T fallbackRsp)
            throws VendixxxServiceException, ServiceNotAvaibleException, ServiceNotFoundException;


    /**
     * 同步服务调用
     *
     * @param serviceName  服务方法
     * @param request      请求消息体. 可以为空
     * @param responseType 响应类型
     * @param <T>          类型
     * @return 响应对象 Future .
     * @throws VendixxxServiceException       服务返回的结果异常
     * @throws ServiceNotAvaibleException 服务不可用
     * @throws ServiceNotFoundException   找不到服务
     */
    <T> T call(String serviceName, Object request, Class<T> responseType)
            throws VendixxxServiceException, ServiceNotAvaibleException, ServiceNotFoundException;


    /**
     * 同步服务调用
     *
     * @param serviceName  服务方法
     * @param request      请求消息体. 可以为空
     * @param responseType 响应类型
     * @param <T>          类型
     * @param timeout      超时时间
     * @return 响应对象 Future .
     * @throws VendixxxServiceException       服务返回的结果异常
     * @throws ServiceNotAvaibleException 服务不可用
     * @throws ServiceNotFoundException   找不到服务
     * @author gaoren.huang@yoho.cn
     */
    <T> T call(String serviceName, Object request, Class<T> responseType, int timeout)
            throws VendixxxServiceException, ServiceNotAvaibleException, ServiceNotFoundException;


    /**
     * 同步服务调用, 支持服务降级
     *
     * @param serviceName  服务方法
     * @param request      请求消息体. 可以为空
     * @param responseType 响应类型
     * @param <T>          类型
     * @param fallbackRsp  降级响应
     * @return 响应对象 Future .
     * @throws VendixxxServiceException       服务返回的结果异常
     * @throws ServiceNotAvaibleException 服务不可用
     * @throws ServiceNotFoundException   找不到服务
     */
    <T> T call(String serviceName, Object request, Class<T> responseType, T fallbackRsp)
            throws VendixxxServiceException, ServiceNotAvaibleException, ServiceNotFoundException;

    /**
     * 异步get调用
     *
     * @param serviceName   服务方法
     * @param requestParams 请求参数
     * @param responseType  响应类型
     * @param <T>           类型
     * @return 响应对象 Future .
     * @throws VendixxxServiceException       服务返回的结果异常
     * @throws ServiceNotAvaibleException 服务不可用
     * @throws ServiceNotFoundException   找不到服务
     */
    <T> AsyncFuture<T> asyncGetcall(String serviceName, Map<String, ?> requestParams, Class<T> responseType)
            throws VendixxxServiceException, ServiceNotAvaibleException, ServiceNotFoundException;

    /**
     * 同步get调用
     *
     * @param serviceName   服务方法
     * @param requestParams 请求参数
     * @param responseType  响应类型
     * @param <T>           类型
     * @return 响应对象 Future .
     * @throws VendixxxServiceException       服务返回的结果异常
     * @throws ServiceNotAvaibleException 服务不可用
     * @throws ServiceNotFoundException   找不到服务
     */
    <T> T getcall(String serviceName, Map<String, ?> requestParams, Class<T> responseType)
            throws VendixxxServiceException, ServiceNotAvaibleException, ServiceNotFoundException;


    /**
     * 同步get调用
     *
     * @param serviceName   服务方法
     * @param requestParams 请求参数
     * @param responseType  响应类型
     * @param <T>           类型
     * @param timeout       超时时间
     * @return 响应对象 Future .
     * @throws VendixxxServiceException       服务返回的结果异常
     * @throws ServiceNotAvaibleException 服务不可用
     * @throws ServiceNotFoundException   找不到服务
     */
    <T> T getcall(String serviceName, Map<String, ?> requestParams, Class<T> responseType, int timeout)
            throws VendixxxServiceException, ServiceNotAvaibleException, ServiceNotFoundException;


    /**
     * get  fallback可以不传入
     *
     * @param serviceName   服务方法
     * @param requestParams 请求参数
     * @param responseType  响应类型
     * @param <T>           类型
     * @return 响应对象 Future .
     * @throws VendixxxServiceException       服务返回的结果异常
     * @throws ServiceNotAvaibleException 服务不可用
     * @throws ServiceNotFoundException   找不到服务
     */
    <T> AsyncFuture<T> get(String serviceName, String url, Map<String, ?> requestParams, Class<T> responseType, T fallback)
            throws VendixxxServiceException, ServiceNotAvaibleException, ServiceNotFoundException;


    /**
     * post. fallback 可以不传
     *
     * @param serviceName  服务方法
     * @param request      请求参数
     * @param responseType 响应类型
     * @param <T>          类型
     * @return 响应对象 Future .
     * @throws VendixxxServiceException       服务返回的结果异常
     * @throws ServiceNotAvaibleException 服务不可用
     * @throws ServiceNotFoundException   找不到服务
     */
    <T> AsyncFuture<T> post(String serviceName, String url, Object request, Class<T> responseType, T fallback)
            throws VendixxxServiceException, ServiceNotAvaibleException, ServiceNotFoundException;

}
