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

    public String getCollegeName() {
        return collegeName;
    }

    public void setCollegeName(String collegeName) {
        this.collegeName = collegeName;
    }
}
