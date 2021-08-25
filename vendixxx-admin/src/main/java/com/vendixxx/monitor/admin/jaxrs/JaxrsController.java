package com.vendixxx.monitor.admin.jaxrs;

import com.google.common.collect.Lists;
import com.vendixxx.monitor.admin.inner.sequence.IdNextValService;
import com.vendixxx.monitor.admin.repository.bean.MonitorDataInput;
import com.vendixxx.monitor.admin.repository.dao.ext.MonitorDataInputExtMapper;
import com.vendixxx.monitor.common.annotation.JsxrsBeanMeta;
import com.vendixxx.monitor.common.annotation.ServiceMeta;
import com.vendixxx.monitor.common.enums.CommonDbStatusEnum;
import com.vendixxx.monitor.common.reporter.ReportBean;
import com.vendixxx.monitor.common.rpc.RpcCallResult;
import com.vendixxx.monitor.common.utils.DateInvUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Date;

/**
 * @author liuzheng
 * @date 2021-01-14
 * @since 2021
 */
@RestController
@JsxrsBeanMeta("jaxrs 控制器")
@Path("/com/vendixxx/monitor/admin/jaxrs")
public class JaxrsController {

    @Autowired
    IdNextValService idNextValService;

    @Autowired
    MonitorDataInputExtMapper monitorDataInputExtMapper;

    @GET
    @Path("/helloworld1")
    @Produces({ MediaType.APPLICATION_JSON })
    public Helloworld helloworld1() throws Exception {
        return new Helloworld("Welcome, HelloWorld");
    }

    @POST
    @Path("/helloworld2")
    @Produces({ MediaType.APPLICATION_JSON })
    @Consumes({ MediaType.APPLICATION_JSON })
    public TestBody helloworld2(Helloworld helloworld) throws Exception {
        TestBody testBody = new TestBody();
        testBody.setName("maelk");
        testBody.setList(Lists.newArrayList(11));
        testBody.setHelloworldList(Lists.newArrayList(new Helloworld("hello vendixxx!")));
        return testBody;
    }

    @POST
    @Produces({ MediaType.APPLICATION_JSON })
    @Consumes({ MediaType.APPLICATION_JSON})
    @Path("/exceptionHandlerReport")
    @ServiceMeta(application = "monitor",service = "monitor.report")
    public RpcCallResult exceptionHandlerReport(ReportBean reportBean){
        monitorDataInputExtMapper.insert(buildInput(reportBean));
        RpcCallResult rpcCallResult = RpcCallResult.ofSuccess("SUCCESS");
        return rpcCallResult;
    }

    private MonitorDataInput buildInput(ReportBean reportBean){
        MonitorDataInput monitorDataInput = new MonitorDataInput();
        monitorDataInput.setApplication(reportBean.getApplication());
        monitorDataInput.setApplicationDesc(reportBean.getApplicationDesc());
        //设置bizNo
        monitorDataInput.setBizNo(DateInvUtils.getDate(new Date(),DateInvUtils.YYYYMMDD_STR)+"_"+idNextValService.nextVal("vendixxx","monitor","",1,1));
        monitorDataInput.setHeader(reportBean.getContent().getHeadRequest());
        monitorDataInput.setBody(reportBean.getContent().getBodyResponse());
        monitorDataInput.setEnv(reportBean.getEnv());
        monitorDataInput.setMethod(reportBean.getMethod());
        monitorDataInput.setMethodDesc(reportBean.getMethodDesc());
        monitorDataInput.setIp(reportBean.getIp());
        monitorDataInput.setVersion(reportBean.getVersion());
        monitorDataInput.setDeleted(0L);
        monitorDataInput.setCreator("system");
        monitorDataInput.setModifier("system");
        monitorDataInput.setStatus(CommonDbStatusEnum.INIT.getCode().byteValue());
        monitorDataInput.setToken(reportBean.getToken());
        return monitorDataInput;
    }

}
