package com.baizhi.Shiro;

import com.baizhi.entity.Authority;
import com.baizhi.entity.Role;
import com.baizhi.entity.User;
import com.baizhi.mapper.RoleMapper;
import com.baizhi.mapper.UserMapper;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletContext;
import java.util.List;

public class MyRealm extends AuthorizingRealm {
    @Autowired
    UserMapper userMapper;
    @Autowired
    RoleMapper roleMapper;

    @Override //授权
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String primaryPrincipal = (String) principalCollection.getPrimaryPrincipal();//获得用户名
        //通过该用户名查询该用户的角色
        List<Role> roleList = roleMapper.queryAllRoleByPhone(primaryPrincipal);

        AuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();//授权信息  添加权限
        //通过角色获取到权限，将权限添加至授权信息中
        if (!roleList.isEmpty()) {
            for (Role role : roleList) {
                List<Authority> authorities = roleMapper.queryAllAuthorityByRole(role.getRoleName());
                ((SimpleAuthorizationInfo) authorizationInfo).addRole(role.getRoleName());
                if (!authorities.isEmpty()) {
                    for (Authority authority : authorities) {
                        //将权限添加到授权信息中
                        ((SimpleAuthorizationInfo) authorizationInfo).addStringPermission(authority.getAuthority());
                    }
                }

            }
        }

        return authorizationInfo;
    }

    @Override //认证
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String tokenPrincipal = (String) authenticationToken.getPrincipal();//获取身份信息
        User user=new User();
        user.setPhone(tokenPrincipal);
        User user1 = userMapper.selectOne(user);
        AuthenticationInfo authenticationInfo = null;
        if (user1 != null) {//认证器
            authenticationInfo = new SimpleAuthenticationInfo(user1.getPhone(), user1.getPassword(), ByteSource.Util.bytes(user1.getSalt()), this.getName());
        }
        return authenticationInfo;
    }
}
