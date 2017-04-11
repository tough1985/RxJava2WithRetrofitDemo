package com.queen.rxjava2withretrofitdemo.realmEntity;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by liukun on 2017/4/11.
 */

public class RealmDoubanGenre extends RealmObject {

    @PrimaryKey
    private String name;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
