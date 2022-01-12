package com.uijin.momentrip.data.repository.remote;

import com.uijin.momentrip.data.model.CreateMomentResponse;
import com.uijin.momentrip.data.model.GetBookListResponse;
import com.uijin.momentrip.data.model.GetBookResponse;
import com.uijin.momentrip.data.model.LoginRequest;
import com.uijin.momentrip.data.model.LoginResponse;
import com.uijin.momentrip.data.model.SignupRequest;
import com.uijin.momentrip.data.model.SignupResponse;

import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * 모멘트립 데이터 레포지토리
 *
 * DataSource로 부터 Model을 가져오는 것을 추상화하는 역할
 */
public class Repository {
    private RemoteDataSource remoteDataSource = new RemoteDataSource();

    /** 회원가입 */
    public void signup(SignupRequest request, GetDataCallback<SignupResponse> callback) {
        remoteDataSource.signup(request, callback);
    }

    /** 로그인 */
    public void login(LoginRequest request, GetDataCallback<LoginResponse> callback) {
        remoteDataSource.login(request, callback);
    }

    /** GET User By id */
    public void getUserById(String userId, GetDataCallback<SignupResponse> callback) {
        remoteDataSource.getUserById(userId, callback);
    }

    /** GET Book List 모든 유저 */
    public void getAllBooks(GetDataCallback<GetBookListResponse> callback) {
        remoteDataSource.getAllBooks(callback);
    }

    /** GET Book By id */
    public void getBookById(String book_id, GetDataCallback<GetBookResponse> callback) {
        remoteDataSource.getBookById(book_id, callback);
    }

    /** Post Momet */
    public void createMoment(MultipartBody.Part file , Map<String, RequestBody> request, Repository.GetDataCallback<CreateMomentResponse> callback) {
        remoteDataSource.createMoment(file, request, callback);
    }

    /** 응답 콜백 */
    public interface GetDataCallback<T> {
        void onSuccess(T data);
        void onFailure(Throwable throwable);
    }
}