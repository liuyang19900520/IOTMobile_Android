package com.liuyang19900520.iotmobile_android.presenter;

import android.content.Context;

import com.google.common.collect.Lists;
import com.liuyang19900520.iotmobile_android.base.BaseSubscriber;
import com.liuyang19900520.iotmobile_android.base.RxPresenter;
import com.liuyang19900520.iotmobile_android.config.Constants;
import com.liuyang19900520.iotmobile_android.config.Messages;
import com.liuyang19900520.iotmobile_android.contract.MainContract;
import com.liuyang19900520.iotmobile_android.contract.TestApiContract;
import com.liuyang19900520.iotmobile_android.model.bean.ResultVo;
import com.liuyang19900520.iotmobile_android.model.bean.TestApi;
import com.liuyang19900520.iotmobile_android.model.bean.WeiXinBean;

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


    private Context context;


    @Inject
    public TestApiPresenter(Context context) {

        this.context = context;
    }


    @Override
    public void getTestApiData() {
        addSubscribe(Flowable.just(getTestApis())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new BaseSubscriber<ResultVo>(context, mView) {
                    @Override
                    public void onNext(ResultVo resultVo) {
                        mView.showApis((List<TestApi>) resultVo.getData());
                    }

                    @Override
                    public void onError(Throwable t) {
                        super.onError(t);
                        mView.failGetData();
                    }
                }));
    }

    private ResultVo getTestApis() {

        TestApi.Api api1 = new TestApi.Api("a","a", null);
        TestApi.Api api2 = new TestApi.Api("b", "b",null);
        List<TestApi.Api> testApis1 = Lists.newArrayList(api1, api2);

        TestApi.Api api3 = new TestApi.Api("c", "c",null);
        TestApi.Api api4 = new TestApi.Api("d", "d",null);
        List<TestApi.Api> testApis2 = Lists.newArrayList(api3, api4);

        TestApi testApi1 = new TestApi("auth", testApis1);
        TestApi testApi2 = new TestApi("check", testApis2);

        List<TestApi> testApis = Lists.newArrayList(testApi1, testApi2);


        return ResultVo.success(Messages.OK, Messages.SUCCESS, testApis);
    }

}
