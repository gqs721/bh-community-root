package com.bh.model.dao;

import com.bh.model.domain.VideoManage;

import java.util.List;
import java.util.Map;

public interface VideoManageMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(VideoManage record);

    int insertSelective(VideoManage record);

    VideoManage selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(VideoManage record);

    int updateByPrimaryKey(VideoManage record);

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