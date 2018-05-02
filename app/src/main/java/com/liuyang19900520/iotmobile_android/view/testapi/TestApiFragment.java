package com.liuyang19900520.iotmobile_android.view.testapi;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatSpinner;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;

import com.afollestad.materialdialogs.MaterialDialog;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.liuyang19900520.iotmobile_android.R;
import com.liuyang19900520.iotmobile_android.base.BaseFragment;
import com.liuyang19900520.iotmobile_android.base.RxBus;
import com.liuyang19900520.iotmobile_android.config.IOTApplication;
import com.liuyang19900520.iotmobile_android.model.bean.TestApiBean;
import com.liuyang19900520.iotmobile_android.model.db.GreenDaoManager;
import com.liuyang19900520.iotmobile_android.model.prefs.SharePrefManager;
import com.liuyang19900520.iotmobile_android.util.ToastUtils;
import com.liuyang19900520.iotmobile_android.view.testapi.adapter.TestApiFragmentPagerAdapter;
import com.liuyang19900520.iotmobile_android.view.weixin.WeiXinFragment;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Flowable;
import me.yokeyword.fragmentation.ISupportFragment;
import me.yokeyword.fragmentation.SupportFragment;


/**
 * TestApi
 *
 * @author liuyang
 */

public class TestApiFragment extends BaseFragment {

    private static final int REFRESH_DATA = 0X0000001;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.tabs)
    TabLayout toolbarLayout;

    @BindView(R.id.fab_add)
    FloatingActionButton fabAdd;

    private MaterialDialog insertDialog;
    private EditText etCategory;
    private EditText etName;
    private EditText etUrl;
    private EditText etParams;


    @Inject
    SharePrefManager sharePrefManager;

    private GreenDaoTestApiFragment greenDaoTestApiFragment;
    private TestApiListMainFragment testApiListMainFragment;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_test_api;
    }


    @OnClick(R.id.fab_add)
    public void addTestApi(FloatingActionButton btnAdd) {
        insertDialog =
                new MaterialDialog.Builder(getContext())
                        .title("Insert the Api for test")
                        .customView(R.layout.dialog_insert_test_api, true)
                        .positiveText(android.R.string.ok)
                        .negativeText(android.R.string.cancel)
                        .onPositive(
                                (dialog1, which) -> insertApi())
                        .build();
        insertDialog.show();
    }


    private void initMenu() {
        toolbar.setTitle("Test Api");
        toolbar.setSubtitle("CRUD");
        initToolbarNav(toolbar, true);
        toolbar.setNavigationIcon(R.drawable.ic_menu);
        toolbar.inflateMenu(R.menu.menu_test_api);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.action_crud:
                        toolbar.setSubtitle("CRUD");
                        toolbarLayout.setVisibility(View.GONE);
                        fabAdd.setVisibility(View.VISIBLE);
                        showHideFragment(greenDaoTestApiFragment);
                        break;
                    case R.id.action_test:
                        ToastUtils.showShortToast("test");
                        toolbar.setSubtitle("TEST");
                        toolbarLayout.setVisibility(View.VISIBLE);
                        fabAdd.setVisibility(View.GONE);
                        showHideFragment(testApiListMainFragment);
                        break;
                    default:
                        break;
                }
                return false;
            }
        });
    }


    @Override
    protected void initialize() {
        initMenu();
        greenDaoTestApiFragment = new GreenDaoTestApiFragment();
        testApiListMainFragment = new TestApiListMainFragment();
        loadMultipleRootFragment(R.id.test_api_content, 0, greenDaoTestApiFragment, testApiListMainFragment);
        toolbarLayout.setVisibility(View.GONE);

    }

    private void insertApi() {
        etCategory = insertDialog.getCustomView().findViewById(R.id.et_category);
        etName = insertDialog.getCustomView().findViewById(R.id.ed_name);
        etUrl = insertDialog.getCustomView().findViewById(R.id.ed_url);
        etParams = insertDialog.getCustomView().findViewById(R.id.ed_params);

        String category = String.valueOf(etCategory.getText());
        String name = String.valueOf(etName.getText());
        String url = String.valueOf(etUrl.getText());
        String params = String.valueOf(etParams.getText());

        TestApiBean testApiBean = new TestApiBean();
        testApiBean.setCategory(category);
        testApiBean.setApiName(name);
        testApiBean.setApiUrl(url);
        testApiBean.setParams(params);

        RxBus.getInstance().post(testApiBean);

    }


}
