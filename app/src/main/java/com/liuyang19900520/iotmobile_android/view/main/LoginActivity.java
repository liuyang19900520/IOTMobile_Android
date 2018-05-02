package com.liuyang19900520.iotmobile_android.view.main;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.app.LoaderManager.LoaderCallbacks;

import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;

import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import com.liuyang19900520.iotmobile_android.R;
import com.liuyang19900520.iotmobile_android.base.BaseMVPActivity;
import com.liuyang19900520.iotmobile_android.config.IOTApplication;
import com.liuyang19900520.iotmobile_android.contract.LoginContract;

import com.liuyang19900520.iotmobile_android.di.component.DaggerLoginActivityComponent;
import com.liuyang19900520.iotmobile_android.di.module.LoginActivityModule;
import com.liuyang19900520.iotmobile_android.model.bean.LoginUser;
import com.liuyang19900520.iotmobile_android.model.prefs.SharePrefManager;
import com.liuyang19900520.iotmobile_android.presenter.LoginPresenter;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

import static android.Manifest.permission.READ_CONTACTS;

/**
 * A login screen that offers login via email/password.
 * @author liuya
 */
public class LoginActivity extends BaseMVPActivity<LoginPresenter> implements LoginContract.View {


    @BindView(R.id.email)
    AutoCompleteTextView tvEmail;

    @BindView(R.id.password)
    EditText etPassword;

    @Inject
    SharePrefManager sharePrefManager;

    @Override
    public void showVCode(String imageBase64) {

    }

    @Override
    protected void initInject() {
        DaggerLoginActivityComponent
                .builder()
                .appComponent(IOTApplication.getAppComponent())
                .loginActivityModule(new LoginActivityModule())
                .build()
                .inject(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void initialize() {
        sharePrefManager.setToken(null);
    }

    @OnClick(R.id.email_sign_in_button)
    public void login() {
        String username = tvEmail.getText().toString();
        String password = etPassword.getText().toString();
        LoginUser loginUser = new LoginUser();
        loginUser.setPassword(password);
        loginUser.setUsername(username);
        sharePrefManager.setClientKey(username);
        mPresenter.login(loginUser);
    }

    @Override
    public void toMain() {
        startActivity(new Intent(this, MainActivity.class));
    }
}

