package com.chzu.controller;


import com.chzu.entity.*;
import com.chzu.exception.Globalexception;
import com.chzu.service.*;
import com.chzu.utils.UploadUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
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

    @Resource
    private FileService fileService;

    @Autowired
    private CollegeService collegeService;

    @Autowired
    private SelectedSubjectService selectedSubjectService;




    /**
     * 已选课程
     *
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/selectedCourse")
    public String selectedCourse(Model model,Integer page){
        //获取当前用户名
        Subject subject = SecurityUtils.getSubject();
        String username = (String) subject.getPrincipal();
        //分页
        List<Course> list;
        //页码对象
        PagingVO pagingVO = new PagingVO();
        //设置总页数
        pagingVO.setTotalCount(studentService.studentCourseListCount(Integer.parseInt(username)));
        if (page == null || page == 0) {
            pagingVO.setToPageNo(1);
            list = studentService.studentCourseListByPaging(1,Integer.parseInt(username));
        } else {
            pagingVO.setToPageNo(page);
            list = studentService.studentCourseListByPaging(page,Integer.parseInt(username));
        }
        model.addAttribute("selectedCourseList", list);
        model.addAttribute("pagingVO", pagingVO);
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
     * 查看课程成绩
     *
     * @param id
     */
    @RequestMapping(value = "/showMark")
    public String showMark(Integer id, Model model) throws Exception {
        Subject subject = SecurityUtils.getSubject();
        Integer userId = Integer.parseInt(subject.getPrincipal().toString());
        SelectedCourse selectedCourse = new SelectedCourse();
        selectedCourse.setCourseId(id);
        selectedCourse.setStudentId(userId);
        SelectedCourseCustom mark = selectedCourseService.findOne(selectedCourse);
        model.addAttribute("mark", mark.getMark());
        CourseCustom courseCustom = courseService.findById(id);
        model.addAttribute("courseName", courseCustom.getCourseName());
        return "student/showMark";

    }

    /**
     * 个人信息
     *
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/profile")
    public String profile(Model model){
        Subject subject = SecurityUtils.getSubject();
        String username = (String) subject.getPrincipal();
        Student student = studentService.profile(Integer.parseInt(username));
        model.addAttribute("student", student);
        List<College> list = collegeService.finAll();

        model.addAttribute("collegeList", list);
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
        studentService.profileUpdate(student);
        return "redirect:/student/profile";
    }

    /**
     * 课题列表
     *
     * @param id
     * @param model
     * @return
     */
    @RequestMapping(value = "/classSubject")
    public String classSubject(Integer id, Model model) {
        ClassSubject subject = new ClassSubject();
        subject.setCourseId(id);
        List<ClassSubject> subjectList = fileService.getSubject(subject);
        for(ClassSubject classSubject : subjectList){
            classSubject.setSize(fileService.getSize(classSubject));
        }
        model.addAttribute("subjectList", subjectList);
        return "student/showSubject";
    }

    /**
     * 我的文档
     *
     * @return
     */
    @RequestMapping("/showFile")
    public String showFile(Integer subjectId, Integer courseId, Model model) {
        Subject subject = SecurityUtils.getSubject();
        Integer userId = Integer.parseInt(subject.getPrincipal().toString());
        CourseDoc courseDoc = new CourseDoc();
        courseDoc.setSubjectId(subjectId);
        courseDoc.setStudentId(userId);
        List<CourseDoc> courseDocList = fileService.getCourseDoc(courseDoc);
        model.addAttribute("courseDocList", courseDocList);
        model.addAttribute("subjectId", subjectId);
        model.addAttribute("courseId", courseId);
        return "student/showFile";
    }

    @RequestMapping("deleteFile")
    public String deleteFile(Integer fileId, Integer subjectId, Integer courseId, Model model) {
        Subject subject = SecurityUtils.getSubject();
        Integer userId = Integer.parseInt(subject.getPrincipal().toString());
        CourseDoc courseDoc = new CourseDoc();
        courseDoc.setStudentId(userId);
        courseDoc.setFileId(fileId);
        fileService.deleteCourdeDoc(courseDoc);
        return "redirect:/student/showFile?subjectId=" + subjectId + "&courseId=" + courseId;
    }

    /**
     * 添加文件
     *
     * @return
     */
    @RequestMapping(value = "/addFile", method = RequestMethod.GET)
    public String addFile(Integer courseId, Integer subjectId, Model model) {
        model.addAttribute("courseId", courseId);
        model.addAttribute("subjectId", subjectId);
        return "student/addFile";
    }

    /**
     * 添加文件
     *
     * @return
     */
    @RequestMapping(value = "/addFile", method = RequestMethod.POST)
    public String saveFile(HttpServletRequest request, Integer courseId, Integer subjectId, MultipartFile file) {
        String basePath = request.getSession().getServletContext().getRealPath("/") + "WEB-INF/files/";
        System.out.println("当前项目路径: " + basePath);
        //上传文件的文件名
        String oldName = file.getOriginalFilename();
        Subject subject = SecurityUtils.getSubject();
        Integer userId = Integer.parseInt(subject.getPrincipal().toString());
        System.out.println(oldName);
        // 文件所在项目的相对路径
        String path = UploadUtil.uploadFile(file, basePath);

        System.out.println(path);

        CourseDoc courseDoc = new CourseDoc();
        courseDoc.setSubjectId(subjectId);
        courseDoc.setStudentId(userId);
        courseDoc.setFileName(oldName);
        courseDoc.setFileUrl(path);
        courseDoc.setCourseId(courseId);
        courseDoc.setLastTime(new Date());
        fileService.saveCourseDoc(courseDoc);
        return "redirect:/student/showFile?subjectId=" + subjectId + "&courseId=" + courseId;
    }

    /**
     * 选择课题
     * @param subjectId
     * @param courseId
     * @return
     */
    @RequestMapping("selectSubject")
    public String selectSubject(Integer subjectId, Integer courseId) throws Exception{
        Subject subject = SecurityUtils.getSubject();
        Integer userId = Integer.parseInt(subject.getPrincipal().toString());
        SelectedSubject selectedSubject = new SelectedSubject();
        selectedSubject.setStudentId(userId);
        selectedSubject.setSubjectId(subjectId);
        selectedSubjectService.selectSubject(selectedSubject);
        return "redirect:/student/classSubject?id="+ courseId;
    }

    /**
     * 已选课题
     * @return
     */
    @RequestMapping("selectedSubject")
    public String selectedSubject(Integer page,Model model){
        Subject subject = SecurityUtils.getSubject();
        Integer userId = Integer.parseInt(subject.getPrincipal().toString());
        SelectedSubject selectedSubject = new SelectedSubject();
        selectedSubject.setStudentId(userId);
        PagingVO pagingVO = new PagingVO();
        //设置总页数
        List<ClassSubject> list = new ArrayList<>();
        pagingVO.setTotalCount(selectedSubjectService.getSubjectList(selectedSubject,null).size());
        if (page == null || page == 0) {
            pagingVO.setToPageNo(1);
        } else {
            pagingVO.setToPageNo(page);
        }

        list = selectedSubjectService.getSubjectList(selectedSubject,pagingVO);
        model.addAttribute("list", list);
        model.addAttribute("pagingVO", pagingVO);
        return "student/selectedSubject";

    }

    /**
     * 退选课题
     * @param subjectId
     * @return
     */
    @RequestMapping("deleteSubject")
    public String deleteSubject(Integer subjectId){
        Subject subject = SecurityUtils.getSubject();
        Integer userId = Integer.parseInt(subject.getPrincipal().toString());
        selectedSubjectService.deleteSubject(userId, subjectId);
        return "redirect:/student/selectedSubject";

    }


}
