package com.chzu.entity;

import javax.persistence.*;

@Entity
@Table(name="college")
public class College {

    @Id
    @Column(name="college_id")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer collegeId;

    @Column(name="collegeName")
    private String collegeName;

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