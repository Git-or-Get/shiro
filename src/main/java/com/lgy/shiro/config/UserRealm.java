package com.lgy.shiro.config;

import com.lgy.shiro.pojo.User;
import com.lgy.shiro.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

public class UserRealm extends AuthorizingRealm {

    @Autowired
    UserService userService;

    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
//        info.addStringPermission("user:add");

        //拿到当前对象
        Subject subject = SecurityUtils.getSubject();
        //拿到User对象
        User principal = (User) subject.getPrincipal();
        //设置当前用户的权限
        info.addStringPermission(principal.getPerms());
        return info;
    }

    //认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

        UsernamePasswordToken userToken = (UsernamePasswordToken) token;

        //真实用户名
        User user = userService.queryUserByName(userToken.getUsername());

        if(user==null){
            return null;
        }
        //隐藏登录按钮，设置session对象！
        Subject subject1 = SecurityUtils.getSubject();
        Session session = subject1.getSession();
        session.setAttribute("loginUser",user);
        //密码shiro做
        return new SimpleAuthenticationInfo(user,user.getPwd(),"");
    }
}
