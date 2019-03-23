package com.chzu.controller;


import com.chzu.entity.UserLogin;
import com.chzu.exception.Globalexception;
import com.chzu.service.UserLoginService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;

/**
 * Created by Vinci on 2017/7/6.
 */
@Controller
public class RestPasswordController {

    @Resource(name = "userLoginService")
    private UserLoginService userLoginService;

    // 本账户密码重置
    @RequestMapping(value = "/passwordRest", method = {RequestMethod.POST})
    public String passwordRest(String oldPassword, String password1) throws Exception {
        Subject subject = SecurityUtils.getSubject();
        String username = (String) subject.getPrincipal();

        UserLogin userLogin = userLoginService.findByName(username);

        if (!oldPassword.equals(userLogin.getPassword())) {
            throw new Globalexception("旧密码不正确");
        } else {
            userLogin.setPassword(password1);
            userLoginService.updateByName(username, userLogin);
        }

        return "redirect:/logout";
    }

}
