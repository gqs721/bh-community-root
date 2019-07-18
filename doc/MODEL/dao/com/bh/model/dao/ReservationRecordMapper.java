package com.bh.model.dao;

import com.bh.model.domain.ReservationRecord;

public interface ReservationRecordMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ReservationRecord record);

    int insertSelective(ReservationRecord record);

    ReservationRecord selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ReservationRecord record);

    int updateByPrimaryKey(ReservationRecord record);
}