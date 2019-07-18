package com.bh.model.dao;

import com.bh.model.domain.TenementPassRecord;

import java.util.List;
import java.util.Map;

public interface TenementPassRecordMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TenementPassRecord record);

    int insertSelective(TenementPassRecord record);

    TenementPassRecord selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TenementPassRecord record);

    int updateByPrimaryKey(TenementPassRecord record);

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