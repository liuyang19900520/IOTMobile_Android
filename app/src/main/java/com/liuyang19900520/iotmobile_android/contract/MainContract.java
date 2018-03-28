package com.liuyang19900520.iotmobile_android.contract;


import com.liuyang19900520.iotmobile_android.base.BasePresenter;
import com.liuyang19900520.iotmobile_android.base.BaseView;
import com.liuyang19900520.iotmobile_android.model.bean.LoginUser;


/**
 * @author liuya
 */
public interface MainContract {

    interface View extends BaseView {

        void toMain();

    }

    interface Presenter extends BasePresenter<View> {

        void testApi();


    }
}
