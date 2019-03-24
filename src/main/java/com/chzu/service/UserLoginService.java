package com.chzu.service;

import com.chzu.dao.UserLoginDao;
import com.chzu.entity.UserLogin;
import com.chzu.exception.Globalexception;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 用户登录service
 */
@Service
public class UserLoginService {

    @Autowired
    private UserLoginDao userLoginDao;

    /**
     * 通过姓名查找
     *
     * @param name
     * @return
     */
    public UserLogin findByName(String name) throws Exception {

        List<UserLogin> list = userLoginDao.selectByName(name);
        if (list.size() == 0) {
            throw new Globalexception("未查到该用户");
        }
        return list.get(0);
    }


    /**
     * 保存用户信息
     *
     * @param userlogin
     */
    public void save(UserLogin userlogin) {
        userLoginDao.insert(userlogin);
    }

    /**
     * 通过名字删除
     *
     * @param name
     */
    public void removeByName(String name) {
        userLoginDao.deleteByName(name);
    }


    /**
     * 通过名字更新
     *
     * @param name
     * @param userLogin
     */
    public void updateByName(String name, UserLogin userLogin) {
        userLoginDao.updateByName(userLogin);

    }
}
