package com.vendixxx.monitor.springboot.validater;

import com.alibaba.fastjson.JSON;
import com.vendixxx.monitor.client.caller.RpcCaller;
import com.vendixxx.monitor.common.annotation.MonitReportAnnotation;
import com.vendixxx.monitor.common.config.CuratorVendixxxConfig;
import com.vendixxx.monitor.common.enums.MonitorSendTypeEnum;
import com.vendixxx.monitor.common.reporter.ReportBean;
import com.vendixxx.monitor.common.reporter.ReportTypeEnum;
import com.vendixxx.monitor.common.rpc.RpcCallResult;
import com.vendixxx.monitor.common.utils.NetworkUtils;
import com.vendixxx.monitor.common.utils.StringUtils;
import com.vendixxx.monitor.common.validate.MonitorValidate;
import com.vendixxx.monitor.common.validate.ReportRule;
import com.vendixxx.monitor.common.validate.SimpleReportRule;
import com.vendixxx.monitor.springboot.MonitorAutoConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

/**
 * 注解扫描reporter
 *
 * @author liuzheng
 * @date 2021-01-21
 * @since 2021
 */
@Aspect
@Service
@Slf4j
public class MonitorReportAnnotationValidator implements MonitorValidate<ReportBean, RpcCallResult<String>> {

    @Autowired
    RpcCaller rpcCaller;

    @Autowired
    MonitorAutoConfiguration monitorAutoConfiguration;

    @Autowired
    Environment environment;

    @Pointcut("@annotation(com.vendixxx.monitor.common.annotation.MonitReportAnnotation)")
    public void pointcut() {

    }

    @Around("pointcut()")
    public Object invoke(ProceedingJoinPoint invocation) throws Throwable {
        Signature signature = invocation.getSignature();
        if (!(signature instanceof MethodSignature)) {
            throw new IllegalArgumentException("this annotation is used for method");
        }
        MethodSignature methodSignature = (MethodSignature) signature;
        Method method = methodSignature.getMethod();
        //无参返回值为void
        Class clazz = methodSignature.getReturnType();
        Class[] parameterTypesArray = methodSignature.getParameterTypes();
        String env = environment.getProperty("spring.profiles.active");

        Object target = invocation.getTarget();
        //这个方法有点绕了 直接用 methodSignature.getMethod()
        //Method currentMethod = target.getClass().getMethod(methodSignature.getName(), methodSignature.getParameterTypes());
        //请求方法名
        String methodName = methodSignature.getName();
        MonitReportAnnotation monitReportAnnotation = method.getAnnotation(MonitReportAnnotation.class);
        if (monitReportAnnotation == null) {
            log.error("@MonitReportAnnotation is not present on  class:" + method.getDeclaringClass().getName() + "#" + methodName);
            Object result = invocation.proceed();
            return result;
        }
        String methodDesc = monitReportAnnotation.methodDesc();
        log.info("monitorAnnotation reporter before, method:{} ,args {}", method.getDeclaringClass().getName() + "#" + methodName, invocation.getArgs());
        Object result = invocation.proceed();
        MonitorSendTypeEnum monitorSendTypeEnum = monitReportAnnotation.sendType();
        //只校验入参
        if (monitorSendTypeEnum == MonitorSendTypeEnum.HEADER) {
            return doHeader(parameterTypesArray,method,methodName,methodDesc,env,result,invocation.getArgs());
        }
        return doBody(parameterTypesArray,method,methodName,methodDesc,env,result,invocation.getArgs(),monitReportAnnotation,clazz);
    }

