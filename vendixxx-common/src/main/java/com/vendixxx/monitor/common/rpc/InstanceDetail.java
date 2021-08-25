package com.vendixxx.monitor.common.rpc;

import com.vendixxx.monitor.common.utils.PathURIBuilder;
import org.apache.commons.lang3.StringUtils;

public class InstanceDetail {

    /**
     * 构造器模式
     */
    public static class InstanceDetailBuilder {

        private static final String schema = "http://";

        private InstanceDetail instanceDetail;

        public InstanceDetailBuilder() {
            instanceDetail = new InstanceDetail();
        }

        public InstanceDetailBuilder env(String env){
            instanceDetail.env = env;
            return this;
        }

        public InstanceDetailBuilder address(String address) {
            instanceDetail.address = address;
            return this;
        }

        public InstanceDetailBuilder application(String application) {
            instanceDetail.application = application;
            return this;
        }

        public InstanceDetailBuilder methodName(String methodName) {
            instanceDetail.methodName = methodName;
            return this;
        }

        public InstanceDetailBuilder controllerRequestMapping(String controllerRequestMapping) {
            instanceDetail.controllerRequestMapping = controllerRequestMapping;
            return this;
        }

        public InstanceDetailBuilder methodReuqestMapping(String methodReuqestMapping) {
            instanceDetail.methodRequestMapping = methodReuqestMapping;
            return this;
        }

        /**
         * 是否降级
         * @param degrade 降级
         * @return  builder
         */
        public InstanceDetailBuilder degrade(boolean degrade) {
            instanceDetail.degrade = degrade;
            return this;
        }

        public InstanceDetail build() {
            //全部的url：http://localhost:880/context/sigin/add
            instanceDetail.requestUrl = this.buildRequestUrl();
            instanceDetail.serviceName = getServiceName();
            return this.instanceDetail;
        }

        /**
         * 获取服务名称
         *
         * @return 服务名称
         */
        private String getServiceName() {
            return instanceDetail.methodName;
        }

        private String buildRequestUrl() {
                PathURIBuilder builder = new PathURIBuilder(schema + instanceDetail.address);
                builder.addPath(instanceDetail.controllerRequestMapping);
                builder.addPath(instanceDetail.methodRequestMapping);
                return builder.build();
        }

    }

    public InstanceDetail() {

    }

    public InstanceDetail(String serviceName, String requestUrl) {
        this.serviceName = serviceName;
        this.requestUrl = requestUrl;
    }

    public InstanceDetail(String env,String serviceName, String requestUrl) {
        this.env = env;
        this.serviceName = serviceName;
        this.requestUrl = requestUrl;
    }

    //环境
    private String env;

    //监听的地址, 例如：127.0.0.0:8081
    private String address;

    private String application;

    //controller上方法的名称.
    private String methodName;

    //controller上mapping的url
    private String controllerRequestMapping;

    //controller方法上mapping的URL
    private String methodRequestMapping;

    //服务的名称
    private String serviceName;

    // 服务请求的URL
    private String requestUrl;

    //是否降级
    private boolean degrade = false;


    public String fetchContextUrl() {

         PathURIBuilder builder = new PathURIBuilder(InstanceDetailBuilder.schema + address);
         String  url = builder.build().toString();

        return url;
    }


    /**
     * 获取剔除了 [http://ip:port/context]/get/request 后面的部分
     *
     * @return /get/request
     */
    public String fetchSubUrl() {

        String controller;
        if (StringUtils.isEmpty(controllerRequestMapping)) {
            controller = "/";
        } else {
            //如果不是 / 开始，则加上
            controller = controllerRequestMapping.startsWith("/") ? controllerRequestMapping : "/" + controllerRequestMapping;
            //如果是 /结尾，则去掉/
            controller = controller.endsWith("/") ? controller.substring(0, controller.length() - 1) : controller;
        }

        String method;
        if (StringUtils.isEmpty(methodRequestMapping)) {
            method = "/";
        } else {
            //如果不是 / 开始，则加上
            method = methodRequestMapping.startsWith("/") ? methodRequestMapping : "/" + methodRequestMapping;
        }

        return controller + method;
    }

    public String getEnv() {
        return env;
    }

    public String getAddress() {
        return address;
    }

    public String getApplication() {
        return application;
    }


    public String getMethodName() {
        return methodName;
    }


    public String getControllerRequestMapping() {
        return controllerRequestMapping;
    }


    public String getMethodRequestMapping() {
        return methodRequestMapping;
    }

    //@JsonIgnore
    public boolean isDegrade() {
        return degrade;
    }

    //@JsonIgnore
    public void setDegrade(boolean degrade) {
        this.degrade = degrade;
    }


    public String getServiceName() {
        return serviceName;
    }


    public String getRequestUrl() {
        return requestUrl;
    }


    @Override
    public String toString() {
        return "InstanceDetail{" +
                "listenAddress='" + address + '\'' +
                ", methodName='" + methodName + '\'' +
                ", controllerRequestMapping='" + controllerRequestMapping + '\'' +
                ", methodRequestMapping='" + methodRequestMapping + '\'' +
                ", serviceName='" + serviceName + '\'' +
                ", requestUrl='" + requestUrl + '\'' +
                ", degrade=" + degrade +
                '}';
    }
}
