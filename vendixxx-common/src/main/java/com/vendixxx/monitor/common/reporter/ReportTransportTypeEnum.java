package com.vendixxx.monitor.common.reporter;

/**
 * 上报传输协议
 * @author liuzheng
 * @date 2021-01-19
 * @since 2021
 */
public enum ReportTransportTypeEnum {

    GRPC(1,"grpc"),
    HTTP_JAXRS(2,"http-jaxrs"),
    HTTP_MVC(3,"http-springMvc"),
    TCP(4,"tcp")
    ;

    private final Integer code;

    private final String value;

    ReportTransportTypeEnum(Integer code, String value) {
        this.code = code;
        this.value = value;
    }

    public Integer getCode() {
        return code;
    }

    public String getValue() {
        return value;
    }

    public static String getReportType(Integer code){
        for (ReportTransportTypeEnum reportTypeEnum: ReportTransportTypeEnum.values()){
            if(reportTypeEnum.getCode().equals(code)){
                return reportTypeEnum.getValue();
            }
        }
        return "UNKNOWN";
    }
}
