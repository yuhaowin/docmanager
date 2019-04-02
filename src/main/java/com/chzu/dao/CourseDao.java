package com.chzu.dao;

import com.chzu.entity.Course;
import com.chzu.entity.CourseCustom;
import com.chzu.entity.PagingVO;
import org.hibernate.*;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class CourseDao {

    @Autowired
    private SessionFactory sessionFactory;

    /**
     * 获取所有记录
     *
     * @return
     */
    public Integer count() {

        Session session = sessionFactory.openSession();
        String count = (session.createSQLQuery("select count(*) from course").list().get(0).toString());
        session.close();
        return Integer.parseInt(count);
    }

    /**
     * 根据 courseId 进行删除
     *
     * @param courseId
     * @return
     */
    public void deleteByPrimaryKey(Integer courseId) {
        Course course = new Course();
        course.setCourseId(courseId);
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.delete(course);
        session.getTransaction().commit();
        session.close();
    }

    /**
     * 插入
     *
     * @param record
     */
    public void insert(Course record) {
        Session session = sessionFactory.openSession();
        session.save(record);
        session.close();
    }


    public List<Course> selectByExample(Course course){
        return selectByExample(course, null);
    }

    /**
     * 根据条件查询
     *
     * @param course
     * @return
     */
    public List<Course> selectByExample(Course course, PagingVO pagingVO) {
        Session session = sessionFactory.openSession();
        DetachedCriteria dc = DetachedCriteria.forClass(Course.class);
        if (course.getTeacherId() != null) {
            dc.add(Restrictions.eq("teacherId", course.getTeacherId()));
        }
        if (course.getCourseName() != null) {
            dc.add(Restrictions.like("courseName", "%" + course.getCourseName() + "%"));
        }
        // 开启事务
        Transaction txn = session.beginTransaction();
        Criteria c = dc.getExecutableCriteria(session);
        if(pagingVO != null){
            c.setProjection(null);
            c.setResultTransformer(Criteria.ROOT_ENTITY);
            c.setFirstResult(pagingVO.getTopageNo());
            c.setMaxResults(pagingVO.getPageSize());
        }
        List<Course> courses = c.list();
        txn.commit();
        session.close();
        return courses;
    }

    /**
     * 通过tecaherId查找所有课程
     *
     * @param tecaherId
     * @return
     */
    public List<Course> selectCoursesByTeacherId(Integer tecaherId) {
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("from Course WHERE teacherId = ?");
        query.setParameter(0, tecaherId);
        List<Course> list = query.list();
        session.close();
        return list;
    }

    /**
     * 根据 courseId 查询
     *
     * @param courseId
     * @return
     */
    public Course selectByPrimaryKey(Integer courseId) {
        Session session = sessionFactory.openSession();
        Course course = (Course) session.get(Course.class, courseId);
        session.close();
        return course;
    }

    /**
     * 更新 course
     *
     * @param course
     * @return
     */
    public void updateByPrimaryKey(Course course) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.update(course);
        session.getTransaction().commit();
        session.close();
    }

    /**
     * 分页查询学生信息
     *
     * @param pagingVO
     * @return
     */
    public List<CourseCustom> findByPaging(PagingVO pagingVO) {
        String hql = "select course.*, college.college_name collegeName from course, college WHERE course.college_id = college.college_id" +
                " limit ?, ?";
        Session session = sessionFactory.openSession();
        Query query = session.createSQLQuery(hql).addEntity(CourseCustom.class);
        query.setParameter(0, pagingVO.getTopageNo());
        query.setParameter(1, pagingVO.getPageSize());
        List<CourseCustom> courseCustoms = query.list();
        session.close();
        return courseCustoms;
    }

    public Integer getByListCount(List<Integer> courseIds){
        Session session = sessionFactory.openSession();
        List<Course> list = new ArrayList<>();
        if (courseIds.size() >0 ){
            Query query = session.createQuery("from Course where courseId in (:ids)")
                    .setParameterList("ids", courseIds);
            list = query.list();
        }
        session.close();
        return list.size();
    }

    public List<Course> getByList(PagingVO pagingVO,List<Integer> courseIds) {
        Session session = sessionFactory.openSession();
        List<Course> list = new ArrayList<>();
        if (courseIds.size() >0){
            Query query = session.createQuery("from Course where courseId in (:ids)")
                    .setParameterList("ids", courseIds)
                    .setFirstResult(pagingVO.getTopageNo())
                    .setMaxResults(pagingVO.getPageSize());
            list = query.list();
        }
        session.close();
        return list;
    }
}
