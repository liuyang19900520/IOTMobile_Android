package com.liuyang19900520.iotmobile_android.model.db;

import android.content.Context;

import com.liuyang19900520.iotmobile_android.model.bean.TestApiBean;

import java.util.Date;
import java.util.List;

import javax.inject.Inject;

/**
 * @author liuya
 */
public class GreenDaoManager {

    private DaoMaster mDaoMaster;

    private DaoSession mDaoSession;

    @Inject
    public GreenDaoManager(Context context) {
        DaoMaster.DevOpenHelper devOpenHelper = new DaoMaster.DevOpenHelper(context, "iot", null);
        mDaoMaster = new DaoMaster(devOpenHelper.getWritableDatabase());
        mDaoSession = mDaoMaster.newSession();
    }

    public DaoMaster getMaster() {
        return mDaoMaster;
    }

    public DaoSession getSession() {
        return mDaoSession;
    }

    public DaoSession getNewSession() {
        mDaoSession = mDaoMaster.newSession();
        return mDaoSession;
    }

    public TestApiBeanDao getTestApiDao() {
        return getSession().getTestApiBeanDao();
    }


    /**
     * 查询所有
     *
     * @return
     */
    public List<TestApiBean> queryAll() {
        return getTestApiDao()
                .queryBuilder()
                .orderDesc(TestApiBeanDao.Properties.Id)
                .build()
                .list();
    }

    public List<TestApiBean> queryByCate(String category) {
        return getTestApiDao()
                .queryBuilder()
                .where(TestApiBeanDao.Properties.Category.eq(category))
                .orderDesc(TestApiBeanDao.Properties.Id)
                .build()
                .list();
    }

    /**
     * 新增
     *
     * @param testApi
     */
    public void insert(TestApiBean testApi) {
        testApi.setCreatedAt(new Date());
        testApi.setUpdatedAt(new Date());
        getTestApiDao().insert(testApi);
    }

    /**
     * 根据Guid查询
     *
     * @param id
     * @return
     */
    public boolean queryById(String id) {
        TestApiBean bean = getTestApiDao()
                .queryBuilder()
                .where(TestApiBeanDao.Properties.Id.eq(id))
                .build()
                .unique();
        if (null == bean) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * 根据guid删除
     */
    public void deleteByGuid(String guid) {
        TestApiBean bean = getTestApiDao()
                .queryBuilder()
                .where(TestApiBeanDao.Properties.Id.eq(guid))
                .build()
                .unique();
        if (null != bean) {
            getTestApiDao().delete(bean);
        }
    }

    /**
     * 删除
     *
     * @param likeBean
     */
    public void delete(TestApiBean likeBean) {
        getTestApiDao().delete(likeBean);
    }
}