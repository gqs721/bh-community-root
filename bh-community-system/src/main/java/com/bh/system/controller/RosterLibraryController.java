package com.bh.system.controller;

import com.bh.common.aop.MyLog;
import com.bh.common.result.RestResult;
import com.bh.model.domain.Notice;
import com.bh.model.domain.RosterLibrary;
import com.bh.system.service.RosterLibraryService;
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
 * 黑白名单库管理模块
 */
@RestController
@RequestMapping(value = "/roster/library")
@Api(tags = "黑白名单库接口API", description="黑白名单库管理")
@Validated
public class RosterLibraryController {

    @Autowired
    private RosterLibraryService rosterLibraryService;


    /**
     * 添加黑白名单
     * @param rosterLibrary
     * @param result
     * @return
     */
    @MyLog(value="新增黑白名单库信息")
    @ApiOperation(value = "黑白名单库保存接口", notes = "保存黑白名单库信息")
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public RestResult save(RosterLibrary rosterLibrary, BindingResult result) {
        return rosterLibraryService.save(rosterLibrary);
    }


    /**
     * 删除、批量删除
     * @param map
     * @return
     */
    @MyLog(value="删除黑白名单库信息")
    @ApiOperation(value = "批量删除黑白名单接口", notes = "黑白名单批量删除，逻辑删除")
    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    public RestResult delete(@RequestBody Map map){
        List<Integer> ids = (List<Integer>) map.get("ids");
        return rosterLibraryService.delete(ids);
    }


    /**
     * 修改黑白名单
     * @param rosterLibrary
     * @param result
     * @return
     */
    @MyLog(value="修改黑白名单信息")
    @ApiOperation(value = "修改黑白名单信息接口", notes = "修改黑白名单信息")
    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public RestResult update(RosterLibrary rosterLibrary, BindingResult result){
        return rosterLibraryService.update(rosterLibrary);
    }


    /**
     * 获取黑白名单信息
     * @param id
     * @return
     */
    @ApiOperation(value = "获取黑白名单信息接口", notes = "获取黑白名单信息")
    @RequestMapping(value = "/get",method = RequestMethod.GET)
    public RestResult get( @RequestParam int id){
        return rosterLibraryService.get(id);
    }


    /**
     * 黑白名单库列表
     * @param map
     */
    @ApiOperation(value = "获取黑白名单库列表接口（分页）", notes = "获取黑白名单库列表,参数：keyword：关键字，pageNum:页码，pageSize：条数")
    @RequestMapping(value = "/list",method = RequestMethod.POST)
    public RestResult list(@RequestBody Map map){
        String keyword = (String) map.get("keyword");
        Integer pageNum = (Integer) map.get("pageNum");
        Integer pageSize = (Integer) map.get("pageSize");
        return rosterLibraryService.list(keyword, pageNum, pageSize);
    }
}
