package com.chzu.entity;


import javax.persistence.*;

@Entity
@Table(name="userlogin")
public class UserLogin {
    private Integer userId;

    private String userName;

    private String password;

    private Integer role;

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }
}