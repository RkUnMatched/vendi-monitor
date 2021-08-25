package com.vendixxx.monitor.admin.enums;

/**
 * 词条类型
 * @author liuzheng
 * @date 2021-04-25
 * @since 2021
 */
public enum WordTypeEnum {

    PRODUCT(1,"商品"),
    UNIT(2,"单位")
    ;

    private final Integer code;

    private final String value;

    WordTypeEnum(Integer code, String value) {
        this.code = code;
        this.value = value;
    }

    public Integer getCode() {
        return code;
    }

    public String getValue() {
        return value;
    }
}
