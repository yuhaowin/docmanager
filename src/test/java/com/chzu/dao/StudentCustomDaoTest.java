package com.chzu.dao;

import com.chzu.entity.PagingVO;
import com.chzu.entity.Role;
import com.chzu.entity.StudentCustom;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/applicationContext-dao.xml"})
public class StudentCustomDaoTest {

    @Resource
    private StudentCustomDao studentCustomDao;

    @Autowired
    private SessionFactory sessionFactory;

    @Test
    public void test() {
        String sql = "select role_id, role_name, permissions from role";

        String hql = "from Role";
        Session session = sessionFactory.openSession();
        Query query = session.createSQLQuery(sql).addEntity(Role.class);
        List list = query.list();
        System.out.println(list.size());
    }


    @Test
    public void findByPaging() {
        PagingVO pagingVO = new PagingVO();
        pagingVO.setPageSize(5);
        pagingVO.setToPageNo(1);
        List<StudentCustom> byPaging = studentCustomDao.findByPaging(pagingVO);
        System.out.println(byPaging.size());
        pagingVO.setToPageNo(2);
        List<StudentCustom> byPaging1 = studentCustomDao.findByPaging(pagingVO);
        System.out.println(byPaging1.size());
    }

    @Test
    public void findStudentAndSelectCourseListById() {
        StudentCustom studentAndSelectCourseListById = studentCustomDao.findStudentAndSelectCourseListById(10006);
        System.out.println(studentAndSelectCourseListById);


    }
}