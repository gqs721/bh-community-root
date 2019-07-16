package com.bh.system.controller;

import com.bh.common.result.RestResult;
import com.bh.common.utils.PageUtils;
import com.bh.model.domain.PropertyManagement;
import com.bh.system.service.PropertyManagementService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;


/**
 * Created by Mr.BH
 */
@RestController
@RequestMapping("/property/management")
@Api(value = "物业管理api", description="物业管理api")
public class PropertyManagementController {

    @Autowired
    private PropertyManagementService propertyManagementService;

    /**
     * 添加物业信息
     * @param propertyManagement
     * @return
     */
    @ApiOperation(value = "添加物业信息接口", notes = "添加物业信息")
    @RequestMapping(value = "/save",method = RequestMethod.POST)
    public RestResult save(PropertyManagement propertyManagement){
        return propertyManagementService.add(propertyManagement);
    }


    /**
     * 批量删除 删除物业信息
     * @param map
     * @return
     */
    @ApiOperation(value = "批量删除 删除物业信息接口", notes = "批量删除 删除物业信息")
    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    public RestResult delete(@RequestBody Map map){
        List<Integer> ids = (List<Integer>) map.get("ids");
        return propertyManagementService.delete(ids);
    }


    /**
     * 修改物业信息
     * @param propertyManagement
     * @return
     */
    @ApiOperation(value = "修改物业信息接口", notes = "修改物业信息")
    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public RestResult update(PropertyManagement propertyManagement){
        return propertyManagementService.update(propertyManagement);
    }


    /**
     * 获取物业信息列表
     * @return
     */
    @ApiOperation(value = "获取物业信息列表接口（分页）", notes = "获取物业信息列表")
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public RestResult list(){
        return propertyManagementService.getAll();
    }


}
