package com.queen.rxjava2withretrofitdemo.greenDaoEntity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by liukun on 2017/4/10.
 */
@Entity
public class JoinDirector {

    @Id
    private Long gId;

    private Long subjectId;
    private Long directorId;
    @Generated(hash = 1734491371)
    public JoinDirector(Long gId, Long subjectId, Long directorId) {
        this.gId = gId;
        this.subjectId = subjectId;
        this.directorId = directorId;
    }
    @Generated(hash = 1535299083)
    public JoinDirector() {
    }

    public JoinDirector(Long subjectId, Long directorId) {
        this.subjectId = subjectId;
        this.directorId = directorId;
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
    public Long getDirectorId() {
        return this.directorId;
    }
    public void setDirectorId(Long directorId) {
        this.directorId = directorId;
    }
}
