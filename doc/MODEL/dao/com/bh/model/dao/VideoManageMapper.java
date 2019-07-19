package com.bh.model.dao;

import com.bh.model.domain.VideoManage;

public interface VideoManageMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(VideoManage record);

    int insertSelective(VideoManage record);

    VideoManage selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(VideoManage record);

    int updateByPrimaryKey(VideoManage record);
}