package com.chzu.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "subject")
public class ClassSubject {

    @Id
    @Column(name = "subject_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer subjectId;
    @Column(name = "teacher_id")
    private Integer teacherId;
    @Column(name = "course_id")
    private Integer courseId;
    @Column(name = "subject_name")
    private String subjectName;
    @Column(name = "subject_desc")
    private String describe;
    @Column(name = "file_name")
    private String fileName;
    @Column(name = "file_url")
    private String fileUrl;
    @Column(name = "last_time")
    private Date lastTime;
    private Integer bak;

    public Integer getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Integer subjectId) {
        this.subjectId = subjectId;
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

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
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

    public Integer getBak() {
        return bak;
    }

    public void setBak(Integer bak) {
        this.bak = bak;
    }
}
