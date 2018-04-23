package com.liuyang19900520.iotmobile_android.di.component;



import com.liuyang19900520.iotmobile_android.di.module.TestApiFragmentModule;
import com.liuyang19900520.iotmobile_android.di.module.WeiXinFragmentModule;
import com.liuyang19900520.iotmobile_android.di.scope.FragmentScope;
import com.liuyang19900520.iotmobile_android.view.testapi.TestApiFragment;
import com.liuyang19900520.iotmobile_android.view.testapi.TestApiListFragment;
import com.liuyang19900520.iotmobile_android.view.weixin.WeiXinFragment;

import dagger.Component;

/**
 *
 * @author xiarh
 * @date 2017/11/8
 */

@FragmentScope
@Component(dependencies = AppComponent.class,
        modules = TestApiFragmentModule.class)
public interface TestApiFragmentComponent {

    void inject(TestApiFragment testApiFragment);

}
