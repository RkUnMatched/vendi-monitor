package com.vendixxx.monitor.admin.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 健康检查,不要删除
 * @author liuzheng
 * @date 2021-01-20
 * @since 2021
 */
@RestController
@Slf4j
public class CheckController {

    @GetMapping("/check")
    public String check() {
        return "success";
    }
}
