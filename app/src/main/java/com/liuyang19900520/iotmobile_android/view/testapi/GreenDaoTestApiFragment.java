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
import com.liuyang19900520.iotmobile_android.base.BaseMVPFragment;
import com.liuyang19900520.iotmobile_android.config.IOTApplication;
import com.liuyang19900520.iotmobile_android.contract.TestApiContract;
import com.liuyang19900520.iotmobile_android.di.component.DaggerTestApiFragmentComponent;
import com.liuyang19900520.iotmobile_android.model.bean.TestApiBean;
import com.liuyang19900520.iotmobile_android.presenter.TestApiPresenter;
import com.liuyang19900520.iotmobile_android.util.ToastUtils;
import com.liuyang19900520.iotmobile_android.view.testapi.adapter.ApiCRUDAdapter;

import java.util.List;

import butterknife.BindView;

/**
 * @author liuya
 */
public class GreenDaoTestApiFragment extends BaseMVPFragment<TestApiPresenter> implements TestApiContract.View {

    @BindView(R.id.recyclerview)
    RecyclerView mRecyclerView;

    private List<TestApiBean> mData = Lists.newArrayList();
    private ApiCRUDAdapter mAdapter;

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

    }

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

        mAdapter = new ApiCRUDAdapter(mData);
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

    @Override
    public void testApiRusult(int position, boolean isSuccess) {

    }

}
