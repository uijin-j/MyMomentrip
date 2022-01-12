package com.uijin.momentrip.data.model;

import com.google.gson.annotations.SerializedName;

public class LoginResponse { // 로그인 응답을 위한 객체
    @SerializedName("user")
    public User user;

    @SerializedName("token")
    public String token;

    // Getter and Setter
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
