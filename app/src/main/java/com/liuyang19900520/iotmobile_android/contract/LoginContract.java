package com.liuyang19900520.iotmobile_android.contract;


import com.liuyang19900520.iotmobile_android.base.BasePresenter;
import com.liuyang19900520.iotmobile_android.base.BaseView;
import com.liuyang19900520.iotmobile_android.model.bean.LoginUser;

/**
 * Created by xiarh on 2017/11/3.
 */

public interface LoginContract {

    interface View extends BaseView {

        void showVCode(String imageBase64);


    }

    interface Presenter extends BasePresenter<View> {

        /**
         * 登录
         */
        void login(LoginUser loginUser);


    }
}
