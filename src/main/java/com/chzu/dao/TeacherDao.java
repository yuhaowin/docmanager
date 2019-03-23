package com.chzu.dao;

import com.chzu.entity.Teacher;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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
        Teacher teacher = new Teacher();
        teacher.setUserId(Id);
        session.delete(teacher);
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
        Query query = session.createQuery("from Teacher where user_name = ?");
        query.setParameter(0, name);
        List<Teacher> list = query.list();
        return list;
    }

    public List<Teacher> selectAll() {
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("from Teacher");
        List<Teacher> list = query.list();
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
        return (Teacher) session.get(Teacher.class, Id);
    }

    /**
     * @param teacher
     * @return
     */
    public int update(Teacher teacher) {
        Session session = sessionFactory.openSession();
        session.update(teacher);
        session.close();
        return 1;
    }
}
