package com.bh.system.service;

import com.bh.model.domain.Admin;
import com.bh.common.result.RestResult;

import java.util.List;
import java.util.Map;

/**
 * Created by Mr.BH
 */
public interface AdminService {


    /**
     * 登录
     * @param map
     * @return
     */
    RestResult login(Map map);


    /**
     * 退出
     * @param Authorization
     * @return
     */
    RestResult logout(String Authorization);


    /**
     * 添加 管理员
     * @param admin
     * @return
     */
    RestResult saveAdmin(Admin admin);


    /**
     * 批量添加 管理员
     * @param admins
     * @return
     */
    RestResult saveBatch(List<Admin> admins);


    /**
     * 根据 pkId  删除管理员
     * @param ids
     * @return
     */
    RestResult deleteAdmin(List<Integer> ids);


    /**
     * 修改 管理员信息
     * @param admin
     * @return
     */
    RestResult updateAdmin(Admin admin);


    /**
     * 获取管理员信息  根据主键ID\账户名
     * @param type
     * @param keys
     * @return
     */
    RestResult getAdmin(String type, String keys);


    /**
     * 管理员列表
     * @param keyword
     * @param pageNum
     * @param pageSize
     * @return
     */
    RestResult listAdmin(String keyword, Integer pageNum, Integer pageSize);


    /**
     * 修改管理员状态
     * @param id
     * @return
     */
    RestResult setStatus(Integer id);

    /**
     * 修改密码
     * @param id
     * @param newPassword
     * @return
     */
    RestResult changePassword(Integer id, String oldPassword, String newPassword);

    /**
     * 重置密码
     * @param id
     * @return
     */
    RestResult resetPassword(Integer id);

    /**
     * 通过邮箱获取用户，看该邮箱是否已注册
     * @param email
     * @return
     */
    Admin findByEmail(String email);

    /**
     * 重新设置密码
     * @param userName
     * @param newPassword
     * @return
     */
    RestResult resetPassword(String userName, String newPassword);
}
