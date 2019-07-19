package com.bh.model.dao;

import com.bh.model.domain.RosterLibrary;

public interface RosterLibraryMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(RosterLibrary record);

    int insertSelective(RosterLibrary record);

    RosterLibrary selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RosterLibrary record);

    int updateByPrimaryKey(RosterLibrary record);
}