package com.vendixxx.monitor.common.enums;

/**
 * 状态
 *
 * @author liuzheng
 * @date 2021-01-21
 * @since 2021
 */
public enum CommonDbStatusEnum {

    INIT(1,"未处理"),
    END(2,"已处理")
    ;

    private final Integer code;

    private final String value;

    CommonDbStatusEnum(Integer code, String value) {
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
        for (CommonDbStatusEnum reportTypeEnum: values()){
            if(reportTypeEnum.getCode().equals(code)){
                return reportTypeEnum.getValue();
            }
        }
        return "UNKNOWN";
    }
}
