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
     * 按名字删除
     *
     * @param name
     * @return
     */
    public int deleteByName(String name) {
        Session session = sessionFactory.openSession();
        UserLogin userLogin = new UserLogin();
        userLogin.setUserName(name);
        session.delete(userLogin);
        session.close();
        return 1;
    }

    /**
     * 插入记录
     *
     * @param userLogin
     * @return
     */
    public int insert(UserLogin userLogin) {
        Session session = sessionFactory.openSession();
        session.save(userLogin);
        session.close();
        return 1;
    }

    /**
     * 按名字查询
     *
     * @return
     */
    public List<UserLogin> selectByName(String name) {
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("from UserLogin where user_name = ?");
        query.setParameter(0, name);
        List<UserLogin> list = query.list();
        session.close();
        return list;
    }

    /**
     * 按名字更新
     *
     * @param userlogin
     * @return
     */
    public int updateByName(UserLogin userlogin) {
        Session session = sessionFactory.openSession();
        session.update(userlogin);
        session.close();
        return 1;
    }
}
