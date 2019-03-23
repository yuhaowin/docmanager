package com.chzu.entity;

import javax.persistence.Entity;

/**
 * teacher扩展类
 */
@Entity
public class TeacherCustom extends Teacher {

    /**
     * 教师所属院系名
     */
    private String collegeName;

    public void setcollegeName(String collegeName) {
        this.collegeName = collegeName;
    }

    public String getcollegeName() {
        return collegeName;
    }
}
