package com.bh.model.dao;

import com.bh.model.domain.BlacklistAlarm;

import java.util.List;
import java.util.Map;

public interface BlacklistAlarmMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BlacklistAlarm record);

    int insertSelective(BlacklistAlarm record);

    BlacklistAlarm selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BlacklistAlarm record);

    int updateByPrimaryKey(BlacklistAlarm record);

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