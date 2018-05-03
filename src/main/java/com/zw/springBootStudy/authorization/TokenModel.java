package com.zw.springBootStudy.authorization;

/**
 * Created by Nack on 2018/4/24.
 * Token的Model类，
 */
public class TokenModel {

    private String userName;
    private String token;

    public TokenModel(String userName, String token) {
        this.userName = userName;
        this.token = token;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "TokenModel{" +
                "userName='" + userName + '\'' +
                ", token='" + token + '\'' +
                '}';
    }
}
