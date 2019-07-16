package com.bh.system.service;

import com.bh.common.result.RestResult;
import com.bh.model.domain.Admin;
import com.bh.model.domain.Community;

import java.util.List;
import java.util.Map;

/**
 * Created by Mr.BH
 */
public interface CommunityService {


    /**
     * 添加小区
     * @param community
     * @return
     */
    RestResult saveCommunity(Community community);


    /**
     * 根据 pkId  删除小区
     * @param ids
     * @return
     */
    RestResult deleteCommunity(List<Integer> ids);


    /**
     * 修改 小区信息
     * @param community
     * @return
     */
    RestResult updateCommunity(Community community);


    /**
     * 获取小区信息  根据主键ID
     * @param id
     * @return
     */
    RestResult getCommunity(Integer id);


    /**
     * 小区列表
     * @param keyword
     * @param pageNum
     * @param pageSize
     * @return
     */
    RestResult listCommunity(String keyword, Integer pageNum, Integer pageSize);


    /**
     * 修改小区状态
     * @param id
     * @return
     */
    RestResult setStatus(Integer id);


    /**
     * 通过物业id获取所有的小区信息
     * @param pmId
     * @return
     */
    RestResult findListByPmId(Integer pmId);

}
