package com.chzu.dao;

import com.chzu.entity.Course;
import com.chzu.entity.CourseDoc;
import com.chzu.entity.Subject;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class FileDao {

    @Autowired
    private SessionFactory sessionFactory;

    /**
     * 学生保存课程文件
     *
     * @param courseDoc
     */
    public void saveCourseDoc(CourseDoc courseDoc) {
        Session session = sessionFactory.openSession();
        session.save(courseDoc);
        session.close();
    }

    /**
     * 学生获取课程文件
     *
     * @param courseDoc
     * @return
     */
    public List<CourseDoc> getCourseDoc(CourseDoc courseDoc) {
        Session session = sessionFactory.openSession();
        DetachedCriteria dc = DetachedCriteria.forClass(Course.class);
        if (courseDoc.getStudentId() != null) {
            dc.add(Restrictions.eq("studentId", courseDoc.getStudentId()));
        }
        if (courseDoc.getCourseId() != null) {
            dc.add(Restrictions.eq("courseId", courseDoc.getCourseId()));
        }
        if (courseDoc.getCourseId() != null) {
            dc.add(Restrictions.eq("subjectId", courseDoc.getCourseId()));
        }
        // 开启事务
        session.beginTransaction();
        Criteria c = dc.getExecutableCriteria(session);
        List<CourseDoc> courseDocs = c.list();
        session.getTransaction().commit();
        session.close();
        return courseDocs;
    }

    /**
     * 学生更新课程文件
     *
     * @param courseDoc
     */
    public void updateCourseDoc(CourseDoc courseDoc) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.update(courseDoc);
        session.getTransaction().commit();
        session.close();
    }


    /**
     * 教师保存课件
     *
     * @param subject
     */
    public void saveSubject(Subject subject) {
        Session session = sessionFactory.openSession();
        session.save(subject);
        session.close();
    }

    /**
     * 教师获取课件
     *
     * @param subject
     * @return
     */
    public List<Subject> getSubject(Subject subject) {
        Session session = sessionFactory.openSession();
        DetachedCriteria dc = DetachedCriteria.forClass(Course.class);
        if (subject.getTeacherId() != null) {
            dc.add(Restrictions.eq("teacherId", subject.getTeacherId()));
        }
        if (subject.getCourseId() != null) {
            dc.add(Restrictions.eq("courseId", subject.getCourseId()));
        }
        if (subject.getSubjectName() != null) {
            dc.add(Restrictions.like("subjectName", "%" + subject.getSubjectName() + "%"));
        }
        // 开启事务
        session.beginTransaction();
        Criteria c = dc.getExecutableCriteria(session);
        List<Subject> subjects = c.list();
        session.getTransaction().commit();
        session.close();
        return subjects;
    }

    /**
     * 教师更新课件
     *
     * @param subject
     */
    public void updateSubject(Subject subject) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.update(subject);
        session.getTransaction().commit();
        session.close();
    }

}
