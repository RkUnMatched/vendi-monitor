package com.vendixxx.monitor.springboot.validater;

import com.vendixxx.monitor.client.caller.RpcCaller;
import com.vendixxx.monitor.common.reporter.ReportBean;
import com.vendixxx.monitor.common.reporter.ReportDTO;
import com.vendixxx.monitor.common.reporter.ReportTransportTypeEnum;
import com.vendixxx.monitor.common.reporter.ReportTypeEnum;
import com.vendixxx.monitor.common.rpc.RpcCallResult;
import com.vendixxx.monitor.common.utils.NetworkUtils;
import com.vendixxx.monitor.common.utils.ObjectHelperUtils;
import com.vendixxx.monitor.springboot.MonitorAutoConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 手工上报服务类
 * @author liuzheng
 * @date 2021-03-03
 * @since 2021
 */
@Service
@Slf4j
public class ReportMonitorServiceImpl implements ReportMonitorService{

    @Autowired
    RpcCaller rpcCaller;

    @Autowired
    MonitorAutoConfiguration monitorAutoConfiguration;

    @Autowired
    Environment environment;

    @Override
    public RpcCallResult<String> reportWithHandwork(ReportDTO reportDTO, ReportTypeEnum reportTypeEnum, ReportTransportTypeEnum reportTransportTypeEnum) {
        if(reportDTO == null){
            return RpcCallResult.ofFail(500,"报文体为空");
        }
        return httpReport(buildReportBean(reportDTO),reportTypeEnum);
    }

    private ReportBean buildReportBean(ReportDTO reportDTO){
        ReportBean reportBean = new ReportBean();
        reportBean.setApplication(monitorAutoConfiguration.monitorClientProperties.getApplication());
        reportBean.setApplicationDesc(monitorAutoConfiguration.monitorClientProperties.getApplcationDesc());
        reportBean.setVersion(monitorAutoConfiguration.monitorClientProperties.getVersion());
        reportBean.setIp(NetworkUtils.getIp());
        reportBean.setRepoteTime(System.currentTimeMillis());
        reportBean.setEnv(reportDTO.getEnv());
        reportBean.setMethod(ObjectHelperUtils.buildEmpty(reportDTO.getMethod()));
        reportBean.setMethodDesc(ObjectHelperUtils.buildEmpty(reportDTO.getMethodDesc()));
        ReportBean.Content content = new ReportBean.Content();
        content.setHeadRequest(ObjectHelperUtils.buildEmpty(reportDTO.getHeadRequest()));
        content.setBodyResponse(ObjectHelperUtils.buildEmpty(reportDTO.getBodyResponse()));
        reportBean.setContent(content);
        //这里不用设置,save时自动调用序列生成序列值
        //reportBean.setBizNo("YY"+System.currentTimeMillis());
        reportBean.setToken(monitorAutoConfiguration.monitorClientProperties.getToken());
        return reportBean;
    }

    private RpcCallResult<String> httpReport(ReportBean reportBean, ReportTypeEnum reportTypeEnum) {
        RpcCallResult<String> rpcCallResult;
        try {
            rpcCallResult = rpcCaller.call(RpcUtils.RPC_METHOD, reportBean, RpcCallResult.class);
        } catch (Exception e) {
            e.printStackTrace();
            return RpcCallResult.ofFail(500, "系统远程调用异常");
        }
        if (rpcCallResult.isSuccess()) {
            log.info("report success,bean is {}", rpcCallResult);
        }
        return rpcCallResult;
    }

}
