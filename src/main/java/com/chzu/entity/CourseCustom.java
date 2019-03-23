package com.chzu.entity;

import javax.persistence.Entity;

/**
 * Course扩展类    课程
 */
@Entity
public class CourseCustom extends Course {

    //所属院系名
    private String collegeName;

    public String getCollegeName() {
        return collegeName;
    }

    public void setCollegeName(String collegeName) {
        this.collegeName = collegeName;
    }
}
