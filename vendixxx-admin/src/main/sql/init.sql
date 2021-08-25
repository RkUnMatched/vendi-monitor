CREATE TABLE `monitor_sequence` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `seq_value` bigint(20) NOT NULL COMMENT '序列值',
  `seq_name` varchar(64) NOT NULL COMMENT '序列名',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `creator` varchar(64) NOT NULL DEFAULT '' COMMENT '创建人',
  `modify_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `modifier` varchar(64) NOT NULL DEFAULT '' COMMENT '更新人',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_monitor_sequence_name` (`seq_name`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COMMENT='序列';

CREATE TABLE `monitor_data_input` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `biz_no` varchar(128) NOT NULL COMMENT '业务单号',
  `biz_type` varchar(64) DEFAULT '' COMMENT '业务类型',
  `application` varchar(32) NOT NULL COMMENT '来源系统',
  `application_desc` varchar(32) NOT NULL COMMENT '来源系统标示',
  `ip` varchar(64) DEFAULT '' COMMENT 'ip地址',
  `version` varchar(32) DEFAULT '' COMMENT '版本号',
  `env` varchar(32) DEFAULT '' COMMENT '环境标示',
  `method` varchar(32) DEFAULT '' COMMENT '调用方法名称',
  `method_desc` varchar(64) DEFAULT '' COMMENT '调用方法描述',
  `report_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '上报时间',
  `header` text NOT NULL COMMENT '请求体',
  `body` text NOT NULL COMMENT '相应内容',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `creator` varchar(64) NOT NULL DEFAULT '' COMMENT '创建人',
  `modify_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `modifier` varchar(64) NOT NULL DEFAULT '' COMMENT '更新人',
  `deleted` bigint(20) NOT NULL DEFAULT '0' COMMENT '是否删除',
  `status` tinyint(4) NOT NULL DEFAULT '1' COMMENT '1未处理2已处理',
  `token` varchar(64) NOT NULL DEFAULT '' COMMENT '系统token',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_monitor_data_input_biz_no` (`biz_no`),
  KEY `idx_monitor_data_input_method_application` (`method`,`application`),
  KEY `idx_monitor_data_input_application_createtime` (`application`,`create_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='监控数据表';

CREATE TABLE `monitor_switch` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `token` varchar(64) NOT NULL COMMENT 'token',
  `application` varchar(32) NOT NULL COMMENT '来源系统',
  `ip` varchar(64) DEFAULT '' COMMENT 'ip地址',
  `method` varchar(32) DEFAULT '' COMMENT '调用方法名称',
  `can_report` tinyint(4) NOT NULL DEFAULT '1' COMMENT '1上报2不上报',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `creator` varchar(64) NOT NULL DEFAULT '' COMMENT '创建人',
  `modify_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `modifier` varchar(64) NOT NULL DEFAULT '' COMMENT '更新人',
  `deleted` bigint(20) NOT NULL DEFAULT '0' COMMENT '是否删除',
  PRIMARY KEY (`id`),
  KEY `idx_monitor_switch_application_ip_method` (`application`,`ip`,`method`),
  KEY `idx_monitor_switch_application_createtime` (`application`,`create_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='监控数据上报开关表';


CREATE TABLE `id_sequence` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `seq_value` bigint(20) NOT NULL COMMENT '序号',
  `seq_type` varchar(64) NOT NULL COMMENT '序号业务类型',
  `seq_subtype` varchar(64) NOT NULL COMMENT '序号业务子类型',
  `gmt_create` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `creator` varchar(32) NOT NULL DEFAULT 'system' COMMENT '创建人编码',
  `gmt_modify` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `modifier` varchar(32) NOT NULL DEFAULT 'system' COMMENT '更新人编码',
  `extra` varchar(64) DEFAULT '' COMMENT '额外信息（例如时间控制）',
  `control_num` bigint(20) NOT NULL DEFAULT '0' COMMENT '控制数字',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='id生成器';


create function currval(v_type varchar(64),v_subtype varchar(64),v_extra varchar(64))
returns bigint(20)
begin
 DECLARE value BIGINT;
 set value = -1;
 select seq_value into value from id_sequence where seq_type = v_type and seq_subtype = v_subtype and extra = v_extra;
 if value is null
    then return -1;
 end if;
 return value;
end;

create function nextVal(v_type varchar(64),v_subtype varchar(64),v_extra varchar(64),v_step BIGINT,control_num BIGINT)
returns BIGINT(20)
begin
  if v_type is null || v_type= ''  then return -1;
	end if;
	if v_subtype is null || v_subtype= ''  then return -1;
	end if;

	if v_extra is null || v_extra= ''
	   then
		 if currval(v_type,v_subtype,'') < 0
		     then
				 INSERT INTO `id_sequence`(seq_value,seq_type,seq_subtype,extra,control_num) VALUES (0, v_type, v_subtype,'',0);
				 update id_sequence set seq_value = seq_value + v_step where seq_type = v_type and seq_subtype = v_subtype and extra = '';
		     return currval(v_type,v_subtype,'');
		 else
				update id_sequence set seq_value = seq_value + v_step where seq_type = v_type and seq_subtype = v_subtype and extra = '';
				return currval(v_type,v_subtype,'');
		 end if;
  end if;
  if currval(v_type,v_subtype,v_extra) < 0
	then
		  INSERT INTO `id_sequence`(seq_value,seq_type,seq_subtype,extra,control_num) VALUES (0, v_type, v_subtype,v_extra,control_num);
	end if;
	update id_sequence set seq_value = seq_value + v_step where seq_type = v_type and seq_subtype = v_subtype and extra = v_extra;
	return currval(v_type,v_subtype,v_extra);
end
