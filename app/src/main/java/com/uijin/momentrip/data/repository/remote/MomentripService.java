package com.uijin.momentrip.data.repository.remote;

import com.uijin.momentrip.data.model.CreateBookRequest;
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
import com.uijin.momentrip.data.model.UpdateUserRequest;
import com.uijin.momentrip.data.model.UpdateUserResponse;

import java.util.HashMap;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Path;

public interface MomentripService { // 네트워크 통신(Retrofit2)를 위한 API 명세
    /**
     * Auth
     */
    @POST("auth/signUp")
    Call<SignupResponse> signup(@Body SignupRequest signupRequest);

    @POST("auth/signIn")
    Call<LoginResponse> login(@Body LoginRequest loginRequest);

    /**
     * User
     */
    @PATCH("user/{user_id}")
    Call<UpdateUserResponse> updateUser(@Path("user_id") String user_id, @Body UpdateUserRequest updateUserRequest);

    @GET("user/select/id/{user_id}")
    Call<SignupResponse> getUserById(@Path("user_id") String user_id);

    /**
     * Book
     */
    @Multipart
    @POST("book")
    Call<CreateBookResponse> createBook(@Part MultipartBody.Part file, @PartMap Map<String, RequestBody> map);

    @GET("book")
    Call<GetBookListResponse> getAllBooks();

    @GET("book/select/{id}")
    Call<GetBookResponse> getBookById(@Path("id") String book_id);

    @GET("book/user/{user_id}")
    Call<GetBookListResponse> getBooksByUser(@Path("user_id") String user_id);

    @GET("book/search/{keyword}")
    Call<GetBookListResponse> getBooksByKeyword(@Path("keyword") String keyword);

    /**
     * Moment
     */
    @Multipart
    @POST("moment")
    Call<CreateMomentResponse> createMoment(@Part MultipartBody.Part file, @PartMap Map<String, RequestBody> map);

    @GET("moment/user/{user_id}")
    Call<GetMomentListResponse> getMomentsByUser(@Path("user_id") String user_id);

    /**
     * Follow
     */

}
