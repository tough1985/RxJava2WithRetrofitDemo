package com.queen.rxjava2withretrofitdemo.greenDaoEntity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by liukun on 2017/4/10.
 */
@Entity
public class JoinCast {
    @Id
    private Long gId;

    private Long subjectId;
    private Long castId;

    @Generated(hash = 1573720734)
    public JoinCast(Long gId, Long subjectId, Long castId) {
        this.gId = gId;
        this.subjectId = subjectId;
        this.castId = castId;
    }
    @Generated(hash = 1402690611)
    public JoinCast() {
    }

    public JoinCast(Long subjectId, Long castId) {
        this.subjectId = subjectId;
        this.castId = castId;
    }

    public Long getGId() {
        return this.gId;
    }
    public void setGId(Long gId) {
        this.gId = gId;
    }
    public Long getSubjectId() {
        return this.subjectId;
    }
    public void setSubjectId(Long subjectId) {
        this.subjectId = subjectId;
    }
    public Long getCastId() {
        return this.castId;
    }
    public void setCastId(Long castId) {
        this.castId = castId;
    }
}
