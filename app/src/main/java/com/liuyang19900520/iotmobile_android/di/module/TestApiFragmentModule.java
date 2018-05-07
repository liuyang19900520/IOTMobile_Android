package com.liuyang19900520.iotmobile_android.di.module;


import com.liuyang19900520.iotmobile_android.di.qualifier.TestApiURL;
import com.liuyang19900520.iotmobile_android.di.scope.FragmentScope;
import com.liuyang19900520.iotmobile_android.model.http.TestApiApi;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author liuyang
 * @date 2017/11/8
 */

@Module
public class TestApiFragmentModule {

    @TestApiURL
    @Provides
    @FragmentScope
    Retrofit provideTestApiRetrofit(Retrofit.Builder builder, OkHttpClient client) {
        return builder
                .baseUrl(TestApiApi.HOST)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    @Provides
    @FragmentScope
    TestApiApi provideTestApi(@TestApiURL Retrofit retrofit) {
        return retrofit.create(TestApiApi.class);
    }

}
