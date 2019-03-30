package com.chzu.controller;


import com.chzu.entity.*;
import com.chzu.exception.Globalexception;
import com.chzu.service.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.security.auth.Subject;
import java.util.List;


/**
 *
 */
@Controller
@RequestMapping("/admin")
public class AdminController {

    @Resource(name = "studentService")
    private StudentService studentService;

    @Resource(name = "teacherService")
    private TeacherService teacherService;

    @Resource(name = "courseService")
    private CourseService courseService;

    @Resource(name = "collegeService")
    private CollegeService collegeService;

    @Resource(name = "userLoginService")
    private UserLoginService userloginService;

    @Resource
    private FileService fileService;

    /*<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<学生操作>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>*/

    /**
     * 学生信息显示
     *
     * @param model
     * @param page
     * @return
     * @throws Exception
     */
    @RequestMapping("/showStudent")
    public String showStudent(Model model, Integer page) throws Exception {

        List<StudentCustom> list = null;
        //页码对象
        PagingVO pagingVO = new PagingVO();
        //设置总页数
        pagingVO.setTotalCount(studentService.getCountStudent());
        if (page == null || page == 0) {
            pagingVO.setToPageNo(1);
            list = studentService.findByPaging(1);
        } else {
            pagingVO.setToPageNo(page);
            list = studentService.findByPaging(page);
        }

        model.addAttribute("studentList", list);
        model.addAttribute("pagingVO", pagingVO);

        return "admin/showStudent";

    }

    /**
     * 添加学生信息页面显示
     *
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/addStudent", method = {RequestMethod.GET})
    public String addStudentUI(Model model) throws Exception {

        List<College> list = collegeService.finAll();

        model.addAttribute("collegeList", list);

        return "admin/addStudent";
    }

    /**
     * 添加学生信息操作
     *
     * @param student
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/addStudent", method = {RequestMethod.POST})
    public String addStudent(Student student, Model model) throws Exception {

        Boolean result = studentService.save(student);

        if (!result) {
            model.addAttribute("message", "学号重复");
            return "error";
        }
        //添加成功后，也添加到登录表
        UserLogin userlogin = new UserLogin();
        userlogin.setUserName(student.getUserId().toString());
        userlogin.setPassword("123");
        userlogin.setRoleId(2);
        userloginService.save(userlogin);

        //重定向
        return "redirect:/admin/showStudent";
    }

    /**
     * 修改学生信息页面显示
     *
     * @param id
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/editStudent", method = {RequestMethod.GET})
    public String editStudentUI(Integer id, Model model) throws Exception {
        if (id == null) {
            //加入没有带学生id就进来的话就返回学生显示页面
            return "redirect:/admin/showStudent";
        }
        StudentCustom studentCustom = studentService.findById(id);
        if (studentCustom == null) {
            throw new Globalexception("未找到该名学生");
        }
        List<College> list = collegeService.finAll();

        model.addAttribute("collegeList", list);
        model.addAttribute("student", studentCustom);


        return "admin/editStudent";
    }

    /**
     * 修改学生信息处理
     *
     * @param student
     * @return
     */
    @RequestMapping(value = "/editStudent", method = {RequestMethod.POST})
    public String editStudent(Student student) {

        studentService.updataById(student);

        //重定向
        return "redirect:/admin/showStudent";
    }

    /**
     * 删除学生
     *
     * @param id
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/removeStudent", method = {RequestMethod.GET})
    private String removeStudent(Integer id) throws Exception {
        if (id == null) {
            //加入没有带学生id就进来的话就返回学生显示页面
            return "admin/showStudent";
        }
        studentService.removeById(id);
        userloginService.removeByName(id.toString());

        return "redirect:/admin/showStudent";
    }

    /**
     * 搜索学生
     *
     * @param findByName
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "selectStudent", method = {RequestMethod.POST})
    private String selectStudent(String findByName, Model model) throws Exception {

        List<StudentCustom> list = studentService.findByName(findByName);

        model.addAttribute("studentList", list);
        return "admin/showStudent";
    }

    /*<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<教师操作>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>*/

