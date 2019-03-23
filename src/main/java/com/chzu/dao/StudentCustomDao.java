package com.chzu.dao;


import com.chzu.entity.PagingVO;
import com.chzu.entity.StudentCustom;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class StudentCustomDao {

    //分页查询学生信息
    public List<StudentCustom> findByPaging(PagingVO pagingVO){
        return null;
    }

    //查询学生信息，和其选课信息
    public StudentCustom findStudentAndSelectCourseListById(Integer id){
        return null;
    }
}
