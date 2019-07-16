package com.bh.system.service.impl;

import com.bh.common.constants.AdminConstant;
import com.bh.common.enums.ResultCodeEnum;
import com.bh.common.result.PageResult;
import com.bh.common.result.RestResult;
import com.bh.common.result.ResultUtils;
import com.bh.common.utils.CollectionUtils;
import com.bh.common.utils.DateUtil;
import com.bh.model.dao.AdminMapper;
import com.bh.model.dao.AdminRoleMapper;
import com.bh.model.dao.RoleMapper;
import com.bh.model.dao.RoleResourceMapper;
import com.bh.model.domain.Admin;
import com.bh.model.domain.Role;
import com.bh.model.domain.RoleResource;
import com.bh.system.redis.JWTRedisDAO;
import com.bh.system.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by Mr.BH
 */
@Service
@Transactional
public class RoleSerivceImpl implements RoleService {


    @Autowired
    private RoleMapper roleMapper;
    
    @Autowired
    private RoleResourceMapper roleResourceMapper;

    @Autowired
    private AdminRoleMapper adminRoleMapper;

    @Autowired
    private AdminMapper adminMapper;

    @Autowired
    private JWTRedisDAO jwtRedisDAO;


    @Override
    @Transactional(rollbackFor = Exception.class)
    public RestResult add(Map map) {

        // 角色\资源 信息
        String ukName = (String) map.get("ukName");
        String remark = (String) map.get("remark");
        List<Integer> menus = (List<Integer>) map.get("menus");

        if (null == ukName || null == remark || menus.size() < 1 ){
            return ResultUtils.error(ResultCodeEnum.ILLEGAL_ARGUMENT.getCode(),ResultCodeEnum.ILLEGAL_ARGUMENT.getMsg());
        }
        
        Date currentDate = new Date();

        // 持久化角色信息
        Role role = new Role();
        role.setUkName(ukName);
        role.setRemark(remark);
        role.setCreateTime(currentDate);
        role.setUpdateTime(currentDate);
        role.setDeleteFlag(0);
        roleMapper.insertSelective(role);

        // 一级菜单\二级列表
        persistenceRoleResource(CollectionUtils.toSet(menus),role.getPkId(),currentDate);

        return ResultUtils.success(null);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public RestResult delete(List<Integer> roleIds) {
        Role role = null;
        for (Integer roleId:roleIds
             ) {
            role = roleMapper.selectByPrimaryKey(roleId);
            if (null != role){
                role.setDeleteFlag(1);
                roleMapper.updateByPrimaryKeySelective(role);
            }
        }
        return ResultUtils.success(null);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public RestResult update(Map map) {

        Integer roleId = (Integer) map.get("pkId");
        String ukName = (String) map.get("ukName");
        String remark = (String) map.get("remark");
        List<Integer> menus = (List<Integer>) map.get("menus");


        if (roleId == null || ukName == null || remark == null || menus.size() <1)
            return ResultUtils.error(ResultCodeEnum.ILLEGAL_ARGUMENT.getCode(),ResultCodeEnum.ILLEGAL_ARGUMENT.getMsg());

        Date currentDate = new Date();

        // 修改角色基本信息
        Role role = new Role();
        role.setPkId(roleId);
        role.setUkName(ukName);
        role.setRemark(remark);
        role.setUpdateTime(new Date());
        roleMapper.updateByPrimaryKeySelective(role);

        // 更新角色关联 资源信息
        roleResourceMapper.deleteByRoleId(roleId);
        persistenceRoleResource(CollectionUtils.toSet(menus), roleId, currentDate);

        //清空redis的用户token，让该角色所属的用户自动退到登陆页面
        List<Integer> adminIds = adminRoleMapper.getAdminRolesByRoleId(roleId);
        for (int i = 0; i < adminIds.size(); i++){
            Admin admin = adminMapper.selectByPrimaryKey(adminIds.get(i));
            if(admin != null)
                jwtRedisDAO.delete(AdminConstant.JWT_TOKEN + admin.getUserName());
        }

        return ResultUtils.success(null);
    }

    @Override
    public RestResult listPage(Map map) {
        List<Map> roles = roleMapper.listPage(map);
        // 格式化时间
        for (Map role:roles
                ) {
            role.put("createTime", DateUtil.parseDate((Date) role.get("createTime")));
        }
        int count = roleMapper.countPage(map);
        return ResultUtils.success(new PageResult<>(roles,count));
    }

    @Override
    public RestResult getAll() {


        return ResultUtils.success(roleMapper.getAll());
    }

    @Override
    public RestResult getRoleHaveResource(Integer roleId) {
        return ResultUtils.success(roleMapper.getRoleHaveResourceListId(roleId));
    }


    // ********* 抽取封装 *****
    
    public void persistenceRoleResource(Set<Integer> menus, Integer roleId, Date currentDate){
        for (Integer id:menus
             ) {
            RoleResource roleResource = new RoleResource();
            roleResource.setRoleId(roleId);
            roleResource.setResourceId(id);
            roleResource.setCreateTime(currentDate);
            roleResource.setUpdateTime(currentDate);
            roleResource.setDeleteFlag(0);
            roleResourceMapper.insertSelective(roleResource);
        }
    }

}
