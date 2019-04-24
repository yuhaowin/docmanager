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

    @Autowired
    private CollegeService collegeService;

    @Autowired
    private StudentService studentService;

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
        pagingVO.setTotalCount(courseService.findByTeacherID(Integer.parseInt(username), null).size());
        if (page == null || page == 0) {
            pagingVO.setToPageNo(1);
            //list = courseService.findByPaging(1);
        } else {
            pagingVO.setToPageNo(page);
            //list = courseService.findByPaging(page);
        }

        List<CourseCustom> list = courseService.findByTeacherID(Integer.parseInt(username), pagingVO);
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
    public String gradeCourse(Integer id, Integer page, Model model) throws Exception {
        if (id == null) {
            return "";
        }
        //页码对象
        PagingVO pagingVO = new PagingVO();
        //设置总页数
        pagingVO.setTotalCount(selectedCourseService.findByCourseID(id, null).size());
        if (page == null || page == 0) {
            pagingVO.setToPageNo(1);
            //list = courseService.findByPaging(1);
        } else {
            pagingVO.setToPageNo(page);
            //list = courseService.findByPaging(page);
        }

        List<SelectedCourseCustom> list = selectedCourseService.findByCourseID(id, pagingVO);
        model.addAttribute("pagingVO", pagingVO);
        model.addAttribute("selectedCourseList", list);
        model.addAttribute("id", id);
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
        List<College> list = collegeService.finAll();

        model.addAttribute("collegeList", list);
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
        TeacherCustom teacherCustom = teacherService.findById(Integer.parseInt(userid));
        teacher.setCollegeId(teacherCustom.getCollegeId());
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
                              String describe, Integer size, MultipartFile file) {
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
        if(size != null){
            classSubject.setSize(size);
        }
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
    public String showSubject(Integer id, Integer page, Model model) throws  Exception{
        Subject subject = SecurityUtils.getSubject();
        String userid = (String) subject.getPrincipal();
        PagingVO pagingVO = new PagingVO();
        ClassSubject classSubject = new ClassSubject();
        classSubject.setCourseId(id);
        classSubject.setTeacherId(Integer.parseInt(userid));
        List<ClassSubject> subjectList = fileService.getSubject(classSubject);
        pagingVO.setTotalCount(subjectList.size());
        if (page == null || page == 0) {
            pagingVO.setToPageNo(1);
        }else {
            pagingVO.setToPageNo(page);
        }
        subjectList = fileService.getSubject(classSubject, pagingVO);
        // 返回课程ID 用于新增选题使用
        model.addAttribute("courseId", id);
        model.addAttribute("subjectList", subjectList);
        model.addAttribute("pagingVO", pagingVO);
        return "teacher/showSubject";
    }

    /**
     * 搜索选题
     *
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/selectSubject", method = RequestMethod.POST)
    public String selectSubject(String findByName, Integer courseId, Model model){
        Subject subject = SecurityUtils.getSubject();
        String userid = (String) subject.getPrincipal();
        ClassSubject classSubject = new ClassSubject();
        classSubject.setCourseId(courseId);
        classSubject.setSubjectName(findByName);
        classSubject.setTeacherId(Integer.parseInt(userid));
        List<ClassSubject> subjectList = fileService.getSubject(classSubject);
        // 返回课程ID 用于新增选题使用
        model.addAttribute("courseId", courseId);
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


    /**
     * 我的课程搜索
     * @param findByName
     * @return
     */
    @RequestMapping(value = "/selectCourse", method = RequestMethod.POST)
    public String selectCourse(String findByName, Model model){
        Subject subject = SecurityUtils.getSubject();
        String username = (String) subject.getPrincipal();
        List<CourseCustom> list = courseService.findCourseCustom(findByName,Integer.parseInt(username));
        model.addAttribute("courseList", list);
        return "teacher/showCourse";

    }

    @RequestMapping(value = "importStudent")
    public String importStudent(Model model,Integer page,Integer courseId){
        CourseCustom course = courseService.findById(courseId);
        Integer collegeId =course.getCollegeId();
        PagingVO pagingVO = new PagingVO();
        pagingVO.setTotalCount(studentService.getStudentByCollege(collegeId,null,courseId).size());
        if (page == null || page == 0) {
            pagingVO.setToPageNo(1);
        }else {
            pagingVO.setToPageNo(page);
        }
        List<Student> studentList = studentService.getStudentByCollege(collegeId, pagingVO,courseId);
        model.addAttribute("studentList", studentList);
        model.addAttribute("pagingVO", pagingVO);
        model.addAttribute("courseId" ,courseId);
        return "teacher/importStudent";
    }

    @RequestMapping("import")
    public String importStu(Integer page, Integer studentId, Integer courseId)throws Exception{
        SelectedCourseCustom selectedCourseCustom = new SelectedCourseCustom();
        selectedCourseCustom.setCourseId(courseId);
        selectedCourseCustom.setStudentId(studentId);

        SelectedCourse selectedCourse = new SelectedCourse();
        selectedCourse.setCourseId(courseId);
        selectedCourse.setStudentId(selectedCourseCustom.getStudentId());

        SelectedCourseCustom s = selectedCourseService.findOne(selectedCourse);

        if (s == null) {
            selectedCourseService.save(selectedCourseCustom);
        } else {
            throw new Globalexception("该门课程你已经选了，不能再选");
        }
        return "redirect:/teacher/importStudent?page=" + page + "&courseId=" + courseId;
    }

}
