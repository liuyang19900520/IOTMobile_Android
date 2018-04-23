package com.liuyang19900520.iotmobile_android.contract;


import com.liuyang19900520.iotmobile_android.base.BasePresenter;
import com.liuyang19900520.iotmobile_android.base.BaseView;
import com.liuyang19900520.iotmobile_android.model.bean.LoginUser;
import com.liuyang19900520.iotmobile_android.model.bean.TestApi;

import java.util.List;


/**
 * @author liuya
 */
public interface MainContract {

    interface View extends BaseView {
        /**
         * show apis
         */
        void showApiList(List<TestApi> apis);

    }

    interface Presenter extends BasePresenter<View> {

    }
}
