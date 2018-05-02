package com.liuyang19900520.iotmobile_android.di.component;



import com.liuyang19900520.iotmobile_android.di.module.WeiXinFragmentModule;
import com.liuyang19900520.iotmobile_android.di.scope.FragmentScope;
import com.liuyang19900520.iotmobile_android.view.weixin.WeiXinFragment;

import dagger.Component;

/**
 *
 * @author liuyang
 * @date 2017/11/8
 */

@FragmentScope
@Component(dependencies = AppComponent.class,
        modules = WeiXinFragmentModule.class)
public interface WeiXinFragmentComponent {

    void inject(WeiXinFragment weiXinFragment);

}
