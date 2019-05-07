package com.chzu.service;

import com.chzu.dao.*;
import com.chzu.entity.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


/**
 * Student
 */
@Service
public class StudentService {

    @Autowired
    private StudentCustomDao studentCustomDao;

    @Autowired
    private StudentDao studentDao;

    @Autowired
    private CollegeDao collegeDao;

    @Autowired
    private CourseDao courseDao;

    @Autowired
    private SelectedCourseDao selectedCourseDao;

    public void updataById(Student student) {
        studentDao.update(student);
    }

    public void removeById(Integer id) {
        studentDao.deleteById(id);
    }

    public List<StudentCustom> findByPaging(Integer toPageNo) {
        PagingVO pagingVO = new PagingVO();
        pagingVO.setToPageNo(toPageNo);
        List<StudentCustom> list = studentCustomDao.findByPaging(pagingVO);
        return list;
    }

    public Boolean save(Student student) {
        studentDao.insert(student);
        return true;
    }

    //返回学生总数
    public int getCountStudent() {
        return studentDao.count();
    }

    public StudentCustom findById(Integer id) {

        Student student = studentDao.selectById(id);
        StudentCustom studentCustom = null;
        if (student != null) {
            studentCustom = new StudentCustom();
            //类拷贝
            BeanUtils.copyProperties(student, studentCustom);
        }
        return studentCustom;
    }

    //模糊查询
    public List<StudentCustom> findByName(String name) {
        List<Student> list = studentDao.selectByName(name);
        List<StudentCustom> studentCustomList = null;
        if (list != null) {
            studentCustomList = new ArrayList<StudentCustom>();
            for (Student s : list) {
                StudentCustom studentCustom = new StudentCustom();
                //类拷贝
                BeanUtils.copyProperties(s, studentCustom);
                //获取课程名
                College college = collegeDao.selectByPrimaryKey(s.getCollegeId());
                studentCustom.setCollegeName(college.getCollegeName());
                studentCustomList.add(studentCustom);
            }
        }
        return studentCustomList;
    }

    public StudentCustom findStudentAndSelectCourseListByName(String name) {

        StudentCustom studentCustom = studentCustomDao.findStudentAndSelectCourseListById(Integer.parseInt(name));
//
//        List<SelectedCourseCustom> list = studentCustom.getSelectedCourseList();
//
//        // 判断该课程是否修完
//        for (SelectedCourseCustom s : list) {
//            if (s.getMark() != null) {
//                s.setOver(true);
//            }
//        }
        return studentCustom;
    }

    public Student profile(int userid) {
        return studentDao.selectById(userid);
    }

    public int profileUpdate(Student student) {
        return studentDao.update(student);
    }

    public Integer studentCourseListCount (Integer studentId){
        List<Integer> list = selectedCourseDao.getByStudentId(studentId);
        return courseDao.getByListCount(list);
    }

    public List<Course> studentCourseListByPaging(Integer toPageNo, Integer studentId) {
        PagingVO pagingVO = new PagingVO();
        pagingVO.setToPageNo(toPageNo);
        List<Integer> list = selectedCourseDao.getByStudentId(studentId);
        List<Course> courses = courseDao.getByList(pagingVO, list);
        return courses;
    }

    public List<Student> getStudentByCollege(Integer collegeId, PagingVO pagingVO, Integer courseId){
        return studentDao.getStudentByCollege(collegeId, pagingVO,courseId);
    }

}
