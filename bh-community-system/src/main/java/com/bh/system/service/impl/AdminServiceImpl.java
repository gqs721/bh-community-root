package com.bh.system.service.impl;

import com.bh.common.aop.WebLogAspect;
import com.bh.common.constants.AdminConstant;
import com.bh.common.constants.ParamConstant;
import com.bh.common.enums.ResultCodeEnum;
import com.bh.common.exceptions.APIRunTimeException;
import com.bh.common.exceptions.RoleNotExistException;
import com.bh.common.result.PageResult;
import com.bh.common.result.RestResult;
import com.bh.common.result.ResultUtils;
import com.bh.common.utils.DateUtil;
import com.bh.common.utils.EncryptUtils;
import com.bh.common.utils.JWTUtils;
import com.bh.common.utils.StringUtil;
import com.bh.model.dao.AdminMapper;
import com.bh.model.dao.AdminRoleMapper;
import com.bh.model.dao.RoleMapper;
import com.bh.model.domain.Admin;
import com.bh.model.domain.AdminRole;
import com.bh.model.domain.Role;
import com.bh.system.params.AdminRequest;
import com.bh.system.params.AdminUpdateRequest;
import com.bh.system.redis.JWTRedisDAO;
import com.bh.system.service.AdminService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * Created by Mr.BH
 */
@Service
@Transactional
public class AdminServiceImpl implements AdminService {


    @Autowired
    private AdminMapper adminMapper;

    @Autowired
    private AdminRoleMapper adminRoleMapper;

    @Autowired
    private JWTRedisDAO jwtRedisDAO;


    @Autowired
    private RoleMapper roleMapper;

    @Override
    public RestResult login(Map map) {
        String account = (String) map.get("ukAccount");
        // 参数校验
        if (StringUtil.isEmpty(account)){
            return ResultUtils.error(ResultCodeEnum.ILLEGAL_ARGUMENT.getCode(),ResultCodeEnum.ILLEGAL_ARGUMENT.getMsg());
        }
        // 验证账户是否存在
        Admin admin = adminMapper.getByAuthenticatorUkAccount(account);
        if (null == admin){
            return ResultUtils.error(ResultCodeEnum.USER_NOT_FOUND.getCode(),ResultCodeEnum.USER_NOT_FOUND.getMsg());
        }
        // 验证密码
        String password = (String) map.get("password");
        if (!admin.getPassword().equals(EncryptUtils.encrypt(password,admin.getSalt()))){
            return ResultUtils.error(ResultCodeEnum.USER_PASSWORD_NOT_MATCH.getCode(),ResultCodeEnum.USER_PASSWORD_NOT_MATCH.getMsg());
        }
        // 验证是否禁用
        if (1 == admin.getStatus()){
            return ResultUtils.error(ResultCodeEnum.USER_BAN.getCode(),ResultCodeEnum.USER_BAN.getMsg());
        }

        WebLogAspect.getUser(admin);

        // 生成 token
        Map domain = new HashMap();
        domain.put(AdminConstant.PKID, admin.getPkId());
        domain.put(AdminConstant.UKACCOUNT, admin.getUkAccount());
        domain.put("time", System.currentTimeMillis());
        String token = JWTUtils.generateToken(AdminConstant.JWT_SALT, domain);

        // 持久化到 Redis
        jwtRedisDAO.set(AdminConstant.JWT_TOKEN + account, token);

        // 返回数据
        Map adminMap = adminMapper.getAdminByUkAccount(account);
        Map params = new HashMap();
        params.put("admin",adminMap);
        params.put("token",token);

        return ResultUtils.success(params);
    }

    @Override
    public RestResult logout(String authorization) {

        Map params = JWTUtils.getClaims(AdminConstant.JWT_SALT, authorization);
        if (StringUtil.isEmpty((String) params.get(AdminConstant.UKACCOUNT))){
            return ResultUtils.error(ResultCodeEnum.ILLEGAL_ARGUMENT.getCode(),ResultCodeEnum.ILLEGAL_ARGUMENT.getMsg());
        }
        // 更新 redis token
        jwtRedisDAO.delete(AdminConstant.JWT_TOKEN+params.get(AdminConstant.UKACCOUNT));
        return ResultUtils.success(null);
    }

