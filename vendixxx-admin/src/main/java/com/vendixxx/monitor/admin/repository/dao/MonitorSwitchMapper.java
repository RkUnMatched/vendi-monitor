package com.vendixxx.monitor.admin.repository.dao;

import com.vendixxx.monitor.admin.repository.bean.MonitorSwitch;
import com.vendixxx.monitor.admin.repository.bean.MonitorSwitchExample;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

public interface MonitorSwitchMapper extends BaseMapper {
    @SelectProvider(type=MonitorSwitchSqlProvider.class, method="countByExample")
    long countByExample(MonitorSwitchExample example);

    @DeleteProvider(type=MonitorSwitchSqlProvider.class, method="deleteByExample")
    int deleteByExample(MonitorSwitchExample example);

    @Delete({
        "delete from monitor_switch",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long id);

    @Insert({
        "insert into monitor_switch (id, token, ",
        "application, ip, ",
        "method, can_report, ",
        "create_time, creator, ",
        "modify_time, modifier, ",
        "deleted)",
        "values (#{id,jdbcType=BIGINT}, #{token,jdbcType=VARCHAR}, ",
        "#{application,jdbcType=VARCHAR}, #{ip,jdbcType=VARCHAR}, ",
        "#{method,jdbcType=VARCHAR}, #{canReport,jdbcType=TINYINT}, ",
        "#{createTime,jdbcType=TIMESTAMP}, #{creator,jdbcType=VARCHAR}, ",
        "#{modifyTime,jdbcType=TIMESTAMP}, #{modifier,jdbcType=VARCHAR}, ",
        "#{deleted,jdbcType=BIGINT})"
    })
    int insert(MonitorSwitch record);

    @InsertProvider(type=MonitorSwitchSqlProvider.class, method="insertSelective")
    int insertSelective(MonitorSwitch record);

    @SelectProvider(type=MonitorSwitchSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="token", property="token", jdbcType=JdbcType.VARCHAR),
        @Result(column="application", property="application", jdbcType=JdbcType.VARCHAR),
        @Result(column="ip", property="ip", jdbcType=JdbcType.VARCHAR),
        @Result(column="method", property="method", jdbcType=JdbcType.VARCHAR),
        @Result(column="can_report", property="canReport", jdbcType=JdbcType.TINYINT),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="creator", property="creator", jdbcType=JdbcType.VARCHAR),
        @Result(column="modify_time", property="modifyTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="modifier", property="modifier", jdbcType=JdbcType.VARCHAR),
        @Result(column="deleted", property="deleted", jdbcType=JdbcType.BIGINT)
    })
    List<MonitorSwitch> selectByExample(MonitorSwitchExample example);

    @Select({
        "select",
        "id, token, application, ip, method, can_report, create_time, creator, modify_time, ",
        "modifier, deleted",
        "from monitor_switch",
        "where id = #{id,jdbcType=BIGINT}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="token", property="token", jdbcType=JdbcType.VARCHAR),
        @Result(column="application", property="application", jdbcType=JdbcType.VARCHAR),
        @Result(column="ip", property="ip", jdbcType=JdbcType.VARCHAR),
        @Result(column="method", property="method", jdbcType=JdbcType.VARCHAR),
        @Result(column="can_report", property="canReport", jdbcType=JdbcType.TINYINT),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="creator", property="creator", jdbcType=JdbcType.VARCHAR),
        @Result(column="modify_time", property="modifyTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="modifier", property="modifier", jdbcType=JdbcType.VARCHAR),
        @Result(column="deleted", property="deleted", jdbcType=JdbcType.BIGINT)
    })
    MonitorSwitch selectByPrimaryKey(Long id);

    @UpdateProvider(type=MonitorSwitchSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") MonitorSwitch record, @Param("example") MonitorSwitchExample example);

    @UpdateProvider(type=MonitorSwitchSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") MonitorSwitch record, @Param("example") MonitorSwitchExample example);

    @UpdateProvider(type=MonitorSwitchSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(MonitorSwitch record);

    @Update({
        "update monitor_switch",
        "set token = #{token,jdbcType=VARCHAR},",
          "application = #{application,jdbcType=VARCHAR},",
          "ip = #{ip,jdbcType=VARCHAR},",
          "method = #{method,jdbcType=VARCHAR},",
          "can_report = #{canReport,jdbcType=TINYINT},",
          "create_time = #{createTime,jdbcType=TIMESTAMP},",
          "creator = #{creator,jdbcType=VARCHAR},",
          "modify_time = #{modifyTime,jdbcType=TIMESTAMP},",
          "modifier = #{modifier,jdbcType=VARCHAR},",
          "deleted = #{deleted,jdbcType=BIGINT}",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(MonitorSwitch record);
}