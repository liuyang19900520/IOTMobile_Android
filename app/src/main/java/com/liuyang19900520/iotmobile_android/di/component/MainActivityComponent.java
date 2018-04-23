package com.liuyang19900520.iotmobile_android.di.component;


import com.liuyang19900520.iotmobile_android.di.module.MainActivityModule;
import com.liuyang19900520.iotmobile_android.di.scope.ActivityScope;
import com.liuyang19900520.iotmobile_android.view.main.MainActivity;

import dagger.Component;

/**
 * Created by xiarh on 2017/10/10.
 */

@ActivityScope
@Component(dependencies = AppComponent.class,
        modules = MainActivityModule.class )
public interface MainActivityComponent {

    void inject(MainActivity mainActivity);

}
