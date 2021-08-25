package com.vendixxx.monitor.common.annotation;

import java.lang.annotation.*;

/**
 * 描述符注解，表明是一个JsxrsBean
 * @author liuzheng
 * @date 2021-01-29
 * @since 2021
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Documented
public @interface JsxrsBeanMeta {

    String value();
}
