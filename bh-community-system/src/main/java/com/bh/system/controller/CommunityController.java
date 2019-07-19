package com.bh.system.controller;

import com.bh.common.aop.MyLog;
import com.bh.common.result.RestResult;
import com.bh.common.result.ResultUtils;
import com.bh.common.utils.MailUtil;
import com.bh.common.utils.VerifyCodeUtils;
import com.bh.model.domain.Admin;
import com.bh.model.domain.Community;
import com.bh.system.service.AdminService;
import com.bh.system.service.CommunityService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.OutputStream;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Map;

/**
 * Created by Mr.BH
 * 小区管理模块
 */
@RestController
@RequestMapping(value = "/community")
@Api(tags = "小区管理接口API", description="小区管理")
@Validated
public class CommunityController {

    @Autowired
    private CommunityService communityService;


    /**
     * 添加小区
     * @param community
     * @param result
     * @return
     */
    @MyLog(value="新增小区信息")
    @ApiOperation(value = "小区保存接口", notes = "小区保存")
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public RestResult save(Community community, BindingResult result) {
        return communityService.saveCommunity(community);
    }


    /**
     * 删除、批量删除
     * @param map
     * @return
     */
    @MyLog(value="删除小区")
    @ApiOperation(value = "小区批量删除接口", notes = "小区批量删除，逻辑删除")
    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    public RestResult delete(@RequestBody Map map){
        List<Integer> ids = (List<Integer>) map.get("ids");
        return communityService.deleteCommunity(ids);
    }


    /**
     * 修改小区信息
     * @param community
     * @param result
     * @return
     */
    @MyLog(value="修改小区信息")
    @ApiOperation(value = "修改小区信息接口", notes = "修改小区信息")
    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public RestResult update(Community community, BindingResult result){
        return communityService.updateCommunity(community);
    }


    /**
     * 修改小区状态
     *
     * @param id
     * @return
     */
    @MyLog(value="修改小区状态（禁用或启用）")
    @RequestMapping(value = "/setStatus",method = RequestMethod.GET)
    @ApiOperation(value = "修改小区状态接口", notes = "修改小区状态（禁用和启用）")
    public RestResult setStatus(@RequestParam Integer id){
        return communityService.setStatus(id);
    }


    /**
     * 获取小区信息
     * @param id
     * @return
     */
    @ApiOperation(value = "获取小区信息接口", notes = "获取小区信息")
    @RequestMapping(value = "/get",method = RequestMethod.GET)
    public RestResult get( @RequestParam int id){
        return communityService.getCommunity(id);
    }


    /**
     * 获取物业下的所有小区信息
     * @param pmId
     * @return
     */
    @ApiOperation(value = "获取物业下的所有小区信息接口", notes = "获取物业下的所有小区信息")
    @RequestMapping(value = "/findListByPmId",method = RequestMethod.GET)
    public RestResult findListByPmId( @RequestParam int pmId){
        return communityService.findListByPmId(pmId);
    }


    /**
     * 小区列表
     * @param map
     */
    @ApiOperation(value = "获取小区列表接口（分页）", notes = "获取小区列表")
    @RequestMapping(value = "/list",method = RequestMethod.POST)
    public RestResult list(@RequestBody Map map){
        String keyword = (String) map.get("keyword");
        Integer pageNum = (Integer) map.get("pageNum");
        Integer pageSize = (Integer) map.get("pageSize");
        return communityService.listCommunity(keyword, pageNum, pageSize);
    }
}
