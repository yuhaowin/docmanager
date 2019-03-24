package com.chzu.dao;

import com.chzu.entity.Course;
import com.chzu.entity.CourseCustom;
import com.chzu.entity.PagingVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;
import java.util.List;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/applicationContext-dao.xml"})
public class CourseDaoTest {

    @Autowired
    private CourseDao courseDao;

    @Test
    public void count() {
        Integer count = courseDao.count();
        System.out.println(count);
    }

    @Test
    public void deleteByPrimaryKey() {
        courseDao.deleteByPrimaryKey(7);
    }

    @Test
    public void insert() {
        Course course = new  Course();
        course.setCourseId(12);
        course.setCourseName("测试课程1");
        course.setTeacherId(11);
        course.setClassRoom("教室1");
        course.setCourseWeek(18);
        course.setCourseType("必修课");
        course.setCollegeId(1);
        course.setScore(23);
        course.setCourseTime("周五");
        courseDao.insert(course);
    }

    @Test
    public void selectByExample() {
        Course course = new  Course();
        course.setCourseId(12);
        //course.setCourseName("测试课程1");
        course.setTeacherId(11);
        course.setClassRoom("教室1");
        course.setCourseWeek(18);
        course.setCourseType("必修课");
        course.setCollegeId(1);
        course.setScore(23);
        course.setCourseTime("周五");
        List<Course> courses = courseDao.selectByExample(course);
        System.out.println(courses.size());
    }

    @Test
    public void selectByPrimaryKey() {
        courseDao.selectByPrimaryKey(6);
        System.out.println(courseDao.toString());
    }

    @Test
    public void updateByPrimaryKey() {
        Course course = new  Course();
        course.setCourseId(9);
        course.setCourseName("测试课程333");
        course.setTeacherId(11);
        course.setClassRoom("教室1");
        course.setCourseWeek(18);
        course.setCourseType("必修课");
        course.setCollegeId(1);
        course.setScore(23);
        course.setCourseTime("周五");
        courseDao.updateByPrimaryKey(course);

    }

    @Test
    public void findByPaging() {
        PagingVO pagingVO = new PagingVO();
        pagingVO.setToPageNo(1);
        pagingVO.setPageSize(1);
        List<CourseCustom> byPaging = courseDao.findByPaging(pagingVO);
        System.out.println(byPaging.size());
    }

    @Test
    public void getByList(){
        List<Integer> list = Arrays.asList(1, 2);
        List<Course> byList = courseDao.getByList(list);
        System.out.println(byList.size());
    }
}