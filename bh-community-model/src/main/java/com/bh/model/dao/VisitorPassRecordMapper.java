package com.bh.model.dao;

import com.bh.model.domain.VisitorPassRecord;

import java.util.List;
import java.util.Map;

public interface VisitorPassRecordMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(VisitorPassRecord record);

    int insertSelective(VisitorPassRecord record);

    VisitorPassRecord selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(VisitorPassRecord record);

    int updateByPrimaryKey(VisitorPassRecord record);

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