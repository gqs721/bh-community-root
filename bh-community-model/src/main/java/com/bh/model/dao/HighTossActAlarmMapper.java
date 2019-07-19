package com.bh.model.dao;

import com.bh.model.domain.HighTossActAlarm;

import java.util.List;
import java.util.Map;

public interface HighTossActAlarmMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(HighTossActAlarm record);

    int insertSelective(HighTossActAlarm record);

    HighTossActAlarm selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(HighTossActAlarm record);

    int updateByPrimaryKey(HighTossActAlarm record);

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