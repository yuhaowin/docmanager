package com.chzu.entity;


import com.chzu.excelannotation.ExcelMapping;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * 导出excel实体类
 */
@Entity
public class ExcelDTO {

    @Id
    @Column(name = "user_id")
    private Integer studentId;
    /**
     * 课程名称
     */
    @ExcelMapping("课程名称")
    @Column(name = "course_name")
    private String courseName;
    /**
     * 学生姓名
     */
    @ExcelMapping("学生姓名")
    @Column(name = "user_name")
    private String studentName;
    /**
     * 分数
     */
    @ExcelMapping("成绩")
    @Column(name = "mark")
    private String mark;

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }
}
