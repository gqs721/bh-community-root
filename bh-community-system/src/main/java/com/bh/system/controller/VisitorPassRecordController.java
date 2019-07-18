package com.bh.system.controller;

import com.bh.common.aop.MyLog;
import com.bh.common.result.RestResult;
import com.bh.model.domain.TenementPassRecord;
import com.bh.model.domain.VisitorPassRecord;
import com.bh.system.service.VisitorPassRecordService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * Created by Mr.BH
 * 访客进出记录管理模块
 */
@RestController
@RequestMapping(value = "/visitor/passRecord")
@Api(value = "访客进出记录管理api", description="访客进出记录管理api")
public class VisitorPassRecordController {

    @Autowired
    private VisitorPassRecordService visitorPassRecordService;


    /**
     * 添加访客进出记录
     * @param visitorPassRecord
     * @param result
     * @return
     */
    @MyLog(value="新增访客进出记录")
    @ApiOperation(value = "新增访客进出记录接口", notes = "新增访客进出记录")
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public RestResult save(VisitorPassRecord visitorPassRecord, BindingResult result) {
        return visitorPassRecordService.save(visitorPassRecord);
    }


    /**
     * 删除、批量删除
     * @param map
     * @return
     */
    @MyLog(value="删除访客进出记录")
    @ApiOperation(value = "访客进出记录批量删除接口", notes = "访客进出记录批量删除，逻辑删除")
    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    public RestResult delete(@RequestBody Map map){
        List<Integer> ids = (List<Integer>) map.get("ids");
        return visitorPassRecordService.delete(ids);
    }


    /**
     * 访客进出记录列表
     * @param map
     */
    @ApiOperation(value = "获取访客进出记录列表接口（分页）", notes = "获取访客进出记录列表,json串，参数：keyword：关键字，beginDate：开始时间，endDate：结束时间，pageNum:页码，pageSize：条数")
    @RequestMapping(value = "/list",method = RequestMethod.POST)
    public RestResult list(@RequestBody Map map){
        String keyword = (String) map.get("keyword");
        String beginDate = (String) map.get("beginDate");
        String endDate = (String) map.get("endDate");
        Integer pageNum = (Integer) map.get("pageNum");
        Integer pageSize = (Integer) map.get("pageSize");
        return visitorPassRecordService.list(keyword, beginDate, endDate, pageNum, pageSize);
    }
}
