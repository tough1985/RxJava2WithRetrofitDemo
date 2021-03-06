package com.queen.rxjava2withretrofitdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.queen.rxjava2withretrofitdemo.frescoDemo.FrescoActivity;
import com.queen.rxjava2withretrofitdemo.greenDaoDemo.GreenDaoActivity;
import com.queen.rxjava2withretrofitdemo.greenDaoDemo.GreenDaoDoubanActivity;
import com.queen.rxjava2withretrofitdemo.mvpDemo.activity.DoubanMovieActivity;
import com.queen.rxjava2withretrofitdemo.mvpWithRxjavaDemo.activity.RxDoubanMovieActivity;
import com.queen.rxjava2withretrofitdemo.realmDemo.activity.RealmDoubanActivity;
import com.queen.rxjava2withretrofitdemo.simpleRetrofit.SimpleRetrofitActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.main_simple_retrofit_BN)
    Button mainSimpleRetrofitBN;
    @BindView(R.id.main_simple_mvp_BN)
    Button mainSimpleMvpBN;
    @BindView(R.id.main_simple_mvp_rxjava_BN)
    Button mainSimpleMvpRxjavaBN;
    @BindView(R.id.main_greendao_demo_BN)
    Button mainGreendaoDemoBN;
    @BindView(R.id.main_greendao_douban_BN)
    Button mainGreendaoDoubanBN;
    @BindView(R.id.main_realm_douban_BN)
    Button mainRealmDoubanBN;
    @BindView(R.id.main_fresco_BN)
    Button mainFrescoBN;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mainSimpleRetrofitBN.setOnClickListener(this);
        mainSimpleMvpBN.setOnClickListener(this);
        mainSimpleMvpRxjavaBN.setOnClickListener(this);
        mainGreendaoDemoBN.setOnClickListener(this);
        mainGreendaoDoubanBN.setOnClickListener(this);
        mainRealmDoubanBN.setOnClickListener(this);
        mainFrescoBN.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Class c = null;

        switch (v.getId()) {
            case R.id.main_simple_retrofit_BN:
                c = SimpleRetrofitActivity.class;
                break;
            case R.id.main_simple_mvp_BN:
                c = DoubanMovieActivity.class;
                break;
            case R.id.main_simple_mvp_rxjava_BN:
                c = RxDoubanMovieActivity.class;
                break;
            case R.id.main_greendao_demo_BN:
                c = GreenDaoActivity.class;
                break;
            case R.id.main_greendao_douban_BN:
                c = GreenDaoDoubanActivity.class;
                break;
            case R.id.main_realm_douban_BN:
                c = RealmDoubanActivity.class;
                break;
            case R.id.main_fresco_BN:
                c = FrescoActivity.class;
                break;
        }

        Intent i = new Intent(MainActivity.this, c);
        startActivity(i);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

}
