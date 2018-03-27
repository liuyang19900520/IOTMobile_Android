package com.liuyang19900520.iotmobile_android.view.splash;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;


import com.liuyang19900520.iotmobile_android.view.main.LoginActivity;
import com.liuyang19900520.iotmobile_android.view.main.MainActivity;

import me.yokeyword.fragmentation.SupportActivity;

/**
 * 启动页
 * Created by xiarh on 2017/12/27.
 */

public class SplashActivity extends SupportActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashActivity.this, LoginActivity.class));
                finish();
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            }
        }, 1000);
    }
}
