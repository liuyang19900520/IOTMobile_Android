package com.liuyang19900520.iotmobile_android.model.db;

import java.util.Map;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.AbstractDaoSession;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.identityscope.IdentityScopeType;
import org.greenrobot.greendao.internal.DaoConfig;

import com.liuyang19900520.iotmobile_android.model.bean.TestApiBean;

import com.liuyang19900520.iotmobile_android.model.db.TestApiBeanDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see org.greenrobot.greendao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig testApiBeanDaoConfig;

    private final TestApiBeanDao testApiBeanDao;

    public DaoSession(Database db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        testApiBeanDaoConfig = daoConfigMap.get(TestApiBeanDao.class).clone();
        testApiBeanDaoConfig.initIdentityScope(type);

        testApiBeanDao = new TestApiBeanDao(testApiBeanDaoConfig, this);

        registerDao(TestApiBean.class, testApiBeanDao);
    }
    
    public void clear() {
        testApiBeanDaoConfig.clearIdentityScope();
    }

    public TestApiBeanDao getTestApiBeanDao() {
        return testApiBeanDao;
    }

}