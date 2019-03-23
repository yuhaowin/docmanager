package com.chzu.service;


import com.chzu.dao.CollegeDao;
import com.chzu.dao.CourseDao;
import com.chzu.dao.SelectedCourseDao;
import com.chzu.entity.*;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vinci on 2017/6/29.
 */
@Service
public class CourseService{

    @Autowired
    private CourseDao courseDao;



    @Autowired
    private CollegeDao collegeDao;

    @Autowired
    private SelectedCourseDao selectedCourseDao;

    public void updateById(Integer id, CourseCustom courseCustom) throws Exception {
        courseDao.updateByPrimaryKey(courseCustom);
    }

    public Boolean removeById(Integer id) throws Exception {
        //自定义查询条件
        SelectedCourse selectedCourse = new SelectedCourse();
        selectedCourse.setCourseId(id);
        List<SelectedCourse> list = selectedCourseDao.selectByExample(selectedCourse);

        if (list.size() == 0) {
            courseDao.deleteByPrimaryKey(id);
            return true;
        }

        return false;
    }

    public List<CourseCustom> findByPaging(Integer toPageNo) throws Exception {
        PagingVO pagingVO = new PagingVO();
        pagingVO.setToPageNo(toPageNo);

        List<CourseCustom> list = courseDao.findByPaging(pagingVO);
        return list;
    }

    public Boolean save(CourseCustom couseCustom) throws Exception {
        Course course = courseDao.selectByPrimaryKey(couseCustom.getCourseId());
        if (course == null) {
            courseDao.insert(couseCustom);
            return true;
        }
        return false;
    }

    public int getCountCouse() throws Exception {

        return courseDao.count();
    }

    public CourseCustom findById(Integer id) throws Exception {
        Course course = courseDao.selectByPrimaryKey(id);
        CourseCustom courseCustom = null;
        if (course != null) {
            courseCustom = new CourseCustom();
            BeanUtils.copyProperties(courseCustom, course);
        }

        return courseCustom;
    }

    public List<CourseCustom> findByName(String name) throws Exception {

        Course course = new Course();
        course.setCourseName(name);

        List<Course> list = courseDao.selectByExample(course);

        List<CourseCustom> courseCustomList = null;

        if (list != null) {
            courseCustomList = new ArrayList<CourseCustom>();
            for (Course c : list) {
                CourseCustom courseCustom = new CourseCustom();
                //类拷贝
                org.springframework.beans.BeanUtils.copyProperties(c, courseCustom);
                //获取课程名
                College college = collegeDao.selectByPrimaryKey(c.getCollegeId());
                courseCustom.setCollegeName(college.getCollegeName());

                courseCustomList.add(courseCustom);
            }
        }

        return courseCustomList;
    }

    public List<CourseCustom> findByTeacherID(Integer id) throws Exception {
        Course course = new Course();
        course.setTeacherId(id);
        List<Course> list = courseDao.selectByExample(course);
        List<CourseCustom> courseCustomList = null;

        if (list.size() > 0) {
            courseCustomList = new ArrayList<CourseCustom>();
            for (Course c : list) {
                CourseCustom courseCustom = new CourseCustom();
                //类拷贝
                BeanUtils.copyProperties(courseCustom, c);
                //获取课程名
                College college = collegeDao.selectByPrimaryKey(c.getCollegeId());
                courseCustom.setCollegeName(college.getCollegeName());

                courseCustomList.add(courseCustom);
            }
        }

        return courseCustomList;
    }
}
