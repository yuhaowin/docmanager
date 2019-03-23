package com.chzu.dao;

import com.chzu.entity.College;
import com.chzu.entity.CollegeExample;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CollegeDao {
    @Autowired
    private SessionFactory sessionFactory;

    /**
     * 查询课程
     * @param example
     * @return
     */
    public  List<College> selectByExample(CollegeExample example){
        Session session = sessionFactory.openSession();
        List<College> colleges = session.createQuery("from College").list();
        session.close();
        return colleges;
    }

    /**
     * 根据 collegeId 进行查询
     * @param collegeId
     * @return
     */
    public  College selectByPrimaryKey(Integer collegeId){
        Session session = sessionFactory.openSession();
        College college = (College) session.get(College.class, collegeId);
        session.close();
        return college;
    }







}
