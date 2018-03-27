package com.liuyang19900520.iotmobile_android.model.http;

import com.liuyang19900520.iotmobile_android.model.bean.LoginUser;
import com.liuyang19900520.iotmobile_android.model.bean.ResultVo;

import io.reactivex.Flowable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by liuya on 2018/3/26.
 */

public interface LoginApi {

    String HOST = "http://192.168.244.1:8080/";

    @POST("auth/login")
    Flowable<ResultVo> login(@Body LoginUser loginUser);
}
