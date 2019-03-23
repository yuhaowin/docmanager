package com.chzu.dao;

import com.chzu.entity.StudentCustom;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/applicationContext-dao.xml"})
public class StudentCustomDaoTest {

    @Resource
    private  StudentCustomDao studentCustomDao;


    @Test
    public void findByPaging() {
    }

    @Test
    public void findStudentAndSelectCourseListById() {
        StudentCustom studentAndSelectCourseListById = studentCustomDao.findStudentAndSelectCourseListById(10006);
        System.out.println(studentAndSelectCourseListById);


    }
}