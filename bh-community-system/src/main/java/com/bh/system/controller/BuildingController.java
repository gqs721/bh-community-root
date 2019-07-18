package com.bh.system.controller;

import com.bh.common.aop.MyLog;
import com.bh.common.result.RestResult;
import com.bh.model.domain.Building;
import com.bh.model.domain.Community;
import com.bh.system.service.BuildingService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * Created by Mr.BH
 * 楼栋管理模块
 */
@RestController
@RequestMapping(value = "/building")
@Api(value = "楼栋管理api", description="楼栋管理api")
@Validated
public class BuildingController {

    @Autowired
    private BuildingService buildingService;


    /**
     * 添加楼栋
     * @param building
     * @param result
     * @return
     */
    @MyLog(value="新增楼栋信息")
    @ApiOperation(value = "楼栋保存接口", notes = "楼栋保存")
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public RestResult save(Building building, BindingResult result) {
        return buildingService.saveBuilding(building);
    }


    /**
     * 删除、批量删除
     * @param map
     * @return
     */
    @MyLog(value="删除楼栋")
    @ApiOperation(value = "楼栋批量删除接口", notes = "楼栋批量删除，逻辑删除")
    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    public RestResult delete(@RequestBody Map map){
        List<Integer> ids = (List<Integer>) map.get("ids");
        return buildingService.deleteBuilding(ids);
    }


    /**
     * 修改楼栋信息
     * @param building
     * @param result
     * @return
     */
    @MyLog(value="删除楼栋信息")
    @ApiOperation(value = "修改楼栋信息接口", notes = "修改楼栋信息")
    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public RestResult update(Building building, BindingResult result){
        return buildingService.updateBuilding(building);
    }


    /**
     * 获取楼栋信息
     * @param id
     * @return
     */
    @ApiOperation(value = "获取楼栋信息接口", notes = "获取楼栋信息")
    @RequestMapping(value = "/get",method = RequestMethod.GET)
    public RestResult get( @RequestParam int id){
        return buildingService.getBuilding(id);
    }


    /**
     * 获取小区下的所有楼栋信息
     * @param communityId
     * @return
     */
    @ApiOperation(value = "获取小区下的所有楼栋信息接口", notes = "获取小区下的所有楼栋信息")
    @RequestMapping(value = "/findListByCommunityId",method = RequestMethod.GET)
    public RestResult findListByCommunityId( @RequestParam int communityId){
        return buildingService.findListByCommunityId(communityId);
    }


    /**
     * 获取小区下的分期、楼栋、单元的树形结构数据
     * @param communityId
     * @return
     */
    @ApiOperation(value = "获取小区下的分期、楼栋、单元的树形结构数据接口", notes = "获取小区下的分期、楼栋、单元的树形结构数据")
    @RequestMapping(value = "/findTreeByCommunityId",method = RequestMethod.GET)
    public RestResult findTreeByCommunityId( @RequestParam int communityId){
        return buildingService.findTreeByCommunityId(communityId);
    }


    /**
     * 楼栋列表
     * @param map
     */
    @ApiOperation(value = "获取楼栋列表接口（分页）", notes = "获取楼栋列表")
    @RequestMapping(value = "/list",method = RequestMethod.POST)
    public RestResult list(@RequestBody Map map){
        String keyword = (String) map.get("keyword");
        Integer pageNum = (Integer) map.get("pageNum");
        Integer pageSize = (Integer) map.get("pageSize");
        return buildingService.listBuilding(keyword, pageNum, pageSize);
    }
}
