package com.liuyang19900520.iotmobile_android.base;

/**
 * View基类
 * Created by xiarh on 2017/9/22.
 */

public interface BaseView {

    void showMsg(CharSequence msg);

    void showError(CharSequence error);

    void showEmptyView();

    void showErrorView();

    void startLoading();

    void stopLoading();
}
