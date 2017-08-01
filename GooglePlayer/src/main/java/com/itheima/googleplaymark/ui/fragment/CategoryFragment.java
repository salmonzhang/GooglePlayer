package com.itheima.googleplaymark.ui.fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;

import com.itheima.googleplaymark.R;
import com.itheima.googleplaymark.adapter.CategoryAdapter;
import com.itheima.googleplaymark.bean.CategroyBean;
import com.itheima.googleplaymark.bean.CategroyTitleBean;
import com.itheima.googleplaymark.cachemanager.JsonCacheManager;
import com.itheima.googleplaymark.gloab.GooglePlay;
import com.itheima.googleplaymark.interfaces.ItemType;
import com.itheima.googleplaymark.utils.Uris;
import com.itheima.googleplaymark.utils.Utils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * author:salmonzhang
 * Description:分类界面的Fragment
 * Date:2017/8/1 0001 15:50
 */

public class CategoryFragment extends BaseFragment {
    private List<ItemType> mShowItems = new ArrayList<>();
    @BindView(R.id.lv_category_layout)
    ListView mLvCategoryLayout;
    private CategoryAdapter mAdapter;

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
        mAdapter = new CategoryAdapter(mShowItems);
        mLvCategoryLayout.setAdapter(mAdapter);
    }

    //请求数据
    @Override
    public Object questData() {
        //用网络框架请求数据
        List<CategroyBean> categroyBeanList = JsonCacheManager.getInstance().getCacheList(Uris.CATEGORY, CategroyBean.class);
        //数据判空
        if (categroyBeanList == null && categroyBeanList.size() == 0) {
            return null;
        }

        //将请求的数据进行切分
        for (int i = 0; i < categroyBeanList.size(); i++) {
            //头部数据
            CategroyTitleBean titleBean = new CategroyTitleBean();
            titleBean.title = categroyBeanList.get(i).getTitle();
            mShowItems.add(titleBean);
            //身体数据
            mShowItems.addAll(categroyBeanList.get(i).getInfos());
        }

        //通知主线程修改UI
        Utils.runOnUIThread(new Runnable() {
            @Override
            public void run() {
                mAdapter.notifyDataSetChanged();
            }
        });
        return mShowItems;
    }
}
