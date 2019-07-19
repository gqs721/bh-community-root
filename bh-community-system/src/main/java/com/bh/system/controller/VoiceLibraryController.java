package com.bh.system.controller;

import com.bh.common.aop.MyLog;
import com.bh.common.result.RestResult;
import com.bh.model.domain.Community;
import com.bh.model.domain.VoiceLibrary;
import com.bh.system.service.VoiceLibraryService;
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
 * 语音管理模块
 */
@RestController
@RequestMapping(value = "/voice/library")
@Api(tags = "语音库接口API", description="语音库管理")
@Validated
public class VoiceLibraryController {

    @Autowired
    private VoiceLibraryService voiceLibraryService;


    /**
     * 添加语音
     * @param voiceLibrary
     * @param result
     * @return
     */
    @MyLog(value="新增语音信息")
    @ApiOperation(value = "语音信息保存接口", notes = "保存语音信息")
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public RestResult save(VoiceLibrary voiceLibrary, BindingResult result) {
        return voiceLibraryService.save(voiceLibrary);
    }


    /**
     * 删除、批量删除
     * @param map
     * @return
     */
    @MyLog(value="删除语音")
    @ApiOperation(value = "批量语音删除接口", notes = "语音批量删除，逻辑删除")
    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    public RestResult delete(@RequestBody Map map){
        List<Integer> ids = (List<Integer>) map.get("ids");
        return voiceLibraryService.delete(ids);
    }


    /**
     * 修改语音信息
     * @param voiceLibrary
     * @param result
     * @return
     */
    @MyLog(value="修改语音信息")
    @ApiOperation(value = "修改语音信息接口", notes = "修改语音信息")
    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public RestResult update(VoiceLibrary voiceLibrary, BindingResult result){
        return voiceLibraryService.update(voiceLibrary);
    }


    /**
     * 修改语音状态
     *
     * @param id
     * @return
     */
    @MyLog(value="修改语音状态（禁用或启用）")
    @RequestMapping(value = "/setStatus",method = RequestMethod.GET)
    @ApiOperation(value = "修改语音状态接口", notes = "修改语音状态（禁用和启用）")
    public RestResult setStatus(@RequestParam Integer id){
        return voiceLibraryService.setStatus(id);
    }


    /**
     * 获取语音信息
     * @param id
     * @return
     */
    @ApiOperation(value = "获取语音信息接口", notes = "获取语音信息")
    @RequestMapping(value = "/get",method = RequestMethod.GET)
    public RestResult get( @RequestParam int id){
        return voiceLibraryService.get(id);
    }


    /**
     * 通过语音类型获取语音信息
     * @param voiceType
     * @return
     */
    @ApiOperation(value = "通过语音类型获取语音信息接口", notes = "通过语音类型获取语音信息")
    @RequestMapping(value = "/findByVoiceType",method = RequestMethod.GET)
    public RestResult get( @RequestParam Integer voiceType){
        return voiceLibraryService.findByVoiceType(voiceType);
    }


    /**
     * 语音列表
     * @param map
     */
    @ApiOperation(value = "获取语音列表接口（分页）", notes = "获取语音列表")
    @RequestMapping(value = "/list",method = RequestMethod.POST)
    public RestResult list(@RequestBody Map map){
        String keyword = (String) map.get("keyword");
        Integer pageNum = (Integer) map.get("pageNum");
        Integer pageSize = (Integer) map.get("pageSize");
        return voiceLibraryService.list(keyword, pageNum, pageSize);
    }
}
