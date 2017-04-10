package com.queen.rxjava2withretrofitdemo.simpleRetrofit;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by liukun on 2017/4/5.
 */

public interface DemoService {

    @GET("api/users/getUser")
    Call<Object> getDemo(@Query("id") int id, @Query("username") String username);

    @FormUrlEncoded
    @POST("api/users/registerUser")
    Call<Object> postDemo(@Field("username") String username, @Field("age")int age);
}
