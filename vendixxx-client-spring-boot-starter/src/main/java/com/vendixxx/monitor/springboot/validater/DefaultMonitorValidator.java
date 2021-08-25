package com.vendixxx.monitor.springboot.validater;

import com.vendixxx.monitor.client.caller.RpcCaller;
import com.vendixxx.monitor.common.reporter.ReportBean;
import com.vendixxx.monitor.common.reporter.ReportTypeEnum;
import com.vendixxx.monitor.common.rpc.RpcCallResult;
import com.vendixxx.monitor.common.validate.BaseValidate;
import com.vendixxx.monitor.common.validate.MonitorValidate;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Method;

/**
 * 默认处理者
 * @author liuzheng
 * @date 2021-01-20
 * @since 2021
 */
@Aspect
@Service
@Slf4j
public class DefaultMonitorValidator implements MonitorValidate<ReportBean, RpcCallResult<String>> {

    private static final String RPC_METHOD = "monitor.report";

    @Autowired
    RpcCaller rpcCaller;

    @Pointcut("target(com.vendixxx.monitor.common.validate.BaseValidate)")
    public void pointcut() {

    }

    @Around("pointcut()")
    public Object invoke(ProceedingJoinPoint invocation) throws Throwable {
        Signature signature = invocation.getSignature();
        MethodSignature methodSignature;
        if (!(signature instanceof MethodSignature)) {
            throw new IllegalArgumentException("this annotation is used for method");
        }
        methodSignature = (MethodSignature) signature;
        Object target = invocation.getTarget();
        Method currentMethod = target.getClass().getMethod(methodSignature.getName(), methodSignature.getParameterTypes());
        String methodName = methodSignature.getName();
        if(!methodName.equals(BaseValidate.validate)){
            Object result = invocation.proceed();
            return result;
        }
        log.info("baseValidate before, method:{} ,args {}",currentMethod.getDeclaringClass().getName()+"#"+methodName, invocation.getArgs());
        Object result = invocation.proceed();
        //获得对应class实现类的 BaseValidate#withReport变量值
        Method withReportMethod = target.getClass().getMethod(BaseValidate.WITH_REPORT);
        //Object c = invocation.getTarget(); 这里是为了获得当前调用方法的 实现类实例,被Spring cglib增加过后的bean
        Object withReport = withReportMethod.invoke(invocation.getTarget(),null);
        boolean canReport = (boolean)withReport;
        //这里增加一个 开关,用于上报
        if(canReport){
            RpcCallResult<String> rpcCallResult = report((ReportBean) result,ReportTypeEnum.REPORT);
            if(rpcCallResult.isSuccess()){
                log.info("report success,bean is {}",result);
            }
        }
        log.info("baseValidate after, method:{} ,args {}",currentMethod.getDeclaringClass().getName()+"#"+methodName, invocation.getArgs());
        return result;
    }

    @Override
    public RpcCallResult<String> report(ReportBean reportBean, ReportTypeEnum reportTypeEnum) {
        RpcCallResult<String> rpcCallResult = rpcCaller.call(RPC_METHOD,reportBean,RpcCallResult.class);
        return rpcCallResult;
    }
}
