package com.chzu.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "course_ware")
public class CourseWare {

    @Id
    @Column(name = "file_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer fileId;
    @Column(name = "teacher_id")
    private Integer teacherId;
    @Column(name = "course_id")
    private Integer courseId;
    @Column(name = "subject_name")
    private String subjectName;
    @Column(name = "file_name")
    private String fileName;
    @Column(name = "file_url")
    private String fileUrl;
    @Column(name = "last_time")
    private Date lastTime;

    public Integer getFileId() {
        return fileId;
    }

    public void setFileId(Integer fileId) {
        this.fileId = fileId;
    }

    public Integer getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Integer teacherId) {
        this.teacherId = teacherId;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    public Date getLastTime() {
        return lastTime;
    }

    public void setLastTime(Date lastTime) {
        this.lastTime = lastTime;
    }
}
