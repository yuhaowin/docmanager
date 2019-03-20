package com.chzu.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
@RequestMapping("/login")
public class LoginController {

    @RequestMapping(value = "/main")
    public String toLogin() {

        //Shiro实现登录
        UsernamePasswordToken token = new UsernamePasswordToken("yuhao","123456");
        Subject subject = SecurityUtils.getSubject();

        //如果用户名获取不到则登录失败，然后直接抛出异常
        subject.login(token);

//        if (subject.hasRole("admin")) {
//            return "redirect:/admin/showStudent";
//        } else if (subject.hasRole("teacher")) {
//            return "redirect:/teacher/showCourse";
//        } else if (subject.hasRole("student")) {
//            return "redirect:/student/showCourse";
//        }
        return "login";
    }

    @RequestMapping(value = "user-login", method = RequestMethod.POST)
    public String login(Integer userId,String password){
        UsernamePasswordToken token = new UsernamePasswordToken("yuhao","123456");
        return null;
    }
}
