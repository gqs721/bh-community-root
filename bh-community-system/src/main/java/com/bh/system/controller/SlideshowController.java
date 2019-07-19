package com.bh.system.controller;

import com.bh.common.aop.MyLog;
import com.bh.common.result.RestResult;
import com.bh.model.domain.Slideshow;
import com.bh.model.domain.VoiceLibrary;
import com.bh.system.service.SlideshowService;
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
 * 轮播图管理模块
 */
@RestController
@RequestMapping(value = "/slideshow")
@Api(tags = "轮播图接口API", description="轮播图管理")
@Validated
public class SlideshowController {

    @Autowired
    private SlideshowService slideshowService;


    /**
     * 添加轮播图
     * @param slideshow
     * @param result
     * @return
     */
    @MyLog(value="新增轮播图信息")
    @ApiOperation(value = "轮播图保存接口", notes = "保存轮播图")
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public RestResult save(Slideshow slideshow, BindingResult result) {
        return slideshowService.save(slideshow);
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
        return slideshowService.delete(ids);
    }


    /**
     * 修改轮播图
     * @param slideshow
     * @param result
     * @return
     */
    @MyLog(value="修改轮播图信息")
    @ApiOperation(value = "修改轮播图信息接口", notes = "修改轮播图信息")
    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public RestResult update(Slideshow slideshow, BindingResult result){
        return slideshowService.update(slideshow);
    }


    /**
     * 修改轮播图状态
     *
     * @param id
     * @return
     */
    @MyLog(value="修改轮播图状态（禁用或启用）")
    @RequestMapping(value = "/setStatus",method = RequestMethod.GET)
    @ApiOperation(value = "修改轮播图状态接口", notes = "修改轮播图状态（禁用和启用）")
    public RestResult setStatus(@RequestParam Integer id){
        return slideshowService.setStatus(id);
    }


    /**
     * 获取轮播图信息
     * @param id
     * @return
     */
    @ApiOperation(value = "获取轮播图信息接口", notes = "获取轮播图信息")
    @RequestMapping(value = "/get",method = RequestMethod.GET)
    public RestResult get( @RequestParam int id){
        return slideshowService.get(id);
    }


    /**
     * 轮播图列表
     * @param map
     */
    @ApiOperation(value = "获取轮播图列表接口（分页）", notes = "获取轮播图列表,参数：json串，schedule:进度（整型），beginDate：开始时间，endDate：结束时间，pageNum:页码，pageSize：条数")
    @RequestMapping(value = "/list",method = RequestMethod.POST)
    public RestResult list(@RequestBody Map map){
        Integer schedule = (Integer) map.get("schedule");
        String beginDate = (String) map.get("beginDate");
        String endDate = (String) map.get("endDate");
        Integer pageNum = (Integer) map.get("pageNum");
        Integer pageSize = (Integer) map.get("pageSize");
        return slideshowService.list(schedule, beginDate, endDate, pageNum, pageSize);
    }
}
