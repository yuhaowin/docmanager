package com.chzu.service;

import com.chzu.dao.CollegeDao;
import com.chzu.dao.CourseDao;
import com.chzu.dao.TeacherCustomDao;
import com.chzu.dao.TeacherDao;
import com.chzu.entity.*;
import com.chzu.exception.Globalexception;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * teacherservice
 */
@Service
public class TeacherService {

    @Autowired
    private TeacherDao teacherDao;

    @Autowired
    private TeacherCustomDao teacherCustomDao;

    @Autowired
    private CollegeDao collegeDao;

    @Autowired
    private CourseDao courseDao;

    public void updateById(Teacher teacher){
        teacherDao.update(teacher);
    }

    public void removeById(Integer id) throws Globalexception {
        //List<Course> list = courseDao.selectByExample(courseExample);
        List<Course> list = null;
        if (list.size() != 0) {
            throw new Globalexception("请先删除该名老师所教授的课程");
        }

        teacherDao.deleteById(id);
    }

    public List<TeacherCustom> findByPaging(Integer toPageNo) throws Exception {
        PagingVO pagingVO = new PagingVO();
        pagingVO.setToPageNo(toPageNo);

        List<TeacherCustom> list = teacherCustomDao.findByPaging(pagingVO);

        return list;
    }

    public Boolean save(Teacher teacher) {

        Teacher tea = teacherDao.selectById(teacher.getUserId());
        if (tea == null) {
            teacherDao.insert(teacher);
            return true;
        }
        return false;
    }

    public int getCountTeacher() {
        return teacherDao.count();
    }

    public TeacherCustom findById(Integer id) {
        Teacher teacher = teacherDao.selectById(id);
        TeacherCustom teacherCustom = null;
        if (teacher != null) {
            teacherCustom = new TeacherCustom();
            BeanUtils.copyProperties(teacher, teacherCustom);
        }
        return teacherCustom;
    }

    public List<TeacherCustom> findByName(String name) {

        List<Teacher> list = teacherDao.selectByName(name);

        List<TeacherCustom> teacherCustomList = null;

        if (list != null) {
            teacherCustomList = new ArrayList<TeacherCustom>();
            for (Teacher t : list) {
                TeacherCustom teacherCustom = new TeacherCustom();
                //类拷贝
                BeanUtils.copyProperties(t, teacherCustom);
                //获取课程名
                College college = collegeDao.selectByPrimaryKey(t.getCollegeId());
                teacherCustom.setCollegeName(college.getCollegeName());

                teacherCustomList.add(teacherCustom);
            }
        }

        return teacherCustomList;
    }

    public List<TeacherCustom> findAll() {
        List<Teacher> list = teacherDao.selectAll();
        List<TeacherCustom> teacherCustomsList = null;
        if (list != null) {
            teacherCustomsList = new ArrayList<TeacherCustom>();
            for (Teacher t : list) {
                TeacherCustom teacherCustom = new TeacherCustom();
                BeanUtils.copyProperties(t, teacherCustom);
                teacherCustomsList.add(teacherCustom);
            }
        }
        return teacherCustomsList;
    }

    public Teacher profile(int userid) {
        return teacherDao.selectById(userid);
    }

    public int profileUpdate(Teacher teacher) {
        System.out.println(teacher.getUserName());
        return teacherDao.update(teacher);
    }
}
