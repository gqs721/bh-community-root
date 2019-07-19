package com.bh.system.controller;

import com.bh.common.aop.MyLog;
import com.bh.common.result.RestResult;
import com.bh.model.domain.ReservationRecord;
import com.bh.model.domain.TenementPassRecord;
import com.bh.system.service.ReservationRecordService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * Created by Mr.BH
 * 预约记录管理模块
 */
@RestController
@RequestMapping(value = "/reservation/record")
@Api(tags = "预约记录接口API", description="预约记录管理")
public class ReservationRecordController {

    @Autowired
    private ReservationRecordService reservationRecordService;


    /**
     * 添加预约记录
     * @param reservationRecord
     * @param result
     * @return
     */
    @MyLog(value="新增预约记录")
    @ApiOperation(value = "新增预约记录接口", notes = "新增预约记录")
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public RestResult save(ReservationRecord reservationRecord, BindingResult result) {
        return reservationRecordService.save(reservationRecord);
    }


    /**
     * 修改预约进展
     * @param id
     * @return
     */
    @RequestMapping(value = "/editReservationEvolve",method = RequestMethod.GET)
    @ApiOperation(value = "修改预约进展接口", notes = "修改预约进展")
    public RestResult editReservationEvolve(@RequestParam Integer id, @RequestParam Integer reservationEvolve){
        return reservationRecordService.editReservationEvolve(id, reservationEvolve);
    }


    /**
     * 审核预约
     * @param id
     * @return
     */
    @MyLog(value="审核预约")
    @RequestMapping(value = "/auditReservation",method = RequestMethod.GET)
    @ApiOperation(value = "审核预约接口", notes = "审核预约")
    public RestResult auditReservation(@RequestParam Integer id){
        return reservationRecordService.auditReservation(id);
    }


    /**
     * 删除、批量删除
     * @param map
     * @return
     */
    @MyLog(value="删除预约记录")
    @ApiOperation(value = "预约记录批量删除接口", notes = "预约记录批量删除，逻辑删除")
    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    public RestResult delete(@RequestBody Map map){
        List<Integer> ids = (List<Integer>) map.get("ids");
        return reservationRecordService.delete(ids);
    }


    /**
     * 预约记录列表
     * @param map
     */
    @ApiOperation(value = "获取预约记录列表接口（分页）", notes = "获取预约记录列表,json串，参数：keyword：关键字，beginDate：开始时间，endDate：结束时间，pageNum:页码，pageSize：条数")
    @RequestMapping(value = "/list",method = RequestMethod.POST)
    public RestResult list(@RequestBody Map map){
        String keyword = (String) map.get("keyword");
        String beginDate = (String) map.get("beginDate");
        String endDate = (String) map.get("endDate");
        Integer pageNum = (Integer) map.get("pageNum");
        Integer pageSize = (Integer) map.get("pageSize");
        return reservationRecordService.list(keyword, beginDate, endDate, pageNum, pageSize);
    }
}
