package com.uijin.momentrip.data.model;

import com.google.gson.annotations.SerializedName;

public class CreateMomentRequest {
    @SerializedName("momentTitle")
    public String momentTitle;

    @SerializedName("momentContent")
    public String momentContent;

    @SerializedName("momentImg")
    public String momentImg;

    @SerializedName("momentPublic")
    public Boolean momentPublic;

    @SerializedName("UserId")
    public int UserId;

    @SerializedName("BookId")
    public int BookId;

    public CreateMomentRequest(String momentTitle, String momentContent, String momentImg, Boolean momentPublic, int userId, int bookId) {
        this.momentTitle = momentTitle;
        this.momentContent = momentContent;
        this.momentImg = momentImg;
        this.momentPublic = momentPublic;
        UserId = userId;
        BookId = bookId;
    }

    public CreateMomentRequest() {
    }

    public String getMomentTitle() {
        return momentTitle;
    }

    public void setMomentTitle(String momentTitle) {
        this.momentTitle = momentTitle;
    }

    public String getMomentContent() {
        return momentContent;
    }

    public void setMomentContent(String momentContent) {
        this.momentContent = momentContent;
    }

    public String getMomentImg() {
        return momentImg;
    }

    public void setMomentImg(String momentImg) {
        this.momentImg = momentImg;
    }

    public Boolean getMomentPublic() {
        return momentPublic;
    }

    public void setMomentPublic(Boolean momentPublic) {
        this.momentPublic = momentPublic;
    }

    public int getUserId() {
        return UserId;
    }

    public void setUserId(int userId) {
        UserId = userId;
    }

    public int getBookId() {
        return BookId;
    }

    public void setBookId(int bookId) {
        BookId = bookId;
    }
}
