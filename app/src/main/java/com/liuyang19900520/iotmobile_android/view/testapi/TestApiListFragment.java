/*
 * Copyright (C) 2015 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.liuyang19900520.iotmobile_android.view.testapi;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.common.collect.Lists;
import com.liuyang19900520.iotmobile_android.R;
import com.liuyang19900520.iotmobile_android.base.BaseFragment;
import com.liuyang19900520.iotmobile_android.base.BaseMVPFragment;
import com.liuyang19900520.iotmobile_android.config.IOTApplication;
import com.liuyang19900520.iotmobile_android.contract.TestApiContract;
import com.liuyang19900520.iotmobile_android.di.component.DaggerTestApiFragmentComponent;
import com.liuyang19900520.iotmobile_android.di.component.DaggerWeiXinFragmentComponent;
import com.liuyang19900520.iotmobile_android.di.module.TestApiFragmentModule;
import com.liuyang19900520.iotmobile_android.di.module.WeiXinFragmentModule;
import com.liuyang19900520.iotmobile_android.model.bean.TestApiBean;
import com.liuyang19900520.iotmobile_android.model.http.TestApiApi;
import com.liuyang19900520.iotmobile_android.presenter.TestApiPresenter;
import com.liuyang19900520.iotmobile_android.presenter.WeiXinPresenter;
import com.liuyang19900520.iotmobile_android.util.ImageLoader;
import com.liuyang19900520.iotmobile_android.view.testapi.adapter.ApiTestAdapter;

import java.io.Serializable;
import java.util.List;

import butterknife.BindView;

/**
 * @author liuya
 */
public class TestApiListFragment extends BaseMVPFragment<TestApiPresenter> implements TestApiContract.View {

    private static String TEST_API = "TEST_API";

    private List<TestApiBean> testApis = Lists.newArrayList();

    public ApiTestAdapter testApiAdapter;

    @BindView(R.id.recyclerview)
    RecyclerView recyclerView;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_test_api_list;
    }

    @Override
    protected void initInject() {
        DaggerTestApiFragmentComponent
                .builder()
                .appComponent(IOTApplication.getAppComponent())
                .testApiFragmentModule(new TestApiFragmentModule())
                .build()
                .injectTestApi(this);
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Bundle args = getArguments();
        if (args != null) {
            testApis = (List<TestApiBean>) args.getSerializable(TEST_API);
        }
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        setupRecyclerView(recyclerView);
        testApiAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                ImageLoader.loadAll(mContext, R.drawable.ic_executing, (ImageView) view.findViewById(R.id.avatar));
                mPresenter.testApi(position, testApiAdapter.getData().get(position).getApiUrl(), null);
            }
        });
    }

    @Override
    protected void initialize() {

    }


    public static TestApiListFragment newInstance(List<TestApiBean> testApis) {
        Bundle args = new Bundle();
        args.putSerializable(TEST_API, (Serializable) testApis);
        TestApiListFragment fragment = new TestApiListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    private void setupRecyclerView(RecyclerView recyclerView) {
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        recyclerView.addItemDecoration(new DividerItemDecoration(mContext, DividerItemDecoration.VERTICAL));
        testApiAdapter = new ApiTestAdapter(R.layout.item_test_api, testApis);
        recyclerView.setAdapter(testApiAdapter);
    }

    @Override
    public void testApiRusult(int position, boolean isSuccess) {
        if (isSuccess) {
            ImageLoader.loadAll(mContext, R.drawable.ic_executing_success, (ImageView) testApiAdapter.getViewByPosition(recyclerView, position, R.id.avatar));
        } else {
            ImageLoader.loadAll(mContext, R.drawable.ic_executing_failed, (ImageView) testApiAdapter.getViewByPosition(recyclerView, position, R.id.avatar));
        }
    }

    @Override
    public void showApis(List<TestApiBean> testApis) {

    }

    @Override
    public void refreshAdapter(boolean isRefresh) {

    }


}
