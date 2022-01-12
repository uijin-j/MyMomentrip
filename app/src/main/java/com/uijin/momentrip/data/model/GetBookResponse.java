package com.uijin.momentrip.data.model;

import com.google.gson.annotations.SerializedName;

public class GetBookResponse {
    @SerializedName("status")
    public int status;

    @SerializedName("success")
    public boolean success;

    @SerializedName("message")
    public String message;

    @SerializedName("data")
    public Book book;

    public int getStatus() {
        return status;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public Book getBook() {
        return book;
    }
}