    @Override
    public RestResult saveAdmin(AdminRequest adminRequest) {

        // 判断 是否已经存在
        if (null != adminMapper.getAdminByUkAccount(adminRequest.getUkAccount())) {
            return ResultUtils.error(ResultCodeEnum.ACCOUNT_EXIST.getCode(),ResultCodeEnum.ACCOUNT_EXIST.getMsg());
        }

        Role role = roleMapper.selectByPrimaryKey(adminRequest.getRoleId());
        if (null == role){
            throw new RoleNotExistException(ResultCodeEnum.ROLE_NOT_FOUND.getCode(),ResultCodeEnum.ROLE_NOT_FOUND.getMsg());
        }


        Date date = DateUtil.getCurrentDate();

        // 初始化 管理员
        // 传递 date 作用：保持用户创建时间 与 用户角色关联信息创建时间一致性
        Admin admin = formatAdmin(adminRequest,date);

        adminMapper.insertSelective(admin);

        if (null == admin.getPkId()){
            throw new IllegalArgumentException();
        }

        // 持久化 用户角色关联信息
        AdminRole adminRole = formatAdminRole(adminRequest, admin.getPkId(), date);
        adminRoleMapper.insertSelective(adminRole);

        // 处理 返回特殊内容
        admin.setPassword(null);
        admin.setSalt(null);
        return ResultUtils.success(admin);
    }

    @Override
    public RestResult saveBatch(List<AdminRequest> adminRequests) {

        // 验证用户是否存在
        for (AdminRequest a:adminRequests
             ) {
            if (null != adminMapper.getAdminByUkAccount(a.getUkAccount()))
                throw new APIRunTimeException(ResultCodeEnum.ACCOUNT_EXIST.getCode(),ResultCodeEnum.ACCOUNT_EXIST.getMsg());
        }

        // 验证角色是否存在
        for (AdminRequest a:adminRequests){
            if (null == roleMapper.selectByPrimaryKey(a.getRoleId()))
                throw new RoleNotExistException(ResultCodeEnum.ROLE_NOT_FOUND.getCode(),ResultCodeEnum.ROLE_NOT_FOUND.getMsg());

        }

        List<Admin> admins = new ArrayList<>();
        Date currentDate = DateUtil.getCurrentDate();

        adminRequests.forEach(adminRequest -> admins.add(formatAdmin(adminRequest,currentDate)));

        // 批量持久化用户信息

        List<AdminRole> adminRoles = new ArrayList<>();
        for (int i=0; i<adminRequests.size(); i++){
            AdminRole adminRole = new AdminRole();
            adminRole.setAmdinId(admins.get(i).getPkId());
            adminRole.setRoleId(adminRequests.get(i).getRoleId());
            adminRole.setDeleteFlag(0);
            adminRole.setCreateTime(currentDate);
            adminRoles.add(adminRole);
        }

        // 批量持久化用户角色关联信息


        return ResultUtils.success(null);
    }

    @Override
    public RestResult deleteAdmin(List<Integer> pkIds) {
        if(pkIds == null || pkIds.isEmpty()){
            return ResultUtils.error(1,"没有选择需要删除的数据");
        }

        Integer[] ids = new Integer[pkIds.size()];

        if (pkIds.size() > 0){
            for (int i = 0; i < pkIds.size(); i++) {
                ids[i] = pkIds.get(i);
            }
        }

        // 管理员信息 逻辑删除
        adminMapper.deleteBatch(ids);
        // 管理员与角色关联信息 物理删除
//        adminRoleMapper.deleteBatch(ids);
        return ResultUtils.success(null);
    }

    @Override
    public RestResult updateAdmin(AdminUpdateRequest adminUpdateRequest) {

        Date currentDate = DateUtil.getCurrentDate();

//        Admin oldAdmin = adminMapper.selectByPrimaryKey(adminUpdateRequest.getPkId());

        // 初始化 修改信息
        Admin admin = new Admin();
        admin.setPkId(adminUpdateRequest.getPkId());
        admin.setNickname(adminUpdateRequest.getNickname());
//        admin.setStatus(adminUpdateRequest.getStatus());
        admin.setPhone(adminUpdateRequest.getPhone());
        admin.setEmail(adminUpdateRequest.getEmail());
        admin.setUpdateTime(currentDate);

//        String salt = oldAdmin.getSalt();
//        String newPass = EncryptUtils.encrypt(adminUpdateRequest.getPassword(), salt);
//        admin.setPassword(newPass);


//        adminMapper.updateByPrimaryKey(admin);
        adminMapper.updateByPrimaryKeySelective(admin);

        List<Integer> roleIds = adminRoleMapper.getAdminRoles(admin.getPkId());

        // 删除之前 管理员角色关联信息
        adminRoleMapper.deleteByAdminId(admin.getPkId());

        // 添加新的关联信息
        AdminRole adminRole = formatAdminRole(adminUpdateRequest,admin.getPkId(),currentDate);
        adminRoleMapper.insertSelective(adminRole);

        // 修改管理员信息时，如果有修改角色，那就清空用户的token，让用户重新登录
        if(!roleIds.isEmpty()){
            for (int i = 0; i < roleIds.size(); i++){
                if(roleIds.get(i) != adminRole.getRoleId()){
                    Admin a = adminMapper.selectByPrimaryKey(admin.getPkId());
                    jwtRedisDAO.delete(AdminConstant.JWT_TOKEN + a.getUkAccount());
                }
            }
        }

        return ResultUtils.success(null);
    }

