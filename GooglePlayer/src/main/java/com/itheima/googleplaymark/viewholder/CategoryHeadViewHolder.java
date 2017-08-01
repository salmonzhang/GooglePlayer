package com.itheima.googleplaymark.viewholder;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.itheima.googleplaymark.R;
import com.itheima.googleplaymark.bean.CategroyTitleBean;
import com.itheima.googleplaymark.gloab.GooglePlay;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * author:salmonzhang
 * Description:
 * Date:2017/8/1 0001 21:10
 */

public class CategoryHeadViewHolder extends BaseViewHolder<CategroyTitleBean> {
    @BindView(R.id.tv_title)
    TextView mTvTitle;

    @Override
    public View createItemView() {
        View view = LayoutInflater.from(GooglePlay.context).inflate(R.layout.adapter_title, null);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void bindView(CategroyTitleBean titleBean) {
        mTvTitle.setText(titleBean.title);
    }
}
