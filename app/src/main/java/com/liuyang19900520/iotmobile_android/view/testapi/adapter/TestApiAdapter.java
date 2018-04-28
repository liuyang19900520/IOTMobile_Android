//package com.liuyang19900520.iotmobile_android.view.testapi.adapter;
//
//import android.content.Context;
//import android.support.annotation.Nullable;
//import android.support.v7.widget.RecyclerView;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//import com.chad.library.adapter.base.BaseQuickAdapter;
//import com.chad.library.adapter.base.BaseViewHolder;
//import com.liuyang19900520.iotmobile_android.R;
//import com.liuyang19900520.iotmobile_android.model.bean.TestApiBean;
//import com.liuyang19900520.iotmobile_android.model.bean.WeiXinBean;
//import com.liuyang19900520.iotmobile_android.util.ImageLoader;
//
//import java.util.List;
//
///**
// * @author liuya
// */
//public class TestApiAdapter extends BaseQuickAdapter<TestApiBean.Api, BaseViewHolder> {
//
//
//    public TestApiAdapter(int layoutResId, @Nullable List<TestApiBean.Api> data) {
//        super(layoutResId, data);
//    }
//
//    @Override
//    protected void convert(BaseViewHolder helper, TestApiBean.Api item) {
//        helper.setText(R.id.tv_name, item.getName());
//        helper.setText(R.id.tv_url, item.getUrl());
//        ImageLoader.loadDefault(mContext,(ImageView) helper.getView(R.id.avatar));
//    }
//
//
//
//}
