package com.queen.rxjava2withretrofitdemo.greenDaoEntity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.ToOne;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.DaoException;
import com.queen.rxjava2withretrofitdemo.dao.DaoSession;
import com.queen.rxjava2withretrofitdemo.dao.GreenDoubanAvatarDao;
import com.queen.rxjava2withretrofitdemo.dao.GreenDoubanCastDao;

/**
 * Created by liukun on 2017/4/6.
 */
@Entity
public class GreenDoubanCast {

    @Id
    private Long gId;

    private String id;
    private String name;
    private String alt;

    private Long doubanAvatarId;

    @ToOne(joinProperty = "doubanAvatarId")
    private GreenDoubanAvatar avatars;

    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;

    /** Used for active entity operations. */
    @Generated(hash = 2119235563)
    private transient GreenDoubanCastDao myDao;

    @Generated(hash = 1358675892)
    public GreenDoubanCast(Long gId, String id, String name, String alt,
            Long doubanAvatarId) {
        this.gId = gId;
        this.id = id;
        this.name = name;
        this.alt = alt;
        this.doubanAvatarId = doubanAvatarId;
    }

    @Generated(hash = 198332937)
    public GreenDoubanCast() {
    }

    public Long getGId() {
        return this.gId;
    }

    public void setGId(Long gId) {
        this.gId = gId;
    }

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

    public Long getDoubanAvatarId() {
        return this.doubanAvatarId;
    }

    public void setDoubanAvatarId(Long doubanAvatarId) {
        this.doubanAvatarId = doubanAvatarId;
    }

    @Generated(hash = 947846224)
    private transient Long avatars__resolvedKey;

    /** To-one relationship, resolved on first access. */
    @Generated(hash = 193194661)
    public GreenDoubanAvatar getAvatars() {
        Long __key = this.doubanAvatarId;
        if (avatars__resolvedKey == null || !avatars__resolvedKey.equals(__key)) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            GreenDoubanAvatarDao targetDao = daoSession.getGreenDoubanAvatarDao();
            GreenDoubanAvatar avatarsNew = targetDao.load(__key);
            synchronized (this) {
                avatars = avatarsNew;
                avatars__resolvedKey = __key;
            }
        }
        return avatars;
    }

    public GreenDoubanAvatar getOriginAvatars() {
        return avatars;
    }

    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 1736913430)
    public void setAvatars(GreenDoubanAvatar avatars) {
        synchronized (this) {
            this.avatars = avatars;
            doubanAvatarId = avatars == null ? null : avatars.getGId();
            avatars__resolvedKey = doubanAvatarId;
        }
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#delete(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 128553479)
    public void delete() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.delete(this);
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#refresh(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 1942392019)
    public void refresh() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.refresh(this);
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#update(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 713229351)
    public void update() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.update(this);
    }

    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 765720522)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getGreenDoubanCastDao() : null;
    }




}
