package com.chzu.entity;

import javax.persistence.*;

@Entity
@Table(name = "selected_subject")
public class SelectedSubject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "subject_id")
    private Integer subjectId;
    @Column(name = "student_id")
    private Integer studentId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Integer subjectId) {
        this.subjectId = subjectId;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    @Override
    public String toString() {
        return "SelectedSubject{" +
                "id=" + id +
                ", subjectId=" + subjectId +
                ", studentId=" + studentId +
                '}';
    }
}
