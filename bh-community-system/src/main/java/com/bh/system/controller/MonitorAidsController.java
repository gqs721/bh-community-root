package com.bh.system.controller;

import com.bh.common.aop.MyLog;
import com.bh.common.result.RestResult;
import com.bh.model.domain.MonitorAids;
import com.bh.model.domain.RosterLibrary;
import com.bh.system.service.MonitorAidsService;
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
 * 监控设备管理模块
 */
@RestController
@RequestMapping(value = "/monitor/aids")
@Api(tags = "监控设备接口API", description="监控设备管理")
@Validated
public class MonitorAidsController {

    @Autowired
    private MonitorAidsService monitorAidsService;


    /**
     * 添加监控设备
     * @param monitorAids
     * @param result
     * @return
     */
    @MyLog(value="新增监控设备信息")
    @ApiOperation(value = "监控设备保存接口", notes = "保存监控设备信息")
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public RestResult save(MonitorAids monitorAids, BindingResult result) {
        return monitorAidsService.save(monitorAids);
    }


    /**
     * 删除、批量删除
     * @param map
     * @return
     */
    @MyLog(value="删除监控设备信息")
    @ApiOperation(value = "批量删除监控设备接口", notes = "监控设备批量删除，逻辑删除")
    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    public RestResult delete(@RequestBody Map map){
        List<Integer> ids = (List<Integer>) map.get("ids");
        return monitorAidsService.delete(ids);
    }


    /**
     * 修改监控设备
     * @param monitorAids
     * @param result
     * @return
     */
    @MyLog(value="修改监控设备信息")
    @ApiOperation(value = "修改监控设备信息接口", notes = "修改监控设备信息")
    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public RestResult update(MonitorAids monitorAids, BindingResult result){
        return monitorAidsService.update(monitorAids);
    }


    /**
     * 修改监控设备状态
     *
     * @param id
     * @return
     */
    @MyLog(value="修改监控设备状态（禁用或启用）")
    @RequestMapping(value = "/setStatus",method = RequestMethod.GET)
    @ApiOperation(value = "修改监控设备状态接口", notes = "修改监控设备状态（禁用和启用）")
    public RestResult setStatus(@RequestParam Integer id){
        return monitorAidsService.setStatus(id);
    }


    /**
     * 获取监控设备信息
     * @param id
     * @return
     */
    @ApiOperation(value = "获取监控设备信息接口", notes = "获取监控设备信息")
    @RequestMapping(value = "/get",method = RequestMethod.GET)
    public RestResult get( @RequestParam int id){
        return monitorAidsService.get(id);
    }


    /**
     * 监控设备列表
     * @param map
     */
    @ApiOperation(value = "获取监控设备列表接口（分页）", notes = "获取监控设备列表,参数：monitorType:监控类型（整型），keyword：关键字，pageNum:页码，pageSize：条数")
    @RequestMapping(value = "/list",method = RequestMethod.POST)
    public RestResult list(@RequestBody Map map){
        String keyword = (String) map.get("keyword");
        Integer monitorType = (Integer) map.get("monitorType");
        Integer pageNum = (Integer) map.get("pageNum");
        Integer pageSize = (Integer) map.get("pageSize");
        return monitorAidsService.list(keyword, monitorType, pageNum, pageSize);
    }
}
