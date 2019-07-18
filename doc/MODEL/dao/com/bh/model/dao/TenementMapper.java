package com.bh.model.dao;

import com.bh.model.domain.Tenement;

public interface TenementMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Tenement record);

    int insertSelective(Tenement record);

    Tenement selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Tenement record);

    int updateByPrimaryKey(Tenement record);
}