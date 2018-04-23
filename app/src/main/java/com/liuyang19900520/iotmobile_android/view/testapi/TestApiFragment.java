package com.liuyang19900520.iotmobile_android.view.testapi;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;

import com.google.common.base.Strings;
import com.liuyang19900520.iotmobile_android.R;
import com.liuyang19900520.iotmobile_android.base.BaseFragment;
import com.liuyang19900520.iotmobile_android.base.BaseMVPFragment;
import com.liuyang19900520.iotmobile_android.config.IOTApplication;
import com.liuyang19900520.iotmobile_android.contract.TestApiContract;
import com.liuyang19900520.iotmobile_android.di.component.DaggerTestApiFragmentComponent;
import com.liuyang19900520.iotmobile_android.di.component.DaggerWeiXinFragmentComponent;
import com.liuyang19900520.iotmobile_android.di.module.TestApiFragmentModule;
import com.liuyang19900520.iotmobile_android.di.module.WeiXinFragmentModule;
import com.liuyang19900520.iotmobile_android.model.bean.TestApi;
import com.liuyang19900520.iotmobile_android.model.prefs.SharePrefManager;
import com.liuyang19900520.iotmobile_android.presenter.TestApiPresenter;
import com.liuyang19900520.iotmobile_android.view.testapi.adapter.TestApiFragmentPagerAdapter;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import io.reactivex.Flowable;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * 微信精选
 * Created by xiarh on 2017/11/8.
 */

public class TestApiFragment extends BaseMVPFragment<TestApiPresenter> implements TestApiContract.View {


    @BindView(R.id.viewpager)
    ViewPager viewPager;


    @Inject
    SharePrefManager sharePrefManager;

    private TestApiFragmentPagerAdapter adapter;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_test_api;
    }


    @Override
    protected void initialize() {

        mPresenter.getTestApiData();


    }

    private void setupViewPager(final ViewPager viewPager, List<TestApi> list) {
        adapter = new TestApiFragmentPagerAdapter(getChildFragmentManager());

        Flowable.fromIterable(list).subscribe(new Consumer<TestApi>() {
            @Override
            public void accept(TestApi testApi) throws Exception {
                adapter.addFragment(TestApiListFragment.newInstance(testApi.getApis()), testApi.getCategory());
            }
        });

        viewPager.setAdapter(adapter);
        TabLayout tabLayout = getActivity().findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
//        adapter.addFragment(new TestApiListFragment(), "Category 3");

    }

    @Override
    protected void initInject() {
        DaggerTestApiFragmentComponent
                .builder()
                .appComponent(IOTApplication.getAppComponent())
                .testApiFragmentModule(new TestApiFragmentModule())
                .build()
                .inject(this);
    }

    @Override
    public void showApis(List<TestApi> testApis) {
        setupViewPager(viewPager, testApis);


    }

    @Override
    public void failGetData() {

    }
}
