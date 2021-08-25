package com.vendixxx.monitor.common;

import com.google.common.collect.Lists;

import java.util.List;

public class PageResult<T> {

    /**
     * 页码
     */
    private Integer pageNum;

    /**
     * 每页数量
     */
    private Integer pageSize;

    /**
     * 列表
     */
    private List<T> currentList;

    /**
     * 总数
     */
    private Long total;

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public List<T> getCurrentList() {
        return currentList;
    }

    public void setCurrentList(List<T> currentList) {
        this.currentList = currentList;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public static PageResult emptyPage(Integer pageNum,Integer pageSize){
        PageResult result = new PageResult();
        result.setTotal(0L);
        result.setPageSize(pageSize);
        result.setPageNum(pageNum);
        result.setCurrentList(Lists.newArrayList());
        return result;
    }
}
