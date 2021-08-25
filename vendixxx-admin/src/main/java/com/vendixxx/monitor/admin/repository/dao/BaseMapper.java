package com.vendixxx.monitor.admin.repository.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface BaseMapper<T,E> {

    long countByExample(E example);

    int deleteByExample(E example);
    
    int deleteByPrimaryKey(Long id);
    
    int insert(T record);

    int insertSelective(T record);
    
    List<T> selectByExample(E example);

    List<T> selectByExamplePage(E example, Integer startR, Integer pageS);

    T selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") T record, @Param("example") E example);

    int updateByExample(@Param("record") T record, @Param("example") E example);

    int updateByPrimaryKeySelective(T record);

    int updateByPrimaryKey(T record);
}
