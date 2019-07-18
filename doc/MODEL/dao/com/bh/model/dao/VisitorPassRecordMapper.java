package com.bh.model.dao;

import com.bh.model.domain.VisitorPassRecord;

public interface VisitorPassRecordMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(VisitorPassRecord record);

    int insertSelective(VisitorPassRecord record);

    VisitorPassRecord selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(VisitorPassRecord record);

    int updateByPrimaryKey(VisitorPassRecord record);
}