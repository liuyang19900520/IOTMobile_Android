package com.liuyang19900520.iotmobile_android.di.module;

import android.content.Context;

import com.liuyang19900520.iotmobile_android.config.IOTApplication;
import com.liuyang19900520.iotmobile_android.model.db.GreenDaoManager;
import com.liuyang19900520.iotmobile_android.model.prefs.SharePrefManager;


import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by xiarh on 2017/9/21.
 */

@Module
public class ApplicationModule {

    private final IOTApplication application;

    public ApplicationModule(IOTApplication application) {
        this.application = application;
    }

    @Provides
    @Singleton
    Context provideContext() {
        return application;
    }

    @Provides
    @Singleton
    SharePrefManager provideSharePrefManager(Context context) {
        return new SharePrefManager(context);
    }

    @Provides
    @Singleton
    GreenDaoManager provideGreenDaoManager(Context context){
        return new GreenDaoManager(context);
    }
}