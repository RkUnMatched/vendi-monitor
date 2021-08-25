package com.vendixxx.monitor.admin.service.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author liuzheng
 * @date 2021-02-25
 * @since 2021
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReportQueryDTO {

    /**
     * 单据号
     */
    String bizNo;

    /**
     * 应用名
     */
    String application;

    /**
     * ip地址
     */
    String ip;

    /**
     * 关键字
     */
    String keyword;

    /**
     * 方法
     */
    String method;

    Long createTimeBegin;

    Long createTimeEnd;

    String token;

}
