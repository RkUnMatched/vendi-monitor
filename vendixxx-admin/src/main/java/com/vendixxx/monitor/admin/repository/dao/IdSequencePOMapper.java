package com.vendixxx.monitor.admin.repository.dao;

import com.vendixxx.monitor.admin.repository.bean.IdSequencePO;
import com.vendixxx.monitor.admin.repository.bean.IdSequencePOExample;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

public interface IdSequencePOMapper extends BaseMapper {
    @Delete({
        "delete from id_sequence",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long id);

    @Insert({
        "insert into id_sequence (id, seq_value, ",
        "seq_type, seq_subtype, ",
        "gmt_create, creator, ",
        "gmt_modify, modifier, ",
        "extra, control_num)",
        "values (#{id,jdbcType=BIGINT}, #{seqValue,jdbcType=BIGINT}, ",
        "#{seqType,jdbcType=VARCHAR}, #{seqSubtype,jdbcType=VARCHAR}, ",
        "#{gmtCreate,jdbcType=TIMESTAMP}, #{creator,jdbcType=VARCHAR}, ",
        "#{gmtModify,jdbcType=TIMESTAMP}, #{modifier,jdbcType=VARCHAR}, ",
        "#{extra,jdbcType=VARCHAR}, #{controlNum,jdbcType=BIGINT})"
    })
    int insert(IdSequencePO record);

    @InsertProvider(type=IdSequencePOSqlProvider.class, method="insertSelective")
    int insertSelective(IdSequencePO record);

    @SelectProvider(type=IdSequencePOSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType= JdbcType.BIGINT, id=true),
        @Result(column="seq_value", property="seqValue", jdbcType= JdbcType.BIGINT),
        @Result(column="seq_type", property="seqType", jdbcType= JdbcType.VARCHAR),
        @Result(column="seq_subtype", property="seqSubtype", jdbcType= JdbcType.VARCHAR),
        @Result(column="gmt_create", property="gmtCreate", jdbcType= JdbcType.TIMESTAMP),
        @Result(column="creator", property="creator", jdbcType= JdbcType.VARCHAR),
        @Result(column="gmt_modify", property="gmtModify", jdbcType= JdbcType.TIMESTAMP),
        @Result(column="modifier", property="modifier", jdbcType= JdbcType.VARCHAR),
        @Result(column="extra", property="extra", jdbcType= JdbcType.VARCHAR),
        @Result(column="control_num", property="controlNum", jdbcType= JdbcType.BIGINT)
    })
    List<IdSequencePO> selectByExample(IdSequencePOExample example);

    @Select({
        "select",
        "id, seq_value, seq_type, seq_subtype, gmt_create, creator, gmt_modify, modifier, ",
        "extra, control_num",
        "from id_sequence",
        "where id = #{id,jdbcType=BIGINT}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType= JdbcType.BIGINT, id=true),
        @Result(column="seq_value", property="seqValue", jdbcType= JdbcType.BIGINT),
        @Result(column="seq_type", property="seqType", jdbcType= JdbcType.VARCHAR),
        @Result(column="seq_subtype", property="seqSubtype", jdbcType= JdbcType.VARCHAR),
        @Result(column="gmt_create", property="gmtCreate", jdbcType= JdbcType.TIMESTAMP),
        @Result(column="creator", property="creator", jdbcType= JdbcType.VARCHAR),
        @Result(column="gmt_modify", property="gmtModify", jdbcType= JdbcType.TIMESTAMP),
        @Result(column="modifier", property="modifier", jdbcType= JdbcType.VARCHAR),
        @Result(column="extra", property="extra", jdbcType= JdbcType.VARCHAR),
        @Result(column="control_num", property="controlNum", jdbcType= JdbcType.BIGINT)
    })
    IdSequencePO selectByPrimaryKey(Long id);

    @UpdateProvider(type=IdSequencePOSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") IdSequencePO record, @Param("example") IdSequencePOExample example);

    @UpdateProvider(type=IdSequencePOSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") IdSequencePO record, @Param("example") IdSequencePOExample example);

    @UpdateProvider(type=IdSequencePOSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(IdSequencePO record);

    @Update({
        "update id_sequence",
        "set seq_value = #{seqValue,jdbcType=BIGINT},",
          "seq_type = #{seqType,jdbcType=VARCHAR},",
          "seq_subtype = #{seqSubtype,jdbcType=VARCHAR},",
          "gmt_create = #{gmtCreate,jdbcType=TIMESTAMP},",
          "creator = #{creator,jdbcType=VARCHAR},",
          "gmt_modify = #{gmtModify,jdbcType=TIMESTAMP},",
          "modifier = #{modifier,jdbcType=VARCHAR},",
          "extra = #{extra,jdbcType=VARCHAR},",
          "control_num = #{controlNum,jdbcType=BIGINT}",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(IdSequencePO record);
}