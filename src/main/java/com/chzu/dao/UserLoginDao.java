package com.chzu.dao;

import com.chzu.entity.UserLogin;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserLoginDao {


    @Autowired
    private SessionFactory sessionFactory;

    /**
     * 用户登录时根据userName查询用户实体类
     * @param userName
     * @return
     */
    public UserLogin login(String userName){
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("from UserLogin where user_name = ?");
        query.setParameter(0, userName);
        List<UserLogin> list = query.list();
        if(list != null && list.size() > 0){
            return list.get(0);
        }
        return null;
    }

    public void addUser(UserLogin user) {
        Session session = sessionFactory.openSession();
        session.save(user);
    }


}
