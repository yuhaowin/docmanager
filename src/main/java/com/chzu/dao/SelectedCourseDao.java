package com.chzu.dao;

import com.chzu.entity.SelectedCourse;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SelectedCourseDao {


    @Autowired
    private SessionFactory sessionFactory;

    /**
     * 根据 courseId 计数
     * @param courseId
     * @return
     */
    public Integer countById(Integer courseId){
        Session session = sessionFactory.openSession();
        Query query = session.createSQLQuery("select count(*) from SelectedCourse where course_id = ?");
        query.setParameter(0, courseId);
        Integer count = (Integer) query.uniqueResult();
        return count;
    }

    /**
     * 根据 courseId 和 studentId 删除
     * @param selectedCourse
     */
    public void delete(SelectedCourse selectedCourse){
        Session session = sessionFactory.openSession();
        Query query = session.createSQLQuery("delete SelectedCourse where  course_id = ? and student_id = ？");
        query.setParameter(0, selectedCourse.getCourseId());
        query.setParameter(1, selectedCourse.getStudentId());
        query.executeUpdate();
    }

    /**
     * 新增
     * @param selectedCourse
     */
    public void insert(SelectedCourse selectedCourse){
        Session session = sessionFactory.openSession();
        session.save(selectedCourse);
    }
    public  List<SelectedCourse> selectByExample(SelectedCourse selectedCourse){

    }

    public  int updateByExample(SelectedCourse selectedCourse){

    }
}
