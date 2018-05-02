package com.liuyang19900520.iotmobile_android.di.component;

import android.content.Context;

import com.liuyang19900520.iotmobile_android.di.module.ApplicationModule;
import com.liuyang19900520.iotmobile_android.di.module.HttpModule;
import com.liuyang19900520.iotmobile_android.model.db.GreenDaoManager;
import com.liuyang19900520.iotmobile_android.model.prefs.SharePrefManager;

import javax.inject.Singleton;

import dagger.Component;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;



/**
 * @author liuya
 */
@Singleton
@Component(modules = {ApplicationModule.class, HttpModule.class})
public interface AppComponent {

    // 提供Context给子Component使用
    Context getContext();

    SharePrefManager getSharePrefManager();

    Retrofit.Builder getRetrofitBuilder();

    OkHttpClient getOkHttpClient();

    GreenDaoManager getGreenDaoManager();

}