package com.bh.model.dao;

import com.bh.model.domain.PropertyManagement;

public interface PropertyManagementMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(PropertyManagement record);

    int insertSelective(PropertyManagement record);

    PropertyManagement selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PropertyManagement record);

    int updateByPrimaryKey(PropertyManagement record);
}