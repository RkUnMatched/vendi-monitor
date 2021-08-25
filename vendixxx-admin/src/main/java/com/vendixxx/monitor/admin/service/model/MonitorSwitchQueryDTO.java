package com.vendixxx.monitor.admin.service.model;

import lombok.Data;

/**
 * @author liuzheng
 * @date 2021-03-18
 * @since 2021
 */
@Data
public class MonitorSwitchQueryDTO {

    String ip;

    String method;

    String application;

    String token;

    Integer pgSize;

    Integer pgNum;

    Integer start;
}
