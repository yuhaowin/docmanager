package com.chzu.dao;

import com.chzu.entity.Teacher;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class TeacherDao {

    @Autowired
    private SessionFactory sessionFactory;

    /**
     * 获取所有记录数
     *
     * @return
     */
    public int count() {
        Session session = sessionFactory.openSession();
        String count = (session.createSQLQuery("select count(*) from teacher").list().get(0).toString());
        session.close();
        return Integer.parseInt(count);
    }

    /**
     * 根据id删除
     *
     * @param Id
     * @return
     */
    public int deleteById(Integer Id) {
        Session session = sessionFactory.openSession();
        Transaction tr = session.beginTransaction();
        Teacher teacher = new Teacher();
        teacher.setUserId(Id);
        session.delete(teacher);
        tr.commit();
        session.close();
        return 1;
    }

    /**
     * 新增
     *
     * @param teacher
     * @return
     */
    public int insert(Teacher teacher) {
        Session session = sessionFactory.openSession();
        session.save(teacher);
        session.close();
        return 1;
    }

    /**
     * 通过名字查找
     *
     * @param name
     * @return
     */
    public List<Teacher> selectByName(String name) {
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("from Teacher where userName = ?");
        query.setParameter(0, name);
        List<Teacher> list = query.list();
        session.close();
        return list;
    }

    public List<Teacher> selectAll() {
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("from Teacher");
        List<Teacher> list = query.list();
        session.close();
        return list;
    }

    /**
     * 通过id查询
     *
     * @param Id
     * @return
     */
    public Teacher selectById(Integer Id) {
        Session session = sessionFactory.openSession();
        Teacher teacher = (Teacher) session.get(Teacher.class, Id);
        session.close();
        return teacher;
    }

    /**
     * @param teacher
     * @return
     */
    public int updateTeacher(Teacher teacher) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.update(teacher);
        session.getTransaction().commit();
        session.close();
        return 1;
    }
}
