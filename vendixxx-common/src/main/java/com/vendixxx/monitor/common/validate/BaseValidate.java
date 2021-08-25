package com.vendixxx.monitor.common.validate;

import com.vendixxx.monitor.common.reporter.ReportBean;

/**
 * 客户端校验接口
 *
 * @author liuzheng
 * @date 2021-01-20
 * @since 2021
 */
public interface BaseValidate<T, R extends ReportBean>{

    String validate = "validate";
    String WITH_REPORT = "withReport";

    /**
     * 校验
     *
     * @param t
     * @return
     */
    R validate(T t);

    /**
     * 是否允许立即上报
     *
     * @return
     */
    default boolean withReport(){
        return getReportRule().canReport(getReportRule().expression());
    }

    /**
     * 获取上报规则
     *
     * @return
     */
    default ReportRule getReportRule() {
        return new ReportRule() {
            @Override
            public String expression() {
                return "default";
            }

            /**
             * 默认上报
             * @param expression
             * @return
             */
            @Override
            public boolean canReport(String expression) {
                return true;
            }
        };
    }

}
