package com.vendixxx.monitor.admin.core;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.vendixxx.monitor.common.annotation.MonitReportAnnotation;
import com.vendixxx.monitor.common.enums.MonitorSendTypeEnum;
import com.vendixxx.monitor.common.reporter.ReportBean;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * @author liuzheng
 * @date 2021-01-20
 * @since 2021
 */
@Service
public class AnnoJointService{

    @MonitReportAnnotation(sendType = MonitorSendTypeEnum.BODY)
    public ComplexData validate(ComplexData s) {
        ComplexData complexData = new ComplexData();
        complexData.setAmount("123.45");
        complexData.setNames(Sets.newHashSet("尼克松","克林顿"));
        complexData.setNum(BigDecimal.ONE);
        Company company = new Company();
        company.setBoss("乔峰");
        company.setName("虚竹");
        company.setAge(24);
        complexData.setCompany(Lists.newArrayList(company));
        return complexData;
    }

    @MonitReportAnnotation(sendType = MonitorSendTypeEnum.BODY)
    public void validate1(String s) {
        ReportBean reportBean = new ReportBean();
        reportBean.setBizNo("XXXX");
        reportBean.setBizType("注解");
    }

    @MonitReportAnnotation(sendType = MonitorSendTypeEnum.BODY)
    public void validate2(String s1,String s2) {
        ReportBean reportBean = new ReportBean();
        reportBean.setBizNo("XXXX");
        reportBean.setBizType("注解");
    }

    @MonitReportAnnotation(sendType = MonitorSendTypeEnum.BODY)
    public ReportBean validate3() {
        ReportBean reportBean = new ReportBean();
        reportBean.setBizNo("XXXX");
        reportBean.setBizType("注解");
        return reportBean;
    }
}