    @Override
    public RestResult getAdmin(String type, String keys) {

        if (AdminConstant.PKID.equals(type)){
            Integer pkId = Integer.valueOf(keys);
            return ResultUtils.success(adminMapper.selectByPrimaryKey(pkId));
        }else if (AdminConstant.UKACCOUNT.equals(type)){
            return ResultUtils.success(adminMapper.getAdminByUkAccount(keys));
        }
        return ResultUtils.error(ResultCodeEnum.ILLEGAL_ARGUMENT.getCode(),ResultCodeEnum.ILLEGAL_ARGUMENT.getMsg());
    }

    @Override
    public RestResult listAdmin(String keyword, Integer pageNum, Integer pageSize) {

        int n = pageNum == 1 ? pageNum = 0 : (pageNum = (pageNum - 1) * pageSize);

        // 查询参数
        Map domainMap = new HashMap();
        domainMap.put(ParamConstant.KEY_WORD,keyword);
        domainMap.put(ParamConstant.PAGE_SIZE,pageSize);
        domainMap.put(ParamConstant.PAGE_NUM,pageNum);

        List<Map> admins = adminMapper.listPage(domainMap);
        for (Map map:admins
             ) {
            map.put("createTime", DateUtil.parseDate((Date) map.get("createTime")));
        }
        int count = adminMapper.countPage(domainMap);

        return ResultUtils.success(new PageResult<Map>(admins,count));
    }


    // ********* 自定义 封装功能函数 ***********

    public Admin formatAdmin(AdminRequest adminRequest, Date currentDate){

        Date date = currentDate;

        // 初始化 管理员
        Admin admin = new Admin();
        admin.setUkAccount(adminRequest.getUkAccount());
        admin.setNickname(adminRequest.getNickname());
        admin.setPhone(adminRequest.getPhone());
        admin.setEmail(adminRequest.getEmail());
        admin.setAvatar(adminRequest.getAvatar());
        admin.setAddress(adminRequest.getAddress());
        admin.setStatus(0);
        admin.setDeleteFlag(0);
        admin.setCreateTime(date);
        admin.setSortTime(date);

        // 生成盐值 加密密码 并持久化管理员信息
        String salt = EncryptUtils.createSalt();
        String newPass = EncryptUtils.encrypt("123456", salt);// 设置默认密码
        String authenticator = EncryptUtils.encrypt(adminRequest.getUkAccount(), salt);      // 散列加密（account + salt） 用于 shiro 的身份认证
        admin.setPassword(newPass);
        admin.setSalt(salt);
        admin.setAuthenticator(authenticator);

        return admin;
    }

    public AdminRole formatAdminRole(Object param, Integer pkId, Date currentDate){
        AdminRole adminRole = new AdminRole();
        if (param instanceof AdminRequest){
            AdminRequest adminRequest = (AdminRequest) param;
            adminRole.setAmdinId(pkId);
            adminRole.setRoleId(adminRequest.getRoleId());
            adminRole.setDeleteFlag(0);
            adminRole.setCreateTime(currentDate);
        }else if (param instanceof AdminUpdateRequest){
            AdminUpdateRequest adminUpdateRequest = (AdminUpdateRequest) param;
            adminRole.setAmdinId(pkId);
            adminRole.setRoleId(adminUpdateRequest.getRoleId());
            adminRole.setDeleteFlag(0);
            adminRole.setCreateTime(currentDate);
        }
        return adminRole;
    }

    @Override
    public RestResult setStatus(Integer pkId){
        Admin admin = adminMapper.selectByPrimaryKey(pkId);
        if(admin.getStatus() == 0){// 设置禁用
            admin.setStatus(1);
        }else{ //设置启用
            admin.setStatus(0);
        }
        adminMapper.updateByPrimaryKeySelective(admin);

        jwtRedisDAO.delete(AdminConstant.JWT_TOKEN + admin.getUkAccount());

        return ResultUtils.success(admin);
    }

    @Override
    public RestResult changePassword(Integer pkId, String oldPassword, String newPassword){
        Admin admin = adminMapper.selectByPrimaryKey(pkId);
        String salt = admin.getSalt();
        String oldPass = EncryptUtils.encrypt(oldPassword, salt);
        if(!StringUtil.CheckIsEqual(oldPass, admin.getPassword())){
            return ResultUtils.error(1,"原密码输入错误，请重新输入");
        }
        String newPass = EncryptUtils.encrypt(newPassword, salt);
        admin.setPassword(newPass);
        adminMapper.updateByPrimaryKeySelective(admin);
        return ResultUtils.success("密码修改成功，请重新登录");
    }

    @Override
    public RestResult resetPassword(Integer pkId){
        Admin admin = adminMapper.selectByPrimaryKey(pkId);
        String salt = admin.getSalt();
        String newPass = EncryptUtils.encrypt("123456", salt);
        admin.setPassword(newPass);
        adminMapper.updateByPrimaryKeySelective(admin);
        return ResultUtils.success("密码重置成功");
    }
}
