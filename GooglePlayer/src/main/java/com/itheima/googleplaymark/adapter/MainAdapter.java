package com.itheima.googleplaymark.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.itheima.googleplaymark.bean.FragmentInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * author:salmonzhang
 * Description:
 * Date:2017/7/17 0017 20:49
 */

public class MainAdapter extends FragmentPagerAdapter {
    //轮播图跟照片这个简单的view展示可以用pagerAdapter
    //其他的使得fragmentPagerAdapter
    //adapter永远跟list<bean>组合在一起
    private List<FragmentInfo> mShowItems = new ArrayList<>();
    public MainAdapter(FragmentManager fm, List<FragmentInfo> showItems) {
        super(fm);
        mShowItems = showItems;
    }

    //返回一个显示Fragment
    @Override
    public Fragment getItem(int position) {
        return mShowItems.get(position).fragment;
    }

    //返回的个数
    @Override
    public int getCount() {
        return mShowItems.size();
    }

    //获取页面标题
    @Override
    public CharSequence getPageTitle(int position) {
        return mShowItems.get(position).title;
    }
}
