package com.bh.model.dao;

import com.bh.model.domain.Generator;
import com.bh.model.domain.Generator;

public interface GeneratorMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Generator record);

    int insertSelective(Generator record);
}