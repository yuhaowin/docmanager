package com.chzu.controller;

import com.chzu.dao.UserDao;
import com.chzu.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
@RequestMapping(value = "/login")
public class LoginController {

    @Autowired
    private UserDao dao;

    @RequestMapping(method = RequestMethod.GET)
    public String get() {
        User user = new User(1223,"yuhao","123321");
        dao.addUser(user);
        return "login";
    }

}
