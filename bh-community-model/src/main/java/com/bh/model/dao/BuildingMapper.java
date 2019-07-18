package com.bh.model.dao;

import com.bh.model.domain.Building;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface BuildingMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Building record);

    int insertSelective(Building record);

    Building selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Building record);

    int updateByPrimaryKey(Building record);

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

    /**
     * 通过小区id获取所有的楼栋
     * @param communityId
     * @return
     */
    List<Building> findListByCommunityId(Integer communityId);

    /**
     * 通过小区id获取不重复的分期
     * @param communityId
     * @return
     */
    List<String> findByStagingByCommunityId(Integer communityId);

    /**
     * 通过小区id、分期获取所有的楼栋
     * @param communityId
     * @param byStaging
     * @return
     */
    List<String> findBuilding(@Param("communityId") Integer communityId, @Param("byStaging") String byStaging);

    /**
     * 通过小区id、分期、楼栋获取所有的单元
     * @param communityId
     * @param byStaging
     * @param building
     * @return
     */
    List<Building> findUnit(@Param("communityId") Integer communityId, @Param("byStaging") String byStaging, @Param("building") String building);
}