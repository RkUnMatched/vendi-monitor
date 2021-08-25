package com.vendixxx.monitor.admin.enums;

/**
 * 引擎模型类型。
 * 电话场景：
 * • 8k_en：电话 8k 英语；
 * • 8k_zh：电话 8k 中文普通话通用；
 * 非电话场景：
 * • 16k_zh：16k 中文普通话通用；
 * • 16k_en：16k 英语；
 * • 16k_ca：16k 粤语；
 * • 16k_ja：16k 日语；
 * •16k_wuu-SH：16k 上海话方言；
 * •16k_zh_medical：16k 医疗。
 *
 * @author liuzheng
 * @date 2021-01-21
 * @since 2021
 */
public enum EngineServiceTypeEnum {

    EIGHT_K_EN("8k_en","电话 8k 英语"),
    EIGHT_K_ZH("8k_zh","电话 8k 中文普通话通用"),
    SIXTEEN_K_ZH("16k_zh","16k 中文普通话通用"),
    SIXTEEN_K_EN("16k_en","英语"),
    SIXTEEN_K_CA("16k_ca","粤语"),
    SIXTEEN_K_JA("16k_ja","日语"),
    SIXTEEN_K_WUU_SH("16k_wuu-SH","16k 上海话方言"),
    SIXTEEN_K_ZH_MEDICAL("16k_zh_medical","16k 医疗");

    private final String code;

    private final String value;

    EngineServiceTypeEnum(String code, String value) {
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
