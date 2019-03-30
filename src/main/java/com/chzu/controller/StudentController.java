package com.chzu.controller;


import com.chzu.entity.*;
import com.chzu.exception.Globalexception;
import com.chzu.service.CourseService;
import com.chzu.service.SelectedCourseService;
import com.chzu.service.StudentService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping(value = "/student")
public class StudentController {

    @Resource(name = "courseService")
    private CourseService courseService;

    @Resource(name = "studentService")
    private StudentService studentService;

    @Resource(name = "selectedCourseService")
    private SelectedCourseService selectedCourseService;

    /**
     * 查看课程
     *
     * @param model
     * @param page
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/showCourse")
    public String stuCourseShow(Model model, Integer page) throws Exception {

        List<CourseCustom> list = null;
        //页码对象
        PagingVO pagingVO = new PagingVO();
        //设置总页数
        pagingVO.setTotalCount(courseService.getCountCouse());
        if (page == null || page == 0) {
            pagingVO.setToPageNo(1);
            list = courseService.findByPaging(1);
        } else {
            pagingVO.setToPageNo(page);
            list = courseService.findByPaging(page);
        }

        model.addAttribute("courseList", list);
        model.addAttribute("pagingVO", pagingVO);

        return "student/showCourse";
    }

    /**
     * 选课操作
     *
     * @param id
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/stuSelectedCourse")
    public String stuSelectedCourse(int id) throws Exception {
        //获取当前用户名
        Subject subject = SecurityUtils.getSubject();
        String username = (String) subject.getPrincipal();

        SelectedCourseCustom selectedCourseCustom = new SelectedCourseCustom();
        selectedCourseCustom.setCourseId(id);
        selectedCourseCustom.setStudentId(Integer.parseInt(username));

        SelectedCourse selectedCourse = new SelectedCourse();
        selectedCourse.setCourseId(id);
        selectedCourse.setStudentId(selectedCourseCustom.getStudentId());

        SelectedCourseCustom s = selectedCourseService.findOne(selectedCourse);

        if (s == null) {
            selectedCourseService.save(selectedCourseCustom);
        } else {
            throw new Globalexception("该门课程你已经选了，不能再选");
        }

        return "redirect:/student/selectedCourse";
    }

    /**
     * 退课操作
     *
     * @param id
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/outCourse")
    public String outCourse(int id) throws Exception {
        Subject subject = SecurityUtils.getSubject();
        String username = (String) subject.getPrincipal();

        SelectedCourse selectedCourse = new SelectedCourse();
        selectedCourse.setCourseId(id);
        selectedCourse.setStudentId(Integer.parseInt(username));

        selectedCourseService.remove(selectedCourse);

        return "redirect:/student/selectedCourse";
    }

    /**
     * 已选课程
     *
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/selectedCourse")
    public String selectedCourse(Model model) throws Exception {
        //获取当前用户名
        Subject subject = SecurityUtils.getSubject();
        String username = (String) subject.getPrincipal();
        List<Course> list = studentService.studentCourseList(Integer.parseInt(username));
        model.addAttribute("selectedCourseList", list);
        return "student/selectCourse";
    }


    /**
     * 修改密码
     *
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/passwordRest")
    public String passwordRest() throws Exception {
        return "student/passwordRest";
    }

    /**
     * 个人信息
     *
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/profile")
    public String profile(Model model) throws Exception {
        Subject subject = SecurityUtils.getSubject();
        String username = (String) subject.getPrincipal();
        Student student = studentService.profile(Integer.parseInt(username));
        model.addAttribute("student", student);
        return "student/profile";
    }

    /**
     * 更新个人信息
     *
     * @param model
     * @param student
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/profile_update")
    public String profileUpdate(Model model, Student student) throws Exception {
        Subject subject = SecurityUtils.getSubject();
        Integer userId = Integer.parseInt(subject.getPrincipal().toString());
        student.setUserId(userId);
        StudentCustom studentOld = studentService.findById(userId);
        student.setBirthYear(studentOld.getBirthYear());
        student.setGrade(studentOld.getGrade());
        student.setCollegeId(studentOld.getCollegeId());
        int result = studentService.profileUpdate(student);
        return profile(model);
    }

    /**
     * 显示文件列表
     * @return
     */
    @RequestMapping("/showFile")
    public String showFile(){
        return "student/showFile";
    }

    /**
     * 添加文件
     * @return
     */
    @RequestMapping("/addFile")
    public String addFile(){
        return "student/addFile";
    }

    /**
     * 添加文件
     * @return
     */
    @RequestMapping("/editFile")
    public String editFile(){
        return "student/editFile";
    }

}
