package com.bh.model.dao;

import com.bh.model.domain.HazardousAreaAlarm;

import java.util.List;
import java.util.Map;

public interface HazardousAreaAlarmMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(HazardousAreaAlarm record);

    int insertSelective(HazardousAreaAlarm record);

    HazardousAreaAlarm selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(HazardousAreaAlarm record);

    int updateByPrimaryKey(HazardousAreaAlarm record);

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