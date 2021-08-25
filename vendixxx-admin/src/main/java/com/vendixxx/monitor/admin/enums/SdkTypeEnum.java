package com.vendixxx.monitor.admin.enums;

/**
 * 厂商
 * @author liuzheng
 * @date 2021-04-25
 * @since 2021
 */
public enum SdkTypeEnum {

    TENCENT("tencentcloud","腾讯云"),
    ALI_CLOUD("alicloud","阿里云"),
    XF_YUN("xfyun","科大讯飞"),
    BAIDU_CLOUD("baiducloud","百度云")
    ;

    private final String code;

    private final String value;

    SdkTypeEnum(String code, String value) {
        this.code = code;
        this.value = value;
    }

    public String getCode() {
        return code;
    }

    public String getValue() {
        return value;
    }
}
