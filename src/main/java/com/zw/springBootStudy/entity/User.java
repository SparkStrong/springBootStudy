package com.zw.springBootStudy.entity;

import java.io.Serializable;

/**
 * Created by Nack on 2018/4/4.
 */
public class User implements Serializable {
    private static final long serialVersionUID = 1L;
    private long id;
    private int userId;
    private String userName;
    private String email;
    private String password;
    private int activationStatus;
    private String activationCode;

    public User() {
        super();
    }
    
    public User(String userName, String email, String password, int activationStatus, String activationCode) {
        super();
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.activationStatus = activationStatus;
        this.activationCode = activationCode;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getActivationStatus() {
        return activationStatus;
    }

    public void setActivationStatus(int activationStatus) {
        this.activationStatus = activationStatus;
    }

    public String getActivationCode() {
        return activationCode;
    }

    public void setActivationCode(String activationCode) {
        this.activationCode = activationCode;
    }

    @Override
    public String toString() {
        return "UserEntity{" +
                "userId='" + userId + '\'' +
                ", userName='" + userName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
