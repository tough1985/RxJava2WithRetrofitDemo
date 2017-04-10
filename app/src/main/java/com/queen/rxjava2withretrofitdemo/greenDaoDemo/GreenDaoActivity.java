package com.queen.rxjava2withretrofitdemo.greenDaoDemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;

import com.queen.rxjava2withretrofitdemo.App;
import com.queen.rxjava2withretrofitdemo.R;
import com.queen.rxjava2withretrofitdemo.entity.User;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by liukun on 2017/4/7.
 */

public class GreenDaoActivity extends AppCompatActivity {


    @BindView(R.id.greendao_add_BN)
    Button greendaoAddBN;
    @BindView(R.id.greendao_delete_BN)
    Button greendaoDeleteBN;
    @BindView(R.id.greendao_query_BN)
    Button greendaoQueryBN;
    @BindView(R.id.greendao_update_BN)
    Button greendaoUpdateBN;
    @BindView(R.id.greendao_content_TV)
    TextView greendaoContentTV;

    private UserDao mUserDao;

    private int addIndex = 0;
    private int deleteIndex = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_greendao);
        ButterKnife.bind(this);

        mUserDao = App.getInstance().getDaoSession().getUserDao();
    }

    @OnClick(R.id.greendao_add_BN)
    protected void addUser(){
        mUserDao.insert(new User(addIndex, "hope_" + addIndex));
        addIndex++;
    }

    @OnClick(R.id.greendao_delete_BN)
    protected void deleteUser(){
        if (deleteIndex < addIndex) {
            mUserDao.deleteByKey((long) deleteIndex);
            deleteIndex++;
        }
    }

    @OnClick(R.id.greendao_update_BN)
    protected void updateUser(){

        mUserDao.update(new User(addIndex-1, "hope_abc"));
    }

    @OnClick(R.id.greendao_query_BN)
    protected void queryUser(){
        List<User> userList = mUserDao.loadAll();
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < userList.size(); i++) {
            sb.append(userList.get(i).toString() + "\n");
        }

        greendaoContentTV.setText(sb.toString());
    }


}
