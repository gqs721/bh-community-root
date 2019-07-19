package com.bh.system.controller;

import com.bh.common.aop.MyLog;
import com.bh.common.result.RestResult;
import com.bh.model.domain.Tenement;
import com.bh.model.domain.TenementPassRecord;
import com.bh.system.service.TenementPassRecordService;
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
 * 住户进出记录管理模块
 */
@RestController
@RequestMapping(value = "/tenement/passRecord")
@Api(tags = "住户进出记录接口API", description="住户进出记录管理")
public class TenementPassRecordController {

    @Autowired
    private TenementPassRecordService tenementPassRecordService;


    /**
     * 添加进出记录
     * @param tenementPassRecord
     * @param result
     * @return
     */
    @MyLog(value="新增住户进出记录")
    @ApiOperation(value = "新增住户进出记录接口", notes = "新增住户进出记录")
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public RestResult save(TenementPassRecord tenementPassRecord, BindingResult result) {
        return tenementPassRecordService.save(tenementPassRecord);
    }


    /**
     * 删除、批量删除
     * @param map
     * @return
     */
    @MyLog(value="删除住户进出记录")
    @ApiOperation(value = "住户进出记录批量删除接口", notes = "住户进出记录批量删除，逻辑删除")
    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    public RestResult delete(@RequestBody Map map){
        List<Integer> ids = (List<Integer>) map.get("ids");
        return tenementPassRecordService.delete(ids);
    }


    /**
     * 住户进出记录列表
     * @param map
     */
    @ApiOperation(value = "获取住户进出记录列表接口（分页）", notes = "获取住户进出记录列表,json串，参数：tenementName：住户姓名，beginDate：开始时间，endDate：结束时间，pageNum:页码，pageSize：条数")
    @RequestMapping(value = "/list",method = RequestMethod.POST)
    public RestResult list(@RequestBody Map map){
        String tenementName = (String) map.get("tenementName");
        String beginDate = (String) map.get("beginDate");
        String endDate = (String) map.get("endDate");
        Integer pageNum = (Integer) map.get("pageNum");
        Integer pageSize = (Integer) map.get("pageSize");
        return tenementPassRecordService.list(tenementName, beginDate, endDate, pageNum, pageSize);
    }
}
