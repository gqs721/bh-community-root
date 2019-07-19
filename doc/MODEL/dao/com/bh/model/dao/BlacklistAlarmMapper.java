package com.bh.model.dao;

import com.bh.model.domain.BlacklistAlarm;

public interface BlacklistAlarmMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BlacklistAlarm record);

    int insertSelective(BlacklistAlarm record);

    BlacklistAlarm selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BlacklistAlarm record);

    int updateByPrimaryKey(BlacklistAlarm record);
}