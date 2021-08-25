package com.vendixxx.monitor.common.validate;

/**
 * 上报规则
 * @author liuzheng
 * @date 2021-01-20
 * @since 2021
 */
public interface ReportRule {

    /**
     * 上报表达式
     * @return
     */
    String expression();

    /**
     * 自定义上报规则
     * @param expression
     * @return
     */
    boolean canReport(String expression);
}
