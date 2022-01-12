package com.uijin.momentrip.data.model;
import java.sql.Date;

public class Moment {
    int id;
    String momentTitle; // 앞면에 적는 것
    String momentContent; // 뒷면에 적는 것
    String momentImg; // 이미지 경로
    boolean momentPublic; //공개범위
    int UserId;
    int BookId;

    public Moment(int id, String momentTitle, String momentContent, String momentImg, boolean momentPublic, int UserId, int BookId) {
        this.id = id; // DB에서 조회한 id
        this.momentTitle = momentTitle;
        this.momentContent = momentContent;
        this.momentImg = momentImg;
        this.momentPublic = momentPublic;
        this.UserId = UserId;
        this.BookId = BookId;
    }

    // Getter and Setter
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public boolean isMomentPublic() {
        return momentPublic;
    }

    public void setMomentPublic(boolean momentPublic) {
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
