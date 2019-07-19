package com.bh.model.dao;

import com.bh.model.domain.HazardousAreaAlarm;

public interface HazardousAreaAlarmMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(HazardousAreaAlarm record);

    int insertSelective(HazardousAreaAlarm record);

    HazardousAreaAlarm selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(HazardousAreaAlarm record);

    int updateByPrimaryKey(HazardousAreaAlarm record);
}