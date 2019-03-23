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
     *
     * @param userName
     * @return
     */
    public UserLogin login(String userName) {
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("from UserLogin where user_name = ?");
        query.setParameter(0, userName);
        return null;
    }

    public void addUser(UserLogin user) {
        Session session = sessionFactory.openSession();
        session.save(user);
    }

    /**
     * 按名字删除
     *
     * @param name
     * @param userLogin
     * @return
     */
    public int deleteByName(String name, UserLogin userLogin) {
        return 1;
    }

    /**
     * 插入记录
     *
     * @param userLogin
     * @return
     */
    public int insert(UserLogin userLogin) {
        return 1;
    }

    /**
     * 按名字查询
     *
     * @return
     */
    public List<UserLogin> selectByName(String name) {
        return null;
    }

    /**
     * 按名字更新
     *
     * @param userlogin
     * @return
     */
    public int updateByName(String name, UserLogin userlogin) {
        return 1;
    }
}
