package com.chzu.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * teacher扩展类
 */
@Entity
public class TeacherCustom {

    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "sex")
    private String sex;

    @Column(name = "birth_year")
    private Date birthYear;

    /**
     * 学历
     */
    @Column(name = "degree")
    private String degree;

    /**
     * 职称
     */
    @Column(name = "title")
    private String title;

    /**
     * 入职时间
     */
    @Column(name = "grade")
    private Date grade;

    /**
     * 所在院系对应的id
     */
    @Column(name = "college_id")
    private Integer collegeId;

    /**
     * 教师所属院系名
     */
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

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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
