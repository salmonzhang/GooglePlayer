package com.itheima.googleplaymark.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.itheima.googleplaymark.bean.SubjectBean;
import com.itheima.googleplaymark.viewholder.SubjectViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * author:salmonzhang
 * Description:专题界面的适配器
 * Date:2017/7/20 0020 00:29
 */

public class SubjectAdapter extends BaseAdapter {

    private List<SubjectBean> mShowsItems = new ArrayList<>();

    public SubjectAdapter(List<SubjectBean> showsItems) {
        mShowsItems = showsItems;
    }

    @Override
    public int getCount() {
        return mShowsItems.size();
    }

    @Override
    public SubjectBean getItem(int position) {
        return mShowsItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        SubjectViewHolder subjectViewHolder;
        if (convertView == null) {
            subjectViewHolder = new SubjectViewHolder();
        } else {
            subjectViewHolder = (SubjectViewHolder) convertView.getTag();
        }
        subjectViewHolder.bindView(mShowsItems.get(position));

        return subjectViewHolder.getView();
    }
}
