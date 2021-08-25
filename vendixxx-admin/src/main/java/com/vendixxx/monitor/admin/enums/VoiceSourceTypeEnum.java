package com.vendixxx.monitor.admin.enums;

/**
 *
 * 语音数据来源。0：语音 URL；1：语音数据
 *
 * @author liuzheng
 * @date 2021-01-21
 * @since 2021
 */
public enum VoiceSourceTypeEnum {

    URL(0L,"url"),
    DATA(1L,"语音数据")
    ;

    private final Long code;

    private final String value;

    VoiceSourceTypeEnum(Long code, String value) {
        this.code = code;
        this.value = value;
    }

    public Long getCode() {
        return code;
    }

    public String getValue() {
        return value;
    }

}
