package com.uijin.momentrip.data.model;

import com.google.gson.annotations.SerializedName;

public class UpdateUserRequest {
    @SerializedName("email")
    public String email;

    @SerializedName("password")
    public String password;

    @SerializedName("snsId")
    public String snsId;

    @SerializedName("profile_img")
    public String profile_img;

    public String getEmail() {
        return email;
    }

    // Constructor
    public UpdateUserRequest(String email, String password, String snsId, String profile_img) {
        this.email = email;
        this.password = password;
        this.snsId = snsId;
        this.profile_img = profile_img;
    }

    // Getter and Setter
    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSnsId() {
        return snsId;
    }

    public void setSnsId(String snsId) {
        this.snsId = snsId;
    }

    public String getProfile_img() {
        return profile_img;
    }

    public void setProfile_img(String profile_img) {
        this.profile_img = profile_img;
    }
}
