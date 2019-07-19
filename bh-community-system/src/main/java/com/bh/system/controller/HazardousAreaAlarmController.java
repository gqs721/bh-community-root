package com.bh.system.controller;

import com.bh.common.aop.MyLog;
import com.bh.common.result.RestResult;
import com.bh.model.domain.HazardousAreaAlarm;
import com.bh.model.domain.HighTossActAlarm;
import com.bh.system.service.HazardousAreaAlarmService;
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
 * 危险区域告警管理模块
 */
@RestController
@RequestMapping(value = "/hazardous/area/alarm")
@Api(tags = "危险区域告警管理接口API", description="危险区域告警管理")
public class HazardousAreaAlarmController {

    @Autowired
    private HazardousAreaAlarmService hazardousAreaAlarmService;


    /**
     * 添加危险区域告警记录
     * @param hazardousAreaAlarm
     * @param result
     * @return
     */
    @MyLog(value="新增危险区域告警记录")
    @ApiOperation(value = "新增危险区域告警记录接口", notes = "新增危险区域告警记录")
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public RestResult save(HazardousAreaAlarm hazardousAreaAlarm, BindingResult result) {
        return hazardousAreaAlarmService.save(hazardousAreaAlarm);
    }


    /**
     * 删除、批量删除
     * @param map
     * @return
     */
    @MyLog(value="删除危险区域告警记录")
    @ApiOperation(value = "危险区域告警记录批量删除接口", notes = "危险区域告警记录批量删除，逻辑删除")
    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    public RestResult delete(@RequestBody Map map){
        List<Integer> ids = (List<Integer>) map.get("ids");
        return hazardousAreaAlarmService.delete(ids);
    }


    /**
     * 危险区域告警记录列表
     * @param map
     */
    @ApiOperation(value = "获取危险区域告警记录列表接口（分页）", notes = "获取危险区域告警记录列表,json串，参数：keyword：区域位置，beginDate：开始时间，endDate：结束时间，pageNum:页码，pageSize：条数")
    @RequestMapping(value = "/list",method = RequestMethod.POST)
    public RestResult list(@RequestBody Map map){
        String keyword = (String) map.get("keyword");
        String beginDate = (String) map.get("beginDate");
        String endDate = (String) map.get("endDate");
        Integer pageNum = (Integer) map.get("pageNum");
        Integer pageSize = (Integer) map.get("pageSize");
        return hazardousAreaAlarmService.list(keyword, beginDate, endDate, pageNum, pageSize);
    }
}
