package com.uijin.momentrip.data.model;

import com.google.gson.annotations.SerializedName;

public class CreateMomentResponse {
    @SerializedName("status")
    public int status;

    @SerializedName("success")
    public boolean success;

    @SerializedName("message")
    public String message;

    @SerializedName("data")
    public Moment moment;

    public int getStatus() {
        return status;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public Moment getMoment() {
        return moment;
    }
}
