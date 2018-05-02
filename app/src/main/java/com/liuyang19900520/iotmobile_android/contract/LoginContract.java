package com.liuyang19900520.iotmobile_android.contract;


import com.liuyang19900520.iotmobile_android.base.BasePresenter;
import com.liuyang19900520.iotmobile_android.base.BaseView;
import com.liuyang19900520.iotmobile_android.model.bean.LoginUser;


/**
 * @author liuya
 */
public interface LoginContract {

    interface View extends BaseView {

        void showVCode(String imageBase64);

        void toMain();

    }

    interface Presenter extends BasePresenter<View> {

        void login(LoginUser loginUser);

    }
}
