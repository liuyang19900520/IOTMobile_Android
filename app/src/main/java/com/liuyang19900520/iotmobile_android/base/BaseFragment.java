package com.liuyang19900520.iotmobile_android.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import com.liuyang19900520.iotmobile_android.R;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.yokeyword.fragmentation.SupportFragment;

/**
 * Fragment基类
 * Created by xiarh on 2017/9/21.
 */

public abstract class BaseFragment extends SupportFragment {

    protected View mView;

    protected Activity mActivity;

    protected Context mContext;

    private Unbinder mUnBinder;

    protected abstract int getLayoutId();

    protected abstract void initialize();

    protected boolean isInited = false;

    protected OnFragmentOpenDrawerListener mOpenDraweListener;

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
    }

    public interface OnFragmentOpenDrawerListener {
        void onOpenDrawer();
    }

    protected void initToolbarNav(Toolbar toolbar) {
        initToolbarNav(toolbar, false);
    }

    protected void initToolbarNav(Toolbar toolbar, boolean isHome) {
        toolbar.setNavigationIcon(R.drawable.ic_menu);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOpenDraweListener != null) {
                    mOpenDraweListener.onOpenDrawer();
                }
            }
        });
    }

    @Override
    public void onAttach(Context context) {
        mActivity = (Activity) context;
        mContext = context;
        super.onAttach(context);
        if (context instanceof OnFragmentOpenDrawerListener) {
            mOpenDraweListener = (OnFragmentOpenDrawerListener) context;
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(getLayoutId(), null);
        mUnBinder = ButterKnife.bind(this, mView);
        return mView;
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        isInited = true;
        initialize();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnBinder.unbind();
        mOpenDraweListener = null;
    }
}