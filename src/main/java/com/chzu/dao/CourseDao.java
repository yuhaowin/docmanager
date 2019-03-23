package com.chzu.dao;

import com.chzu.entity.Course;
import com.chzu.entity.CourseCustom;
import com.chzu.entity.PagingVO;
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
public class CourseDao {

    @Autowired
    private SessionFactory sessionFactory;

    /**
     * 获取所有记录
     * @return
     */
    public Integer count(){
        Session session = sessionFactory.openSession();
        Integer count = (Integer) session.createQuery("select count(*) from Course").uniqueResult();
        return count;
    }

    /**
     * 根据 courseId 进行删除
     * @param courseId
     * @return
     */
    public void deleteByPrimaryKey(Integer courseId){
        Course course = new Course();
        course.setCourseIdd(courseId);
        Session session = sessionFactory.openSession();
        session.delete(course);
    }

    /**
     * 插入
     * @param record
     */
    public void insert(Course record){
        Session session = sessionFactory.openSession();
        session.save(record);
    }

    /**
     * 根据条件查询
     * @param course
     * @return
     */
    public List<Course> selectByExample(Course course){
        Session session = sessionFactory.openSession();
        DetachedCriteria dc = DetachedCriteria.forClass(Course.class);
        if(course.getTeacherId() != null){
            dc.add(Restrictions.eq("teacher_id", course.getTeacherId()));
        }
        if(course.getCourseName() != null){
            dc.add(Restrictions.eq("course_name", "%" + course.getCourseName() + "%"));
        }
        Criteria c = dc.getExecutableCriteria(session);
        return c.list();
    }

    /**
     * 根据 courseId 查询
     * @param courseId
     * @return
     */
    public Course selectByPrimaryKey(Integer courseId){
        Session session = sessionFactory.openSession();
        return (Course) session.get(Course.class, courseId);
    }

    /**
     * 更新 course
     * @param course
     * @return
     */
    public void updateByPrimaryKey(Course course){
        Session session = sessionFactory.openSession();
        session.update(course);
    }

    /**
     * 分页查询学生信息
     * @param pagingVO
     * @return
     */
    public  List<CourseCustom> findByPaging(PagingVO pagingVO){
        String hql="select course.*, college.college_name from course, college WHERE course.college_id = college.college_id" +
                " limit ?, ?";
        Session session = sessionFactory.openSession();
        Query query = session.createSQLQuery(hql);
        query.setParameter(0, pagingVO.getTopageNo());
        query.setParameter(1,pagingVO.getPageSize());
        return  query.list();
    }
}
