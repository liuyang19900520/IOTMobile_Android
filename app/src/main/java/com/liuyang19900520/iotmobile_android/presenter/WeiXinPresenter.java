package com.liuyang19900520.iotmobile_android.presenter;

import android.content.Context;


import com.liuyang19900520.iotmobile_android.base.BaseSubscriber;
import com.liuyang19900520.iotmobile_android.base.RxBus;
import com.liuyang19900520.iotmobile_android.base.RxPresenter;
import com.liuyang19900520.iotmobile_android.config.Constants;
import com.liuyang19900520.iotmobile_android.contract.WeiXinContract;
import com.liuyang19900520.iotmobile_android.model.bean.WeiXinBean;
import com.liuyang19900520.iotmobile_android.model.http.WeiXinApi;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 *
 * @author liuyang
 * @date 2017/11/8
 */

public class WeiXinPresenter extends RxPresenter<WeiXinContract.View> implements WeiXinContract.Presenter {

    private WeiXinApi weiXinApi;

    private Context context;

    @Inject
    public WeiXinPresenter(WeiXinApi weiXinApi, Context context) {
        this.weiXinApi = weiXinApi;
        this.context = context;
    }

    @Override
    public void getWeiXinData(int pagesize,int page) {
        addSubscribe(weiXinApi.getWeiXin(Constants.WEIXIN_KEY, pagesize, page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new BaseSubscriber<WeiXinBean>(context, mView) {
                    @Override
                    public void onNext(WeiXinBean weiXinBean) {
                        mView.showWeiXinData(weiXinBean);
                    }

                    @Override
                    public void onError(Throwable t) {
                        super.onError(t);
                        mView.failGetData();
                    }
                }));
    }

    @Override
    public void getPTP() {
        addSubscribe(RxBus.getInstance().register(Integer.class)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new BaseSubscriber<Integer>(context, mView) {
                    @Override
                    public void onNext(Integer integer) {
                        if (integer == 1001) {
                            mView.refreshAdapter(true);
                        }
                    }
                }));
    }
}
