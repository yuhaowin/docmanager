package com.chzu.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "course_doc")
public class CourseDoc {

    @Id
    @Column(name = "file_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer fileId;
    @Column(name = "student_id")
    private Integer studentId;
    @Column(name = "course_id")
    private Integer courseId;
    @Column(name = "subject_id")
    private Integer subjectId;
    @Column(name = "file_name")
    private String fileName;
    @Column(name = "file_url")
    private String fileUrl;
    @Column(name = "last_time")
    private Date lastTime;
    private Integer bak;
    @Column(name = "is_delete")
    private Integer delete;

    public Integer getFileId() {
        return fileId;
    }

    public void setFileId(Integer fileId) {
        this.fileId = fileId;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public Integer getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Integer subjectId) {
        this.subjectId = subjectId;
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

    public Integer getDelete() {
        return delete;
    }

    public void setDelete(Integer delete) {
        this.delete = delete;
    }
}
