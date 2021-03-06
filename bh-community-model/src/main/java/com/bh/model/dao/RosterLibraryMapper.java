package com.bh.model.dao;

import com.bh.model.domain.RosterLibrary;

import java.util.List;
import java.util.Map;

public interface RosterLibraryMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(RosterLibrary record);

    int insertSelective(RosterLibrary record);

    RosterLibrary selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RosterLibrary record);

    int updateByPrimaryKey(RosterLibrary record);

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