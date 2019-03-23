package com.chzu.dao;

import com.chzu.entity.PagingVO;
import com.chzu.entity.TeacherCustom;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TeacherCustomDao {

    @Autowired
    private SessionFactory sessionFactory;

    //分页查询老师信息
    public List<TeacherCustom> findByPaging(PagingVO pagingVO) {
        String hql = "select teacher.*, college.college_name from teacher, college WHERE teacher.college_id = college.college_id" +
                " limit ?, ?";
        Session session = sessionFactory.openSession();
        Query query = session.createSQLQuery(hql);
        query.setParameter(0, pagingVO.getTopageNo());
        query.setParameter(1, pagingVO.getPageSize());
        return query.list();
    }
}
