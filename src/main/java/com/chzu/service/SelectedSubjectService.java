package com.chzu.service;

import com.chzu.dao.FileDao;
import com.chzu.dao.SelectedSubjectDao;
import com.chzu.entity.ClassSubject;
import com.chzu.entity.PagingVO;
import com.chzu.entity.SelectedSubject;
import com.chzu.exception.Globalexception;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SelectedSubjectService {

    @Autowired
    private SelectedSubjectDao selectedSubjectDao;

    @Autowired
    private FileService fileService;


    /**
     * 选择课题
     * @param selectedSubject
     */
    public void selectSubject(SelectedSubject selectedSubject) throws Exception{
        if(getSelectedSubjectList(selectedSubject, null).size() > 0){
            throw new Globalexception("您已经选择过该课题了");
        }
        // 判断是否已经选完
        ClassSubject classSubject = new ClassSubject();
        classSubject.setSubjectId(selectedSubject.getSubjectId());
        classSubject = fileService.getSubject(classSubject).get(0);
        if(fileService.getSize(classSubject) < 1){
            throw new Globalexception("该课题已被选完");
        }
        selectedSubjectDao.selectSubject(selectedSubject);
    }

    /**
     * 查询已选择的课题
     * @param selectedSubject
     * @param pagingVO
     * @return
     */
    public List<SelectedSubject> getSelectedSubjectList(SelectedSubject selectedSubject, PagingVO pagingVO){
        return selectedSubjectDao.getSelectedSubjectList(selectedSubject, pagingVO);
    }

    /**
     * 获取已选课题详情
     * @param selectedSubject
     * @param pagingVO
     * @return
     */
    public List<ClassSubject> getSubjectList(SelectedSubject selectedSubject, PagingVO pagingVO){
        List<SelectedSubject>  selectedSubjectList = getSelectedSubjectList(selectedSubject, null);
        List<Integer> subjectIds = new ArrayList<>();
        for (SelectedSubject selectedSubject1 : selectedSubjectList){
            subjectIds.add(selectedSubject1.getSubjectId());
        }
        return selectedSubjectDao.getSubject(subjectIds, pagingVO);
    }

    /**
     * 删除课题
     * @param studentId
     * @param subjectId
     */
    public void deleteSubject(Integer studentId, Integer subjectId){
        selectedSubjectDao.deleteSubject(studentId, subjectId);
    }


}
