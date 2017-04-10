package com.queen.rxjava2withretrofitdemo.greenDaoEntity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by liukun on 2017/4/6.
 */
@Entity
public class GreenDoubanRating {

    @Id
    private Long gId;

    private int max;
    private float average;
    private String stars;
    private int min;
    @Generated(hash = 557271689)
    public GreenDoubanRating(Long gId, int max, float average, String stars,
            int min) {
        this.gId = gId;
        this.max = max;
        this.average = average;
        this.stars = stars;
        this.min = min;
    }
    @Generated(hash = 789673235)
    public GreenDoubanRating() {
    }
    public Long getGId() {
        return this.gId;
    }
    public void setGId(Long gId) {
        this.gId = gId;
    }
    public int getMax() {
        return this.max;
    }
    public void setMax(int max) {
        this.max = max;
    }
    public float getAverage() {
        return this.average;
    }
    public void setAverage(float average) {
        this.average = average;
    }
    public String getStars() {
        return this.stars;
    }
    public void setStars(String stars) {
        this.stars = stars;
    }
    public int getMin() {
        return this.min;
    }
    public void setMin(int min) {
        this.min = min;
    }



}
