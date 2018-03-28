package com.liuyang19900520.iotmobile_android.presenter;

import android.content.Context;
import android.content.Intent;

import com.liuyang19900520.iotmobile_android.base.BaseSubscriber;
import com.liuyang19900520.iotmobile_android.base.RxPresenter;
import com.liuyang19900520.iotmobile_android.contract.LoginContract;
import com.liuyang19900520.iotmobile_android.contract.MainContract;
import com.liuyang19900520.iotmobile_android.model.bean.LoginUser;
import com.liuyang19900520.iotmobile_android.model.bean.ResultVo;
import com.liuyang19900520.iotmobile_android.model.http.LoginApi;
import com.liuyang19900520.iotmobile_android.util.LogUtil;
import com.liuyang19900520.iotmobile_android.view.main.MainActivity;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * @author liuya
 * @date 2018/3/26
 */

public class MainPresenter extends RxPresenter<MainContract.View> implements MainContract.Presenter {

    private LoginApi loginApi;

    private Context context;


    @Inject
    public MainPresenter(LoginApi loginApi, Context context) {
        this.loginApi = loginApi;
        this.context = context;
    }


    @Override
    public void testApi() {
        addSubscribe(loginApi.testApi()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new BaseSubscriber<ResultVo>(context, mView) {
                    @Override
                    public void onNext(ResultVo resultVo) {
                        if (resultVo.getRet() == 0) {
                            LogUtil.d(resultVo.toString());
                            mView.showMsg(resultVo.toString());
                        }
                    }
                }));
    }
}
