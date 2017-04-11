package com.queen.rxjava2withretrofitdemo.realmEntity;

import io.realm.RealmObject;

/**
 * Created by liukun on 2017/4/6.
 */
public class RealmDoubanCast extends RealmObject {


    private String id;
    private String name;
    private String alt;

    private RealmDoubanAvatar avatars;


    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAlt() {
        return this.alt;
    }

    public void setAlt(String alt) {
        this.alt = alt;
    }


    public RealmDoubanAvatar getAvatars() {
        return avatars;
    }

    public void setAvatars(RealmDoubanAvatar avatars) {
        this.avatars = avatars;
    }


}
