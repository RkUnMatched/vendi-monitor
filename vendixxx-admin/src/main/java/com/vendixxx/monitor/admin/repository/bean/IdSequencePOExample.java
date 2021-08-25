package com.vendixxx.monitor.admin.repository.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class IdSequencePOExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public IdSequencePOExample() {
        oredCriteria = new ArrayList<>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Long value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Long value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Long value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Long value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Long value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Long> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Long> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Long value1, Long value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Long value1, Long value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andSeqValueIsNull() {
            addCriterion("seq_value is null");
            return (Criteria) this;
        }

        public Criteria andSeqValueIsNotNull() {
            addCriterion("seq_value is not null");
            return (Criteria) this;
        }

        public Criteria andSeqValueEqualTo(Long value) {
            addCriterion("seq_value =", value, "seqValue");
            return (Criteria) this;
        }

        public Criteria andSeqValueNotEqualTo(Long value) {
            addCriterion("seq_value <>", value, "seqValue");
            return (Criteria) this;
        }

        public Criteria andSeqValueGreaterThan(Long value) {
            addCriterion("seq_value >", value, "seqValue");
            return (Criteria) this;
        }

        public Criteria andSeqValueGreaterThanOrEqualTo(Long value) {
            addCriterion("seq_value >=", value, "seqValue");
            return (Criteria) this;
        }

        public Criteria andSeqValueLessThan(Long value) {
            addCriterion("seq_value <", value, "seqValue");
            return (Criteria) this;
        }

        public Criteria andSeqValueLessThanOrEqualTo(Long value) {
            addCriterion("seq_value <=", value, "seqValue");
            return (Criteria) this;
        }

        public Criteria andSeqValueIn(List<Long> values) {
            addCriterion("seq_value in", values, "seqValue");
            return (Criteria) this;
        }

        public Criteria andSeqValueNotIn(List<Long> values) {
            addCriterion("seq_value not in", values, "seqValue");
            return (Criteria) this;
        }

        public Criteria andSeqValueBetween(Long value1, Long value2) {
            addCriterion("seq_value between", value1, value2, "seqValue");
            return (Criteria) this;
        }

        public Criteria andSeqValueNotBetween(Long value1, Long value2) {
            addCriterion("seq_value not between", value1, value2, "seqValue");
            return (Criteria) this;
        }

        public Criteria andSeqTypeIsNull() {
            addCriterion("seq_type is null");
            return (Criteria) this;
        }

        public Criteria andSeqTypeIsNotNull() {
            addCriterion("seq_type is not null");
            return (Criteria) this;
        }

        public Criteria andSeqTypeEqualTo(String value) {
            addCriterion("seq_type =", value, "seqType");
            return (Criteria) this;
        }

        public Criteria andSeqTypeNotEqualTo(String value) {
            addCriterion("seq_type <>", value, "seqType");
            return (Criteria) this;
        }

        public Criteria andSeqTypeGreaterThan(String value) {
            addCriterion("seq_type >", value, "seqType");
            return (Criteria) this;
        }

        public Criteria andSeqTypeGreaterThanOrEqualTo(String value) {
            addCriterion("seq_type >=", value, "seqType");
            return (Criteria) this;
        }

        public Criteria andSeqTypeLessThan(String value) {
            addCriterion("seq_type <", value, "seqType");
            return (Criteria) this;
        }

        public Criteria andSeqTypeLessThanOrEqualTo(String value) {
            addCriterion("seq_type <=", value, "seqType");
            return (Criteria) this;
        }

        public Criteria andSeqTypeLike(String value) {
            addCriterion("seq_type like", value, "seqType");
            return (Criteria) this;
        }

        public Criteria andSeqTypeNotLike(String value) {
            addCriterion("seq_type not like", value, "seqType");
            return (Criteria) this;
        }

        public Criteria andSeqTypeIn(List<String> values) {
            addCriterion("seq_type in", values, "seqType");
            return (Criteria) this;
        }

        public Criteria andSeqTypeNotIn(List<String> values) {
            addCriterion("seq_type not in", values, "seqType");
            return (Criteria) this;
        }

        public Criteria andSeqTypeBetween(String value1, String value2) {
            addCriterion("seq_type between", value1, value2, "seqType");
            return (Criteria) this;
        }

        public Criteria andSeqTypeNotBetween(String value1, String value2) {
            addCriterion("seq_type not between", value1, value2, "seqType");
            return (Criteria) this;
        }

        public Criteria andSeqSubtypeIsNull() {
            addCriterion("seq_subtype is null");
            return (Criteria) this;
        }

        public Criteria andSeqSubtypeIsNotNull() {
            addCriterion("seq_subtype is not null");
            return (Criteria) this;
        }

        public Criteria andSeqSubtypeEqualTo(String value) {
            addCriterion("seq_subtype =", value, "seqSubtype");
            return (Criteria) this;
        }

        public Criteria andSeqSubtypeNotEqualTo(String value) {
            addCriterion("seq_subtype <>", value, "seqSubtype");
            return (Criteria) this;
        }

        public Criteria andSeqSubtypeGreaterThan(String value) {
            addCriterion("seq_subtype >", value, "seqSubtype");
            return (Criteria) this;
        }

        public Criteria andSeqSubtypeGreaterThanOrEqualTo(String value) {
            addCriterion("seq_subtype >=", value, "seqSubtype");
            return (Criteria) this;
        }

        public Criteria andSeqSubtypeLessThan(String value) {
            addCriterion("seq_subtype <", value, "seqSubtype");
            return (Criteria) this;
        }

        public Criteria andSeqSubtypeLessThanOrEqualTo(String value) {
            addCriterion("seq_subtype <=", value, "seqSubtype");
            return (Criteria) this;
        }

        public Criteria andSeqSubtypeLike(String value) {
            addCriterion("seq_subtype like", value, "seqSubtype");
            return (Criteria) this;
        }

        public Criteria andSeqSubtypeNotLike(String value) {
            addCriterion("seq_subtype not like", value, "seqSubtype");
            return (Criteria) this;
        }

        public Criteria andSeqSubtypeIn(List<String> values) {
            addCriterion("seq_subtype in", values, "seqSubtype");
            return (Criteria) this;
        }

        public Criteria andSeqSubtypeNotIn(List<String> values) {
            addCriterion("seq_subtype not in", values, "seqSubtype");
            return (Criteria) this;
        }

        public Criteria andSeqSubtypeBetween(String value1, String value2) {
            addCriterion("seq_subtype between", value1, value2, "seqSubtype");
            return (Criteria) this;
        }

        public Criteria andSeqSubtypeNotBetween(String value1, String value2) {
            addCriterion("seq_subtype not between", value1, value2, "seqSubtype");
            return (Criteria) this;
        }

        public Criteria andGmtCreateIsNull() {
            addCriterion("gmt_create is null");
            return (Criteria) this;
        }

        public Criteria andGmtCreateIsNotNull() {
            addCriterion("gmt_create is not null");
            return (Criteria) this;
        }

        public Criteria andGmtCreateEqualTo(Date value) {
            addCriterion("gmt_create =", value, "gmtCreate");
            return (Criteria) this;
        }

        public Criteria andGmtCreateNotEqualTo(Date value) {
            addCriterion("gmt_create <>", value, "gmtCreate");
            return (Criteria) this;
        }

        public Criteria andGmtCreateGreaterThan(Date value) {
            addCriterion("gmt_create >", value, "gmtCreate");
            return (Criteria) this;
        }

        public Criteria andGmtCreateGreaterThanOrEqualTo(Date value) {
            addCriterion("gmt_create >=", value, "gmtCreate");
            return (Criteria) this;
        }

        public Criteria andGmtCreateLessThan(Date value) {
            addCriterion("gmt_create <", value, "gmtCreate");
            return (Criteria) this;
        }

        public Criteria andGmtCreateLessThanOrEqualTo(Date value) {
            addCriterion("gmt_create <=", value, "gmtCreate");
            return (Criteria) this;
        }

        public Criteria andGmtCreateIn(List<Date> values) {
            addCriterion("gmt_create in", values, "gmtCreate");
            return (Criteria) this;
        }

        public Criteria andGmtCreateNotIn(List<Date> values) {
            addCriterion("gmt_create not in", values, "gmtCreate");
            return (Criteria) this;
        }

        public Criteria andGmtCreateBetween(Date value1, Date value2) {
            addCriterion("gmt_create between", value1, value2, "gmtCreate");
            return (Criteria) this;
        }

        public Criteria andGmtCreateNotBetween(Date value1, Date value2) {
            addCriterion("gmt_create not between", value1, value2, "gmtCreate");
            return (Criteria) this;
        }

        public Criteria andCreatorIsNull() {
            addCriterion("creator is null");
            return (Criteria) this;
        }

        public Criteria andCreatorIsNotNull() {
            addCriterion("creator is not null");
            return (Criteria) this;
        }

        public Criteria andCreatorEqualTo(String value) {
            addCriterion("creator =", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorNotEqualTo(String value) {
            addCriterion("creator <>", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorGreaterThan(String value) {
            addCriterion("creator >", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorGreaterThanOrEqualTo(String value) {
            addCriterion("creator >=", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorLessThan(String value) {
            addCriterion("creator <", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorLessThanOrEqualTo(String value) {
            addCriterion("creator <=", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorLike(String value) {
            addCriterion("creator like", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorNotLike(String value) {
            addCriterion("creator not like", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorIn(List<String> values) {
            addCriterion("creator in", values, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorNotIn(List<String> values) {
            addCriterion("creator not in", values, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorBetween(String value1, String value2) {
            addCriterion("creator between", value1, value2, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorNotBetween(String value1, String value2) {
            addCriterion("creator not between", value1, value2, "creator");
            return (Criteria) this;
        }

        public Criteria andGmtModifyIsNull() {
            addCriterion("gmt_modify is null");
            return (Criteria) this;
        }

        public Criteria andGmtModifyIsNotNull() {
            addCriterion("gmt_modify is not null");
            return (Criteria) this;
        }

        public Criteria andGmtModifyEqualTo(Date value) {
            addCriterion("gmt_modify =", value, "gmtModify");
            return (Criteria) this;
        }

        public Criteria andGmtModifyNotEqualTo(Date value) {
            addCriterion("gmt_modify <>", value, "gmtModify");
            return (Criteria) this;
        }

        public Criteria andGmtModifyGreaterThan(Date value) {
            addCriterion("gmt_modify >", value, "gmtModify");
            return (Criteria) this;
        }

        public Criteria andGmtModifyGreaterThanOrEqualTo(Date value) {
            addCriterion("gmt_modify >=", value, "gmtModify");
            return (Criteria) this;
        }

        public Criteria andGmtModifyLessThan(Date value) {
            addCriterion("gmt_modify <", value, "gmtModify");
            return (Criteria) this;
        }

        public Criteria andGmtModifyLessThanOrEqualTo(Date value) {
            addCriterion("gmt_modify <=", value, "gmtModify");
            return (Criteria) this;
        }

        public Criteria andGmtModifyIn(List<Date> values) {
            addCriterion("gmt_modify in", values, "gmtModify");
            return (Criteria) this;
        }

        public Criteria andGmtModifyNotIn(List<Date> values) {
            addCriterion("gmt_modify not in", values, "gmtModify");
            return (Criteria) this;
        }

        public Criteria andGmtModifyBetween(Date value1, Date value2) {
            addCriterion("gmt_modify between", value1, value2, "gmtModify");
            return (Criteria) this;
        }

        public Criteria andGmtModifyNotBetween(Date value1, Date value2) {
            addCriterion("gmt_modify not between", value1, value2, "gmtModify");
            return (Criteria) this;
        }

        public Criteria andModifierIsNull() {
            addCriterion("modifier is null");
            return (Criteria) this;
        }

        public Criteria andModifierIsNotNull() {
            addCriterion("modifier is not null");
            return (Criteria) this;
        }

        public Criteria andModifierEqualTo(String value) {
            addCriterion("modifier =", value, "modifier");
            return (Criteria) this;
        }

        public Criteria andModifierNotEqualTo(String value) {
            addCriterion("modifier <>", value, "modifier");
            return (Criteria) this;
        }

        public Criteria andModifierGreaterThan(String value) {
            addCriterion("modifier >", value, "modifier");
            return (Criteria) this;
        }

        public Criteria andModifierGreaterThanOrEqualTo(String value) {
            addCriterion("modifier >=", value, "modifier");
            return (Criteria) this;
        }

        public Criteria andModifierLessThan(String value) {
            addCriterion("modifier <", value, "modifier");
            return (Criteria) this;
        }

        public Criteria andModifierLessThanOrEqualTo(String value) {
            addCriterion("modifier <=", value, "modifier");
            return (Criteria) this;
        }

        public Criteria andModifierLike(String value) {
            addCriterion("modifier like", value, "modifier");
            return (Criteria) this;
        }

        public Criteria andModifierNotLike(String value) {
            addCriterion("modifier not like", value, "modifier");
            return (Criteria) this;
        }

        public Criteria andModifierIn(List<String> values) {
            addCriterion("modifier in", values, "modifier");
            return (Criteria) this;
        }

        public Criteria andModifierNotIn(List<String> values) {
            addCriterion("modifier not in", values, "modifier");
            return (Criteria) this;
        }

        public Criteria andModifierBetween(String value1, String value2) {
            addCriterion("modifier between", value1, value2, "modifier");
            return (Criteria) this;
        }

        public Criteria andModifierNotBetween(String value1, String value2) {
            addCriterion("modifier not between", value1, value2, "modifier");
            return (Criteria) this;
        }

        public Criteria andExtraIsNull() {
            addCriterion("extra is null");
            return (Criteria) this;
        }

        public Criteria andExtraIsNotNull() {
            addCriterion("extra is not null");
            return (Criteria) this;
        }

        public Criteria andExtraEqualTo(String value) {
            addCriterion("extra =", value, "extra");
            return (Criteria) this;
        }

        public Criteria andExtraNotEqualTo(String value) {
            addCriterion("extra <>", value, "extra");
            return (Criteria) this;
        }

        public Criteria andExtraGreaterThan(String value) {
            addCriterion("extra >", value, "extra");
            return (Criteria) this;
        }

        public Criteria andExtraGreaterThanOrEqualTo(String value) {
            addCriterion("extra >=", value, "extra");
            return (Criteria) this;
        }

        public Criteria andExtraLessThan(String value) {
            addCriterion("extra <", value, "extra");
            return (Criteria) this;
        }

        public Criteria andExtraLessThanOrEqualTo(String value) {
            addCriterion("extra <=", value, "extra");
            return (Criteria) this;
        }

        public Criteria andExtraLike(String value) {
            addCriterion("extra like", value, "extra");
            return (Criteria) this;
        }

        public Criteria andExtraNotLike(String value) {
            addCriterion("extra not like", value, "extra");
            return (Criteria) this;
        }

        public Criteria andExtraIn(List<String> values) {
            addCriterion("extra in", values, "extra");
            return (Criteria) this;
        }

        public Criteria andExtraNotIn(List<String> values) {
            addCriterion("extra not in", values, "extra");
            return (Criteria) this;
        }

        public Criteria andExtraBetween(String value1, String value2) {
            addCriterion("extra between", value1, value2, "extra");
            return (Criteria) this;
        }

        public Criteria andExtraNotBetween(String value1, String value2) {
            addCriterion("extra not between", value1, value2, "extra");
            return (Criteria) this;
        }

        public Criteria andControlNumIsNull() {
            addCriterion("control_num is null");
            return (Criteria) this;
        }

        public Criteria andControlNumIsNotNull() {
            addCriterion("control_num is not null");
            return (Criteria) this;
        }

        public Criteria andControlNumEqualTo(Long value) {
            addCriterion("control_num =", value, "controlNum");
            return (Criteria) this;
        }

        public Criteria andControlNumNotEqualTo(Long value) {
            addCriterion("control_num <>", value, "controlNum");
            return (Criteria) this;
        }

        public Criteria andControlNumGreaterThan(Long value) {
            addCriterion("control_num >", value, "controlNum");
            return (Criteria) this;
        }

        public Criteria andControlNumGreaterThanOrEqualTo(Long value) {
            addCriterion("control_num >=", value, "controlNum");
            return (Criteria) this;
        }

        public Criteria andControlNumLessThan(Long value) {
            addCriterion("control_num <", value, "controlNum");
            return (Criteria) this;
        }

        public Criteria andControlNumLessThanOrEqualTo(Long value) {
            addCriterion("control_num <=", value, "controlNum");
            return (Criteria) this;
        }

        public Criteria andControlNumIn(List<Long> values) {
            addCriterion("control_num in", values, "controlNum");
            return (Criteria) this;
        }

        public Criteria andControlNumNotIn(List<Long> values) {
            addCriterion("control_num not in", values, "controlNum");
            return (Criteria) this;
        }

        public Criteria andControlNumBetween(Long value1, Long value2) {
            addCriterion("control_num between", value1, value2, "controlNum");
            return (Criteria) this;
        }

        public Criteria andControlNumNotBetween(Long value1, Long value2) {
            addCriterion("control_num not between", value1, value2, "controlNum");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {
        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}