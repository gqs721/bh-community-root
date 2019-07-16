package com.bh.system.service;

import com.bh.common.result.RestResult;
import com.bh.model.domain.PropertyManagement;

import java.util.List;
import java.util.Map;

/**
 * Created by Mr.BH
 */
public interface PropertyManagementService {


    /**
     * 添加物业信息
     * @param propertyManagement
     * @return
     */
    RestResult add(PropertyManagement propertyManagement);


    /**
     * 删除物业信息
     * @param roleIds
     * @return
     */
    RestResult delete(List<Integer> roleIds);


    /**
     * 修改物业信息
     * @param map
     * @return
     */
    RestResult update(PropertyManagement map);


    /**
     * 获取物业信息列表
     * @return
     */
    RestResult getAll();

}
