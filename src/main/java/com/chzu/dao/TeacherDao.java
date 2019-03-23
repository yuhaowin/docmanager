package com.chzu.dao;

import com.chzu.entity.Teacher;
import org.springframework.stereotype.Repository;

import java.util.List;



@Repository
public class TeacherDao {

    /**
     * 获取所有记录数
     *
     * @return
     */
    public int count() {
        return 1;
    }

    /**
     * 根据id删除
     *
     * @param Id
     * @return
     */
    public int deleteById(Integer Id) {
        return 1;
    }

    /**
     * 新增
     *
     * @param teacher
     * @return
     */
    public int insert(Teacher teacher) {
        return 1;
    }

    /**
     * 通过名字查找
     *
     * @param name
     * @return
     */
    public List<Teacher> selectByName(String name) {
        return null;
    }

    public List<Teacher> selectAll(){
        return null;
    }

    /**
     * 通过id查询
     * @param Id
     * @return
     */
    public Teacher selectById(Integer Id){
        return null;
    }

    /**
     *
     * @param teacher
     * @return
     */
    public int update(Teacher teacher){
        return 1;
    }
}
