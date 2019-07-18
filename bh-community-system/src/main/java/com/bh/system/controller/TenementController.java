package com.bh.system.controller;

import com.bh.common.aop.MyLog;
import com.bh.common.result.RestResult;
import com.bh.model.domain.Building;
import com.bh.model.domain.Tenement;
import com.bh.system.service.TenementService;
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
 * 住户管理模块
 */
@RestController
@RequestMapping(value = "/tenement")
@Api(value = "住户管理api", description="住户管理api")
@Validated
public class TenementController {

    @Autowired
    private TenementService tenementService;


    /**
     * 添加住户
     * @param tenement
     * @param result
     * @return
     */
    @MyLog(value="新增住户信息")
    @ApiOperation(value = "住户保存接口", notes = "住户保存")
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public RestResult save(Tenement tenement, BindingResult result) {
        return tenementService.saveTenement(tenement);
    }


    /**
     * 客户端添加住户
     * @param tenement
     * @param result
     * @return
     */
    @MyLog(value="客户端添加住户")
    @ApiOperation(value = "客户端添加住户接口", notes = "客户端添加住户")
    @RequestMapping(value = "/clientSave", method = RequestMethod.POST)
    public RestResult clientSave(Tenement tenement, BindingResult result) {
        return tenementService.saveTenement(tenement);
    }


    /**
     * 删除、批量删除
     * @param map
     * @return
     */
    @MyLog(value="删除住户")
    @ApiOperation(value = "住户批量删除接口", notes = "住户批量删除，逻辑删除")
    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    public RestResult delete(@RequestBody Map map){
        List<Integer> ids = (List<Integer>) map.get("ids");
        return tenementService.deleteTenement(ids);
    }


    /**
     * 修改住户信息
     * @param tenement
     * @param result
     * @return
     */
    @MyLog(value="修改住户信息")
    @ApiOperation(value = "修改住户信息接口", notes = "修改住户信息")
    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public RestResult update(Tenement tenement, BindingResult result){
        return tenementService.updateTenement(tenement);
    }


    /**
     * 修改住户状态
     *
     * @param id
     * @return
     */
    @MyLog(value="修改住户状态（禁用或启用）")
    @RequestMapping(value = "/setStatus",method = RequestMethod.GET)
    @ApiOperation(value = "修改住户状态接口", notes = "修改住户状态（禁用和启用）")
    public RestResult setStatus(@RequestParam Integer id){
        return tenementService.setStatus(id);
    }


    /**
     * 获取住户信息
     * @param id
     * @return
     */
    @ApiOperation(value = "获取住户信息接口", notes = "获取住户信息")
    @RequestMapping(value = "/get",method = RequestMethod.GET)
    public RestResult get( @RequestParam int id){
        return tenementService.getTenement(id);
    }


    /**
     * 住户列表
     * @param map
     */
    @ApiOperation(value = "获取住户列表接口（分页）", notes = "获取住户列表,json串，参数：keyword：关键字，tenementType:住户类型，pageNum:页码，pageSize：条数")
    @RequestMapping(value = "/list",method = RequestMethod.POST)
    public RestResult list(@RequestBody Map map){
        String keyword = (String) map.get("keyword");
        String tenementType = (String) map.get("tenementType");
        Integer pageNum = (Integer) map.get("pageNum");
        Integer pageSize = (Integer) map.get("pageSize");
        return tenementService.listTenement(keyword, tenementType, pageNum, pageSize);
    }
}
