package com.uijin.momentrip.data.model;

import com.google.gson.annotations.SerializedName;

public class Book {
    @SerializedName("id")
    int id;

    @SerializedName("book_img")
    String book_img;

    @SerializedName("book_title")
    String book_title;

    @SerializedName("book_public")
    boolean book_public; // 공개 범위

    @SerializedName("book_hit")
    int book_hit; // 좋아요 수

    @SerializedName("UserId")
    int userId;

    // Constructor
    public Book(int id, String book_img, String book_title, boolean book_public, int book_hit, int userId ) {
        this.id = id; // DB에서 조회한 id
        this.book_img = book_img; // 이미지 경로
        this.book_title = book_title;
        this.book_public = book_public;
        this.book_hit = book_hit;
        this.userId = userId;
    }

    // Getter and Setter
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBook_img() {
        return book_img;
    }

    public void setBook_img(String book_img) {
        this.book_img = book_img;
    }

    public String getBook_title() {
        return book_title;
    }

    public void setBook_title(String book_title) {
        this.book_title = book_title;
    }

    public boolean isBook_public() {
        return book_public;
    }

    public void setBook_public(boolean book_public) {
        this.book_public = book_public;
    }

    public int getBook_hit() {
        return book_hit;
    }

    public void setBook_hit(int book_hit) {
        this.book_hit = book_hit;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
