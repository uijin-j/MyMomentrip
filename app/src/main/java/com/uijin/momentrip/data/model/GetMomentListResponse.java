package com.uijin.momentrip.data.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetMomentListResponse {
    @SerializedName("status")
    public int status;

    @SerializedName("success")
    public boolean success;

    @SerializedName("message")
    public String message;

    @SerializedName("data")
    public List<Moment> moments;

    public int getStatus() {
        return status;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public List<Moment> getMoments() {
        return moments;
    }
}
