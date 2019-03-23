package com.chzu.dao;

import com.chzu.entity.Course;
import com.chzu.entity.UserLogin;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserLoginDao {

    @Autowired
    private SessionFactory sessionFactory;

    public void addUser(UserLogin user) {
        Session session = sessionFactory.openSession();
        session.save(user);
    }

    /**
     * 按名字删除
     *
     * @param name
     * @return
     */
    public int deleteByName(String name) {
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
        return list;
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
