package com.bh.system.shiro;

import com.bh.common.constants.AdminConstant;
import com.bh.common.exceptions.APIRunTimeException;
import com.bh.system.redis.JWTRedisDAO;
import com.bh.common.utils.StringUtil;
import com.bh.model.dao.AdminMapper;
import com.bh.model.dao.ResourceMapper;
import com.bh.model.dao.RoleMapper;
import com.bh.model.domain.Admin;
import com.bh.model.domain.Resource;
import com.bh.model.domain.Role;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Mr.BH
 */
@Slf4j
public class AdminRealm extends AuthorizingRealm {


    @Autowired
    private AdminMapper adminMapper;

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private ResourceMapper resourceMapper;

    @Autowired
    private JWTRedisDAO jwtRedisDAO;

    /**
     * 必须重写此方法，不然Shiro会报错
     */
    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof com.bh.system.shiro.ShiroToken;
    }


    /**
     * 权限认证
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

        String account = (String) principalCollection.getPrimaryPrincipal();
        Admin admin = adminMapper.getByAuthenticatorUserName(account);

        List<String> roleNames = new ArrayList<>();
        List<Integer> roleIds = new ArrayList<>();

        // 权限列表
        List<String> perms = new ArrayList<>();

        if (null != admin){
            // 获取当前 管理员 角色列表
            List<Role> roles = roleMapper.getRolesByAdminId(admin.getId());
            // 获取 角色名称列表、角色ID列表
            for (Role role: roles
                    ) {
                roleNames.add(role.getUkName());
                roleIds.add(role.getPkId());
            }

            if (roleIds.size() > 0){
                // 获取当前角色下的 资源列表
                List<Resource> resources = resourceMapper.getResourcesByRoleId(roleIds);

                for (Resource resource:resources
                     ) {
                    if (null != resource){
                        if (!StringUtil.isEmpty(resource.getPerms())){
                            if (resource.getPerms().contains(",")) {
                                String[] permsArray = resource.getPerms().split(",");
                                perms.addAll(Arrays.asList(permsArray));
                            } else {
                                perms.add(resource.getPerms());
                            }
                        }
                    }
                }
            }

        }else {
            throw new AuthorizationException();
        }

        //为当前用户设置角色和权限
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        authorizationInfo.addRoles(roleNames);
        authorizationInfo.addStringPermissions(perms);
        return authorizationInfo;
    }

    /**
     * 身份认证
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws APIRunTimeException {

        // 验证用户是否携带 token
        String principal  = (String) authenticationToken.getPrincipal();
        if (null == principal){
            throw new AuthorizationException();
        }

        // 获取 account 、 token
        String[] target = principal.split(":");
        String token = target[1];
        String account = target[0];

        // 验证用户是否存在
        Admin admin = adminMapper.getByAuthenticatorUserName(account);
        if (admin == null){
            // 账号不存在
            throw new APIRunTimeException(1,"account not exist");
//            throw new UnknownAccountException();
        }

        // 验证 token
        String redisToken = jwtRedisDAO.get(AdminConstant.JWT_TOKEN+account);
        if (null == redisToken || (!token.equals(redisToken))){
//            throw new AuthorizationException();
            throw new APIRunTimeException(1,"Token not matcher");
        }

        // 交给 AuthenticatingRealm 使用 CredentialsMatcher 进行密码匹配 ，可以自定实现
        SimpleAuthenticationInfo simpleAuthorizationInfo = new
                SimpleAuthenticationInfo(account, admin.getAuthenticator(), ByteSource.Util.bytes(admin.getSalt()), getName());

        return simpleAuthorizationInfo;
    }
}
