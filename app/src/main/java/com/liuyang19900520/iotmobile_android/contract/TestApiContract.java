package com.liuyang19900520.iotmobile_android.contract;


import com.liuyang19900520.iotmobile_android.base.BasePresenter;
import com.liuyang19900520.iotmobile_android.base.BaseView;
import com.liuyang19900520.iotmobile_android.model.bean.TestApiBean;

import java.util.List;

/**
 * @author liuyang
 * @date 2017/11/8
 */

public interface TestApiContract {

    interface View extends BaseView {

        void showApis(List<TestApiBean> testApis);

        void refreshAdapter(boolean isRefresh);

        void testApiRusult(int position, boolean isSuccess);

    }

    interface Presenter extends BasePresenter<View> {

        void getTestApiData(int pagesize, int page);

        void deleteTestApi(TestApiBean api);

        void insertTestApi();

        void testApi(int position, String url, Object parmas);
    }
}
