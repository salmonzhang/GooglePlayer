package com.itheima.googleplaymark.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.itheima.googleplaymark.viewholder.BaseViewHolder;
import com.itheima.googleplaymark.viewholder.HomeViewHolder;
import com.nostra13.universalimageloader.core.DisplayImageOptions;

import java.util.ArrayList;
import java.util.List;

/**
 * author:salmonzhang
 * Description:Adapter抽取的思想：把所有可变的全部抽取到ViewHolder中
 * Date:2017/7/18 0018 20:40
 */

public abstract class HomeAdapter3<T> extends BaseAdapter {
    private List<T> mShowItems = new ArrayList<>();
    private DisplayImageOptions options;

    public HomeAdapter3(List<T> mShowItems) {
        this.mShowItems = mShowItems;
    }

    @Override
    public int getCount() {
        return mShowItems.size();
    }

    @Override
    public Object getItem(int position) {
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
            homeViewHolder = createViewholder();
        } else {
            homeViewHolder = (HomeViewHolder) convertView.getTag();
        }
        //赋值
        homeViewHolder.bindView(mShowItems.get(position));
        return homeViewHolder.getView();
    }

    public abstract BaseViewHolder createViewholder();
}
