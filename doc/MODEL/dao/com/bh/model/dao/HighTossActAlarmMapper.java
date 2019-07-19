package com.bh.model.dao;

import com.bh.model.domain.HighTossActAlarm;

public interface HighTossActAlarmMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(HighTossActAlarm record);

    int insertSelective(HighTossActAlarm record);

    HighTossActAlarm selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(HighTossActAlarm record);

    int updateByPrimaryKey(HighTossActAlarm record);
}