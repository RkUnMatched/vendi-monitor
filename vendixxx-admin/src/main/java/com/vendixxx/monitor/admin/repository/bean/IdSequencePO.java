package com.vendixxx.monitor.admin.repository.bean;

import java.util.Date;

public class IdSequencePO {
    private Long id;

    private Long seqValue;

    private String seqType;

    private String seqSubtype;

    private Date gmtCreate;

    private String creator;

    private Date gmtModify;

    private String modifier;

    private String extra;

    private Long controlNum;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSeqValue() {
        return seqValue;
    }

    public void setSeqValue(Long seqValue) {
        this.seqValue = seqValue;
    }

    public String getSeqType() {
        return seqType;
    }

    public void setSeqType(String seqType) {
        this.seqType = seqType == null ? null : seqType.trim();
    }

    public String getSeqSubtype() {
        return seqSubtype;
    }

    public void setSeqSubtype(String seqSubtype) {
        this.seqSubtype = seqSubtype == null ? null : seqSubtype.trim();
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator == null ? null : creator.trim();
    }

    public Date getGmtModify() {
        return gmtModify;
    }

    public void setGmtModify(Date gmtModify) {
        this.gmtModify = gmtModify;
    }

    public String getModifier() {
        return modifier;
    }

    public void setModifier(String modifier) {
        this.modifier = modifier == null ? null : modifier.trim();
    }

    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra == null ? null : extra.trim();
    }

    public Long getControlNum() {
        return controlNum;
    }

    public void setControlNum(Long controlNum) {
        this.controlNum = controlNum;
    }
}