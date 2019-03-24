package com.chzu.entity;

import javax.persistence.*;

/**
 * Course扩展类    课程
 */
@Entity
public class CourseCustom{

    @Id
    @Column(name="course_id")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer courseId;

    @Column(name="course_name")
    private String courseName;

    @Column(name="teacher_id")
    private Integer teacherId;

    @Column(name="course_time")
    private String courseTime;

    @Column(name="class_room")
    private String classRoom;

    @Column(name="course_week")
    private Integer courseWeek;

    @Column(name="course_type")
    private String courseType;

    @Column(name="college_id")
    private Integer collegeId;

    private Integer score;

    //所属院系名
    private String collegeName;

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public Integer getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Integer teacherId) {
        this.teacherId = teacherId;
    }

    public String getCourseTime() {
        return courseTime;
    }

    public void setCourseTime(String courseTime) {
        this.courseTime = courseTime;
    }

    public String getClassRoom() {
        return classRoom;
    }

    public void setClassRoom(String classRoom) {
        this.classRoom = classRoom;
    }

    public Integer getCourseWeek() {
        return courseWeek;
    }

    public void setCourseWeek(Integer courseWeek) {
        this.courseWeek = courseWeek;
    }

    public String getCourseType() {
        return courseType;
    }

    public void setCourseType(String courseType) {
        this.courseType = courseType;
    }

    public Integer getCollegeId() {
        return collegeId;
    }

    public void setCollegeId(Integer collegeId) {
        this.collegeId = collegeId;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public String getCollegeName() {
        return collegeName;
    }

    public void setCollegeName(String collegeName) {
        this.collegeName = collegeName;
    }
}
