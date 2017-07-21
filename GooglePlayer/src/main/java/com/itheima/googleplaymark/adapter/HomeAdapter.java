package com.itheima.googleplaymark.adapter;

import com.itheima.googleplaymark.bean.HomeBean;
import com.itheima.googleplaymark.viewholder.BaseViewHolder;
import com.itheima.googleplaymark.viewholder.HomeViewHolder;

import java.util.List;

/**
 * author:salmonzhang
 * Description:
 * Date:2017/7/21 0021 21:16
 */

public class HomeAdapter extends BasicAdapter<HomeBean.HomeItem> {

    public HomeAdapter(List<HomeBean.HomeItem> mShowItems) {
        super(mShowItems);
    }

    @Override
    public BaseViewHolder createViewholder() {
        return new HomeViewHolder();
    }
}
