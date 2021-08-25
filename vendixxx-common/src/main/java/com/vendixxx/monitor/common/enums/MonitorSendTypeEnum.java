package com.vendixxx.monitor.common.enums;

/**
 * 消息发送模式
 *
 * @author liuzheng
 * @date 2021-01-21
 * @since 2021
 */
public enum MonitorSendTypeEnum {

    HEADER(1,"校验只上报入参"),
    BODY(2,"校验结果，上报入参和结果")
    ;

    private final Integer code;

    private final String value;

    MonitorSendTypeEnum(Integer code, String value) {
        this.code = code;
        this.value = value;
    }

    public Integer getCode() {
        return code;
    }

    public String getValue() {
        return value;
    }

    public static String getSelected(Integer code){
        for (MonitorSendTypeEnum reportTypeEnum: values()){
            if(reportTypeEnum.getCode().equals(code)){
                return reportTypeEnum.getValue();
            }
        }
        return "UNKNOWN";
    }
}
