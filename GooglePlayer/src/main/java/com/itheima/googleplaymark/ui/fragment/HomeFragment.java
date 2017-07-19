package com.itheima.googleplaymark.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.itheima.googleplaymark.R;
import com.itheima.googleplaymark.adapter.HomeAdapter;
import com.itheima.googleplaymark.bean.HomeBean;
import com.itheima.googleplaymark.cachemanager.JsonCacheManager;
import com.itheima.googleplaymark.uimanager.LoadPager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * author:salmonzhang
 * Description:主页面的Fragment
 * Date:2017/7/17 0017 23:54
 */

public class HomeFragment extends Fragment {

    private List<HomeBean.HomeItem> mShowItems = new ArrayList<>();

    @BindView(R.id.lv_home_list_layout)
    ListView mLvHomeListLayout;
    private HomeAdapter mHomeAdapter;
    private LoadPager mLoadPager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //使用UI切换框架加载页面

        if (mLoadPager == null) {
            mLoadPager = new LoadPager(getContext()) {
                //获取数据
                @Override
                protected Object getNetData() {
                    String url = "http://127.0.0.1:8090/home?index=0";
//              HomeBean homeBean = (HomeBean) JsonCacheManager.getInstance().getCacheData(url, HomeBean.class);
                    HomeBean homeBean = JsonCacheManager.getInstance().getCacheData(url, HomeBean.class);
                    //获取到数据后，将数据添加到mShowItems集合中
                    //从homeBean中获取listview条目中的数据
                    List<HomeBean.HomeItem> homeItems = homeBean.getList();
                    mShowItems.addAll(homeItems);
                    return homeBean;
                }

                //设置界面
                @Override
                public View createSuccessView() {
                    View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_home, null);
                    ButterKnife.bind(HomeFragment.this, view);
                    initView();
                    return view;
                }
            };
        }
        return mLoadPager;
    }

    //初始化listview
    private void initView() {
        //给主界面的listview设置适配器
        mHomeAdapter = new HomeAdapter(mShowItems);
        mLvHomeListLayout.setAdapter(mHomeAdapter);
    }
}
