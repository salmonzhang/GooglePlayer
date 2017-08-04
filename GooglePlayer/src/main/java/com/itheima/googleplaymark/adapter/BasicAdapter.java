package com.itheima.googleplaymark.adapter;

import android.support.v4.view.ViewCompat;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.OvershootInterpolator;
import android.widget.BaseAdapter;

import com.itheima.googleplaymark.viewholder.BaseViewHolder;
import com.nostra13.universalimageloader.core.DisplayImageOptions;

import java.util.ArrayList;
import java.util.List;

/**
 * author:salmonzhang
 * Description:抽取BasicAdapter
 * Date:2017/7/18 0018 20:40
 */

public abstract class BasicAdapter<T> extends BaseAdapter {
    private List<T> mShowItems = new ArrayList<>();
    private DisplayImageOptions options;

    public BasicAdapter(List<T> mShowItems) {
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
            homeViewHolder = (BaseViewHolder) convertView.getTag();
        }
        //赋值
        homeViewHolder.bindView(mShowItems.get(position));

        View view = homeViewHolder.getView();

        //给view设置动画效果（有弹性的动态缩小和还原）

        //XY轴缩放
        view.setScaleX(.6f);
        view.setScaleY(.6f);

        //使用ViewCompat实现动画还原
        ViewCompat.animate(view).scaleX(1.0f).scaleY(1.0f).setInterpolator(new OvershootInterpolator()).setDuration(500).start();

        return view;
    }
    public abstract BaseViewHolder createViewholder();
}
