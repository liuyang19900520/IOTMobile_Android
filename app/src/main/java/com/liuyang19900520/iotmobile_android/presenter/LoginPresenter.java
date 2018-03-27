package com.liuyang19900520.iotmobile_android.presenter;

import android.content.Context;

import com.liuyang19900520.iotmobile_android.base.BaseSubscriber;
import com.liuyang19900520.iotmobile_android.base.RxPresenter;
import com.liuyang19900520.iotmobile_android.contract.LoginContract;
import com.liuyang19900520.iotmobile_android.model.bean.LoginUser;
import com.liuyang19900520.iotmobile_android.model.bean.ResultVo;
import com.liuyang19900520.iotmobile_android.model.http.LoginApi;
import com.liuyang19900520.iotmobile_android.util.AppApplicationUtil;
import com.liuyang19900520.iotmobile_android.util.LogUtil;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by liuya on 2018/3/26.
 */

public class LoginPresenter extends RxPresenter<LoginContract.View> implements LoginContract.Presenter {

    private LoginApi loginApi;

    private Context context;


    @Inject
    public LoginPresenter(LoginApi loginApi, Context context) {
        this.loginApi = loginApi;
        this.context = context;
    }

    @Override
    public void login(LoginUser loginUser) {
        addSubscribe(loginApi.login(loginUser)
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
