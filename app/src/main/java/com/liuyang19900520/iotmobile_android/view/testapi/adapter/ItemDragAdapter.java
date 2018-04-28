package com.liuyang19900520.iotmobile_android.view.testapi.adapter;

import com.chad.library.adapter.base.BaseItemDraggableAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.liuyang19900520.iotmobile_android.R;
import com.liuyang19900520.iotmobile_android.model.bean.TestApiBean;

import java.util.List;

/**
 * Created by luoxw on 2016/6/20.
 */
public class ItemDragAdapter extends BaseItemDraggableAdapter<TestApiBean, BaseViewHolder> {
    public ItemDragAdapter(List data) {
        super(R.layout.item_test_api_crud_view, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, TestApiBean item) {

        helper.setText(R.id.tv_name, item.getApiName());
        helper.setText(R.id.tv_url, item.getApiUrl());
        helper.setText(R.id.tv_params, item.getParams());

    }
}
