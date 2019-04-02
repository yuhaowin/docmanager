package com.chzu.controller;


import com.chzu.entity.*;
import com.chzu.service.CourseService;
import com.chzu.service.FileService;
import com.chzu.service.SelectedCourseService;
import com.chzu.service.TeacherService;
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
import java.util.List;

@Controller
@RequestMapping(value = "/teacher")
public class TeacherController {

    @Resource(name = "teacherService")
    private TeacherService teacherService;

    @Resource(name = "courseService")
    private CourseService courseService;

    @Resource(name = "selectedCourseService")
    private SelectedCourseService selectedCourseService;

    @Autowired
    private FileService fileService;

    /**
     * 显示我的课程
     *
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/showCourse")
    public String stuCourseShow(Model model,Integer page) throws Exception {

        Subject subject = SecurityUtils.getSubject();
        String username = (String) subject.getPrincipal();

        //页码对象
        PagingVO pagingVO = new PagingVO();
        //设置总页数
        pagingVO.setTotalCount(30);
        if (page == null || page == 0) {
            pagingVO.setToPageNo(1);
            //list = courseService.findByPaging(1);
        } else {
            pagingVO.setToPageNo(page);
            //list = courseService.findByPaging(page);
        }

        List<CourseCustom> list = courseService.findByTeacherID(Integer.parseInt(username));
        model.addAttribute("courseList", list);
        model.addAttribute("pagingVO", pagingVO);
        return "teacher/showCourse";
    }

    /**
     * 显示成绩
     *
     * @param id
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/gradeCourse")
    public String gradeCourse(Integer id, Model model) throws Exception {
        if (id == null) {
            return "";
        }
        List<SelectedCourseCustom> list = selectedCourseService.findByCourseID(id);
        model.addAttribute("selectedCourseList", list);
        return "teacher/showGrade";
    }

    /**
     * 打分页面
     *
     * @param scc
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/mark", method = {RequestMethod.GET})
    public String markUI(SelectedCourse scc, Model model) throws Exception {

        SelectedCourseCustom selectedCourseCustom = selectedCourseService.findOne(scc);

        model.addAttribute("selectedCourse", selectedCourseCustom);

        return "teacher/mark";
    }

    /**
     * 打分
     *
     * @param scc
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/mark", method = {RequestMethod.POST})
    public String mark(SelectedCourse scc) throws Exception {

        selectedCourseService.updateOne(scc);

        return "redirect:/teacher/gradeCourse?id=" + scc.getCourseId();
    }


    /**
     * 修改密码
     *
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/passwordRest")
    public String passwordRest() throws Exception {
        return "teacher/passwordRest";
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
        Teacher teacher = teacherService.profile(Integer.parseInt(username));
        model.addAttribute("teacher", teacher);
        return "teacher/profile";
    }

    /**
     * 更新个人信息
     *
     * @param model
     * @param teacher
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/profile_update")
    public String profileUpdate(Model model, Teacher teacher) throws Exception {
        Subject subject = SecurityUtils.getSubject();
        String userid = (String) subject.getPrincipal();

        teacher.setUserId(Integer.parseInt(userid));

        int result = teacherService.profileUpdate(teacher);
        System.out.println(result);
        return profile(model);
    }

    /**
     * 新增选题
     *
     * @param id    课程ID
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/addSubject", method = RequestMethod.GET)
    public String addSubject(Integer id, Model model) {
        model.addAttribute("courseId", id);
        return "teacher/addSubject";
    }

    /**
     * 保存选题信息
     *
     * @param courseId
     * @param subjectName
     * @param describe
     * @param file
     * @return
     */
    @RequestMapping(value = "/addSubject", method = RequestMethod.POST)
    public String saveSubject(HttpServletRequest request, Integer courseId, String subjectName,
                              String describe, MultipartFile file) {
        String basePath = request.getSession().getServletContext().getRealPath("/") + "WEB-INF/files/";
        // 上传文件的文件名
        String oldName = file.getOriginalFilename();
        // 文件所在项目的相对路径
        String path = UploadUtil.uploadFile(file, basePath);
        Subject subject = SecurityUtils.getSubject();
        String userid = (String) subject.getPrincipal();
        ClassSubject classSubject = new ClassSubject();
        classSubject.setCourseId(courseId);
        classSubject.setDescribe(describe);
        classSubject.setFileName(oldName);
        classSubject.setFileUrl(path);
        classSubject.setTeacherId(Integer.parseInt(userid));
        classSubject.setSubjectName(subjectName);
        fileService.addSubject(classSubject);
        return "redirect:/teacher/showsubject?id=" + courseId;
    }


    /**
     * 展示选题信息
     *
     * @param id    课程id
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/showsubject")
    public String showSubject(Integer id, Model model){
        Subject subject = SecurityUtils.getSubject();
        String userid = (String) subject.getPrincipal();
        if (id == null) {
            return "";
        }
        ClassSubject classSubject = new ClassSubject();
        classSubject.setCourseId(id);
        List<ClassSubject> subjectList = fileService.getSubject(classSubject);
        // 返回课程ID 用于新增选题使用
        model.addAttribute("courseId", id);
        model.addAttribute("subjectList", subjectList);
        return "teacher/showSubject";
    }

    /**
     * 删除选题
     *
     * @param id
     * @param courseId
     * @return
     */
    @RequestMapping(value = "/removeSubject")
    public String removeSubject(Integer id, Integer courseId) {
        fileService.removeSubject(id);
        return "redirect:/teacher/showsubject?id=" + courseId;
    }

    @RequestMapping(value = "/getCourseDoc")
    public String getCourseDoc(Integer id, Model model) {
        CourseDoc courseDoc = new CourseDoc();
        courseDoc.setSubjectId(id);
        List<CourseDoc> courseDocList = fileService.getCourseDoc(courseDoc);
        model.addAttribute("courseDocList", courseDocList);
        return "teacher/showCourseDoc";
    }

}
