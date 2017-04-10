package com.queen.rxjava2withretrofitdemo.greenDaoEntity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by liukun on 2017/4/6.
 */
@Entity
public class GreenDoubanAvatar {

    @Id
    private Long gId;

    private String small;
    private String large;
    private String medium;
    @Generated(hash = 1246398355)
    public GreenDoubanAvatar(Long gId, String small, String large, String medium) {
        this.gId = gId;
        this.small = small;
        this.large = large;
        this.medium = medium;
    }
    @Generated(hash = 674634666)
    public GreenDoubanAvatar() {
    }
    public Long getGId() {
        return this.gId;
    }
    public void setGId(Long gId) {
        this.gId = gId;
    }
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
