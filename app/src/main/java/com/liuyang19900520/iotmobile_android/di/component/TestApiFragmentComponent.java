package com.liuyang19900520.iotmobile_android.di.component;


import com.liuyang19900520.iotmobile_android.di.module.TestApiFragmentModule;
import com.liuyang19900520.iotmobile_android.di.scope.FragmentScope;
import com.liuyang19900520.iotmobile_android.view.testapi.GreenDaoTestApiFragment;
import com.liuyang19900520.iotmobile_android.view.testapi.TestApiFragment;

import dagger.Component;

/**
 * @author xiarh
 * @date 2017/11/8
 */

@FragmentScope
@Component(dependencies = AppComponent.class,
        modules = TestApiFragmentModule.class)
public interface TestApiFragmentComponent {

    void injectGreenDaoTestApi(GreenDaoTestApiFragment greenDaoTestApiFragment);

    void injectTestApi(TestApiFragment testApiFragment);
}
