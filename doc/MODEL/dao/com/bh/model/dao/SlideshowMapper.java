package com.bh.model.dao;

import com.bh.model.domain.Slideshow;

public interface SlideshowMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Slideshow record);

    int insertSelective(Slideshow record);

    Slideshow selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Slideshow record);

    int updateByPrimaryKey(Slideshow record);
}