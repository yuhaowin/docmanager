package com.chzu.dao;

import com.chzu.entity.Student;
import com.chzu.entity.Teacher;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class StudentDao {

    @Autowired
    private SessionFactory sessionFactory;

    public Student getStudentByName(String userName) {
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("from Student where user_name = ?");
        query.setParameter(0, userName);
        session.close();
        return null;
    }

    /**
     * 获取学生总数
     *
     * @return
     */
    public Integer count() {
        Session session = sessionFactory.openSession();
        String count = (session.createSQLQuery("select count(*) from student").list().get(0).toString());
        session.close();
        return Integer.parseInt(count);
    }

    /**
     * 按id删除
     *
     * @param Id
     * @return
     */
    public int deleteById(Integer Id) {
        Session session = sessionFactory.openSession();
        Student student = new Student();
        student.setUserId(Id);
        session.beginTransaction();
        session.delete(student);
        session.getTransaction().commit();
        session.close();
        return 1;
    }

    /**
     * 新增
     *
     * @param student
     * @return
     */
    public int insert(Student student) {
        Session session = sessionFactory.openSession();
        session.save(student);
        session.close();
        return 1;
    }

    /**
     * 按名字查找
     *
     * @param name
     * @return
     */
    public List<Student> selectByName(String name) {
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("from Student where user_name = ?");
        query.setParameter(0, name);
        List<Student> list = query.list();
        session.close();
        return list;
    }

    /**
     * 按id查询
     *
     * @param Id
     * @return
     */
    public Student selectById(Integer Id) {
        Session session = sessionFactory.openSession();
        Student student = (Student) session.get(Student.class, Id);
        session.close();
        return student;
    }

    /**
     * 更新
     *
     * @param student
     * @return
     */
    public int update(Student student) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.update(student);
        session.getTransaction().commit();
        session.close();
        return 1;
    }
}
