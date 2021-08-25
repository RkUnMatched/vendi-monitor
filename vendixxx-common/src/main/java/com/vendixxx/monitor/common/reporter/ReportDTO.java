package com.vendixxx.monitor.common.reporter;

import lombok.Data;

/**
 * 上报DTO
 * @author liuzheng
 * @date 2021-01-19
 * @since 2021
 */
@Data
public class ReportDTO {

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
     * 请求入参
     */
    String headRequest;

    /**
     * 校验结果
     */
    String bodyResponse;

}
