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

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.liuyang19900520.iotmobile_android.R;
import com.liuyang19900520.iotmobile_android.base.BaseFragment;
import com.liuyang19900520.iotmobile_android.base.RxBus;
import com.liuyang19900520.iotmobile_android.config.IOTApplication;
import com.liuyang19900520.iotmobile_android.model.bean.TestApiBean;
import com.liuyang19900520.iotmobile_android.model.db.GreenDaoManager;
import com.liuyang19900520.iotmobile_android.view.testapi.adapter.TestApiFragmentPagerAdapter;

import java.util.List;
import java.util.Map;

import butterknife.BindView;
import io.reactivex.Flowable;

/**
 * @author liuya
 */
public class TestApiListMainFragment extends BaseFragment {


    @BindView(R.id.viewpager)
    ViewPager viewPager;


    private TestApiFragmentPagerAdapter pagerAdapter;

    private List<TestApiBean> mData;

    private GreenDaoManager dbManager;

    private Map<String, List<TestApiBean>> map = Maps.newConcurrentMap();


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_test_api_viewpagers;
    }

    @Override
    protected void initialize() {
        dbManager = IOTApplication.getAppComponent().getGreenDaoManager();
        mData = Lists.newArrayList();
        mData.addAll(dbManager.queryAll());
        setupViewPager(viewPager);


    }

    private void setupViewPager(final ViewPager viewPager) {
        pagerAdapter = new TestApiFragmentPagerAdapter(getChildFragmentManager());
        constructAdapter();
        viewPager.setAdapter(pagerAdapter);

        TabLayout tabLayout = getActivity().findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

    }

    private void constructAdapter() {
        Flowable.fromIterable(mData).subscribe(new io.reactivex.functions.Consumer<TestApiBean>() {
            @Override
            public void accept(TestApiBean testApi) throws Exception {
                if (!map.containsKey(testApi.getCategory())) {
                    List<TestApiBean> listByCate = Lists.newArrayList();
                    listByCate.add(testApi);
                    map.put(testApi.getCategory(), listByCate);
                } else {
                    List<TestApiBean> listByCate = map.get(testApi.getCategory());
                    listByCate.add(testApi);
                    map.put(testApi.getCategory(), listByCate);
                }
            }
        });

        Flowable.fromIterable(map.keySet()).subscribe(new io.reactivex.functions.Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                pagerAdapter.addFragment(TestApiListFragment.newInstance(map.get(s)), s);
            }
        });
        pagerAdapter.addData(map);
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (hidden) {

        } else {
            if (pagerAdapter != null) {
                mData.clear();
                mData.addAll(dbManager.queryAll());
                map.clear();
                pagerAdapter.claerFragments();
                constructAdapter();
                pagerAdapter.notifyDataSetChanged();
                viewPager.setCurrentItem(0);

            }
        }
    }


}
