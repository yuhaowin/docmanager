package com.chzu.dao;

import com.chzu.entity.Student;
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
        return null;
    }

    /**
     * 获取学生总数
     *
     * @return
     */
    public int count() {
        return 1;
    }

    /**
     * 按id删除
     *
     * @param Id
     * @return
     */
    public int deleteById(Integer Id) {
        return 1;
    }

    /**
     * 新增
     *
     * @param student
     * @return
     */
    public int insert(Student student) {
        return 1;
    }

    /**
     * 按名字查找
     *
     * @param name
     * @return
     */
    public List<Student> selectByName(String name) {
        return null;
    }

    /**
     * 按id查询
     *
     * @param Id
     * @return
     */
    public Student selectById(Integer Id) {
        return null;
    }

    /**
     * 更新
     *
     * @param record
     * @return
     */
    public int update(Student record) {
        return 1;
    }
}
