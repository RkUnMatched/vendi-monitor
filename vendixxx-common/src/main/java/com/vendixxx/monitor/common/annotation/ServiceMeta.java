package com.vendixxx.monitor.common.annotation;

import java.lang.annotation.*;

/**
 * service 描述
 *
 * @author liuzheng
 * @date 2021-01-12
 * @since 2021
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
@Documented
public @interface ServiceMeta {

    /**
     * 应用名
     * 例如采购系统：purchase
     *
     * @return 应用名称
     */
    String application();

    /**
     * 服务名
     * @return 服务的名称。
     */
    String service();


    /**
     * 可选，如果不填写，则设置为context
     * @return 服务的名称。
     */
    String value() default "";


    /**
     * 是否对请求进行降级， 默认false
     * @return  是否降级
     */
    boolean degrade() default  false;
}
