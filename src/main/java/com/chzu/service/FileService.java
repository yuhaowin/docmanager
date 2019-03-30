package com.chzu.service;

import com.chzu.dao.FileDao;
import com.chzu.entity.ClassSubject;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import java.util.Date;
import java.util.List;

@Service
public class FileService {

    @Resource
    private FileDao fileDao;

    /**
     * 老师新增课程
     * @param classSubject
     */
    public void addSubject(ClassSubject classSubject){
        classSubject.setLastTime(new Date());
        fileDao.saveSubject(classSubject);
    }

    /**
     * 获取课程
     * @param classSubject
     * @return
     */
    public List<ClassSubject> getSubject(ClassSubject classSubject){
        return fileDao.getSubject(classSubject);
    }

    public void removeSubject(Integer id){
        ClassSubject classSubject = new ClassSubject();
        classSubject.setSubjectId(id);
        fileDao.deleteSubject(classSubject);
    }
}
