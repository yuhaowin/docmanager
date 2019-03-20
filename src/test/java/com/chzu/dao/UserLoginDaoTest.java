package com.chzu.dao;

import com.chzu.entity.UserLogin;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/applicationContext-dao.xml"})
public class UserLoginDaoTest {

    @Autowired
    private UserLoginDao userLoginDao;
    @Test
    public void login() {
        UserLogin login = userLoginDao.login("1");
        UserLogin userLogin = new UserLogin();
        userLogin.setUserName("33232");
        userLogin.setPassword("343443");
        userLogin.setUserId(88);
        userLogin.setRole(1);
        userLoginDao.addUser(userLogin);

        System.out.println();
    }
}