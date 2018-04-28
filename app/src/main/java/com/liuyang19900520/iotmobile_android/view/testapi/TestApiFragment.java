package com.liuyang19900520.iotmobile_android.view.testapi;

import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatSpinner;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.EditText;

import com.afollestad.materialdialogs.MaterialDialog;
import com.liuyang19900520.iotmobile_android.R;
import com.liuyang19900520.iotmobile_android.base.BaseFragment;
import com.liuyang19900520.iotmobile_android.base.RxBus;
import com.liuyang19900520.iotmobile_android.config.IOTApplication;
import com.liuyang19900520.iotmobile_android.model.bean.TestApiBean;
import com.liuyang19900520.iotmobile_android.model.db.GreenDaoManager;
import com.liuyang19900520.iotmobile_android.model.prefs.SharePrefManager;
import com.liuyang19900520.iotmobile_android.util.ToastUtils;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * TestApi
 *
 * @author liuyang
 */

public class TestApiFragment extends BaseFragment {

    private static final int REFRESH_DATA = 0X0000001;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    private MaterialDialog insertDialog;
    private EditText etCategory;
    private EditText etName;
    private EditText etUrl;
    private EditText etParams;


    @Inject
    SharePrefManager sharePrefManager;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_test_api;
    }


    @OnClick(R.id.fab_add)
    public void addTestApi(FloatingActionButton btnAdd) {

        insertDialog =
                new MaterialDialog.Builder(getContext())
                        .title("Insert the Api for test")
                        .customView(R.layout.dialog_insert_test_api, true)
                        .positiveText(android.R.string.ok)
                        .negativeText(android.R.string.cancel)
                        .onPositive(
                                (dialog1, which) -> insertApi())
                        .build();


        insertDialog.show();

    }


    private void initMenu() {
        toolbar.setTitle("Test Api");
        toolbar.setSubtitle("CRUD");
        initToolbarNav(toolbar, true);
        toolbar.setNavigationIcon(R.drawable.ic_menu);
        toolbar.inflateMenu(R.menu.menu_test_api);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_crud:
                        ToastUtils.showShortToast("crud");
                        toolbar.setSubtitle("CRUD");
                        break;
                    case R.id.action_test:
                        ToastUtils.showShortToast("test");
                        toolbar.setSubtitle("TEST");
                        break;
                    default:
                        break;
                }
                return false;
            }
        });
    }


    @Override
    protected void initialize() {
//        daoManager = IOTApplication.getAppComponent().getGreenDaoManager();
        initMenu();
        GreenDaoTestApiFragment greenDaoTestApiFragment = new GreenDaoTestApiFragment();
        loadRootFragment(R.id.test_api_content, greenDaoTestApiFragment);

    }

    private void insertApi() {
        etCategory = insertDialog.getCustomView().findViewById(R.id.et_category);
        etName = insertDialog.getCustomView().findViewById(R.id.ed_name);
        etUrl = insertDialog.getCustomView().findViewById(R.id.ed_url);
        etParams = insertDialog.getCustomView().findViewById(R.id.ed_params);

        String category = String.valueOf(etCategory.getText());
        String name = String.valueOf(etName.getText());
        String url = String.valueOf(etUrl.getText());
        String params = String.valueOf(etParams.getText());

        TestApiBean testApiBean = new TestApiBean();
        testApiBean.setCategory(category);
        testApiBean.setApiName(name);
        testApiBean.setApiUrl(url);
        testApiBean.setParams(params);

//        daoManager.insert(testApiBean);

        RxBus.getInstance().post(testApiBean);

    }


    private void setupViewPager(final ViewPager viewPager, List<TestApiBean> list) {
//        adapter = new TestApiFragmentPagerAdapter(getChildFragmentManager());
//
//        Flowable.fromIterable(list).subscribe(new Consumer<TestApiBean>() {
//            @Override
//            public void accept(TestApiBean testApi) throws Exception {
//                adapter.addFragment(TestApiListFragment.newInstance(testApi.getApis()), testApi.getCategory());
//            }
//        });
//
//        viewPager.setAdapter(adapter);
//        TabLayout tabLayout = getActivity().findViewById(R.id.tabs);
//        tabLayout.setupWithViewPager(viewPager);
//        adapter.addFragment(new TestApiListFragment(), "Category 3");

    }


//    private void insertTestApi() {
//        int index = 0;
//        List<TestApiBean> testApiBeans = daoManager.queryAll();
//        if (testApiBeans.size() != 0) {
//            Long id = testApiBeans.get(0).getId();
//            index = (int) (id + 1);
//        }
//
//        TestApiBean testApiBean = new TestApiBean();
//        testApiBean.setApiName("name==" + index);
//        testApiBean.setApiUrl("url==" + index);
//        testApiBean.setCategory("cate==" + index);
//        testApiBean.setParams("params==" + index);
////        daoManager.insert(testApiBean);
//    }
}
