package com.queen.rxjava2withretrofitdemo.realmEntity;

import io.realm.RealmObject;

/**
 * Created by liukun on 2017/4/6.
 */
public class RealmDoubanAvatar extends RealmObject {

    private String small;
    private String large;
    private String medium;

    public String getSmall() {
        return this.small;
    }
    public void setSmall(String small) {
        this.small = small;
    }
    public String getLarge() {
        return this.large;
    }
    public void setLarge(String large) {
        this.large = large;
    }
    public String getMedium() {
        return this.medium;
    }
    public void setMedium(String medium) {
        this.medium = medium;
    }



}
