package com.bh.system.service;

import com.bh.common.result.RestResult;
import com.bh.model.domain.Building;
import com.bh.model.domain.Tenement;

import java.util.List;

/**
 * Created by Mr.BH
 */
public interface TenementService {


    /**
     * 添加住户
     * @param tenement
     * @return
     */
    RestResult saveTenement(Tenement tenement);


    /**
     * 根据 Id  删除住户
     * @param ids
     * @return
     */
    RestResult deleteTenement(List<Integer> ids);


    /**
     * 修改 住户信息
     * @param tenement
     * @return
     */
    RestResult updateTenement(Tenement tenement);


    /**
     * 获取住户信息  根据主键ID
     * @param id
     * @return
     */
    RestResult getTenement(Integer id);


    /**
     * 住户列表
     * @param keyword
     * @param tenement
     * @param pageNum
     * @param pageSize
     * @return
     */
    RestResult listTenement(String keyword, String tenement, Integer pageNum, Integer pageSize);

    /**
     * 修改住户状态（启用或禁用）
     * @param id
     * @return
     */
    RestResult setStatus(Integer id);
}
