package com.itheima.googleplaymark.adapter;

import com.itheima.googleplaymark.viewholder.BaseViewHolder;
import com.itheima.googleplaymark.viewholder.SubjectViewHolder;

import java.util.List;

/**
 * author:salmonzhang
 * Description:专题界面的适配器
 * Date:2017/7/20 0020 00:29
 */

public class SubjectAdapter extends BasicAdapter {


    public SubjectAdapter(List mShowItems) {
        super(mShowItems);
    }

    @Override
    public BaseViewHolder createViewholder() {
        return new SubjectViewHolder();
    }
}
