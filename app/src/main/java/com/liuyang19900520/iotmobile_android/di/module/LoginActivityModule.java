package com.liuyang19900520.iotmobile_android.di.module;

import com.liuyang19900520.iotmobile_android.di.qualifier.LoginURL;
import com.liuyang19900520.iotmobile_android.di.scope.ActivityScope;
import com.liuyang19900520.iotmobile_android.model.http.LoginApi;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 *
 * @author liuyang
 * @date 2018/3/26
 */

@Module
public class LoginActivityModule {


    @LoginURL
    @Provides
    @ActivityScope
    Retrofit provideBingRetrofit(Retrofit.Builder builder, OkHttpClient client) {
        return builder
                .baseUrl(LoginApi.HOST)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    @Provides
    @ActivityScope
    LoginApi provideBingApi(@LoginURL Retrofit retrofit) {
        return retrofit.create(LoginApi.class);
    }





}
