package com.liuyang19900520.iotmobile_android.contract;


import com.liuyang19900520.iotmobile_android.base.BasePresenter;
import com.liuyang19900520.iotmobile_android.base.BaseView;
import com.liuyang19900520.iotmobile_android.model.bean.TestApi;
import com.liuyang19900520.iotmobile_android.model.bean.WeiXinBean;

import java.util.List;

/**
 *
 * @author xiarh
 * @date 2017/11/8
 */

public interface TestApiContract {

    interface View extends BaseView {

        /**
         * 成功获取数据
         *
         * @param testApis
         */
        void showApis(List<TestApi> testApis);

        /**
         * 获取数据失败
         */
        void failGetData();

    }

    interface Presenter extends BasePresenter<View> {

        void getTestApiData();


    }
}
