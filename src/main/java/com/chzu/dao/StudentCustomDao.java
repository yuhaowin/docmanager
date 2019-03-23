package com.chzu.dao;


import com.chzu.entity.PagingVO;
import com.chzu.entity.StudentCustom;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class StudentCustomDao {

    @Autowired
    private SessionFactory sessionFactory;

    //分页查询学生信息
    public List<StudentCustom> findByPaging(PagingVO pagingVO) {
        String hql = "select student.*, college.college_name from student, college WHERE student.college_id = college.college_id" +
                " limit ?, ?";
        Session session = sessionFactory.openSession();
        Query query = session.createSQLQuery(hql);
        query.setParameter(0, pagingVO.getTopageNo());
        query.setParameter(1, pagingVO.getPageSize());
        return query.list();
    }

    //查询学生信息，和其选课信息
    public StudentCustom findStudentAndSelectCourseListById(Integer id) {
        String hql = "select student.*,selected_course.*,course.* from student,selected_course,course WHERE student.user_id =? " +
                " AND student.user_id = selected_course.student_id AND selected_course.course_id = course.course_id";
        Session session = sessionFactory.openSession();
        Query query = session.createSQLQuery(hql);
        query.setParameter(0, id);
        return (StudentCustom) query.list().get(0);
    }
}
