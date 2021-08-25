package com.vendixxx.monitor.admin.repository.dao;

import com.vendixxx.monitor.admin.repository.bean.MonitorDataInput;
import com.vendixxx.monitor.admin.repository.bean.MonitorDataInputExample;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

public interface MonitorDataInputMapper extends BaseMapper {
    @SelectProvider(type=MonitorDataInputSqlProvider.class, method="countByExample")
    long countByExample(MonitorDataInputExample example);

    @DeleteProvider(type=MonitorDataInputSqlProvider.class, method="deleteByExample")
    int deleteByExample(MonitorDataInputExample example);

    @Delete({
        "delete from monitor_data_input",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long id);

    @Insert({
        "insert into monitor_data_input (id, biz_no, ",
        "biz_type, application, ",
        "application_desc, ip, ",
        "version, env, method, ",
        "method_desc, report_time, ",
        "header, body, create_time, ",
        "creator, modify_time, ",
        "modifier, deleted, ",
        "status, token)",
        "values (#{id,jdbcType=BIGINT}, #{bizNo,jdbcType=VARCHAR}, ",
        "#{bizType,jdbcType=VARCHAR}, #{application,jdbcType=VARCHAR}, ",
        "#{applicationDesc,jdbcType=VARCHAR}, #{ip,jdbcType=VARCHAR}, ",
        "#{version,jdbcType=VARCHAR}, #{env,jdbcType=VARCHAR}, #{method,jdbcType=VARCHAR}, ",
        "#{methodDesc,jdbcType=VARCHAR}, #{reportTime,jdbcType=TIMESTAMP}, ",
        "#{header,jdbcType=VARCHAR}, #{body,jdbcType=VARCHAR}, #{createTime,jdbcType=TIMESTAMP}, ",
        "#{creator,jdbcType=VARCHAR}, #{modifyTime,jdbcType=TIMESTAMP}, ",
        "#{modifier,jdbcType=VARCHAR}, #{deleted,jdbcType=BIGINT}, ",
        "#{status,jdbcType=TINYINT}, #{token,jdbcType=VARCHAR})"
    })
    int insert(MonitorDataInput record);

    @InsertProvider(type=MonitorDataInputSqlProvider.class, method="insertSelective")
    int insertSelective(MonitorDataInput record);

    @SelectProvider(type=MonitorDataInputSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="biz_no", property="bizNo", jdbcType=JdbcType.VARCHAR),
        @Result(column="biz_type", property="bizType", jdbcType=JdbcType.VARCHAR),
        @Result(column="application", property="application", jdbcType=JdbcType.VARCHAR),
        @Result(column="application_desc", property="applicationDesc", jdbcType=JdbcType.VARCHAR),
        @Result(column="ip", property="ip", jdbcType=JdbcType.VARCHAR),
        @Result(column="version", property="version", jdbcType=JdbcType.VARCHAR),
        @Result(column="env", property="env", jdbcType=JdbcType.VARCHAR),
        @Result(column="method", property="method", jdbcType=JdbcType.VARCHAR),
        @Result(column="method_desc", property="methodDesc", jdbcType=JdbcType.VARCHAR),
        @Result(column="report_time", property="reportTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="header", property="header", jdbcType=JdbcType.VARCHAR),
        @Result(column="body", property="body", jdbcType=JdbcType.VARCHAR),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="creator", property="creator", jdbcType=JdbcType.VARCHAR),
        @Result(column="modify_time", property="modifyTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="modifier", property="modifier", jdbcType=JdbcType.VARCHAR),
        @Result(column="deleted", property="deleted", jdbcType=JdbcType.BIGINT),
        @Result(column="status", property="status", jdbcType=JdbcType.TINYINT),
        @Result(column="token", property="token", jdbcType=JdbcType.VARCHAR)
    })
    List<MonitorDataInput> selectByExample(MonitorDataInputExample example);

    @Select({
        "select",
        "id, biz_no, biz_type, application, application_desc, ip, version, env, method, ",
        "method_desc, report_time, header, body, create_time, creator, modify_time, modifier, ",
        "deleted, status, token",
        "from monitor_data_input",
        "where id = #{id,jdbcType=BIGINT}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="biz_no", property="bizNo", jdbcType=JdbcType.VARCHAR),
        @Result(column="biz_type", property="bizType", jdbcType=JdbcType.VARCHAR),
        @Result(column="application", property="application", jdbcType=JdbcType.VARCHAR),
        @Result(column="application_desc", property="applicationDesc", jdbcType=JdbcType.VARCHAR),
        @Result(column="ip", property="ip", jdbcType=JdbcType.VARCHAR),
        @Result(column="version", property="version", jdbcType=JdbcType.VARCHAR),
        @Result(column="env", property="env", jdbcType=JdbcType.VARCHAR),
        @Result(column="method", property="method", jdbcType=JdbcType.VARCHAR),
        @Result(column="method_desc", property="methodDesc", jdbcType=JdbcType.VARCHAR),
        @Result(column="report_time", property="reportTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="header", property="header", jdbcType=JdbcType.VARCHAR),
        @Result(column="body", property="body", jdbcType=JdbcType.VARCHAR),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="creator", property="creator", jdbcType=JdbcType.VARCHAR),
        @Result(column="modify_time", property="modifyTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="modifier", property="modifier", jdbcType=JdbcType.VARCHAR),
        @Result(column="deleted", property="deleted", jdbcType=JdbcType.BIGINT),
        @Result(column="status", property="status", jdbcType=JdbcType.TINYINT),
        @Result(column="token", property="token", jdbcType=JdbcType.VARCHAR)
    })
    MonitorDataInput selectByPrimaryKey(Long id);

    @UpdateProvider(type=MonitorDataInputSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") MonitorDataInput record, @Param("example") MonitorDataInputExample example);

    @UpdateProvider(type=MonitorDataInputSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") MonitorDataInput record, @Param("example") MonitorDataInputExample example);

    @UpdateProvider(type=MonitorDataInputSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(MonitorDataInput record);

    @Update({
        "update monitor_data_input",
        "set biz_no = #{bizNo,jdbcType=VARCHAR},",
          "biz_type = #{bizType,jdbcType=VARCHAR},",
          "application = #{application,jdbcType=VARCHAR},",
          "application_desc = #{applicationDesc,jdbcType=VARCHAR},",
          "ip = #{ip,jdbcType=VARCHAR},",
          "version = #{version,jdbcType=VARCHAR},",
          "env = #{env,jdbcType=VARCHAR},",
          "method = #{method,jdbcType=VARCHAR},",
          "method_desc = #{methodDesc,jdbcType=VARCHAR},",
          "report_time = #{reportTime,jdbcType=TIMESTAMP},",
          "header = #{header,jdbcType=VARCHAR},",
          "body = #{body,jdbcType=VARCHAR},",
          "create_time = #{createTime,jdbcType=TIMESTAMP},",
          "creator = #{creator,jdbcType=VARCHAR},",
          "modify_time = #{modifyTime,jdbcType=TIMESTAMP},",
          "modifier = #{modifier,jdbcType=VARCHAR},",
          "deleted = #{deleted,jdbcType=BIGINT},",
          "status = #{status,jdbcType=TINYINT},",
          "token = #{token,jdbcType=VARCHAR}",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(MonitorDataInput record);
}