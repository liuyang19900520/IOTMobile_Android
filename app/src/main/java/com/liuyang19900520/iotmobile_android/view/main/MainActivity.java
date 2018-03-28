package com.liuyang19900520.iotmobile_android.view.main;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

import com.liuyang19900520.iotmobile_android.R;
import com.liuyang19900520.iotmobile_android.base.BaseMVPActivity;
import com.liuyang19900520.iotmobile_android.config.IOTApplication;
import com.liuyang19900520.iotmobile_android.contract.LoginContract;
import com.liuyang19900520.iotmobile_android.contract.MainContract;
import com.liuyang19900520.iotmobile_android.di.component.DaggerLoginActivityComponent;
import com.liuyang19900520.iotmobile_android.di.module.LoginActivityModule;
import com.liuyang19900520.iotmobile_android.model.bean.LoginUser;
import com.liuyang19900520.iotmobile_android.model.prefs.SharePrefManager;
import com.liuyang19900520.iotmobile_android.presenter.LoginPresenter;
import com.liuyang19900520.iotmobile_android.presenter.MainPresenter;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author liuya
 */
public class MainActivity extends BaseMVPActivity<MainPresenter> implements MainContract.View {

    @Inject
    SharePrefManager sharePrefManager;

    @BindView(R.id.btn_test)
    Button btnTest;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initialize() {
        btnTest.setText(sharePrefManager.getToken());
    }

    @Override
    protected void initInject() {
        DaggerLoginActivityComponent
                .builder()
                .appComponent(IOTApplication.getAppComponent())
                .loginActivityModule(new LoginActivityModule())
                .build()
                .injectMain(this);
    }


    @OnClick(R.id.btn_test)
    public void testApi() {
        mPresenter.testApi();
    }

    @Override
    public void toMain() {
        startActivity(new Intent(this, MainActivity.class));
    }
}
