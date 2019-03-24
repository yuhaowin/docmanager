package com.chzu.entity;

import javax.persistence.*;

@Entity
@Table(name = "selected_course")
@IdClass(KeyMap.class)
public class SelectedCourse {
    @Id
    @Column(name = "course_id")
    private Integer courseId;
    @Id
    @Column(name = "student_id")
    private Integer studentId;

    private Integer mark;

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public Integer getMark() {
        return mark;
    }

    public void setMark(Integer mark) {
        this.mark = mark;
    }
}