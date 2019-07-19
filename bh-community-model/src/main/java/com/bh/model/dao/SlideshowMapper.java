package com.bh.model.dao;

import com.bh.model.domain.Slideshow;

import java.util.List;
import java.util.Map;

public interface SlideshowMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Slideshow record);

    int insertSelective(Slideshow record);

    Slideshow selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Slideshow record);

    int updateByPrimaryKey(Slideshow record);

    /**
     * 批量删除
     * @param ids
     * @return
     */
    int deleteBatch(Integer[] ids);


    /**
     * 列表
     * @param queryMap
     * @return
     */
    List<Map> listPage(Map queryMap);

    /**
     * 统计
     * @param queryMap
     * @return
     */
    Integer countPage(Map queryMap);
}