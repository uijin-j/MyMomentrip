package com.uijin.momentrip.data.repository.remote;

import com.uijin.momentrip.data.model.CreateBookResponse;
import com.uijin.momentrip.data.model.CreateMomentRequest;
import com.uijin.momentrip.data.model.CreateMomentResponse;
import com.uijin.momentrip.data.model.GetBookListResponse;
import com.uijin.momentrip.data.model.GetBookResponse;
import com.uijin.momentrip.data.model.GetMomentListResponse;
import com.uijin.momentrip.data.model.LoginRequest;
import com.uijin.momentrip.data.model.LoginResponse;
import com.uijin.momentrip.data.model.SignupRequest;
import com.uijin.momentrip.data.model.SignupResponse;

import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RemoteDataSource { // API를 통해 데이터 소스를 가져오는 객체
    private Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://52.79.158.158:3000/momentrip/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    private MomentripService service = retrofit.create(MomentripService.class);

    /** 회원가입 */
    public void signup(SignupRequest request, RemoteDataSource.GetDataCallback<SignupResponse> callback) {
        // 인터페이스 구현
        service.signup(request).enqueue(new Callback<SignupResponse>() {
            @Override
            public void onResponse(Call<SignupResponse> call, Response<SignupResponse> response) { // 요청 성공
                if (response.isSuccessful()) {
                    callback.onSuccess(response.body());
                }
            }
            @Override
            public void onFailure(Call<SignupResponse> call, Throwable t) { // 요청 실패
                callback.onFailure(t);
            }
        });
    }

    /** 로그인 */
    public void login(LoginRequest request, RemoteDataSource.GetDataCallback<LoginResponse> callback) {
        // 인터페이스 구현
        service.login(request).enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if (response.isSuccessful()) { callback.onSuccess(response.body()); }
            }
            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) { callback.onFailure(t); }
        });
    }

    /** GET User By id */
    public void getUserById(String userId, RemoteDataSource.GetDataCallback<SignupResponse> callback) {
        // 인터페이스 구현
        service.getUserById(userId).enqueue(new Callback<SignupResponse>() {
            @Override
            public void onResponse(Call<SignupResponse> call, Response<SignupResponse> response) {
                if (response.isSuccessful()) { callback.onSuccess(response.body()); }
            }
            @Override
            public void onFailure(Call<SignupResponse> call, Throwable t) { callback.onFailure(t); }
        });
    }

    /** Book */
    /* Post Book */
    public void createBook(MultipartBody.Part file , Map<String, RequestBody> request, RemoteDataSource.GetDataCallback<CreateBookResponse> callback) {
        // 인터페이스 구현
        service.createBook(file, request).enqueue(new Callback<CreateBookResponse>() {
            @Override
            public void onResponse(Call<CreateBookResponse> call, Response<CreateBookResponse> response) {
                if (response.isSuccessful()) { callback.onSuccess(response.body()); }
            }
            @Override
            public void onFailure(Call<CreateBookResponse> call, Throwable t) { callback.onFailure(t); }
        });
    }

    /* GET Book List 모든 유저 */
    public void getAllBooks(RemoteDataSource.GetDataCallback<GetBookListResponse> callback) {
        // 인터페이스 구현
        service.getAllBooks().enqueue(new Callback<GetBookListResponse>() {
            @Override
            public void onResponse(Call<GetBookListResponse> call, Response<GetBookListResponse> response) {
                if (response.isSuccessful()) { callback.onSuccess(response.body()); }
            }
            @Override
            public void onFailure(Call<GetBookListResponse> call, Throwable t) { callback.onFailure(t); }
        });
    }

    /* GET Book By id */
    public void getBookById(String book_id, RemoteDataSource.GetDataCallback<GetBookResponse> callback) {
        // 인터페이스 구현
        service.getBookById(book_id).enqueue(new Callback<GetBookResponse>() {
            @Override
            public void onResponse(Call<GetBookResponse> call, Response<GetBookResponse> response) {
                if (response.isSuccessful()) { callback.onSuccess(response.body()); }
            }
            @Override
            public void onFailure(Call<GetBookResponse> call, Throwable t) { callback.onFailure(t); }
        });
    }

    /* GET Book By userId */
    public void getBooksByUser(String user_id, RemoteDataSource.GetDataCallback<GetBookListResponse> callback) {
        // 인터페이스 구현
        service.getBooksByUser(user_id).enqueue(new Callback<GetBookListResponse>() {
            @Override
            public void onResponse(Call<GetBookListResponse> call, Response<GetBookListResponse> response) {
                if (response.isSuccessful()) { callback.onSuccess(response.body()); }
            }
            @Override
            public void onFailure(Call<GetBookListResponse> call, Throwable t) { callback.onFailure(t); }
        });
    }

    /** Moment */
    /* Post Moment */
    public void createMoment(MultipartBody.Part file , Map<String, RequestBody> request, RemoteDataSource.GetDataCallback<CreateMomentResponse> callback) {
        // 인터페이스 구현
        service.createMoment(file, request).enqueue(new Callback<CreateMomentResponse>() {
            @Override
            public void onResponse(Call<CreateMomentResponse> call, Response<CreateMomentResponse> response) {
                if (response.isSuccessful()) { callback.onSuccess(response.body()); }
            }
            @Override
            public void onFailure(Call<CreateMomentResponse> call, Throwable t) { callback.onFailure(t); }
        });
    }

    public  void getMomentsByUser(String user_id, RemoteDataSource.GetDataCallback<GetMomentListResponse> callback) {
        // 인터페이스 구현
        service.getMomentsByUser(user_id).enqueue(new Callback<GetMomentListResponse>() {
            @Override
            public void onResponse(Call<GetMomentListResponse> call, Response<GetMomentListResponse> response) {
                if (response.isSuccessful()) { callback.onSuccess(response.body()); }
            }
            @Override
            public void onFailure(Call<GetMomentListResponse> call, Throwable t) { callback.onFailure(t); }
        });
    }

    /** 응답 콜백 */
    public interface GetDataCallback<T> {
        void onSuccess(T data);
        void onFailure(Throwable throwable);
    }
}
