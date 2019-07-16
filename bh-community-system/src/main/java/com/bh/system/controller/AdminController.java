package com.bh.system.controller;

import com.bh.common.aop.MyLog;
import com.bh.common.constants.ParamConstant;
import com.bh.common.result.RestResult;
import com.bh.common.result.ResultUtils;
import com.bh.common.utils.MailUtil;
import com.bh.common.utils.VerifyCodeUtils;
import com.bh.model.domain.Admin;
import com.bh.system.params.AdminRequest;
import com.bh.system.params.AdminUpdateRequest;
import com.bh.system.service.AdminService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.web.session.HttpServletSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import javax.validation.constraints.Size;
import java.io.IOException;
import java.io.OutputStream;
import java.time.LocalDateTime;
import java.time.ZoneId;
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
     * 获取图片验证码
     * @param response
     * @param request
     * @throws IOException
     */
    @ApiOperation(value = "获取图片验证码接口", notes = "获取图片验证码")
    @RequestMapping(value="/image",method=RequestMethod.GET)
    public void authImage(HttpServletResponse response, HttpServletRequest request) throws IOException {
        HttpSession session = request.getSession();
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        response.setContentType("image/jpeg");
        // 生成随机字串
        String verifyCode = VerifyCodeUtils.generateVerifyCode(4);// 图片验证码位数
        session.removeAttribute("verCode");
        session.removeAttribute("codeTime");

        session.setAttribute("verCode", verifyCode.toLowerCase());
        session.setAttribute("codeTime", LocalDateTime.now());

        // 生成图片
        int w = 100, h = 30;// 生成图片验证码的宽高
        OutputStream out = response.getOutputStream();
        VerifyCodeUtils.outputImage(w, h, out, verifyCode);
    }

    /**
     * 获取邮箱验证码
     * @param request
     * @param toMail
     * @param code
     * @return
     * @throws MessagingException
     */
    @ApiOperation(value = "获取邮箱验证码接口", notes = "获取邮箱验证码")
    @RequestMapping(value="/sendMail",method=RequestMethod.GET)
    public RestResult sendMail(HttpServletRequest request, String toMail, String code) throws MessagingException {
        HttpSession session = request.getSession();

        String verCode = (String)session.getAttribute("verCode");
        if (null == verCode) {
            session.removeAttribute("verCode");
            session.removeAttribute("codeTime");
            return ResultUtils.error(1, "验证码已失效，请重新输入");
        }
        String verCodeStr = verCode.toString();
        LocalDateTime localDateTime = (LocalDateTime)session.getAttribute("codeTime");

        long past = localDateTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
        long now = LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();

        if(code == null || code.isEmpty() || !verCodeStr.equalsIgnoreCase(code)){
            return ResultUtils.error(1, "验证码错误");
        } else if((now-past)/1000/60>10){//10分钟
            session.removeAttribute("verCode");
            session.removeAttribute("codeTime");
            return ResultUtils.error(1, "验证码已过期，重新获取");
        } else {
            //验证成功，删除存储的验证码
            session.removeAttribute("verCode");
            session.removeAttribute("codeTime");
            // 生成随机字串
            String verifyCode = VerifyCodeUtils.generateVerifyCode(4);
            session.removeAttribute("mailCode");
            session.removeAttribute("mailCodeTime");

            session.setAttribute("mailCode", verifyCode.toLowerCase());
            session.setAttribute("mailCodeTime", LocalDateTime.now());

            MailUtil.send_mail(toMail,verifyCode);
            return ResultUtils.error(0, "邮箱验证码发送成功");
        }
    }

    /**
     * 验证邮箱验证码接口
     * @param request
     * @param code
     * @return
     */
    @ApiOperation(value = "验证邮箱验证码接口", notes = "验证邮箱验证码")
    @RequestMapping(value="/verifyMailCode",method=RequestMethod.GET)
    public RestResult verifyMailCode(HttpServletRequest request, String code){
        HttpSession session = request.getSession();

        String verCode = (String)session.getAttribute("mailCode");
        if (null == verCode) {
            session.removeAttribute("mailCode");
            session.removeAttribute("mailCodeTime");
            return ResultUtils.error(1, "验证码错误");
        }
        String verCodeStr = verCode.toString();
        LocalDateTime localDateTime = (LocalDateTime)session.getAttribute("mailCodeTime");

        long past = localDateTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
        long now = LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();

        if(code == null || code.isEmpty() || !verCodeStr.equalsIgnoreCase(code)){
            return ResultUtils.error(1, "验证码错误");
        } else if((now-past)/1000/60>10){//10分钟
            session.removeAttribute("mailCode");
            session.removeAttribute("mailCodeTime");
            return ResultUtils.error(1, "验证码已过期，重新获取");
        } else {
            //验证成功，删除存储的验证码
            session.removeAttribute("mailCode");
            session.removeAttribute("mailCodeTime");

            return ResultUtils.error(0, "邮箱验证码验证成功");
        }
    }

    /**
     * 验证邮箱是否已注册
     * @param email
     */
    @RequestMapping(value = "/verifyMail",method = RequestMethod.GET)
    @ApiOperation(value = "验证邮箱是否已注册接口", notes = "验证邮箱是否已注册")
    public RestResult verifyMail(String email){
        // 验证邮箱是否注册
        Admin admin = adminService.findByEmail(email);
        if(admin == null){
            return ResultUtils.success("该邮箱未注册");
        }
        return ResultUtils.error(1,"该邮箱已注册，请更换邮箱");
    }

    /**
     * 忘记密码的重新设置密码
     * @param map
     */
    @RequestMapping(value = "/resetPassword",method = RequestMethod.POST)
    @ApiOperation(value = "忘记密码的重新设置密码接口", notes = "忘记密码的重新设置密码，传输json串,参数：userName：用户名，newPassword：字符串")
    public RestResult resetPassword(@RequestBody Map map){
        String userName = (String) map.get("userName");
        String newPassword = (String) map.get("newPassword");
        return adminService.resetPassword(userName, newPassword);
    }

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
    @MyLog(value="退出")
    @ApiOperation(value = "退出接口", notes = "退出系统")
    @RequestMapping(value = "/logout",method = RequestMethod.POST)
    public RestResult logout(@RequestHeader("Authorization") String Authorization){
        return adminService.logout(Authorization);
    }


    /**
     * 通过注册新增管理员
     * @param admin
     * @param result
     * @return
     */
    @ApiOperation(value = "注册管理员接口", notes = "管理员保存，注册的管理员需要进行审核")
    @RequestMapping(value = "/registerSave", method = RequestMethod.POST)
    public RestResult registerSave(Admin admin, BindingResult result) {
        admin.setAdminType(1);
        admin.setIsAudit(1);// 需要审核
        admin.setAuditStatus(0);// 默认为待审核
        return adminService.saveAdmin(admin);
    }


    /**
     * 添加管理员
     * @param admin
     * @param result
     * @return
     */
    @MyLog(value="新增管理员")
    @ApiOperation(value = "管理员保存接口", notes = "管理员保存")
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public RestResult save(Admin admin, BindingResult result) {
        admin.setIsAudit(0);// 无需审核
        admin.setAuditStatus(1);// 默认审核通过
        return adminService.saveAdmin(admin);
    }


    /**
     * 批量添加
     * @param adminRequests
     * @param result
     * @return
     */
