package com.liuyang19900520.iotmobile_android.view.testapi;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import com.chad.library.adapter.base.callback.ItemDragAndSwipeCallback;
import com.chad.library.adapter.base.listener.OnItemSwipeListener;
import com.google.common.collect.Lists;
import com.liuyang19900520.iotmobile_android.R;
import com.liuyang19900520.iotmobile_android.base.BaseFragment;
import com.liuyang19900520.iotmobile_android.base.BaseMVPFragment;
import com.liuyang19900520.iotmobile_android.base.BaseSubscriber;
import com.liuyang19900520.iotmobile_android.base.RxBus;
import com.liuyang19900520.iotmobile_android.base.RxPresenter;
import com.liuyang19900520.iotmobile_android.config.IOTApplication;
import com.liuyang19900520.iotmobile_android.contract.TestApiContract;
import com.liuyang19900520.iotmobile_android.di.component.DaggerTestApiFragmentComponent;
import com.liuyang19900520.iotmobile_android.di.module.WeiXinFragmentModule;
import com.liuyang19900520.iotmobile_android.model.bean.TestApiBean;
import com.liuyang19900520.iotmobile_android.model.db.GreenDaoManager;
import com.liuyang19900520.iotmobile_android.presenter.TestApiPresenter;
import com.liuyang19900520.iotmobile_android.presenter.WeiXinPresenter;
import com.liuyang19900520.iotmobile_android.util.LogUtil;
import com.liuyang19900520.iotmobile_android.util.ToastUtils;
import com.liuyang19900520.iotmobile_android.view.testapi.adapter.ItemDragAdapter;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.List;

import butterknife.BindView;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * @author liuya
 */
public class GreenDaoTestApiFragment extends BaseMVPFragment<TestApiPresenter> implements TestApiContract.View {

    private static final int REFRESH_DATA = 0X0000001;

    @BindView(R.id.recyclerview)
    RecyclerView mRecyclerView;

//    private GreenDaoManager daoManager;

    private List<TestApiBean> mData = Lists.newArrayList();
    private ItemDragAdapter mAdapter;
    private ItemTouchHelper mItemTouchHelper;
    private ItemDragAndSwipeCallback mItemDragAndSwipeCallback;


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_test_api_greendao;
    }

    @Override
    protected void initialize() {

        mData = Lists.newArrayList();

        mPresenter.getTestApiData(0, 0);

        mPresenter.insertTestApi();
//        daoManager = IOTApplication.getAppComponent().getGreenDaoManager();
//        generateData(2);

    }


//    private void generateData(int size) {
//        for (int i = 0; i < size; i++) {
//            TestApiBean testApiBean = new TestApiBean();
//            testApiBean.setApiName("name==" + i);
//            testApiBean.setApiUrl("url==" + i);
//            testApiBean.setCategory("cate==" + 1);
//            testApiBean.setParams("params==" + i);
//            daoManager.insert(testApiBean);
//        }
//    }

    @Override
    protected void initInject() {
        DaggerTestApiFragmentComponent
                .builder()
                .appComponent(IOTApplication.getAppComponent())
                .build()
                .injectGreenDaoTestApi(this);
    }

    @Override
    public void showApis(List<TestApiBean> testApis) {
        mData.clear();
        mData.addAll(testApis);
        ToastUtils.showShortToast("现在DB中有" + mData.size() + "条数据");

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        final Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setTextSize(24);
        paint.setColor(Color.BLACK);
        OnItemSwipeListener onItemSwipeListener = new OnItemSwipeListener() {
            @Override
            public void onItemSwipeStart(RecyclerView.ViewHolder viewHolder, int pos) {
            }

            @Override
            public void clearView(RecyclerView.ViewHolder viewHolder, int pos) {
            }

            @Override
            public void onItemSwiped(RecyclerView.ViewHolder viewHolder, int pos) {
                TestApiBean testApiBean = mData.get(pos);
                mPresenter.deleteTestApi(testApiBean);

            }

            @Override
            public void onItemSwipeMoving(Canvas canvas, RecyclerView.ViewHolder viewHolder, float dX, float dY, boolean isCurrentlyActive) {
                canvas.drawColor(ContextCompat.getColor(getContext(), R.color.md_blue_50));
                canvas.drawText("Move this time to delete the Api", 0, 40, paint);
            }
        };

        mAdapter = new ItemDragAdapter(mData);
        mItemDragAndSwipeCallback = new ItemDragAndSwipeCallback(mAdapter);
        mItemTouchHelper = new ItemTouchHelper(mItemDragAndSwipeCallback);
        mItemTouchHelper.attachToRecyclerView(mRecyclerView);

        mItemDragAndSwipeCallback.setSwipeMoveFlags(ItemTouchHelper.START | ItemTouchHelper.END);
        mAdapter.enableSwipeItem();
        mAdapter.setOnItemSwipeListener(onItemSwipeListener);

        mRecyclerView.setAdapter(mAdapter);

    }

    @Override
    public void refreshAdapter(boolean isRefresh) {

    }


}
