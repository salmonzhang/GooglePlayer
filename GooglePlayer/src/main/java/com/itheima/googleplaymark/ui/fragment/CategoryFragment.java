package com.itheima.googleplaymark.ui.fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;

import com.itheima.googleplaymark.R;
import com.itheima.googleplaymark.gloab.GooglePlay;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * author:salmonzhang
 * Description:分类界面的Fragment
 * Date:2017/8/1 0001 15:50
 */

public class CategoryFragment extends BaseFragment {
    @BindView(R.id.lv_category_layout)
    ListView mLvCategoryLayout;

    //创建一个布局
    @Override
    public View createItemView() {
        View view = LayoutInflater.from(GooglePlay.context).inflate(R.layout.fragment_category, null);
        ButterKnife.bind(CategoryFragment.this, view);
        init();
        return view;
    }

    //初始化
    private void init() {
        //给Listview设置适配器
//        mLvCategoryLayout.setAdapter();
    }

    //请求数据
    @Override
    public Object questData() {
        return null;
    }
}
