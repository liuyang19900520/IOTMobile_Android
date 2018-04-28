package com.liuyang19900520.iotmobile_android.presenter;

import android.content.Context;

import com.google.common.collect.Lists;
import com.liuyang19900520.iotmobile_android.base.BaseSubscriber;
import com.liuyang19900520.iotmobile_android.base.RxBus;
import com.liuyang19900520.iotmobile_android.base.RxPresenter;
import com.liuyang19900520.iotmobile_android.config.IOTApplication;
import com.liuyang19900520.iotmobile_android.config.Messages;
import com.liuyang19900520.iotmobile_android.contract.TestApiContract;
import com.liuyang19900520.iotmobile_android.model.bean.ResultVo;
import com.liuyang19900520.iotmobile_android.model.bean.TestApiBean;
import com.liuyang19900520.iotmobile_android.model.db.GreenDaoManager;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * @author liuya
 * @date 2018/3/26
 */

public class TestApiPresenter extends RxPresenter<TestApiContract.View> implements TestApiContract.Presenter {

    private static final int REFRESH_DATA = 0X0000001;
    private Context context;

    private GreenDaoManager dbManager;


    @Inject
    public TestApiPresenter(Context context) {

        this.context = context;
        dbManager = IOTApplication.getAppComponent().getGreenDaoManager();
    }

    @Override
    public void getTestApiData(int pagesize, int page) {
        List<TestApiBean> list = dbManager.queryAll();
        addSubscribe(Flowable.just(1).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribeWith(new BaseSubscriber<Integer>(context, mView) {
                    @Override
                    public void onNext(Integer integer) {
                        mView.showApis(list);
                    }

                }));

    }

    @Override
    public void deleteTestApi(TestApiBean api) {
        dbManager.delete(api);
        getTestApiData(0, 0);
    }

    @Override
    public void insertTestApi() {
        addSubscribe(RxBus.getInstance().register(TestApiBean.class)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new BaseSubscriber<TestApiBean>(context, mView) {
                    @Override
                    public void onNext(TestApiBean api) {

                        dbManager.insert(api);
                        getTestApiData(0, 0);
                    }

                }));
    }

}







