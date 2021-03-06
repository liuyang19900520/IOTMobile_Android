package com.liuyang19900520.iotmobile_android.di.component;

import com.liuyang19900520.iotmobile_android.di.module.LoginActivityModule;
import com.liuyang19900520.iotmobile_android.di.scope.ActivityScope;
import com.liuyang19900520.iotmobile_android.view.main.LoginActivity;
import com.liuyang19900520.iotmobile_android.view.main.MainActivity;


import dagger.Component;

/**
 *
 * @author liuyang
 * @date 2017/11/3
 */

@ActivityScope
@Component(dependencies = AppComponent.class,
        modules = LoginActivityModule.class)
public interface LoginActivityComponent {

    void inject(LoginActivity loginActivity);

    void injectMain(MainActivity mainActivity);

}
