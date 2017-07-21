package com.itheima.googleplaymark.viewholder;

import android.view.View;

import butterknife.ButterKnife;

/**
 * author:salmonzhang
 * Description:抽取一个BaseViewHolder
 * Date:2017/7/21 0021 19:39
 */

public abstract class BaseViewHolder<T> {

    private View view;
    //在构造函数中初始化
    public BaseViewHolder() {
        view = createItemView();
        ButterKnife.bind(this, view);
        //绑定
        view.setTag(this);
    }

    //创建一个布局
    public abstract View createItemView();

    //绑定数据
    public abstract void bindView(T t);

    //定义一个getView方法，返回到getView中
    public View getView() {
        return view;
    }
}