    /**
     * 教师页面显示
     *
     * @param model
     * @param page
     * @return
     * @throws Exception
     */
    @RequestMapping("/showTeacher")
    public String showTeacher(Model model, Integer page) throws Exception {

        List<TeacherCustom> list = null;
        //页码对象
        PagingVO pagingVO = new PagingVO();
        //设置总页数
        pagingVO.setTotalCount(teacherService.getCountTeacher());
        if (page == null || page == 0) {
            pagingVO.setToPageNo(1);
            list = teacherService.findByPaging(1);
        } else {
            pagingVO.setToPageNo(page);
            list = teacherService.findByPaging(page);
        }

        model.addAttribute("teacherList", list);
        model.addAttribute("pagingVO", pagingVO);

        return "admin/showTeacher";

    }

    /**
     * 添加教师信息
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/addTeacher", method = {RequestMethod.GET})
    public String addTeacherUI(Model model) {

        List<College> list = collegeService.finAll();

        model.addAttribute("collegeList", list);

        return "admin/addTeacher";
    }

    /**
     * 添加教师信息处理
     *
     * @param teacher
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/addTeacher", method = {RequestMethod.POST})
    public String addTeacher(Teacher teacher, Model model) throws Exception {

        Boolean result = teacherService.save(teacher);

        if (!result) {
            model.addAttribute("message", "工号重复");
            return "error";
        }
        //添加成功后，也添加到登录表
        UserLogin userlogin = new UserLogin();
        userlogin.setUserName(teacher.getUserId().toString());
        userlogin.setPassword("123");
        userlogin.setRoleId(1);
        userloginService.save(userlogin);


        //重定向
        return "redirect:/admin/showTeacher";
    }

    /**
     * 修改教师信息页面显示
     *
     * @param id
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/editTeacher", method = {RequestMethod.GET})
    public String editTeacherUI(Integer id, Model model) throws Exception {
        if (id == null) {
            return "redirect:/admin/showTeacher";
        }
        TeacherCustom teacherCustom = teacherService.findById(id);
        if (teacherCustom == null) {
            throw new Globalexception("未找到该名学生");
        }
        List<College> list = collegeService.finAll();

        model.addAttribute("collegeList", list);
        model.addAttribute("teacher", teacherCustom);


        return "admin/editTeacher";
    }

    /**
     * 修改教师信息页面处理
     *
     * @param teacher
     * @return
     */
    @RequestMapping(value = "/editTeacher", method = {RequestMethod.POST})
    public String editTeacher(Teacher teacher) {

        teacherService.updateById(teacher);

        //重定向
        return "redirect:/admin/showTeacher";
    }

    /**
     * 删除教师
     *
     * @param id
     * @return
     * @throws Exception
     */
    @RequestMapping("/removeTeacher")
    public String removeTeacher(Integer id) throws Exception {
        if (id == null) {
            //加入没有带教师id就进来的话就返回教师显示页面
            return "admin/showTeacher";
        }
        teacherService.removeById(id);
        userloginService.removeByName(id.toString());

        return "redirect:/admin/showTeacher";
    }

    /**
     * 搜索教师
     *
     * @param findByName
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "selectTeacher", method = {RequestMethod.POST})
    private String selectTeacher(String findByName, Model model) throws Exception {

        List<TeacherCustom> list = teacherService.findByName(findByName);

        model.addAttribute("teacherList", list);
        return "admin/showTeacher";
    }

    /*<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<课程操作>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>*/

    /**
     * 课程信息显示
     *
     * @param model
     * @param page
     * @return
     * @throws Exception
     */
    @RequestMapping("/showCourse")
    public String showCourse(Model model, Integer page) throws Exception {

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

        return "admin/showCourse";

    }

