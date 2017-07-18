package com.itheima.googleplaymark.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.itheima.googleplaymark.bean.HomeBean;
import com.itheima.googleplaymark.cachemanager.JsonCacheManager;
import com.itheima.googleplaymark.uimanager.LoadPager;

/**
 * author:salmonzhang
 * Description:主页面的Fragment
 * Date:2017/7/17 0017 23:54
 */

public class HomeFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //使用UI切换框架加载页面
        LoadPager loadPager = new LoadPager(getContext()) {
            //获取数据
            @Override
            protected Object getNetData() {
                String url = "http://127.0.0.1:8090/home?index=0";
//                HomeBean homeBean = (HomeBean) JsonCacheManager.getInstance().getCacheData(url, HomeBean.class);
                HomeBean homeBean = JsonCacheManager.getInstance().getCacheData(url, HomeBean.class);
                return homeBean;
            }

            //设置界面
            @Override
            public View createSuccessView() {
                TextView tv = new TextView(getContext());
                tv.setText("框架而生");
                return tv;
            }
        };
        return loadPager;
    }
}
