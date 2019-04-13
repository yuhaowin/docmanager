package com.chzu.dao;

import com.chzu.entity.PagingVO;
import com.chzu.entity.Student;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.*;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
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
        DetachedCriteria dc = DetachedCriteria.forClass(Student.class);
        if (StringUtils.isNoneBlank(name)) {
            dc.add(Restrictions.like("userName", "%" + name + "%"));
        }
        // 开启事务
        Transaction txn = session.beginTransaction();
        Criteria c = dc.getExecutableCriteria(session);
        List<Student> list = c.list();
        txn.commit();
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

    /**
     * 根据学院获取学生
     * @param collegeId
     * @return
     */
    public List<Student> getStudentByCollege(Integer collegeId, PagingVO pagingVO, Integer courseId){
        Session session = sessionFactory.openSession();

        String sql = "select * from student where college_id = ? and  user_id not in (select student_id from " +
                "selected_course where course_id =? )";

        Query query = session.createSQLQuery(sql).addEntity(Student.class);
        query.setParameter(0, collegeId);
        query.setParameter(1, courseId);
        if(pagingVO != null){
            query.setFirstResult(pagingVO.getTopageNo())
                    .setMaxResults(pagingVO.getPageSize());
        }
        List<Student> studentList = query.list();
        session.close();
        return studentList;
    }
}
