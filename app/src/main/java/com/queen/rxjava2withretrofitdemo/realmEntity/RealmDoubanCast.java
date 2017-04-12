package com.queen.rxjava2withretrofitdemo.realmEntity;

import android.os.Parcelable;

import org.parceler.Parcel;

import io.realm.RealmDoubanCastRealmProxy;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by liukun on 2017/4/6.
 */
public class RealmDoubanCast extends RealmObject implements Parcelable{

    @PrimaryKey
    private String id;
    private String name;
    private String alt;

    private RealmDoubanAvatar avatars;

    public RealmDoubanCast() {
    }


    /**
     * ———————————————— ↓↓↓↓ getter and setter ↓↓↓↓ ————————————————
     */

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

    /**
     * ———————————————— ↓↓↓↓ Parcelable code ↓↓↓↓ ————————————————
     */
    protected RealmDoubanCast(android.os.Parcel in) {
        id = in.readString();
        name = in.readString();
        alt = in.readString();
        avatars = in.readParcelable(RealmDoubanAvatar.class.getClassLoader());
    }

    public static final Creator<RealmDoubanCast> CREATOR = new Creator<RealmDoubanCast>() {
        @Override
        public RealmDoubanCast createFromParcel(android.os.Parcel in) {
            return new RealmDoubanCast(in);
        }

        @Override
        public RealmDoubanCast[] newArray(int size) {
            return new RealmDoubanCast[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(android.os.Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(name);
        dest.writeString(alt);
        dest.writeParcelable(avatars, flags);
    }

}
