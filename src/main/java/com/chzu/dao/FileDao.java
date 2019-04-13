package com.chzu.dao;

import com.chzu.entity.BakFile;
import com.chzu.entity.ClassSubject;
import com.chzu.entity.CourseDoc;
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
public class FileDao {

    @Autowired
    private SessionFactory sessionFactory;

    /**
     * 学生保存课程文件
     *
     * @param courseDoc
     */
    public void saveCourseDoc(CourseDoc courseDoc) {
        courseDoc.setBak(0);
        courseDoc.setDelete(0);
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
    public List<CourseDoc> getCourseDoc(CourseDoc courseDoc, Boolean isAdmin) {
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

        if (isAdmin) {
            dc.add(Restrictions.or(Restrictions.eq("bak", 1), Restrictions.eq("delete", 0)));
        } else {
            dc.add(Restrictions.eq("delete", 0));
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
     *
     * @param courseDoc
     */
    public void deleteCourseDoc(CourseDoc courseDoc) {
        String hql = "update course_doc set is_delete =1 where file_id = ?";
        Session session = sessionFactory.openSession();
        Query query = session.createSQLQuery(hql);
        query.setParameter(0, courseDoc.getFileId());
        query.executeUpdate();
        session.close();
    }


    /**
     * 教师保存课件
     *
     * @param classSubject
     */
    public void saveSubject(ClassSubject classSubject) {
        classSubject.setDelete(0);
        classSubject.setBak(0);
        Session session = sessionFactory.openSession();
        session.save(classSubject);
        session.close();
    }

    /**
     * 获取课件总数
     *
     * @return
     */
    public Integer classSubjectCount(Integer courseId, Boolean isAdmin) {
        Session session = sessionFactory.openSession();
        String sql = "select count(*) from subject where is_delete = 0 and course_id = ?";
        if (isAdmin) {
            sql = "select count(*) from subject where (is_delete = 0 or bak = 1) and course_id = ?";
        }
        Query query = session.createSQLQuery(sql);
        query.setParameter(0, courseId);
        String count = query.list().get(0).toString();
        session.close();
        return Integer.parseInt(count);
    }

    /**
     * 教师获取课件 （分页）
     *
     * @param pagingVO
     * @param isAdmin
     * @return
     */
    public List<ClassSubject> findByPaging(PagingVO pagingVO, Integer courseId, Boolean isAdmin) {
        String sql = "select * from subject where is_delete = 0 and course_id = ? limit ?, ?";
        if (isAdmin) {
            sql = "select * from subject where (is_delete = 0 or bak = 1) and course_id = ? limit ?, ?";
        }
        Session session = sessionFactory.openSession();
        Query query = session.createSQLQuery(sql).addEntity(ClassSubject.class);
        query.setParameter(0, courseId);
        query.setParameter(1, pagingVO.getTopageNo());
        query.setParameter(2, pagingVO.getPageSize());
        List<ClassSubject> classSubjects = query.list();
        session.close();
        return classSubjects;
    }

    public List<ClassSubject> getSubject(ClassSubject classSubject, Boolean isAdmin){
        return  getSubject(classSubject, isAdmin, null);
    }

    /**
     * 教师获取课件
     *
     * @param classSubject
     * @return
     */
    public List<ClassSubject> getSubject(ClassSubject classSubject, Boolean isAdmin, PagingVO pagingVO) {
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
        if (isAdmin) {
            dc.add(Restrictions.or(Restrictions.eq("delete", 0), Restrictions.eq("bak", 1)));
        } else {
            dc.add(Restrictions.eq("delete", 0));
        }
        // 开启事务
        session.beginTransaction();
        Criteria c = dc.getExecutableCriteria(session);
        if(pagingVO != null){
            c.setProjection(null);
            c.setResultTransformer(Criteria.ROOT_ENTITY);
            c.setFirstResult(pagingVO.getTopageNo());
            c.setMaxResults(pagingVO.getPageSize());
        }
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

    public void deleteSubject(ClassSubject classSubject) {
        String hql = "update subject set is_delete =1 where subject_id = ?";
        Session session = sessionFactory.openSession();
        Query query = session.createSQLQuery(hql);
        query.setParameter(0, classSubject.getSubjectId());
        query.executeUpdate();
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

    /**
     * 查询备份文件
     * @param fileName
     * @param pagingVO
     * @return
     */
    public List<BakFile> getBakFile(String fileName,PagingVO pagingVO){
        String sql = "select * from (select student_id user_id,file_name,file_url,last_time,'学生文档' file_from from " +
                "course_doc  where bak = 1  union all select teacher_id user_id,file_name,file_url,last_time,'教师文档' " +
                "file_from from `subject` where bak = 1)  a ";
        if(fileName != null){
            sql += "where file_name like '%"+fileName+"%' ";
        }
        sql += " ORDER BY last_time desc ";
        if(pagingVO != null){
            sql += " limit " + pagingVO.getTopageNo() + "," + pagingVO.getPageSize();
        }
        Session session = sessionFactory.openSession();
        Query query = session.createSQLQuery(sql).addEntity(BakFile.class);
        List<BakFile> bakFileList = query.list();
        session.close();
        return bakFileList;
    }

}
