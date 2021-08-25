package com.vendixxx.monitor.admin.inner.sequence;

import com.vendixxx.monitor.admin.repository.dao.ext.IdSequencePOMapperExt;
import com.vendixxx.monitor.common.exception.MonitorSystemException;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author liuzheng
 * @date 2021-08-24
 * @since 2021
 */
@Component
public class IdNextValService {

    @Resource
    IdSequencePOMapperExt idSequencePOMapperExt;

    /**
     * 获取下一个序列
     * @param type         业务类型
     * @param subType      子业务类型
     * @param extra        额外信息，例如日期控制
     * @param xstep        增加的步长
     * @param controlNum   距离1970/1/1的天数
     * @return
     */
    public long nextVal(String type, String subType, String extra, long xstep, long controlNum){
        /*if(StringUtils.isEmpty(type)){
            throw new MonitorSystemException(500,"业务类型不能为空");
        }
        if(StringUtils.isEmpty(subType)){
            throw new MonitorSystemException(500,"子业务类型不能为空");
        }
        if(xstep <1){
            throw new MonitorSystemException(500,"步长不能小于1");
        }*/
        if(StringUtils.isEmpty(extra)){
            return idSequencePOMapperExt.nextVal(type,subType,"",xstep,controlNum);
        }
        return idSequencePOMapperExt.nextVal(type,subType,extra,xstep,controlNum);
    }
}
