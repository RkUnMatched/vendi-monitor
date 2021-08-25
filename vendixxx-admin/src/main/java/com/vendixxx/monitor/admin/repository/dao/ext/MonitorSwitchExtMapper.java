package com.vendixxx.monitor.admin.repository.dao.ext;

import com.vendixxx.monitor.admin.repository.bean.MonitorSwitch;
import com.vendixxx.monitor.admin.repository.dao.MonitorSwitchMapper;
import com.vendixxx.monitor.admin.service.model.MonitorSwitchQueryDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 开关mapper
 * @author liuzheng
 * @date 2021-01-25
 * @since 2021
 */
@Mapper
public interface MonitorSwitchExtMapper extends MonitorSwitchMapper {

    @Select("<script>"
            +"select * from monitor_switch where 1=1"

            + "<if test='token!=null'>"
            + " and token=#{token} "
            + "</if>"

            + "<if test='application!=null'>"
            + " and application=#{application} "
            + "</if>"

            + "<if test='ip!=null'>"
            + " and ip=#{ip} "
            + "</if>"


            + "<if test='method!=null'>"
            + " and method=#{method} "
            + "</if>"

            + " order by modify_time desc "

            + "<if test='pgSize!=null'>"
            + " limit ${start}, ${pgSize} "
            + "</if>"

            + "</script>")
    List<MonitorSwitch> queryMonitorSwitchPageList(MonitorSwitchQueryDTO monitorSwitchQueryDTO);

    @Select("<script>"
            +"select count(1) from monitor_switch where 1=1"

            + "<if test='token!=null'>"
            + " and token=#{token} "
            + "</if>"

            + "<if test='application!=null'>"
            + " and application=#{application} "
            + "</if>"

            + "<if test='ip!=null'>"
            + " and ip=#{ip} "
            + "</if>"


            + "<if test='method!=null'>"
            + " and method=#{method} "
            + "</if>"

            + "</script>")
    long queryMonitorSwitchPageTotal(MonitorSwitchQueryDTO monitorSwitchQueryDTO);
}