    /**
     * 添加课程
     *
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/addCourse", method = {RequestMethod.GET})
    public String addCourseUI(Model model) throws Exception {

        List<TeacherCustom> list = teacherService.findAll();
        List<College> collegeList = collegeService.finAll();

        model.addAttribute("collegeList", collegeList);
        model.addAttribute("teacherList", list);

        return "admin/addCourse";
    }

    /**
     * 添加课程信息处理
     *
     * @param courseCustom
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/addCourse", method = {RequestMethod.POST})
    public String addCourse(Course courseCustom, Model model) throws Exception {

        Boolean result = courseService.save(courseCustom);

        if (!result) {
            model.addAttribute("message", "课程号重复");
            return "error";
        }


        //重定向
        return "redirect:/admin/showCourse";
    }

    /**
     * 修改教师信息页面显示
     *
     * @param id
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/editCourse", method = {RequestMethod.GET})
    public String editCourseUI(Integer id, Model model) throws Exception {
        if (id == null) {
            return "redirect:/admin/showCourse";
        }
        CourseCustom courseCustom = courseService.findById(id);
        if (courseCustom == null) {
            throw new Globalexception("未找到该课程");
        }
        List<TeacherCustom> list = teacherService.findAll();
        List<College> collegeList = collegeService.finAll();

        model.addAttribute("teacherList", list);
        model.addAttribute("collegeList", collegeList);
        model.addAttribute("course", courseCustom);


        return "admin/editCourse";
    }

    /**
     * 修改教师信息页面处理
     *
     * @param course
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/editCourse", method = {RequestMethod.POST})
    public String editCourse(Course course) throws Exception {

        courseService.updateById(course);

        //重定向
        return "redirect:/admin/showCourse";
    }

    /**
     * 删除课程信息
     *
     * @param id
     * @return
     * @throws Exception
     */
    @RequestMapping("/removeCourse")
    public String removeCourse(Integer id) throws Exception {
        if (id == null) {
            //加入没有带教师id就进来的话就返回教师显示页面
            return "admin/showCourse";
        }
        courseService.removeById(id);

        return "redirect:/admin/showCourse";
    }

    /**
     * 搜索课程
     *
     * @param findByName
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "selectCourse", method = {RequestMethod.POST})
    private String selectCourse(String findByName, Model model) throws Exception {

        List<CourseCustom> list = courseService.findByName(findByName);

        model.addAttribute("courseList", list);
        return "admin/showCourse";
    }

    /**
     * 展示课题
     * @param courseId
     * @param model
     * @return
     */
    @RequestMapping("showSubject")
    public String showSubject(Integer courseId, Model model){
        ClassSubject classSubject = new ClassSubject();
        classSubject.setCourseId(courseId);
        List<ClassSubject> classSubjectList = fileService.getSubject(classSubject);
        model.addAttribute("classSubjectList", classSubjectList);
        model.addAttribute("courseId", courseId);
        return "admin/showSubject";
    }

    @RequestMapping("subjectBak")
    public String subjectBak(Integer subjectId,Integer courseId){
        fileService.subjectBak(subjectId);
        return "redirect:/admin/showSubject?courseId=" + courseId;
    }

    @RequestMapping("showCourseDoc")
    public String showCourseDoc(Integer subjectId, Model model){
        CourseDoc courseDoc = new CourseDoc();
        courseDoc.setSubjectId(subjectId);
        List<CourseDoc> courseDocList = fileService.getCourseDoc(courseDoc);
        model.addAttribute("subjectId", subjectId);
        model.addAttribute("courseDocList", courseDocList);
        return "admin/showCourseDoc";
    }

    @RequestMapping("courseDocBak")
    public String courseDocBak(Integer subjectId,Integer fileId){
        fileService.courseDocBak(fileId);
        return "redirect:/admin/showCourseDoc?subjectId=" + subjectId;
    }

    /*<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<其他操作>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>*/

    /**
     * 普通用户账号密码重置
     *
     * @return
     * @throws Exception
     */
    @RequestMapping("/userPasswordRest")
    public String userPasswordRestUI() throws Exception {
        return "admin/userPasswordRest";
    }

    /**
     * 普通用户账号密码重置处理
     *
     * @param userLogin
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/userPasswordRest", method = {RequestMethod.POST})
    public String userPasswordRest(UserLogin userLogin) throws Exception {
        UserLogin u = userloginService.findByName(userLogin.getUserName());
        if (u != null) {
            if (u.getRoleId() == 0) {
                throw new Globalexception("该账户为管理员账户，没法修改");
            }
            u.setPassword(userLogin.getPassword());
            userloginService.updateByName(userLogin.getUserName(), u);
        } else {
            throw new Globalexception("没找到该用户");
        }

        return "admin/userPasswordRest";
    }

    /**
     * 本账户密码重置
     *
     * @return
     * @throws Exception
     */
    @RequestMapping("/passwordRest")
    public String passwordRestUI() throws Exception {
        return "admin/passwordRest";
    }


}
