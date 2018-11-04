package com.stylefeng.guns.core.shiro;

import com.stylefeng.guns.core.shiro.factory.IShiro;
import com.stylefeng.guns.core.shiro.factory.ShiroFactroy;
import com.stylefeng.guns.core.util.ToolUtil;
import com.stylefeng.guns.modular.system.model.User;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ShiroDbRealm extends AuthorizingRealm {

    /**
     * 登录认证
     * 就是告诉shiro正确的密码是什么，然后进行验证
     * 登录时数据库和shiro做的交互
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken)
            throws AuthenticationException {
        IShiro shiroFactory = ShiroFactroy.me();
        UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
        //这里抛出异常的话会反馈显示到页面上
        User user = shiroFactory.user(token.getUsername()); //通过账号获取user实体
        //shiro可识别的一个对象
        ShiroUser shiroUser = shiroFactory.shiroUser(user);
        //realName 类的名称
        //将查到的东西返回给SimpleAuthenticationInfo这个对象，并传给shiro做交互，检验密码是否正确
        return shiroFactory.info(shiroUser, user, super.getName());
    }

    /**
     * 权限认证
     * 告诉shiro（这个用户）有哪些角色，哪些权限，
     * 不然权限的管理怎么控制
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        IShiro shiroFactory = ShiroFactroy.me();
        ShiroUser shiroUser = (ShiroUser) principals.getPrimaryPrincipal();
        List<Integer> roleList = shiroUser.getRoleList();

        //从数据库中查出来 这样shiro才知道当前登录用户有哪些角色哪些权限
        //当前登录用户相关的权限
        Set<String> permissionSet = new HashSet<>();
        //当前登录用户相关的角色
        Set<String> roleNameSet = new HashSet<>();

        for (Integer roleId : roleList) {
            //从数据库查出权限
            List<String> permissions = shiroFactory.findPermissionsByRoleId(roleId);
            if (permissions != null) {
                for (String permission : permissions) {
                    if (ToolUtil.isNotEmpty(permission)) {
                        permissionSet.add(permission);
                    }
                }
            }
            //从数据库查出名称
            String roleName = shiroFactory.findRoleNameByRoleId(roleId);
            roleNameSet.add(roleName);
        }

        //主要目的，生成这个，返回给shiro，告诉shiro我们的权限角色都包含在这，封装了
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.addStringPermissions(permissionSet);
        info.addRoles(roleNameSet);
        return info;
    }

    /**
     * 设置认证加密方式
     * 设置密码的加密方式
     * 不仅仅是密码+盐值直接加密的方法
     * 可能还有它自己独特的算法加密
     */
    @Override
    public void setCredentialsMatcher(CredentialsMatcher credentialsMatcher) {
        HashedCredentialsMatcher md5CredentialsMatcher = new HashedCredentialsMatcher();
        md5CredentialsMatcher.setHashAlgorithmName(ShiroKit.hashAlgorithmName);
        md5CredentialsMatcher.setHashIterations(ShiroKit.hashIterations);
        super.setCredentialsMatcher(md5CredentialsMatcher);
    }
}
