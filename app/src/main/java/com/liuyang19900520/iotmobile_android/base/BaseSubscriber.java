package com.liuyang19900520.iotmobile_android.base;

import android.content.Context;


import com.liuyang19900520.iotmobile_android.R;
import com.liuyang19900520.iotmobile_android.util.AppNetWorkUtil;

import io.reactivex.subscribers.ResourceSubscriber;

/**
 * Subscriber基类
 *
 * @author liuyang
 * @date 2017/11/13
 */

public abstract class BaseSubscriber<T> extends ResourceSubscriber<T> {

    private Context context;

    private BaseView view;

    public BaseSubscriber(Context context, BaseView view) {
        this.context = context;
        this.view = view;
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (!AppNetWorkUtil.isNetworkConnected(context)) {
            view.showError(context.getString(R.string.no_network));
            onComplete();
            return;
        }
    }

    @Override
    public void onError(Throwable t) {
//        view.showError(t.getMessage());
    }

    @Override
    public void onComplete() {

    }
}
