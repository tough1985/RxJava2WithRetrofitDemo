package com.queen.rxjava2withretrofitdemo.realmEntity;

import android.os.Parcelable;

import org.parceler.Parcel;

import io.realm.RealmDoubanAvatarRealmProxy;
import io.realm.RealmObject;

/**
 * Created by liukun on 2017/4/6.
 */
public class RealmDoubanAvatar extends RealmObject implements Parcelable {

    private String small;
    private String large;
    private String medium;

    public RealmDoubanAvatar() {
    }

    /**
     * ———————————————— ↓↓↓↓ getter and setter ↓↓↓↓ ————————————————
     */

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

    /**
     * ———————————————— ↓↓↓↓ Parcelable code ↓↓↓↓ ————————————————
     */

    protected RealmDoubanAvatar(android.os.Parcel in) {
        small = in.readString();
        large = in.readString();
        medium = in.readString();
    }

    public static final Creator<RealmDoubanAvatar> CREATOR = new Creator<RealmDoubanAvatar>() {
        @Override
        public RealmDoubanAvatar createFromParcel(android.os.Parcel in) {
            return new RealmDoubanAvatar(in);
        }

        @Override
        public RealmDoubanAvatar[] newArray(int size) {
            return new RealmDoubanAvatar[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(android.os.Parcel dest, int flags) {
        dest.writeString(small);
        dest.writeString(large);
        dest.writeString(medium);
    }
}
