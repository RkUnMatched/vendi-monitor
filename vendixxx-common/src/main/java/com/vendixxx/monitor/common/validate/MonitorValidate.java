package com.vendixxx.monitor.common.validate;

import com.vendixxx.monitor.common.reporter.ReportBean;
import com.vendixxx.monitor.common.reporter.ReportTypeEnum;

/**
 * 上报bean
 *
 * @author liuzheng
 * @date 2021-01-20
 * @since 2021
 */
public interface MonitorValidate<R extends ReportBean,M>{


    /**
     * 上报
     *
     * 默认不上报
     * @param r
     * @param reportTypeEnum
     * @return
     */
    default M report(R r, ReportTypeEnum reportTypeEnum){
        return null;
    }


}
