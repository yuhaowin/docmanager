package com.chzu.entity;

import javax.persistence.Entity;

/**
 * Student的扩展类
 */
@Entity
public class StudentCustom extends Student {
    //所属院系名
    private String collegeName;



    public String getCollegeName() {
        return collegeName;
    }

    public void setCollegeName(String collegeName) {
        this.collegeName = collegeName;
    }

}


