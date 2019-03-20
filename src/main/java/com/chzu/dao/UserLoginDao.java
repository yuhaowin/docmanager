package com.chzu.dao;

import com.chzu.entity.User;
import com.chzu.entity.UserLogin;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserLoginDao {


    @Autowired
    private SessionFactory sessionFactory;

    /**
     * 用户登录时根据userId查询用户实体类
     * @param userId
     * @return
     */
    public UserLogin login(Integer userId){
        Session session = sessionFactory.openSession();
        UserLogin userLogin = (UserLogin) session.get(UserLogin.class, userId);
        return userLogin;
    }

    public void addUser(UserLogin user) {
        Session session = sessionFactory.openSession();
        session.save(user);
    }


}
