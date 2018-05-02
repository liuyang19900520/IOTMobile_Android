package com.liuyang19900520.iotmobile_android.presenter;

import android.content.Context;

import com.liuyang19900520.iotmobile_android.base.RxPresenter;
import com.liuyang19900520.iotmobile_android.contract.MainContract;

import javax.inject.Inject;

/**
 * @author liuya
 * @date 2018/3/26
 */

public class MainPresenter extends RxPresenter<MainContract.View> implements MainContract.Presenter {

    private Context context;

    @Inject
    public MainPresenter(Context context) {
        this.context = context;
    }


}
