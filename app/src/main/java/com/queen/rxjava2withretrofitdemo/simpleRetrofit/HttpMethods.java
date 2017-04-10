package com.queen.rxjava2withretrofitdemo.simpleRetrofit;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by liukun on 2017/4/5.
 */

public class HttpMethods {

    public static final String BASE_URL = "http://192.168.10.129:3001/";

    private Retrofit mRetrofit;
    private OkHttpClient client;

    private DemoService mDemoService;

    private HttpMethods() {

        client = new OkHttpClient.Builder().addInterceptor(new SignInterceptor())
//                .addNetworkInterceptor()
                .connectTimeout(15, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true)
                .build();

        mRetrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        mDemoService = mRetrofit.create(DemoService.class);
    }

    private static HttpMethods instance;

    public static HttpMethods getInstance() {

        if (instance == null) {
            synchronized (HttpMethods.class) {
                instance = new HttpMethods();
            }
        }
        return instance;

    }

    public void getUser(Callback<Object> call){
        mDemoService.getDemo(1, "希爸").enqueue(call);
    }

    public void registerUser(Callback<Object> call){
        mDemoService.postDemo("希爸", 32).enqueue(call);
    }


}
