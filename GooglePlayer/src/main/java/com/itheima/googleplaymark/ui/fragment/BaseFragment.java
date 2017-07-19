package com.itheima.googleplaymark.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.itheima.googleplaymark.uimanager.LoadPager;

/**
 * author:salmonzhang
 * Description:fragment的基类
 * Date:2017/7/17 0017 23:54
 */

public abstract class BaseFragment extends Fragment {


    private LoadPager mLoadPager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //使用UI切换框架加载页面
        if (mLoadPager == null) {
            mLoadPager = new LoadPager(getContext()) {
                @Override
                protected Object getNetData() {
                    return questData();
                }

                @Override
                public View createSuccessView() {
                    return createItemView();
                }
            };
        }
        return mLoadPager;
    }

    //子类创建布局
    public abstract View createItemView();

    //子类实现数据请求
    public abstract Object questData();

}
