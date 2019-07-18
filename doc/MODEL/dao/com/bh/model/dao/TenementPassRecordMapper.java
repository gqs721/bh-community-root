package com.bh.model.dao;

import com.bh.model.domain.TenementPassRecord;

public interface TenementPassRecordMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TenementPassRecord record);

    int insertSelective(TenementPassRecord record);

    TenementPassRecord selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TenementPassRecord record);

    int updateByPrimaryKey(TenementPassRecord record);
}