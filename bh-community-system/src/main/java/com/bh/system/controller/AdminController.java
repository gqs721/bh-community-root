package com.bh.system.controller;

import com.bh.common.aop.MyLog;
import com.bh.common.constants.ParamConstant;
import com.bh.common.result.RestResult;
import com.bh.system.params.AdminRequest;
import com.bh.system.params.AdminUpdateRequest;
import com.bh.system.service.AdminService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Map;

/**
 * Created by Mr.BH
 * 管理员 管理模块
 */
@RestController
@RequestMapping(value = "/sys/admin")
@Api(value = "管理员api", description="管理员api")
@Validated
public class AdminController {

    @Autowired
    private AdminService adminService;

    /**
     * 登录
     * @param map
     */

    @MyLog(value="登录")
    @ApiOperation(value = "登陆接口", notes = "根据用户名密码登陆系统")
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public RestResult login(@RequestBody Map map){
        return adminService.login(map);
    }


    /**
     * 退出
     * @param Authorization
     * @return
     */
    @ApiOperation(value = "退出接口", notes = "退出系统")
    @RequestMapping(value = "/logout",method = RequestMethod.POST)
    public RestResult logout(@RequestHeader("Authorization") String Authorization){
        return adminService.logout(Authorization);
    }



    /**
     * 添加管理员
     * @param adminRequest
     * @param result
     * @return
     */
    @ApiOperation(value = "管理员保存接口", notes = "管理员保存,图片上传参数：file，单文件上传")
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public RestResult save(@Valid AdminRequest adminRequest, BindingResult result) {
        return adminService.saveAdmin(adminRequest);
    }


    /**
     * 批量添加
     * @param adminRequests
     * @param result
     * @return
     */
    @RequestMapping(value = "/batch",method = RequestMethod.POST)
    public RestResult saveBatch(@RequestBody @Valid @Size(min = 1,message = ParamConstant.PARAMS_LIST_NOT_NULL)
                                            List<AdminRequest> adminRequests, BindingResult result){
        return adminService.saveBatch(adminRequests);
    }


    /**
     * 删除、批量删除
     * @param map
     * @return
     */
    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    public RestResult delete(@RequestBody Map map){
        List<Integer> pkIds = (List<Integer>) map.get("pkIds");
        System.out.println(pkIds);
        return adminService.deleteAdmin(pkIds);
    }


    /**
     * 修改信息
     * @param adminUpdateRequest
     * @param result
     * @return
     */
    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public RestResult update(@Valid AdminUpdateRequest adminUpdateRequest, BindingResult result){
        return adminService.updateAdmin(adminUpdateRequest);
    }


    /**
     * 修改管理员状态
     *
     * @param pkId
     * @return
     */
    @RequestMapping(value = "/setStatus",method = RequestMethod.GET)
    @ApiOperation(value = "修改管理员状态接口", notes = "修改管理员状态")
    public RestResult setStatus(@RequestParam Integer pkId){
        return adminService.setStatus(pkId);
    }


    /**
     * 修改密码
     * @param map
     */
    @RequestMapping(value = "/changePassword",method = RequestMethod.POST)
    @ApiOperation(value = "修改密码接口", notes = "修改密码,参数：pkId：整型，oldPassword：字符串，newPassword：字符串")
    public RestResult changePassword(@RequestBody Map map){
        Integer pkId = Integer.parseInt((String) map.get("pkId"));
        String oldPassword = (String) map.get("oldPassword");
        String newPassword = (String) map.get("newPassword");
        return adminService.changePassword(pkId, oldPassword, newPassword);
    }


    /**
     * 重置密码
     *
     * @param pkId
     * @return
     */
    @RequestMapping(value = "/resetPassword",method = RequestMethod.GET)
    @ApiOperation(value = "重置密码接口", notes = "重置密码")
    public RestResult resetPassword(@RequestParam Integer pkId){
        return adminService.resetPassword(pkId);
    }


    /**
     * 获取管理员信息  pkId / ukAccount
     * @param type
     * @param keys
     * @return
     */
    @RequestMapping(value = "/get",method = RequestMethod.GET)
    public RestResult getAdmin( @RequestParam String type,
                                @RequestParam String keys){
        return adminService.getAdmin(type,keys);
    }


    /**
     * 管理员列表
     * @param map
     */
    @RequestMapping(value = "/list",method = RequestMethod.POST)
    public RestResult list(@RequestBody Map map){
        String keyword = (String) map.get("keyword");
        Integer pageNum = (Integer) map.get("pageNum");
        Integer pageSize = (Integer) map.get("pageSize");
        return adminService.listAdmin(keyword, pageNum, pageSize);
    }
}
