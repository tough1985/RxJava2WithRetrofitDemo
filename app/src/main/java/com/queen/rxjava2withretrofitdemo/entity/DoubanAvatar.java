package com.queen.rxjava2withretrofitdemo.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by liukun on 2017/4/6.
 */
public class DoubanAvatar {

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
