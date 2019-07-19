package com.bh.model.dao;

import com.bh.model.domain.MonitorAids;

public interface MonitorAidsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(MonitorAids record);

    int insertSelective(MonitorAids record);

    MonitorAids selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MonitorAids record);

    int updateByPrimaryKey(MonitorAids record);
}