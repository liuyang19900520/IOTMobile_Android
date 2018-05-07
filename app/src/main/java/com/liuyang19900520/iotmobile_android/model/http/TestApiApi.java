package com.liuyang19900520.iotmobile_android.model.http;


import com.liuyang19900520.iotmobile_android.model.bean.ResultVo;
import com.liuyang19900520.iotmobile_android.model.bean.WeiXinBean;

import io.reactivex.Flowable;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * 微信精选
 * Created by liuyang on 2017/11/8.
 */

public interface TestApiApi {

    String HOST = "http://192.168.244.1:8085/";


    @POST("{url}")
    Flowable<ResultVo> tesApi(@Path(value = "url", encoded = true) String url, @Query("params") Object parmas);
}
