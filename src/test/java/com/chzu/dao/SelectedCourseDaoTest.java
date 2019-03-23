package com.chzu.dao;

import com.chzu.entity.SelectedCourse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/applicationContext-dao.xml"})
public class SelectedCourseDaoTest {

    @Autowired
    private SelectedCourseDao selectedCourseDao;


    @Test
    public void countById() {
    }

    @Test
    public void delete() {
    }

    @Test
    public void insert() {
    }

    @Test
    public void selectByExample() {
    }

    @Test
    public void updateByExample() {
        SelectedCourse selectedCourse = new SelectedCourse();
        selectedCourse.setCourseId(2);
        selectedCourse.setStudentId(123);
        selectedCourse.setMark(1);
        selectedCourseDao.updateByExample(selectedCourse);
    }
}