package com.queen.rxjava2withretrofitdemo;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;

import com.queen.rxjava2withretrofitdemo.dao.DaoMaster;
import com.queen.rxjava2withretrofitdemo.dao.DaoSession;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by liukun on 2017/4/7.
 */

public class App extends Application {

    private DaoMaster.DevOpenHelper mHelper;
    private SQLiteDatabase db;
    private DaoMaster mDaoMaster;
    private DaoSession mDaoSession;

    public static App instance;

    public static App getInstance(){
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        Realm.init(this);
        RealmConfiguration defaultConfig = new RealmConfiguration.Builder().build();
        Realm.setDefaultConfiguration(defaultConfig);

        setDatabase();
    }

    private void setDatabase(){
        mHelper = new DaoMaster.DevOpenHelper(this, "notes-db", null);
        db = mHelper.getWritableDatabase();
        mDaoMaster = new DaoMaster(db);
        mDaoSession = mDaoMaster.newSession();
    }
    public DaoSession getDaoSession(){
        return mDaoSession;
    }

    public SQLiteDatabase getDb(){
        return db;
    }
}
