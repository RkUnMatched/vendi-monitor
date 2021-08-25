package com.vendixxx.monitor.springboot.validater;

import com.vendixxx.monitor.common.reporter.ReportDTO;
import com.vendixxx.monitor.common.reporter.ReportTransportTypeEnum;
import com.vendixxx.monitor.common.reporter.ReportTypeEnum;
import com.vendixxx.monitor.common.rpc.RpcCallResult;

/**
 * 手工上报接口
 * @author liuzheng
 * @date 2021-03-03
 * @since 2021
 */
public interface ReportMonitorService {

    /**
     * 手工上报接口
     * @param reportDTO             上报报文
     * @param reportTypeEnum        上报内容类型
     * @param reportTransportTypeEnum 上报协议类型
     * @return
     */
    RpcCallResult<String> reportWithHandwork(ReportDTO reportDTO, ReportTypeEnum reportTypeEnum, ReportTransportTypeEnum reportTransportTypeEnum);
}
