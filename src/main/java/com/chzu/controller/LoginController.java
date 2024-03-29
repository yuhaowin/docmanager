package com.chzu.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class LoginController {

    /**
     * 用户登录操作
     *
     * @param userName
     * @param passWord
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(String userName, String passWord, String role, Model model) {
        //Shiro实现登录
        UsernamePasswordToken token = new UsernamePasswordToken(userName, passWord);
        Subject subject = SecurityUtils.getSubject();
        //如果用户名获取不到则登录失败，然后直接抛出异常
        subject.login(token);

        if (subject.hasRole("admin") && "admin".equalsIgnoreCase(role)) {
            return "redirect:/admin/showStudent";
        } else if (subject.hasRole("teacher") && "teacher".equalsIgnoreCase(role)) {
            return "redirect:/teacher/showCourse";
        } else if (subject.hasRole("student") && "student".equalsIgnoreCase(role)) {
            return "redirect:/student/selectedCourse";
        }
        model.addAttribute("message", "暂无该权限");
        return "error";
    }
}
