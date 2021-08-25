package com.vendixxx.monitor.common.validate;

import lombok.Data;

/**
 * 封装上报baseModel
 * @author liuzheng
 * @date 2021-02-26
 * @since 2021
 */
@Data
public class ValidateBase<T> {

    T t;

    String msg;

    boolean success;

    public static <T> ValidateBase ofFail(String msg, T t) {
        ValidateBase base = new ValidateBase();
        base.setMsg(msg);
        base.setSuccess(false);
        base.setT(t);
        return base;
    }

    public static <T> ValidateBase ofSuccess(String msg, T t) {
        ValidateBase base = new ValidateBase();
        base.setMsg(msg);
        base.setSuccess(true);
        base.setT(t);
        return base;
    }
}
