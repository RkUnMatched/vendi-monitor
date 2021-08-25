package com.vendixxx.monitor.common.reporter;

import lombok.Data;

/**
 * 上报DTO
 * @author liuzheng
 * @date 2021-01-19
 * @since 2021
 */
@Data
public class ReportBean {

    /**
     * 业务单号
     */
    String bizNo;

    /**
     * 业务类型
     */
    String bizType;

    /**
     * 来源系统
     */
    String application;

    /**
     * 来源系统描述
     */
    String applicationDesc;

    /**
     * ip
     */
    String ip;

    /**
     * 版本号
     */
    String version;

    /**
     * 环境标示
     */
    String env;

    /**
     * 调用方法名
     */
    String method;

    /**
     * 调用方法描述
     */
    String methodDesc;

    /**
     * 上报时间
     */
    Long repoteTime;

    /**
     * 上报内容
     */
    Content content;

    @Data
    public static class Content{
        /**
         * 请求入参
         */
        String headRequest;

        /**
         * 校验结果
         */
        String bodyResponse;
    }

    String token;

}
