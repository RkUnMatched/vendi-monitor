package com.vendixxx.monitor.common;

import com.alibaba.fastjson.annotation.JSONField;

public class PageQuery {

    public PageQuery() {
        this.page = 1;
        this.size = 20;
    }

    public PageQuery(int page, int size) {
        if (page <= 0) {
            page = 1;
        }
        if (size <= 0) {
            size = 20;
        }
        if (size > 5000) {
            size = 1000;
        }
        this.page = page;
        this.size = size;
    }

    int page = 1;
    int size = 20;

    public int getPage() {
        return page;
    }

    public int getSize() {
        return size;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public void setSize(int size) {
        this.size = size;
    }

    @JSONField(serialize = false)
    public int getOffset() {
        if (page > 1) {
            return (page - 1) * size;
        }
        return size;
    }
}