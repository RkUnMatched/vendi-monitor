package com.vendixxx.monitor.admin.core;

import com.vendixxx.monitor.common.reporter.ReportBean;
import com.vendixxx.monitor.common.validate.BaseValidate;
import org.springframework.stereotype.Service;

/**
 * @author liuzheng
 * @date 2021-01-20
 * @since 2021
 */
@Service
public class JointService implements BaseValidate<String, ReportBean> {

    @Override
    public ReportBean validate(String s) {
        ReportBean reportBean = new ReportBean();
        reportBean.setBizNo("YY12345678");
        reportBean.setBizType("调拨");
        return reportBean;
    }
}
