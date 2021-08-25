package com.vendixxx.monitor.common.annotation;


import com.vendixxx.monitor.common.enums.MonitorSendTypeEnum;
import com.vendixxx.monitor.common.validate.ReportRule;
import com.vendixxx.monitor.common.validate.SimpleReportRule;

import java.lang.annotation.*;

/**
 * 监控上报注解
 * @author liuzheng
 * @date 2021-01-21
 * @since 2021
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
@Documented
public @interface MonitReportAnnotation {

    /**
     * 方法描述
     * @return
     */
    String methodDesc() default "";


    /**
     * 上报方式
     * @return
     */
    MonitorSendTypeEnum sendType() default MonitorSendTypeEnum.BODY;

    /**
     * 规则
     * @return
     */
    String rule() default SimpleReportRule.SIMPLE;

    /**
     * 自定义实现的ruleType
     * 必须包含空 构造器
     * @return
     */
    Class<? extends ReportRule> ruleType() default SimpleReportRule.class;


}
