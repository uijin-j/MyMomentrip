package com.uijin.momentrip.data.model;

import com.google.gson.annotations.SerializedName;

public class CreateBookRequest {
    @SerializedName("book_title")
    public String book_title;

    @SerializedName("UserId")
    public int userId;

    @SerializedName("book_public")
    public boolean book_public;

    @SerializedName("book_img")
    public String book_img;

    // Constructor
    public CreateBookRequest(String book_title, int userId, boolean book_public, String book_img) {
        this.book_title = book_title;
        this.userId = userId;
        this.book_public = book_public;
        this.book_img = book_img;
    }

    // Getter and Setter
    public String getBook_title() {
        return book_title;
    }

    public void setBook_title(String book_title) {
        this.book_title = book_title;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public boolean isBook_public() {
        return book_public;
    }

    public void setBook_public(boolean book_public) {
        this.book_public = book_public;
    }

    public String getBook_img() {
        return book_img;
    }

    public void setBook_img(String book_img) {
        this.book_img = book_img;
    }
}
