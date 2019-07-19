package com.bh.system.controller;

import com.bh.common.aop.MyLog;
import com.bh.common.result.RestResult;
import com.bh.model.domain.Notice;
import com.bh.model.domain.Slideshow;
import com.bh.system.service.NoticeService;
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
 * 公告管理模块
 */
@RestController
@RequestMapping(value = "/notice")
@Api(tags = "公告接口API", description="公告管理")
@Validated
public class NoticeController {

    @Autowired
    private NoticeService noticeService;


    /**
     * 添加公告
     * @param notice
     * @param result
     * @return
     */
    @MyLog(value="新增公告信息")
    @ApiOperation(value = "公告保存接口", notes = "保存公告信息")
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public RestResult save(Notice notice, BindingResult result) {
        return noticeService.save(notice);
    }


    /**
     * 删除、批量删除
     * @param map
     * @return
     */
    @MyLog(value="删除轮播图")
    @ApiOperation(value = "批量删除轮播图接口", notes = "轮播图批量删除，逻辑删除")
    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    public RestResult delete(@RequestBody Map map){
        List<Integer> ids = (List<Integer>) map.get("ids");
        return noticeService.delete(ids);
    }


    /**
     * 修改公告
     * @param notice
     * @param result
     * @return
     */
    @MyLog(value="修改公告信息")
    @ApiOperation(value = "修改公告信息接口", notes = "修改公告信息")
    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public RestResult update(Notice notice, BindingResult result){
        return noticeService.update(notice);
    }


    /**
     * 修改公告信息状态
     *
     * @param id
     * @return
     */
    @MyLog(value="修改公告状态（禁用或启用）")
    @RequestMapping(value = "/setStatus",method = RequestMethod.GET)
    @ApiOperation(value = "修改公告状态接口", notes = "修改公告状态（禁用和启用）")
    public RestResult setStatus(@RequestParam Integer id){
        return noticeService.setStatus(id);
    }


    /**
     * 获取公告信息
     * @param id
     * @return
     */
    @ApiOperation(value = "获取公告信息接口", notes = "获取公告信息")
    @RequestMapping(value = "/get",method = RequestMethod.GET)
    public RestResult get( @RequestParam int id){
        return noticeService.get(id);
    }


    /**
     * 公告列表
     * @param map
     */
    @ApiOperation(value = "获取公告列表接口（分页）", notes = "获取公告列表,参数：json串，schedule:进度（整型），beginDate：开始时间，endDate：结束时间，pageNum:页码，pageSize：条数")
    @RequestMapping(value = "/list",method = RequestMethod.POST)
    public RestResult list(@RequestBody Map map){
        Integer schedule = (Integer) map.get("schedule");
        String beginDate = (String) map.get("beginDate");
        String endDate = (String) map.get("endDate");
        Integer pageNum = (Integer) map.get("pageNum");
        Integer pageSize = (Integer) map.get("pageSize");
        return noticeService.list(schedule, beginDate, endDate, pageNum, pageSize);
    }
}
