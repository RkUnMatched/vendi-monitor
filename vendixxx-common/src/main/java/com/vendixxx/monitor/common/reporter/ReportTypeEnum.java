package com.vendixxx.monitor.common.reporter;

/**
 * 上报类型
 * @author liuzheng
 * @date 2021-01-19
 * @since 2021
 */
public enum ReportTypeEnum {

    EVENT(1,"事件"),
    REPORT(2,"异常上报"),
    METRICS(3,"分析综合数据"),
    TRANSACTION(4, "事务")
    ;

    private final Integer code;

    private final String value;

    ReportTypeEnum(Integer code, String value) {
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
        for (ReportTypeEnum reportTypeEnum:ReportTypeEnum.values()){
            if(reportTypeEnum.getCode().equals(code)){
                return reportTypeEnum.getValue();
            }
        }
        return "UNKNOWN";
    }
}
