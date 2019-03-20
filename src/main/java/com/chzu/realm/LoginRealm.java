package com.chzu.realm;

import com.chzu.entity.Role;
import com.chzu.entity.UserLogin;
import com.chzu.service.UserLoginService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.Set;


@Component
public class LoginRealm extends AuthorizingRealm {


    @Resource
    private UserLoginService userLoginService;

    /**
     * 获取身份信息，我们可以在这个方法中，从数据库获取该用户的权限和角色信息
     * 当调用权限验证时，就会调用此方法
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String username = (String) getAvailablePrincipal(principalCollection);
        Role role = new Role();
        //通过用户名从数据库获取权限/角色信息
        try {
            UserLogin userLogin = userLoginService.login(username);
            //获取角色对象
            role = userLoginService.getRole(userLogin.getRole());
        } catch (Exception e) {
            e.printStackTrace();
        }
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        Set<String> r = new HashSet<String>();
        if (role != null) {
            r.add(role.getRoleName());
            info.setRoles(r);
        }
        return info;
    }

    /**
     * 在这个方法中，进行身份验证
     * login时调用
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        //用户名
        String username = (String) token.getPrincipal();
        //密码
        String password = new String((char[]) token.getCredentials());

        //UserLogin userlogin = null;
//        try {
//            //userlogin = userloginService.findByName(username);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

//        if (userlogin == null) {
//            //没有该用户名
//            throw new UnknownAccountException();
//        } else if (!password.equals(userlogin.getPassword())) {
//            //密码错误
//            throw new IncorrectCredentialsException();
//        }

        //身份验证通过,返回一个身份信息
        AuthenticationInfo aInfo = new SimpleAuthenticationInfo(username, password, getName());
        return aInfo;
    }
}
