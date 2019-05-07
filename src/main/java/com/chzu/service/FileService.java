package com.chzu.service;

import com.chzu.dao.FileDao;
import com.chzu.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class FileService {

    @Resource
    private FileDao fileDao;

    @Autowired
    private SelectedSubjectService selectedSubjectService;

    /**
     * 老师新增课程
     *
     * @param classSubject
     */
    public void addSubject(ClassSubject classSubject) {
        classSubject.setLastTime(new Date());
        fileDao.saveSubject(classSubject);
    }

    /**
     * 获取课程
     *
     * @param classSubject
     * @return
     */
    public List<ClassSubject> getSubject(ClassSubject classSubject) {
        return fileDao.getSubject(classSubject, false);
    }

    /**
     * 获取课程
     *
     * @param classSubject
     * @return
     */
    public List<ClassSubject> getSubject(ClassSubject classSubject,PagingVO pagingVO) {
        return fileDao.getSubject(classSubject, false, pagingVO);
    }

    /**
     * 获取我的文档
     *
     * @param courseDoc
     * @return
     */
    public List<CourseDoc> getCourseDoc(CourseDoc courseDoc) {
        return fileDao.getCourseDoc(courseDoc, false);
    }


    /**
     * 获取课件总数
     *
     * @param isAdmin
     * @return
     */
    public Integer classSubjectCount(Integer courseId, Boolean isAdmin) {
        return fileDao.classSubjectCount(courseId, isAdmin);
    }

    public List<ClassSubject> findByPaging(Integer toPageNo, Integer courseId, Boolean isAdmin) {
        PagingVO pagingVO = new PagingVO();
        pagingVO.setToPageNo(toPageNo);
        List<ClassSubject> list = fileDao.findByPaging(pagingVO, courseId, isAdmin);
        return list;
    }


    /**
     * 获取课程
     *
     * @param classSubject
     * @return
     */
    public List<ClassSubject> getSubject(ClassSubject classSubject, Boolean isAdmin) {
        return fileDao.getSubject(classSubject, isAdmin);
    }

    /**
     * 获取我的文档
     *
     * @param courseDoc
     * @return
     */
    public List<CourseDoc> getCourseDoc(CourseDoc courseDoc, Boolean isAdmin) {
        return fileDao.getCourseDoc(courseDoc, isAdmin);
    }

    /**
     * 学生保存文件
     *
     * @param courseDoc
     */
    public void saveCourseDoc(CourseDoc courseDoc) {
        fileDao.saveCourseDoc(courseDoc);
    }

    /**
     * 学生删除文件
     *
     * @param courseDoc
     */
    public void deleteCourdeDoc(CourseDoc courseDoc) {
        fileDao.deleteCourseDoc(courseDoc);
    }

    public void removeSubject(Integer id) {
        ClassSubject classSubject = new ClassSubject();
        classSubject.setSubjectId(id);
        fileDao.deleteSubject(classSubject);
    }

    public void subjectBak(Integer subjectId) {
        fileDao.subjectBak(subjectId);
    }

    public void courseDocBak(Integer fileId) {
        fileDao.courseDocBak(fileId);
    }

    public List<BakFile> getBakFile(String fileName,PagingVO pagingVO){
        return fileDao.getBakFile(fileName,pagingVO);
    }

    public Integer getSize(ClassSubject classSubject){
        CourseDoc courseDoc = new CourseDoc();
        courseDoc.setSubjectId(classSubject.getSubjectId());
        SelectedSubject selectedSubject = new SelectedSubject();
        selectedSubject.setSubjectId(classSubject.getSubjectId());
        return  classSubject.getSize() - selectedSubjectService.getSelectedSubjectList(selectedSubject,null).size();
    }

}
