package com.queen.rxjava2withretrofitdemo.entity;

/**
 * Created by liukun on 2017/4/6.
 */
public class DoubanCast {


    private String id;
    private String name;
    private String alt;

    private DoubanAvatar avatars;


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


    public DoubanAvatar getAvatars() {
        return avatars;
    }

    public void setAvatars(DoubanAvatar avatars) {
        this.avatars = avatars;
    }


}
