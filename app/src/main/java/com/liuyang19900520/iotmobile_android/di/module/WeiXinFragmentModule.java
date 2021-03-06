package com.liuyang19900520.iotmobile_android.di.module;


import com.liuyang19900520.iotmobile_android.di.qualifier.WeiXinURL;
import com.liuyang19900520.iotmobile_android.di.scope.FragmentScope;
import com.liuyang19900520.iotmobile_android.model.http.WeiXinApi;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 *
 * @author liuyang
 * @date 2017/11/8
 */

@Module
public class WeiXinFragmentModule {

    @WeiXinURL
    @Provides
    @FragmentScope
    Retrofit provideWeiXinRetrofit(Retrofit.Builder builder, OkHttpClient client) {
        return builder
                .baseUrl(WeiXinApi.HOST)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    @Provides
    @FragmentScope
    WeiXinApi provideWeiXinApi(@WeiXinURL Retrofit retrofit) {
        return retrofit.create(WeiXinApi.class);
    }
}
