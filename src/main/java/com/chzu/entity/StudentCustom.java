package com.chzu.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * Student的扩展类
 */
@Entity
public class StudentCustom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "user_name")
    private String userName;

    private String sex;

    @Column(name = "birth_year")
    private Date birthYear;

    private Date grade;

    @Column(name = "college_id")
    private Integer collegeId;

    //所属院系名
    private String collegeName;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Date getBirthYear() {
        return birthYear;
    }

    public void setBirthYear(Date birthYear) {
        this.birthYear = birthYear;
    }

    public Date getGrade() {
        return grade;
    }

    public void setGrade(Date grade) {
        this.grade = grade;
    }

    public Integer getCollegeId() {
        return collegeId;
    }

    public void setCollegeId(Integer collegeId) {
        this.collegeId = collegeId;
    }

    public String getCollegeName() {
        return collegeName;
    }

    public void setCollegeName(String collegeName) {
        this.collegeName = collegeName;
    }
}


