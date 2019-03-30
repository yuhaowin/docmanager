package com.chzu.service;

import com.chzu.dao.FileDao;
import com.chzu.entity.ClassSubject;
import com.chzu.entity.CourseDoc;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class FileService {

    @Resource
    private FileDao fileDao;

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
     * 获取我的文档
     *
     * @param courseDoc
     * @return
     */
    public List<CourseDoc> getCourseDoc(CourseDoc courseDoc) {
        return fileDao.getCourseDoc(courseDoc, false);
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
}
