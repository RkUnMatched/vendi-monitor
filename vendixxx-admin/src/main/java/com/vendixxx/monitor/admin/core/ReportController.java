package com.vendixxx.monitor.admin.core;

import com.vendixxx.monitor.common.reporter.ReportDTO;
import com.vendixxx.monitor.common.reporter.ReportTransportTypeEnum;
import com.vendixxx.monitor.common.reporter.ReportTypeEnum;
import com.vendixxx.monitor.common.rpc.RpcCallResult;
import com.vendixxx.monitor.springboot.validater.ReportMonitorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 上报服务
 * @author liuzheng
 * @date 2021-01-20
 * @since 2021
 */
@RestController
public class ReportController {

    @Autowired
    ReportMonitorService reportMonitorService;

    @ResponseBody
    @RequestMapping("/handleWith")
    public RpcCallResult<String> handleWith(){
        ReportDTO reportDTO = new ReportDTO();
        reportDTO.setBodyResponse("test");
        reportDTO.setEnv("dev");
        reportDTO.setHeadRequest("test");
        reportDTO.setMethod("handleWith");
        reportDTO.setMethodDesc("handleWith");
        try {
            reportMonitorService.reportWithHandwork(reportDTO, ReportTypeEnum.REPORT, ReportTransportTypeEnum.GRPC);
        } catch (Exception e) {
            System.out.println("failed");
        }
        RpcCallResult<String> rpcCallResult = RpcCallResult.ofSuccess("SUCCESS");
        return rpcCallResult;
    }
}
