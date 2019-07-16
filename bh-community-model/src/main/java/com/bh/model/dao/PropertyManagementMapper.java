package com.bh.model.dao;

import com.bh.model.domain.PropertyManagement;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

public interface PropertyManagementMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(PropertyManagement record);

    int insertSelective(PropertyManagement record);

    PropertyManagement selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PropertyManagement record);

    int updateByPrimaryKey(PropertyManagement record);

    /**
     * 所有物业信息列表
     * @return
     */
    List<PropertyManagement> getAll();
}