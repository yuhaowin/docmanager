package com.chzu.dao;

import com.chzu.entity.PagingVO;
import com.chzu.entity.TeacherCustom;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TeacherCustomDao {
    //分页查询老师信息
    public List<TeacherCustom> findByPaging(PagingVO pagingVO){
        return null;
    }
}