    private Object doBody(Class[] parameterTypesArray,Method method,String methodName,String methodDesc,String env,Object result,Object[] args,MonitReportAnnotation monitReportAnnotation,Class clazz){
        //没有返回值...
        if(parameterTypesArray == null || parameterTypesArray.length == CuratorVendixxxConfig.NUM_0){
            log.error("there is no parameterTypes for the method,method:{}",method.getDeclaringClass().getName() + "#" + methodName);
            return result;
        }
        //没有返回值
        if(clazz == void.class){
            log.error("there is no returnType for the method,method:{}",method.getDeclaringClass().getName() + "#" + methodName);
            return result;
        }
        Class<? extends ReportRule> ruleType = monitReportAnnotation.ruleType();
        //是否抽象类
        boolean isAbstract = Modifier.isAbstract(ruleType.getModifiers());
        //是否接口
        boolean isInterface = ruleType.isInterface();
        //规则不正确
        if (isAbstract || isInterface) {
            log.error("@MonitReportAnnotation ruleType:" + method.getDeclaringClass().getName() + "#" + methodName + " is not instantiate");
            return result;
        }
        String response;
        if(result == null){
            response = CuratorVendixxxConfig.EMPTY_STR;
        }else{
            response = JSON.toJSONString(result);
        }
        //获得monitorAnnotation 的expression
        String rule = monitReportAnnotation.rule();
        ReportBean reportBean = buildReportBean(JSON.toJSONString(args[0]),response,env,methodName,methodDesc);
        //这里为了避免重复的newInstance影响性能
        if (StringUtils.isNotEmpty(rule) && rule.equals(SimpleReportRule.SIMPLE)) {
            report(reportBean, ReportTypeEnum.REPORT);
            return result;
        } else {
            ReportRule reportRule = null;
            try {
                reportRule = ruleType.newInstance();
            } catch (InstantiationException e) {
                log.error("the class can not be instantiated, class name is {},",ruleType.getName());
                return result;
            } catch (IllegalAccessException e) {
                log.error("the class can not be access, class name is {},",ruleType.getName());
                return result;
            }

            boolean canReport = reportRule.canReport(reportRule.expression());
            if (canReport) {
                report(reportBean, ReportTypeEnum.REPORT);
            } else {
                log.info("no need to handle with remote report, method:{} ,args {}", method.getDeclaringClass().getName() + "#" + methodName, args);
                return result;
            }
        }
        log.info("monitorAnnotation reporter after, method:{} ,args {}", method.getDeclaringClass().getName() + "#" + methodName, args);
        return result;
    }

    private Object doHeader(Class[] parameterTypesArray,Method method,String methodName,String methodDesc,String env,Object result,Object[] args){
        //如果没有入参,那么直接返回,不上报
        if(parameterTypesArray == null || parameterTypesArray.length == CuratorVendixxxConfig.NUM_0){
            log.info("there is no parameterTypes for the method,method:{}",method.getDeclaringClass().getName() + "#" + methodName);
            return result;
        }
        //如果多于1个入参,那么直接取第一个入参返回上报
        if(parameterTypesArray.length > CuratorVendixxxConfig.NUM_1){
            log.info("there is more than one parameterTypes for the method,method:{}",method.getDeclaringClass().getName() + "#" + methodName);
        }
        ReportBean reportBean = buildReportBean(JSON.toJSONString(args[0]), CuratorVendixxxConfig.EMPTY_STR,env,methodName,methodDesc);
        report(reportBean, ReportTypeEnum.REPORT);
        log.info("monitorAnnotation reporter after, method:{} ,args {}", method.getDeclaringClass().getName() + "#" + methodName, args);
        return result;
    }

    private ReportBean buildReportBean(String request,String response,String env,String method,String methodDesc){
        ReportBean reportBean = new ReportBean();
        reportBean.setApplication(monitorAutoConfiguration.monitorClientProperties.getApplication());
        reportBean.setApplicationDesc(monitorAutoConfiguration.monitorClientProperties.getApplcationDesc());
        reportBean.setVersion(monitorAutoConfiguration.monitorClientProperties.getVersion());
        reportBean.setIp(NetworkUtils.getIp());
        reportBean.setRepoteTime(System.currentTimeMillis());
        reportBean.setEnv(env);
        reportBean.setMethod(method);
        reportBean.setMethodDesc(methodDesc);
        ReportBean.Content content = new ReportBean.Content();
        content.setHeadRequest(request);
        content.setBodyResponse(response);
        reportBean.setContent(content);
        //业务单号 todo
        reportBean.setBizNo("");
        reportBean.setToken(monitorAutoConfiguration.monitorClientProperties.getToken());
        return reportBean;
    }

    @Override
    public RpcCallResult<String> report(ReportBean reportBean, ReportTypeEnum reportTypeEnum) {
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
