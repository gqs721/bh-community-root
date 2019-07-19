package com.bh.model.dao;

import com.bh.model.domain.MonitorAids;

import java.util.List;
import java.util.Map;

public interface MonitorAidsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(MonitorAids record);

    int insertSelective(MonitorAids record);

    MonitorAids selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MonitorAids record);

    int updateByPrimaryKey(MonitorAids record);

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