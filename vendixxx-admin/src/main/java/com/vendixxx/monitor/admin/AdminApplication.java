package com.vendixxx.monitor.admin;

import com.vendixxx.monitor.springboot.annotation.EnableVendixxxMonitor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @author liuzheng
 * @date 2021-01-14
 * @since 2021
 */
@SpringBootApplication(scanBasePackages ={"com.vendixxx.monitor"})
@EnableVendixxxMonitor(scanPackage ={"com.vendixxx.monitor"})
@MapperScan({"com.vendixxx.monitor.admin.repository.dao.ext"})
@EnableAspectJAutoProxy(exposeProxy = true)
public class AdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(AdminApplication.class, args);
    }
}
