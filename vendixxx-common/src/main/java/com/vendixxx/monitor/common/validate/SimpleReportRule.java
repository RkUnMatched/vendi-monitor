package com.vendixxx.monitor.common.validate;

/**
 * simple rule
 * @author liuzheng
 * @date 2021-01-21
 * @since 2021
 */
public class SimpleReportRule implements ReportRule{

    public static final String SIMPLE = "simple";

    @Override
    public String expression() {
        return "default";
    }

    @Override
    public boolean canReport(String expression) {
        return true;
    }

    /*public static void main(String[] args) {
        System.out.println(SimpleReportRule.class.isInterface());
        System.out.println(SimpleReportRule.class.isAnonymousClass());
        System.out.println(ReportRule.class.isInterface());
        System.out.println(SimpleReportRule.class.isAssignableFrom(ReportRule.class));
        System.out.println(Modifier.isAbstract(SimpleReportRule.class.getModifiers()));
        System.out.println(Modifier.isAbstract(AbstractList.class.getModifiers()));
    }*/
}
