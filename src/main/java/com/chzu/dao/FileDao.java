package com.chzu.dao;

import com.chzu.entity.Course;
import com.chzu.entity.CourseDoc;
import com.chzu.entity.ClassSubject;
import com.chzu.entity.SelectedCourse;
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
        DetachedCriteria dc = DetachedCriteria.forClass(CourseDoc.class);
        if (courseDoc.getStudentId() != null) {
            dc.add(Restrictions.eq("studentId", courseDoc.getStudentId()));
        }
        if (courseDoc.getCourseId() != null) {
            dc.add(Restrictions.eq("courseId", courseDoc.getCourseId()));
        }
        if (courseDoc.getSubjectId() != null) {
            dc.add(Restrictions.eq("subjectId", courseDoc.getSubjectId()));
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
     * 学生删除课程文件
     * @param courseDoc
     */
    public void deleteCourseDoc(CourseDoc courseDoc){
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.delete(courseDoc);
        session.getTransaction().commit();
        session.close();
    }


    /**
     * 教师保存课件
     *
     * @param classSubject
     */
    public void saveSubject(ClassSubject classSubject) {
        Session session = sessionFactory.openSession();
        session.save(classSubject);
        session.close();
    }

    /**
     * 教师获取课件
     *
     * @param classSubject
     * @return
     */
    public List<ClassSubject> getSubject(ClassSubject classSubject) {
        Session session = sessionFactory.openSession();
        DetachedCriteria dc = DetachedCriteria.forClass(ClassSubject.class);
        if (classSubject.getTeacherId() != null) {
            dc.add(Restrictions.eq("teacherId", classSubject.getTeacherId()));
        }
        if (classSubject.getCourseId() != null) {
            dc.add(Restrictions.eq("courseId", classSubject.getCourseId()));
        }
        if (classSubject.getSubjectName() != null) {
            dc.add(Restrictions.like("subjectName", "%" + classSubject.getSubjectName() + "%"));
        }
        // 开启事务
        session.beginTransaction();
        Criteria c = dc.getExecutableCriteria(session);
        List<ClassSubject> classSubjects = c.list();
        session.getTransaction().commit();
        session.close();
        return classSubjects;
    }

    /**
     * 教师更新课件
     *
     * @param classSubject
     */
    public void updateSubject(ClassSubject classSubject) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.update(classSubject);
        session.getTransaction().commit();
        session.close();
    }

    public void deleteSubject(ClassSubject classSubject){
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.delete(classSubject);
        session.getTransaction().commit();
        session.close();
    }


    public void subjectBak(Integer subjectId) {
        String hql = "update subject set bak = 1  where subject_id =?";
        Session session = sessionFactory.openSession();
        Query query = session.createSQLQuery(hql);
        query.setParameter(0, subjectId);
        query.executeUpdate();
        session.close();
    }

    public void courseDocBak(Integer fileId) {
        String hql = "update course_doc set bak = 1  where file_id =?";
        Session session = sessionFactory.openSession();
        Query query = session.createSQLQuery(hql);
        query.setParameter(0, fileId);
        query.executeUpdate();
        session.close();
    }
}
