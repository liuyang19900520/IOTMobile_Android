package com.liuyang19900520.iotmobile_android.view.weixin;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.liuyang19900520.iotmobile_android.R;
import com.liuyang19900520.iotmobile_android.base.BaseMVPFragment;
import com.liuyang19900520.iotmobile_android.config.IOTApplication;
import com.liuyang19900520.iotmobile_android.contract.WeiXinContract;

import com.liuyang19900520.iotmobile_android.di.component.DaggerWeiXinFragmentComponent;
import com.liuyang19900520.iotmobile_android.di.module.HttpModule;
import com.liuyang19900520.iotmobile_android.di.module.WeiXinFragmentModule;
import com.liuyang19900520.iotmobile_android.model.bean.WeiXinBean;
import com.liuyang19900520.iotmobile_android.model.prefs.SharePrefManager;
import com.liuyang19900520.iotmobile_android.presenter.WeiXinPresenter;
import com.liuyang19900520.iotmobile_android.view.weixin.adapter.WeiXinAdapter;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * 微信精选
 * Created by xiarh on 2017/11/8.
 */

public class WeiXinFragment extends BaseMVPFragment<WeiXinPresenter> implements WeiXinContract.View,
        SwipeRefreshLayout.OnRefreshListener, BaseQuickAdapter.RequestLoadMoreListener {

    @BindView(R.id.swiperefreshlayout)
    SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerView;

    @Inject
    SharePrefManager sharePrefManager;

    private WeiXinAdapter weiXinAdapter;

    private int page = 1;

    private static final int PAGE_SIZE = 20;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_weixin;
    }

    @Override
    protected void initInject() {
        DaggerWeiXinFragmentComponent
                .builder()
                .appComponent(IOTApplication.getAppComponent())
                .weiXinFragmentModule(new WeiXinFragmentModule())
                .build()
                .inject(this);
    }

    @Override
    protected void initialize() {
        swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary);
        swipeRefreshLayout.setRefreshing(true);
        swipeRefreshLayout.setOnRefreshListener(this);
        mPresenter.getWeiXinData(PAGE_SIZE, page);
        mPresenter.getPTP();
        weiXinAdapter = new WeiXinAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        recyclerView.addItemDecoration(new DividerItemDecoration(mContext, 0));
        recyclerView.setAdapter(weiXinAdapter);
        weiXinAdapter.setPTP(sharePrefManager.getProvincialTrafficPattern());
        weiXinAdapter.setOnLoadMoreListener(this, recyclerView);
//        weiXinAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
//            @Override
//            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
//                WeiXinBean.NewslistBean bean = (WeiXinBean.NewslistBean) adapter.getData().get(position);
//                WebActivity.open(new WebActivity.Builder()
//                        .setGuid(bean.getUrl())//微信Item没有id，使用url作为guid
//                        .setImgUrl(bean.getPicUrl())
//                        .setType(Constants.TYPE_WEIXIN)
//                        .setUrl(bean.getUrl())
//                        .setTitle(bean.getTitle())
//                        .setShowLikeIcon(true)
//                        .setContext(mContext)
//                );
//            }
//        });
    }

    /**
     * 下拉刷新
     */
    @Override
    public void onRefresh() {
        page = 1;
        mPresenter.getWeiXinData(PAGE_SIZE, page);
        // 这里的作用是防止下拉刷新的时候还可以上拉加载
        weiXinAdapter.setEnableLoadMore(false);
    }

    /**
     * 上拉加载
     */

    @Override
    public void onLoadMoreRequested() {
        page++;
        mPresenter.getWeiXinData(PAGE_SIZE, page);
        // 防止上拉加载的时候可以下拉刷新
        swipeRefreshLayout.setEnabled(false);
    }

    /**
     * 加载成功
     *
     * @param weiXinBean
     */
    @Override
    public void showWeiXinData(WeiXinBean weiXinBean) {
        if (null != swipeRefreshLayout && swipeRefreshLayout.isRefreshing()) {
            swipeRefreshLayout.setRefreshing(false);
            // 下拉刷新后可以上拉加载
            weiXinAdapter.setEnableLoadMore(true);
        }
        if (null != weiXinAdapter && weiXinAdapter.isLoading()) {
            // 上拉加载后可以下拉刷新
            swipeRefreshLayout.setEnabled(true);
        }
        if (page == 1) {
            weiXinAdapter.setNewData(weiXinBean.getNewslist());
        } else {
            weiXinAdapter.addData(weiXinBean.getNewslist());
        }
        if (weiXinBean.getNewslist().size() == PAGE_SIZE) {
            weiXinAdapter.loadMoreComplete();
        } else if (weiXinBean.getNewslist().size() < PAGE_SIZE) {
            weiXinAdapter.loadMoreEnd();
        }
    }

    /**
     * 加载失败
     */
    @Override
    public void failGetData() {
        weiXinAdapter.loadMoreFail();
        swipeRefreshLayout.setEnabled(true);
        swipeRefreshLayout.setRefreshing(false);
    }

    /**
     * 省流量模式，刷新Adapter
     */
    @Override
    public void refreshAdapter(boolean isRefreshed) {
        if (isRefreshed) {
            weiXinAdapter.setPTP(sharePrefManager.getProvincialTrafficPattern());
            weiXinAdapter.notifyDataSetChanged();
        }
    }
}
