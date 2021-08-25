package com.vendixxx.monitor.common.rpc;

import java.io.Serializable;
import java.util.Objects;

/**
 * 结果
 * @param <R>
 */
public class RpcCallResult<R> implements Serializable {

    private static final long serialVersionUID = 1L;

    private boolean success;
    private int code;
    private String msg;
    private R data;

    public static <R> RpcCallResult<R> ofSuccess(R data) {
        return new RpcCallResult<R>()
                .setCode(200)
                .setSuccess(true)
                .setMsg("success")
                .setData(data);
    }

    public static <R> RpcCallResult<R> ofFailMsg(String msg){
        return new RpcCallResult<R>()
                .setSuccess(false)
                .setMsg(msg);
    }

    public static <R> RpcCallResult<R> ofFailMsg(String msg,R data){
        return new RpcCallResult<R>()
                .setSuccess(false)
                .setMsg(msg)
                .setData(data);
    }

    public static <R> RpcCallResult<R> ofSuccessMsg(String msg) {
        return new RpcCallResult<R>()
                .setSuccess(true)
                .setMsg(msg);
    }

    public static <R> RpcCallResult<R> ofFail(int code, String msg) {
        RpcCallResult<R> result = new RpcCallResult<>();
        result.setSuccess(false);
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }



    public static <R> RpcCallResult<R> ofThrowable(int code, Throwable throwable) {
        RpcCallResult<R> result = new RpcCallResult<>();
        result.setSuccess(false);
        result.setCode(code);
        result.setMsg(throwable.getClass().getName() + ", " + throwable.getMessage());
        return result;
    }

    public boolean isSuccess() {
        return success;
    }

    public RpcCallResult<R> setSuccess(boolean success) {
        this.success = success;
        return this;
    }

    public int getCode() {
        return code;
    }

    public RpcCallResult<R> setCode(int code) {
        this.code = code;
        return this;
    }

    public String getMsg() {
        return msg;
    }

    public RpcCallResult<R> setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public R getData() {
        return data;
    }

    public RpcCallResult<R> setData(R data) {
        this.data = data;
        return this;
    }

    public boolean success(){
        return success && Objects.nonNull(data);
    }
    @Override
    public String toString() {
        return "Result{" +
                "success=" + success +
                ", code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }



}
