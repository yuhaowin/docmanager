package com.chzu.service;


import com.chzu.dao.SelectedCourseDao;
import com.chzu.dao.StudentDao;
import com.chzu.entity.SelectedCourse;
import com.chzu.entity.SelectedCourseCustom;
import com.chzu.entity.Student;
import com.chzu.entity.StudentCustom;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SelectedCourseService{

    @Autowired
    private SelectedCourseDao selectedCourseDao;

    @Autowired
    private StudentDao studentDao;

    public List<SelectedCourseCustom> findByCourseID(Integer id) throws Exception {
        SelectedCourse selectedCourse = new SelectedCourse();
        selectedCourse.setCourseId(id);
        List<SelectedCourse> list = selectedCourseDao.selectByExample(selectedCourse);
        List<SelectedCourseCustom> secList = new ArrayList<SelectedCourseCustom>();
        for (SelectedCourse s : list) {
            SelectedCourseCustom sec = new SelectedCourseCustom();
            BeanUtils.copyProperties(s, sec);
            //判断是否完成类该课程
            if (sec.getMark() != null) {
                sec.setOver(true);
            }
            Student student = studentDao.selectById(sec.getStudentId());
            StudentCustom studentCustom = new StudentCustom();
            BeanUtils.copyProperties(student, studentCustom);
            sec.setStudentCustom(studentCustom);
            secList.add(sec);
        }
        return secList;
    }

    public List<SelectedCourseCustom> findByCourseIDPaging(Integer page, Integer id) throws Exception {
        return null;
    }

    /**
     * 获取该课程学生数
     * @param id
     * @return
     * @throws Exception
     */
    public Integer countByCourseID(Integer id) throws Exception {
        return selectedCourseDao.countById(id);
    }

    /**
     * 查询指定学生成绩
     * @param selectedCourseCustom
     * @return
     * @throws Exception
     */
    public SelectedCourseCustom findOne(SelectedCourseCustom selectedCourseCustom) throws Exception {
        List<SelectedCourse> list = selectedCourseDao.selectByExample(selectedCourseCustom);
        if (list.size() > 0) {
            SelectedCourseCustom sc = new SelectedCourseCustom();
            BeanUtils.copyProperties(list.get(0), sc);
            Student student = studentDao.selectById(selectedCourseCustom.getStudentId());
            StudentCustom studentCustom = new StudentCustom();
            BeanUtils.copyProperties(student, studentCustom);
            sc.setStudentCustom(studentCustom);
            return sc;
        }
        return null;
    }

    /**
     * 更新
     * @param selectedCourseCustom
     * @throws Exception
     */
    public void updateOne(SelectedCourseCustom selectedCourseCustom) throws Exception {
        selectedCourseDao.updateByExample(selectedCourseCustom);
    }

    /**
     * 保存
     * @param selectedCourseCustom
     * @throws Exception
     */
    public void save(SelectedCourseCustom selectedCourseCustom) throws Exception {
        selectedCourseDao.insert(selectedCourseCustom);
    }


    public List<SelectedCourseCustom> findByStudentID(Integer id) throws Exception {
        return null;
    }

    /**
     * 删除
     * @param selectedCourseCustom
     * @throws Exception
     */
    public void remove(SelectedCourseCustom selectedCourseCustom) throws Exception {
        selectedCourseDao.delete(selectedCourseCustom);
    }

}
