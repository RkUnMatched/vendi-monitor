package com.vendixxx.monitor.admin.repository.dao.ext;

import com.vendixxx.monitor.admin.repository.dao.IdSequencePOMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @author liuzheng
 * @date 2021-08-23
 * @since 2021
 */
@Mapper
public interface IdSequencePOMapperExt extends IdSequencePOMapper {

    /**
     * 获取下一个序列
     * @param type         业务类型
     * @param subType      子业务类型
     * @param extra        额外信息，例如日期控制
     * @param xstep        增加的步长
     * @param controlNum   距离1970/1/1的天数
     * @return
     */
    @Select("<script>"
            +"select nextVal(#{type,jdbcType=VARCHAR},#{subType,jdbcType=VARCHAR},#{extra,jdbcType=VARCHAR},#{xstep,jdbcType=BIGINT},#{controlNum,jdbcType=BIGINT}) from dual"
            + "</script>")
    long nextVal(@Param("type") String type, @Param("subType") String subType, @Param("extra") String extra, @Param("xstep") long xstep, @Param("controlNum") long controlNum);
}
