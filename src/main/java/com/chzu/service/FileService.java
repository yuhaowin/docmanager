package com.chzu.service;

import com.chzu.dao.FileDao;
import com.chzu.entity.Subject;
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
     * @param subject
     */
    public void addSubject(Subject subject){
        subject.setLastTime(new Date());
        fileDao.saveSubject(subject);
    }

    /**
     * 获取课程
     * @param subject
     * @return
     */
    public List<Subject> getSubject(Subject subject){
        return fileDao.getSubject(subject);
    }
}
