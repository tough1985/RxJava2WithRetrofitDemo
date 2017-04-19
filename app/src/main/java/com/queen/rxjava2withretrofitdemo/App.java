package com.queen.rxjava2withretrofitdemo;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.queen.rxjava2withretrofitdemo.dao.DaoMaster;
import com.queen.rxjava2withretrofitdemo.dao.DaoSession;
import com.queen.rxjava2withretrofitdemo.realmEntity.RealmDoubanModule;

import io.realm.DynamicRealm;
import io.realm.FieldAttribute;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmMigration;
import io.realm.RealmObjectSchema;
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
                .schemaVersion(3)
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

            Log.e("migrate", "oldVersion=" + oldVersion);

            if (oldVersion == 0) {
//                schema.get("RealmDoubanAvatar").removePrimaryKey();

//                schema.create("RealmDoubanGenre")
//                        .addField("small", String.class)
//                        .addField("large", String.class)
//                        .addField("medium", String.class);
                oldVersion++;
            }

            //如果数据库升级，需要告知Realm做了那些变更
            if (oldVersion == 1) {

//                if (!schema.get("RealmDoubanGenre").getPrimaryKey().equals("name")) {
//                    schema.get("RealmDoubanGenre").addField("name", String.class, FieldAttribute.PRIMARY_KEY);
//                }


//                if (!schema.get("RealmDoubanMovieSubject").hasField("genresList")) {
//                    schema.get("RealmDoubanMovieSubject").addRealmListField("genresList", schema.get("RealmDoubanGenre"));
//                }

                oldVersion++;
            }

            if (oldVersion == 2) {

                if (schema.get("RealmDoubanGenre").getPrimaryKey().equals("name")) {
                    schema.get("RealmDoubanGenre")
                            .removePrimaryKey()
                            .removeField("name");
                }
                if (!schema.get("RealmDoubanGenre").getPrimaryKey().equals("value")) {
                    
                    if (schema.get("RealmDoubanGenre").hasPrimaryKey()) {
                        schema.get("RealmDoubanGenre").removePrimaryKey();
                    }

                    schema.get("RealmDoubanGenre")
                            .addField("value", String.class, FieldAttribute.PRIMARY_KEY);
                }

                oldVersion++;
            }

            if (oldVersion == 3) {


                RealmObjectSchema movieSubject = schema.get("RealmDoubanMovieSubject");

                if (movieSubject != null) {
                    if (!movieSubject.hasPrimaryKey() || !movieSubject.getPrimaryKey().equals("id")) {
                        if (movieSubject.hasPrimaryKey()) { //如果有主键
                            if (!movieSubject.getPrimaryKey().equals("id")) {   //主键不是id
                                movieSubject.removePrimaryKey();
                            }
                        }

                        try{
                            if (movieSubject.hasField("id")) {  //如果有id
                                movieSubject.addPrimaryKey("id");   //把id添加为主键
                            } else {    //如果没有id
                                movieSubject.addField("id", String.class, FieldAttribute.PRIMARY_KEY);  //添加id 并设置主键
                            }
                        } catch (RuntimeException e){
//                            schema.remove("RealmDoubanMovieSubject");

                            realm.delete("RealmDoubanMovieSubject");
                            if (movieSubject.hasField("id")) {  //如果有id
                                movieSubject.addPrimaryKey("id");   //把id添加为主键
                            } else {    //如果没有id
                                movieSubject.addField("id", String.class, FieldAttribute.PRIMARY_KEY);  //添加id 并设置主键
                            }

                        }

                    }
                }

                RealmObjectSchema doubanCastSchema = schema.get("RealmDoubanCast");

                if (doubanCastSchema != null) {
                    if (!doubanCastSchema.hasPrimaryKey() || !doubanCastSchema.getPrimaryKey().equals("id")) {
                        if (doubanCastSchema.hasPrimaryKey()) { //如果有主键
                            if (!doubanCastSchema.getPrimaryKey().equals("id")) {   //主键不是id
                                doubanCastSchema.removePrimaryKey();
                            }
                        }

                        try{
                            if (doubanCastSchema.hasField("id")) {  //如果有id
                                doubanCastSchema.addPrimaryKey("id");   //把id添加为主键
                            } else {    //如果没有id
                                doubanCastSchema.addField("id", String.class, FieldAttribute.PRIMARY_KEY);  //添加id 并设置主键
                            }
                        } catch (RuntimeException e){
//                            schema.remove("RealmDoubanCast");
                            realm.delete("RealmDoubanCast");
                            if (doubanCastSchema.hasField("id")) {  //如果有id
                                doubanCastSchema.addPrimaryKey("id");   //把id添加为主键
                            } else {    //如果没有id
                                doubanCastSchema.addField("id", String.class, FieldAttribute.PRIMARY_KEY);  //添加id 并设置主键
                            }
                        }


                    }
                }


                if (schema.get("RealmDoubanGenre").hasField("small")) {
                    schema.get("RealmDoubanGenre").removeField("small");
                }

                if (schema.get("RealmDoubanGenre").hasField("large")) {
                    schema.get("RealmDoubanGenre").removeField("large");
                }

                if (schema.get("RealmDoubanGenre").hasField("medium")) {
                    schema.get("RealmDoubanGenre").removeField("medium");
                }

                if (schema.get("RealmDoubanMovieSubject").hasField("genresList")) {
                    schema.get("RealmDoubanMovieSubject")
                            .removeField("genresList");
                }
                if (!schema.get("RealmDoubanMovieSubject").hasField("genres")) {
                    schema.get("RealmDoubanMovieSubject")
                            .addRealmListField("genres", schema.get("RealmDoubanGenre"));
                }
//                schema.get("RealmDoubanMovieSubject")
//                        .removeField("genresList")
//                        .addRealmListField("genres", schema.get("RealmDoubanGenre"));
                oldVersion++;
            }
        }
    }
}
