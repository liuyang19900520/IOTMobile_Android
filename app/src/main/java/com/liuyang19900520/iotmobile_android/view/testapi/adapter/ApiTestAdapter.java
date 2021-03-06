package com.liuyang19900520.iotmobile_android.view.testapi.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.liuyang19900520.iotmobile_android.R;
import com.liuyang19900520.iotmobile_android.model.bean.TestApiBean;
import com.liuyang19900520.iotmobile_android.model.bean.WeiXinBean;
import com.liuyang19900520.iotmobile_android.util.ImageLoader;

import java.util.List;

/**
 * @author liuya
 */
public class ApiTestAdapter extends BaseQuickAdapter<TestApiBean, BaseViewHolder> {


    public ApiTestAdapter(int layoutResId, @Nullable List<TestApiBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, TestApiBean item) {
        helper.setText(R.id.tv_name, item.getApiName());
        helper.setText(R.id.tv_url, item.getApiUrl());
        ImageLoader.loadAll(mContext, R.drawable.ic_executing_waiting, (ImageView) helper.getView(R.id.avatar));
    }

}
