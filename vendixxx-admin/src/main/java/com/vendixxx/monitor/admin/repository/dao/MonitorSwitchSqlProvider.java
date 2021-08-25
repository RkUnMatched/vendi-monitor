package com.vendixxx.monitor.admin.repository.dao;

import com.vendixxx.monitor.admin.repository.bean.MonitorSwitch;
import com.vendixxx.monitor.admin.repository.bean.MonitorSwitchExample;
import com.vendixxx.monitor.admin.repository.bean.MonitorSwitchExample.Criteria;
import com.vendixxx.monitor.admin.repository.bean.MonitorSwitchExample.Criterion;
import org.apache.ibatis.jdbc.SQL;

import java.util.List;
import java.util.Map;

public class MonitorSwitchSqlProvider {

    public String countByExample(MonitorSwitchExample example) {
        SQL sql = new SQL();
        sql.SELECT("count(*)").FROM("monitor_switch");
        applyWhere(sql, example, false);
        return sql.toString();
    }

    public String deleteByExample(MonitorSwitchExample example) {
        SQL sql = new SQL();
        sql.DELETE_FROM("monitor_switch");
        applyWhere(sql, example, false);
        return sql.toString();
    }

    public String insertSelective(MonitorSwitch record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("monitor_switch");
        
        if (record.getId() != null) {
            sql.VALUES("id", "#{id,jdbcType=BIGINT}");
        }
        
        if (record.getToken() != null) {
            sql.VALUES("token", "#{token,jdbcType=VARCHAR}");
        }
        
        if (record.getApplication() != null) {
            sql.VALUES("application", "#{application,jdbcType=VARCHAR}");
        }
        
        if (record.getIp() != null) {
            sql.VALUES("ip", "#{ip,jdbcType=VARCHAR}");
        }
        
        if (record.getMethod() != null) {
            sql.VALUES("method", "#{method,jdbcType=VARCHAR}");
        }
        
        if (record.getCanReport() != null) {
            sql.VALUES("can_report", "#{canReport,jdbcType=TINYINT}");
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
        
        return sql.toString();
    }

    public String selectByExample(MonitorSwitchExample example) {
        SQL sql = new SQL();
        if (example != null && example.isDistinct()) {
            sql.SELECT_DISTINCT("id");
        } else {
            sql.SELECT("id");
        }
        sql.SELECT("token");
        sql.SELECT("application");
        sql.SELECT("ip");
        sql.SELECT("method");
        sql.SELECT("can_report");
        sql.SELECT("create_time");
        sql.SELECT("creator");
        sql.SELECT("modify_time");
        sql.SELECT("modifier");
        sql.SELECT("deleted");
        sql.FROM("monitor_switch");
        applyWhere(sql, example, false);
        
        if (example != null && example.getOrderByClause() != null) {
            sql.ORDER_BY(example.getOrderByClause());
        }
        
        return sql.toString();
    }

    public String updateByExampleSelective(Map<String, Object> parameter) {
        MonitorSwitch record = (MonitorSwitch) parameter.get("record");
        MonitorSwitchExample example = (MonitorSwitchExample) parameter.get("example");
        
        SQL sql = new SQL();
        sql.UPDATE("monitor_switch");
        
        if (record.getId() != null) {
            sql.SET("id = #{record.id,jdbcType=BIGINT}");
        }
        
        if (record.getToken() != null) {
            sql.SET("token = #{record.token,jdbcType=VARCHAR}");
        }
        
        if (record.getApplication() != null) {
            sql.SET("application = #{record.application,jdbcType=VARCHAR}");
        }
        
        if (record.getIp() != null) {
            sql.SET("ip = #{record.ip,jdbcType=VARCHAR}");
        }
        
        if (record.getMethod() != null) {
            sql.SET("method = #{record.method,jdbcType=VARCHAR}");
        }
        
        if (record.getCanReport() != null) {
            sql.SET("can_report = #{record.canReport,jdbcType=TINYINT}");
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
        
        applyWhere(sql, example, true);
        return sql.toString();
    }

    public String updateByExample(Map<String, Object> parameter) {
        SQL sql = new SQL();
        sql.UPDATE("monitor_switch");
        
        sql.SET("id = #{record.id,jdbcType=BIGINT}");
        sql.SET("token = #{record.token,jdbcType=VARCHAR}");
        sql.SET("application = #{record.application,jdbcType=VARCHAR}");
        sql.SET("ip = #{record.ip,jdbcType=VARCHAR}");
        sql.SET("method = #{record.method,jdbcType=VARCHAR}");
        sql.SET("can_report = #{record.canReport,jdbcType=TINYINT}");
        sql.SET("create_time = #{record.createTime,jdbcType=TIMESTAMP}");
        sql.SET("creator = #{record.creator,jdbcType=VARCHAR}");
        sql.SET("modify_time = #{record.modifyTime,jdbcType=TIMESTAMP}");
        sql.SET("modifier = #{record.modifier,jdbcType=VARCHAR}");
        sql.SET("deleted = #{record.deleted,jdbcType=BIGINT}");
        
        MonitorSwitchExample example = (MonitorSwitchExample) parameter.get("example");
        applyWhere(sql, example, true);
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(MonitorSwitch record) {
        SQL sql = new SQL();
        sql.UPDATE("monitor_switch");
        
        if (record.getToken() != null) {
            sql.SET("token = #{token,jdbcType=VARCHAR}");
        }
        
        if (record.getApplication() != null) {
            sql.SET("application = #{application,jdbcType=VARCHAR}");
        }
        
        if (record.getIp() != null) {
            sql.SET("ip = #{ip,jdbcType=VARCHAR}");
        }
        
        if (record.getMethod() != null) {
            sql.SET("method = #{method,jdbcType=VARCHAR}");
        }
        
        if (record.getCanReport() != null) {
            sql.SET("can_report = #{canReport,jdbcType=TINYINT}");
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
        
        sql.WHERE("id = #{id,jdbcType=BIGINT}");
        
        return sql.toString();
    }

    protected void applyWhere(SQL sql, MonitorSwitchExample example, boolean includeExamplePhrase) {
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