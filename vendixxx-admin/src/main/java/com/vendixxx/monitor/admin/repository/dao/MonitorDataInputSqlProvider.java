package com.vendixxx.monitor.admin.repository.dao;

import com.vendixxx.monitor.admin.repository.bean.MonitorDataInput;
import com.vendixxx.monitor.admin.repository.bean.MonitorDataInputExample;
import com.vendixxx.monitor.admin.repository.bean.MonitorDataInputExample.Criteria;
import com.vendixxx.monitor.admin.repository.bean.MonitorDataInputExample.Criterion;
import org.apache.ibatis.jdbc.SQL;

import java.util.List;
import java.util.Map;

public class MonitorDataInputSqlProvider {

    public String countByExample(MonitorDataInputExample example) {
        SQL sql = new SQL();
        sql.SELECT("count(*)").FROM("monitor_data_input");
        applyWhere(sql, example, false);
        return sql.toString();
    }

    public String deleteByExample(MonitorDataInputExample example) {
        SQL sql = new SQL();
        sql.DELETE_FROM("monitor_data_input");
        applyWhere(sql, example, false);
        return sql.toString();
    }

    public String insertSelective(MonitorDataInput record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("monitor_data_input");
        
        if (record.getId() != null) {
            sql.VALUES("id", "#{id,jdbcType=BIGINT}");
        }
        
        if (record.getBizNo() != null) {
            sql.VALUES("biz_no", "#{bizNo,jdbcType=VARCHAR}");
        }
        
        if (record.getBizType() != null) {
            sql.VALUES("biz_type", "#{bizType,jdbcType=VARCHAR}");
        }
        
        if (record.getApplication() != null) {
            sql.VALUES("application", "#{application,jdbcType=VARCHAR}");
        }
        
        if (record.getApplicationDesc() != null) {
            sql.VALUES("application_desc", "#{applicationDesc,jdbcType=VARCHAR}");
        }
        
        if (record.getIp() != null) {
            sql.VALUES("ip", "#{ip,jdbcType=VARCHAR}");
        }
        
        if (record.getVersion() != null) {
            sql.VALUES("version", "#{version,jdbcType=VARCHAR}");
        }
        
        if (record.getEnv() != null) {
            sql.VALUES("env", "#{env,jdbcType=VARCHAR}");
        }
        
        if (record.getMethod() != null) {
            sql.VALUES("method", "#{method,jdbcType=VARCHAR}");
        }
        
        if (record.getMethodDesc() != null) {
            sql.VALUES("method_desc", "#{methodDesc,jdbcType=VARCHAR}");
        }
        
        if (record.getReportTime() != null) {
            sql.VALUES("report_time", "#{reportTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getHeader() != null) {
            sql.VALUES("header", "#{header,jdbcType=VARCHAR}");
        }
        
        if (record.getBody() != null) {
            sql.VALUES("body", "#{body,jdbcType=VARCHAR}");
        }
        
        if (record.getCreateTime() != null) {
            sql.VALUES("create_time", "#{createTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getCreator() != null) {
            sql.VALUES("creator", "#{creator,jdbcType=VARCHAR}");
        }
        
        if (record.getModifyTime() != null) {
            sql.VALUES("modify_time", "#{modifyTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getModifier() != null) {
            sql.VALUES("modifier", "#{modifier,jdbcType=VARCHAR}");
        }
        
        if (record.getDeleted() != null) {
            sql.VALUES("deleted", "#{deleted,jdbcType=BIGINT}");
        }
        
        if (record.getStatus() != null) {
            sql.VALUES("status", "#{status,jdbcType=TINYINT}");
        }
        
        if (record.getToken() != null) {
            sql.VALUES("token", "#{token,jdbcType=VARCHAR}");
        }
        
        return sql.toString();
    }

    public String selectByExample(MonitorDataInputExample example) {
        SQL sql = new SQL();
        if (example != null && example.isDistinct()) {
            sql.SELECT_DISTINCT("id");
        } else {
            sql.SELECT("id");
        }
        sql.SELECT("biz_no");
        sql.SELECT("biz_type");
        sql.SELECT("application");
        sql.SELECT("application_desc");
        sql.SELECT("ip");
        sql.SELECT("version");
        sql.SELECT("env");
        sql.SELECT("method");
        sql.SELECT("method_desc");
        sql.SELECT("report_time");
        sql.SELECT("header");
        sql.SELECT("body");
        sql.SELECT("create_time");
        sql.SELECT("creator");
        sql.SELECT("modify_time");
        sql.SELECT("modifier");
        sql.SELECT("deleted");
        sql.SELECT("status");
        sql.SELECT("token");
        sql.FROM("monitor_data_input");
        applyWhere(sql, example, false);
        
        if (example != null && example.getOrderByClause() != null) {
            sql.ORDER_BY(example.getOrderByClause());
        }
        
        return sql.toString();
    }

    public String updateByExampleSelective(Map<String, Object> parameter) {
        MonitorDataInput record = (MonitorDataInput) parameter.get("record");
        MonitorDataInputExample example = (MonitorDataInputExample) parameter.get("example");
        
        SQL sql = new SQL();
        sql.UPDATE("monitor_data_input");
        
        if (record.getId() != null) {
            sql.SET("id = #{record.id,jdbcType=BIGINT}");
        }
        
        if (record.getBizNo() != null) {
            sql.SET("biz_no = #{record.bizNo,jdbcType=VARCHAR}");
        }
        
        if (record.getBizType() != null) {
            sql.SET("biz_type = #{record.bizType,jdbcType=VARCHAR}");
        }
        
        if (record.getApplication() != null) {
            sql.SET("application = #{record.application,jdbcType=VARCHAR}");
        }
        
        if (record.getApplicationDesc() != null) {
            sql.SET("application_desc = #{record.applicationDesc,jdbcType=VARCHAR}");
        }
        
        if (record.getIp() != null) {
            sql.SET("ip = #{record.ip,jdbcType=VARCHAR}");
        }
        
        if (record.getVersion() != null) {
            sql.SET("version = #{record.version,jdbcType=VARCHAR}");
        }
        
        if (record.getEnv() != null) {
            sql.SET("env = #{record.env,jdbcType=VARCHAR}");
        }
        
        if (record.getMethod() != null) {
            sql.SET("method = #{record.method,jdbcType=VARCHAR}");
        }
        
        if (record.getMethodDesc() != null) {
            sql.SET("method_desc = #{record.methodDesc,jdbcType=VARCHAR}");
        }
        
        if (record.getReportTime() != null) {
            sql.SET("report_time = #{record.reportTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getHeader() != null) {
            sql.SET("header = #{record.header,jdbcType=VARCHAR}");
        }
        
        if (record.getBody() != null) {
            sql.SET("body = #{record.body,jdbcType=VARCHAR}");
        }
        
        if (record.getCreateTime() != null) {
            sql.SET("create_time = #{record.createTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getCreator() != null) {
            sql.SET("creator = #{record.creator,jdbcType=VARCHAR}");
        }
        
        if (record.getModifyTime() != null) {
            sql.SET("modify_time = #{record.modifyTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getModifier() != null) {
            sql.SET("modifier = #{record.modifier,jdbcType=VARCHAR}");
        }
        
        if (record.getDeleted() != null) {
            sql.SET("deleted = #{record.deleted,jdbcType=BIGINT}");
        }
        
        if (record.getStatus() != null) {
            sql.SET("status = #{record.status,jdbcType=TINYINT}");
        }
        
        if (record.getToken() != null) {
            sql.SET("token = #{record.token,jdbcType=VARCHAR}");
        }
        
        applyWhere(sql, example, true);
        return sql.toString();
    }

    public String updateByExample(Map<String, Object> parameter) {
        SQL sql = new SQL();
        sql.UPDATE("monitor_data_input");
        
        sql.SET("id = #{record.id,jdbcType=BIGINT}");
        sql.SET("biz_no = #{record.bizNo,jdbcType=VARCHAR}");
        sql.SET("biz_type = #{record.bizType,jdbcType=VARCHAR}");
        sql.SET("application = #{record.application,jdbcType=VARCHAR}");
        sql.SET("application_desc = #{record.applicationDesc,jdbcType=VARCHAR}");
        sql.SET("ip = #{record.ip,jdbcType=VARCHAR}");
        sql.SET("version = #{record.version,jdbcType=VARCHAR}");
        sql.SET("env = #{record.env,jdbcType=VARCHAR}");
        sql.SET("method = #{record.method,jdbcType=VARCHAR}");
        sql.SET("method_desc = #{record.methodDesc,jdbcType=VARCHAR}");
        sql.SET("report_time = #{record.reportTime,jdbcType=TIMESTAMP}");
        sql.SET("header = #{record.header,jdbcType=VARCHAR}");
        sql.SET("body = #{record.body,jdbcType=VARCHAR}");
        sql.SET("create_time = #{record.createTime,jdbcType=TIMESTAMP}");
        sql.SET("creator = #{record.creator,jdbcType=VARCHAR}");
        sql.SET("modify_time = #{record.modifyTime,jdbcType=TIMESTAMP}");
        sql.SET("modifier = #{record.modifier,jdbcType=VARCHAR}");
        sql.SET("deleted = #{record.deleted,jdbcType=BIGINT}");
        sql.SET("status = #{record.status,jdbcType=TINYINT}");
        sql.SET("token = #{record.token,jdbcType=VARCHAR}");
        
        MonitorDataInputExample example = (MonitorDataInputExample) parameter.get("example");
        applyWhere(sql, example, true);
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(MonitorDataInput record) {
        SQL sql = new SQL();
        sql.UPDATE("monitor_data_input");
        
        if (record.getBizNo() != null) {
            sql.SET("biz_no = #{bizNo,jdbcType=VARCHAR}");
        }
        
        if (record.getBizType() != null) {
            sql.SET("biz_type = #{bizType,jdbcType=VARCHAR}");
        }
        
        if (record.getApplication() != null) {
            sql.SET("application = #{application,jdbcType=VARCHAR}");
        }
        
        if (record.getApplicationDesc() != null) {
            sql.SET("application_desc = #{applicationDesc,jdbcType=VARCHAR}");
        }
        
        if (record.getIp() != null) {
            sql.SET("ip = #{ip,jdbcType=VARCHAR}");
        }
        
        if (record.getVersion() != null) {
            sql.SET("version = #{version,jdbcType=VARCHAR}");
        }
        
        if (record.getEnv() != null) {
            sql.SET("env = #{env,jdbcType=VARCHAR}");
        }
        
        if (record.getMethod() != null) {
            sql.SET("method = #{method,jdbcType=VARCHAR}");
        }
        
        if (record.getMethodDesc() != null) {
            sql.SET("method_desc = #{methodDesc,jdbcType=VARCHAR}");
        }
        
        if (record.getReportTime() != null) {
            sql.SET("report_time = #{reportTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getHeader() != null) {
            sql.SET("header = #{header,jdbcType=VARCHAR}");
        }
        
        if (record.getBody() != null) {
            sql.SET("body = #{body,jdbcType=VARCHAR}");
        }
        
        if (record.getCreateTime() != null) {
            sql.SET("create_time = #{createTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getCreator() != null) {
            sql.SET("creator = #{creator,jdbcType=VARCHAR}");
        }
        
        if (record.getModifyTime() != null) {
            sql.SET("modify_time = #{modifyTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getModifier() != null) {
            sql.SET("modifier = #{modifier,jdbcType=VARCHAR}");
        }
        
        if (record.getDeleted() != null) {
            sql.SET("deleted = #{deleted,jdbcType=BIGINT}");
        }
        
        if (record.getStatus() != null) {
            sql.SET("status = #{status,jdbcType=TINYINT}");
        }
        
        if (record.getToken() != null) {
            sql.SET("token = #{token,jdbcType=VARCHAR}");
        }
        
        sql.WHERE("id = #{id,jdbcType=BIGINT}");
        
        return sql.toString();
    }

    protected void applyWhere(SQL sql, MonitorDataInputExample example, boolean includeExamplePhrase) {
        if (example == null) {
            return;
        }
        
        String parmPhrase1;
        String parmPhrase1_th;
        String parmPhrase2;
        String parmPhrase2_th;
        String parmPhrase3;
        String parmPhrase3_th;
        if (includeExamplePhrase) {
            parmPhrase1 = "%s #{example.oredCriteria[%d].allCriteria[%d].value}";
            parmPhrase1_th = "%s #{example.oredCriteria[%d].allCriteria[%d].value,typeHandler=%s}";
            parmPhrase2 = "%s #{example.oredCriteria[%d].allCriteria[%d].value} and #{example.oredCriteria[%d].criteria[%d].secondValue}";
            parmPhrase2_th = "%s #{example.oredCriteria[%d].allCriteria[%d].value,typeHandler=%s} and #{example.oredCriteria[%d].criteria[%d].secondValue,typeHandler=%s}";
            parmPhrase3 = "#{example.oredCriteria[%d].allCriteria[%d].value[%d]}";
            parmPhrase3_th = "#{example.oredCriteria[%d].allCriteria[%d].value[%d],typeHandler=%s}";
        } else {
            parmPhrase1 = "%s #{oredCriteria[%d].allCriteria[%d].value}";
            parmPhrase1_th = "%s #{oredCriteria[%d].allCriteria[%d].value,typeHandler=%s}";
            parmPhrase2 = "%s #{oredCriteria[%d].allCriteria[%d].value} and #{oredCriteria[%d].criteria[%d].secondValue}";
            parmPhrase2_th = "%s #{oredCriteria[%d].allCriteria[%d].value,typeHandler=%s} and #{oredCriteria[%d].criteria[%d].secondValue,typeHandler=%s}";
            parmPhrase3 = "#{oredCriteria[%d].allCriteria[%d].value[%d]}";
            parmPhrase3_th = "#{oredCriteria[%d].allCriteria[%d].value[%d],typeHandler=%s}";
        }
        
        StringBuilder sb = new StringBuilder();
        List<Criteria> oredCriteria = example.getOredCriteria();
        boolean firstCriteria = true;
        for (int i = 0; i < oredCriteria.size(); i++) {
            Criteria criteria = oredCriteria.get(i);
            if (criteria.isValid()) {
                if (firstCriteria) {
                    firstCriteria = false;
                } else {
                    sb.append(" or ");
                }
                
                sb.append('(');
                List<Criterion> criterions = criteria.getAllCriteria();
                boolean firstCriterion = true;
                for (int j = 0; j < criterions.size(); j++) {
                    Criterion criterion = criterions.get(j);
                    if (firstCriterion) {
                        firstCriterion = false;
                    } else {
                        sb.append(" and ");
                    }
                    
                    if (criterion.isNoValue()) {
                        sb.append(criterion.getCondition());
                    } else if (criterion.isSingleValue()) {
                        if (criterion.getTypeHandler() == null) {
                            sb.append(String.format(parmPhrase1, criterion.getCondition(), i, j));
                        } else {
                            sb.append(String.format(parmPhrase1_th, criterion.getCondition(), i, j,criterion.getTypeHandler()));
                        }
                    } else if (criterion.isBetweenValue()) {
                        if (criterion.getTypeHandler() == null) {
                            sb.append(String.format(parmPhrase2, criterion.getCondition(), i, j, i, j));
                        } else {
                            sb.append(String.format(parmPhrase2_th, criterion.getCondition(), i, j, criterion.getTypeHandler(), i, j, criterion.getTypeHandler()));
                        }
                    } else if (criterion.isListValue()) {
                        sb.append(criterion.getCondition());
                        sb.append(" (");
                        List<?> listItems = (List<?>) criterion.getValue();
                        boolean comma = false;
                        for (int k = 0; k < listItems.size(); k++) {
                            if (comma) {
                                sb.append(", ");
                            } else {
                                comma = true;
                            }
                            if (criterion.getTypeHandler() == null) {
                                sb.append(String.format(parmPhrase3, i, j, k));
                            } else {
                                sb.append(String.format(parmPhrase3_th, i, j, k, criterion.getTypeHandler()));
                            }
                        }
                        sb.append(')');
                    }
                }
                sb.append(')');
            }
        }
        
        if (sb.length() > 0) {
            sql.WHERE(sb.toString());
        }
    }
}