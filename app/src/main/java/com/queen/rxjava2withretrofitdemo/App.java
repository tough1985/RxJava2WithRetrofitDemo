package com.queen.rxjava2withretrofitdemo;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.queen.rxjava2withretrofitdemo.dao.DaoMaster;
import com.queen.rxjava2withretrofitdemo.dao.DaoSession;
import com.queen.rxjava2withretrofitdemo.realmEntity.RealmDoubanModule;

import io.realm.DynamicRealm;
import io.realm.FieldAttribute;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmMigration;
import io.realm.RealmSchema;

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
        RealmConfiguration defaultConfig = new RealmConfiguration.Builder()
                .schemaVersion(2)
                .modules(new RealmDoubanModule())
                .migration(new DefaultMigration())
                .build();
        Realm.setDefaultConfiguration(defaultConfig);

        setDatabase();

        Fresco.initialize(this);
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

    private class DefaultMigration implements RealmMigration {

        @Override
        public void migrate(DynamicRealm realm, long oldVersion, long newVersion) {
            RealmSchema schema = realm.getSchema();

            if (oldVersion == 0) {
//                schema.get("RealmDoubanAvatar").removePrimaryKey();

                schema.create("RealmDoubanGenre")
                        .addField("small", String.class)
                        .addField("large", String.class)
                        .addField("medium", String.class);
                oldVersion++;
            }

            //如果数据库升级，需要告知Realm做了那些变更
            if (oldVersion == 1) {
                schema.create("RealmDoubanGenre").addField("name", String.class, FieldAttribute.PRIMARY_KEY);
                schema.get("RealmDoubanMovieSubject").addRealmListField("genresList", schema.get("RealmDoubanGenre"));
            }
        }
    }
}
