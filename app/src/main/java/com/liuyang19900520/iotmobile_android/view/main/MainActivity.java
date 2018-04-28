package com.liuyang19900520.iotmobile_android.view.main;

import android.os.Bundle;
import android.view.View;

import com.liuyang19900520.iotmobile_android.R;
import com.liuyang19900520.iotmobile_android.base.BaseFragment;
import com.liuyang19900520.iotmobile_android.base.BaseMVPActivity;
import com.liuyang19900520.iotmobile_android.config.IOTApplication;
import com.liuyang19900520.iotmobile_android.contract.MainContract;
import com.liuyang19900520.iotmobile_android.di.component.DaggerMainActivityComponent;
import com.liuyang19900520.iotmobile_android.di.module.MainActivityModule;
import com.liuyang19900520.iotmobile_android.model.bean.TestApiBean;
import com.liuyang19900520.iotmobile_android.model.prefs.SharePrefManager;
import com.liuyang19900520.iotmobile_android.presenter.MainPresenter;
import com.liuyang19900520.iotmobile_android.view.testapi.TestApiFragment;
import com.liuyang19900520.iotmobile_android.view.weixin.WeiXinFragment;
import com.mikepenz.itemanimators.AlphaCrossFadeAnimator;
import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IProfile;

import java.util.List;

import javax.inject.Inject;

import me.yokeyword.fragmentation.ISupportFragment;
import me.yokeyword.fragmentation.SupportFragment;


/**
 * @author liuya
 */
public class MainActivity extends BaseMVPActivity<MainPresenter> implements MainContract.View, BaseFragment.OnFragmentOpenDrawerListener {

    @Inject
    SharePrefManager sharePrefManager;

    private WeiXinFragment weiXinFragment;

    private TestApiFragment testApiFragment;

    /**
     * save our header or result
     */
    private AccountHeader headerDrawer = null;
    private Drawer resultDrawer = null;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initialize() {

        headerDrawer = new AccountHeaderBuilder()
                .withActivity(this)
                .withTranslucentStatusBar(true)
                .addProfiles(new ProfileDrawerItem().withName("Liu Yang").withEmail("liuyang19900520@hotmail.com").withIcon(R.drawable.user_profile_icon))
                .withHeaderBackground(R.drawable.header)
                .withOnAccountHeaderListener(new AccountHeader.OnAccountHeaderListener() {
                    @Override
                    public boolean onProfileChanged(View view, IProfile profile, boolean currentProfile) {
                        return false;
                    }
                })
                .build();

        resultDrawer = new DrawerBuilder()
                .withActivity(this)
                .withHasStableIds(true)
                .withItemAnimator(new AlphaCrossFadeAnimator())
                .withAccountHeader(headerDrawer)
                .addDrawerItems(new PrimaryDrawerItem().withName("WeChat News").withIdentifier(1), new PrimaryDrawerItem().withName("Test Api").withIdentifier(2))
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {


                        if (drawerItem != null) {
                            final ISupportFragment topFragment = getTopFragment();
                            BaseFragment myHome = (BaseFragment) topFragment;
                            if (drawerItem.getIdentifier() == 1) {

                                WeiXinFragment fragment = findFragment(WeiXinFragment.class);
                                Bundle newBundle = new Bundle();
                                newBundle.putString("from", "From:" + topFragment.getClass().getSimpleName());
                                fragment.putNewBundle(newBundle);
                                myHome.start(fragment, SupportFragment.SINGLETASK);


                            } else if (drawerItem.getIdentifier() == 2) {
                                TestApiFragment fragment = findFragment(TestApiFragment.class);
                                if (fragment == null) {
                                    myHome.startWithPopTo(new TestApiFragment(), WeiXinFragment.class, false);
                                } else {
                                    // 如果已经在栈内,则以SingleTask模式start
                                    myHome.start(fragment, SupportFragment.SINGLETASK);

                                }

                            }
                        }
                        return false;
                    }
                }).build();

        initFragment();



    }


    @Override
    protected void initInject() {
        DaggerMainActivityComponent
                .builder()
                .appComponent(IOTApplication.getAppComponent())
                .mainActivityModule(new MainActivityModule(this))
                .build()
                .inject(this);
    }


    @Override
    public void showApiList(List<TestApiBean> apis) {

    }


    private void initFragment() {
        weiXinFragment = new WeiXinFragment();
        testApiFragment = new TestApiFragment();
        loadRootFragment(R.id.main_content, weiXinFragment);


    }


    @Override
    public void onOpenDrawer() {
        if (!resultDrawer.isDrawerOpen()) {
            resultDrawer.openDrawer();
        }
    }
}