//    @RequestMapping(value = "/batch",method = RequestMethod.POST)
//    public RestResult saveBatch(@RequestBody @Valid @Size(min = 1,message = ParamConstant.PARAMS_LIST_NOT_NULL)
//                                            List<Admin> adminRequests, BindingResult result){
//        return adminService.saveBatch(adminRequests);
//    }


    /**
     * 删除、批量删除
     * @param map
     * @return
     */
    @MyLog(value="删除管理员")
    @ApiOperation(value = "删除、批量删除接口", notes = "删除、批量删除")
    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    public RestResult delete(@RequestBody Map map){
        List<Integer> ids = (List<Integer>) map.get("ids");
        System.out.println(ids);
        return adminService.deleteAdmin(ids);
    }


    /**
     * 修改信息
     * @param admin
     * @param result
     * @return
     */
    @MyLog(value="修改管理员信息")
    @ApiOperation(value = "修改管理员信息接口", notes = "修改管理员信息")
    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public RestResult update(Admin admin, BindingResult result){
        return adminService.updateAdmin(admin);
    }


    /**
     * 修改管理员状态
     *
     * @param id
     * @return
     */
    @MyLog(value="修改管理员状态")
    @RequestMapping(value = "/setStatus",method = RequestMethod.GET)
    @ApiOperation(value = "修改管理员状态接口", notes = "修改管理员状态（禁用和启用）")
    public RestResult setStatus(@RequestParam Integer id){
        return adminService.setStatus(id);
    }


    /**
     * 修改密码
     * @param map
     */
    @RequestMapping(value = "/changePassword",method = RequestMethod.POST)
    @ApiOperation(value = "修改密码接口", notes = "修改密码,参数：id：整型，oldPassword：字符串，newPassword：字符串")
    public RestResult changePassword(@RequestBody Map map){
        Integer id = Integer.parseInt((String) map.get("id"));
        String oldPassword = (String) map.get("oldPassword");
        String newPassword = (String) map.get("newPassword");
        return adminService.changePassword(id, oldPassword, newPassword);
    }


    /**
     * 重置密码
     *
     * @param id
     * @return
     */
//    @RequestMapping(value = "/resetPassword",method = RequestMethod.GET)
//    @ApiOperation(value = "重置密码接口", notes = "重置密码")
//    public RestResult resetPassword(@RequestParam Integer id){
//        return adminService.resetPassword(id);
//    }


    /**
     * 获取管理员信息  id / userName
     * @param type
     * @param keys
     * @return
     */
    @ApiOperation(value = "获取管理员信息接口", notes = "获取管理员信息")
    @RequestMapping(value = "/get",method = RequestMethod.GET)
    public RestResult getAdmin( @RequestParam String type,
                                @RequestParam String keys){
        return adminService.getAdmin(type,keys);
    }


    /**
     * 管理员列表
     * @param map
     */
    @ApiOperation(value = "获取管理员列表接口", notes = "获取管理员列表")
    @RequestMapping(value = "/list",method = RequestMethod.POST)
    public RestResult list(@RequestBody Map map){
        String keyword = (String) map.get("keyword");
        Integer pageNum = (Integer) map.get("pageNum");
        Integer pageSize = (Integer) map.get("pageSize");
        return adminService.listAdmin(keyword, pageNum, pageSize);
    }
}
