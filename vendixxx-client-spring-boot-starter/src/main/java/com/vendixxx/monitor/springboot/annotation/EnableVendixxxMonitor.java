package com.vendixxx.monitor.springboot.annotation;

import com.vendixxx.monitor.springboot.MonitorAutoConfiguration;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;

import java.lang.annotation.*;

/**
 * 监控系统引导注解
 *
 * @author liuzheng
 * @date 2021-01-13
 * @since 2021
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@ImportAutoConfiguration(MonitorAutoConfiguration.class)
public @interface EnableVendixxxMonitor {

    /**
     * 扫描的包路径
     *
     * @return
     */
    String[] scanPackage();
}

