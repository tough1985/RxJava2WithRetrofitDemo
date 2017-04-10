package com.queen.rxjava2withretrofitdemo.simpleRetrofit;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import com.queen.rxjava2withretrofitdemo.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by liukun on 2017/4/5.
 */

public class SimpleRetrofitActivity extends AppCompatActivity {

    @BindView(R.id.simple_retrofit_result_TV)
    TextView simpleRetrofitResultTV;
    @BindView(R.id.simple_retrofit_get_BN)
    Button simpleRetrofitGetBN;
    @BindView(R.id.simple_retrofit_post_BN)
    Button simpleRetrofitPostBN;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_retrofit);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.simple_retrofit_get_BN)
    protected void sendGetRequest(){
        HttpMethods httpMethods = HttpMethods.getInstance();
        httpMethods.getUser(new Callback<Object>() {
            @Override
            public void onResponse(Call<Object> call, Response<Object> response) {
                simpleRetrofitResultTV.setText(response.body().toString());
                Log.e("sendGetRequest","onResponse");
            }

            @Override
            public void onFailure(Call<Object> call, Throwable t) {
                Log.e("sendGetRequest","onFailure");
            }
        });
    }

    @OnClick(R.id.simple_retrofit_post_BN)
    protected void sendPostRequest(){
        HttpMethods httpMethods = HttpMethods.getInstance();
        httpMethods.registerUser(new Callback<Object>() {
            @Override
            public void onResponse(Call<Object> call, Response<Object> response) {
                simpleRetrofitResultTV.setText(response.body().toString());
                Log.e("sendPostRequest","onResponse");
            }

            @Override
            public void onFailure(Call<Object> call, Throwable t) {
                Log.e("sendPostRequest","onFailure");
            }
        });
    }
}
