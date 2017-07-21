package com.itheima.googleplaymark.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.itheima.googleplaymark.bean.HomeBean;
import com.itheima.googleplaymark.viewholder.BaseViewHolder;
import com.nostra13.universalimageloader.core.DisplayImageOptions;

import java.util.ArrayList;
import java.util.List;

/**
 * author:salmonzhang
 * Description:Adapter抽取的思想：把所有可变的全部抽取到ViewHolder中
 * Date:2017/7/18 0018 20:40
 */

public abstract class HomeAdapter2 extends BaseAdapter {
    private List<HomeBean.HomeItem> mShowItems = new ArrayList<>();
    private DisplayImageOptions options;

    public HomeAdapter2(List<HomeBean.HomeItem> mShowItems) {
        this.mShowItems = mShowItems;
    }

    @Override
    public int getCount() {
        return mShowItems.size();
    }

    @Override
    public HomeBean.HomeItem getItem(int position) {
        return mShowItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        BaseViewHolder homeViewHolder;
        if (convertView == null) {
            //谁用Adapter谁传Viewholder
            homeViewHolder = createViewHolder();
        } else {
            homeViewHolder = (BaseViewHolder) convertView.getTag();
        }
        //赋值
        homeViewHolder.bindView(mShowItems.get(position));
        return homeViewHolder.getView();
    }

    public abstract BaseViewHolder createViewHolder();
}
