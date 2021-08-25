package com.vendixxxx.monitor.client.hystrix;

import com.netflix.hystrix.exception.HystrixBadRequestException;
import com.vendixxx.monitor.common.exception.ServiceNotAvaibleException;
import com.vendixxx.monitor.common.exception.VendixxxServiceException;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;


/**
 * 包装了一下对异常的处理和调用统计
 * <p/>
 */
@Slf4j
public class AsyncFuture<T> {

    private static final int timeout = 3;

    private final Future<T> future;
    private final String serviceName;
    private final T fallbackRsp;

    /**
     *  constructor
     */
    public AsyncFuture(Future<T> future, String serviceName, T fallback) {
        this.future = future;
        this.serviceName = serviceName;
        this.fallbackRsp = fallback;
    }


    /**
     * 获取服务调用结果
     *
     * @return 获取服务调用结果
     * @throws VendixxxServiceException       业务异常
     * @throws ServiceNotAvaibleException 服务不可用异常
     */
    public T get(int seconds) throws VendixxxServiceException, ServiceNotAvaibleException {
        T t;
        try {
            t = this.innerGet(seconds, TimeUnit.SECONDS);
        } catch (ServiceNotAvaibleException e) {
            if (this.fallbackRsp != null) {
                t = this.fallbackRsp;
            } else {
                throw e;
            }
        }

        return t;

    }
    
    
    public T get(int seconds,TimeUnit unit) throws VendixxxServiceException, ServiceNotAvaibleException {
        T t;
        try {
            t = this.innerGet(seconds,unit);
        } catch (ServiceNotAvaibleException e) {
            if (this.fallbackRsp != null) {
                t = this.fallbackRsp;
            } else {
                throw e;
            }
        }
        return t;
    }
    
    
    /**
     * 获取服务调用结果
     *
     * @return 获取服务调用结果
     * @throws VendixxxServiceException       服务自定义异常
     * @throws ServiceNotAvaibleException 服务不可用异常
     */
    private T innerGet(int waitingSeconds,TimeUnit unit) throws VendixxxServiceException, ServiceNotAvaibleException {
        T t;
        try {
            t = this.future.get(waitingSeconds, unit);
            
            //避免LogUtils.shotter  先执行
        }catch(TimeoutException te){//增加超时异常
        	log.warn("call service: {} failed:{} with TimeoutException.", serviceName,te);
            throw new ServiceNotAvaibleException(serviceName);
        }catch(InterruptedException ie){//增加线程中断异常
        	log.warn("call service: {} failed:{} with InterruptedException.", serviceName,ie);
            throw new ServiceNotAvaibleException(serviceName);
        } catch (Exception ex) {
            // 异步场景下，会抛出 ExecutionException
            if (ex instanceof ExecutionException) {
                //hystrix 场景 HystrixBadRequestException 异常，不需要进行fallback
                if (ex.getCause() instanceof HystrixBadRequestException ) {
                    VendixxxServiceException se = (VendixxxServiceException) (ex.getCause().getCause());
                    log.info("call service: {}, return: {} success.", serviceName, se.getMessage());
                    throw se;
                }
                //native 场景
                else if (ex.getCause() instanceof VendixxxServiceException) {
                    VendixxxServiceException se = (VendixxxServiceException) (ex.getCause());
                    log.info("call service: {}, return: {} success.", serviceName, se.getMessage());
                    throw se;
                }
                //不是service exception
                else {
                    log.warn("call service: {} failed with exception.", serviceName, ex);
                    throw new ServiceNotAvaibleException(serviceName);
                }
            } else {
                log.warn("call service: {} failed with exception.", serviceName);
                throw new ServiceNotAvaibleException(serviceName);
            }
        }
        return t;
    }




    /**
     * 获取服务调用结果
     *
     * @return 获取服务调用结果
     * @throws VendixxxServiceException           服务自定义异常
     * @throws ServiceNotAvaibleException 服务不可用异常
     */
    public T get() throws VendixxxServiceException, ServiceNotAvaibleException {

        // 默认future的超时时间.
        return this.get(timeout);
    }
}
