package com.bh.system.service;

import com.bh.common.result.RestResult;
import com.bh.model.domain.Building;
import com.bh.model.domain.Community;

import java.util.List;

/**
 * Created by Mr.BH
 */
public interface BuildingService {


    /**
     * 添加楼栋
     * @param building
     * @return
     */
    RestResult saveBuilding(Building building);


    /**
     * 根据 pkId  删除楼栋
     * @param ids
     * @return
     */
    RestResult deleteBuilding(List<Integer> ids);


    /**
     * 修改 楼栋信息
     * @param building
     * @return
     */
    RestResult updateBuilding(Building building);


    /**
     * 获取口袋信息  根据主键ID
     * @param id
     * @return
     */
    RestResult getBuilding(Integer id);


    /**
     * 楼栋列表
     * @param keyword
     * @param pageNum
     * @param pageSize
     * @return
     */
    RestResult listBuilding(String keyword, Integer pageNum, Integer pageSize);


    /**
     * 通过小区id获取所有的楼栋信息
     * @param communityId
     * @return
     */
    RestResult findListByCommunityId(Integer communityId);


    /**
     * 通过小区id获取分期、楼栋、单元的树形结构
     * @param communityId
     * @return
     */
    RestResult findTreeByCommunityId(Integer communityId);

}
