package com.chzu.entity;

/**
 * teacher扩展类
 */
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
