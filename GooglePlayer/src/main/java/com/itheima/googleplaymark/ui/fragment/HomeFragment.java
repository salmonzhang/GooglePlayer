package com.itheima.googleplaymark.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.itheima.googleplaymark.R;
import com.itheima.googleplaymark.adapter.HomeAdapter;
import com.itheima.googleplaymark.bean.HomeBean;
import com.itheima.googleplaymark.cachemanager.JsonCacheManager;
import com.itheima.googleplaymark.ui.activity.ShowActivity;
import com.itheima.googleplaymark.utils.ToastUtil;
import com.itheima.googleplaymark.utils.Uris;
import com.itheima.googleplaymark.utils.Utils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * author:salmonzhang
 * Description:主页面的Fragment
 * Date:2017/7/17 0017 23:54
 */

public class HomeFragment extends BaseFragment {

    @BindView(R.id.pull_refresh_list)
    PullToRefreshListView mPullRefreshList;
    //    @BindView(R.id.lv_home_list_layout)
//    ListView mLvHomeListLayout;
    private HomeAdapter mHomeAdapter;
    private List<HomeBean.HomeItem> mShowItems = new ArrayList<>();

    @Override
    public View createItemView() {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_home, null);
        ButterKnife.bind(HomeFragment.this, view);
        initView();
        return view;
    }

    @Override
    public Object questData() {

        /**
         * 判断当前刷新状态：
         * 下拉刷新：请求更新数据
         * 上拉刷新:加载下一页的数据到集合中
         */
        if (mPullRefreshList.getCurrentMode() == PullToRefreshBase.Mode.PULL_FROM_START) {
            mShowItems.clear();
        }

        HomeBean homeBean = JsonCacheManager.getInstance().getCacheData(Uris.HOMEADDRESS+mShowItems.size(), HomeBean.class);
        //获取到数据后，将数据添加到mShowItems集合中
        /**
         * 下拉刷新的逻辑分析：
         * 1：如果集合的总长度为0
         *    1.1 如果当前数据不为0，则正常添加
         *    1.2 如果当前数据长度为0 ，则返回null
         * 2.如果集合的总长度不为0
         *    2.1 如果当前数据长度为0，则提示用户重新刷新
         *    2.2 如果当前数据长度不为0，则正常加载数据
         */

        if (mShowItems.size() == 0) {
            if (homeBean != null && homeBean.getList() != null && homeBean.getList().size() > 0) {
                List<HomeBean.HomeItem> homeItems = homeBean.getList();
                mShowItems.addAll(homeItems);
            } else {
                return null;
            }
        } else {
            if (homeBean != null && homeBean.getList() != null && homeBean.getList().size() > 0) {
                List<HomeBean.HomeItem> homeItems = homeBean.getList();
                mShowItems.addAll(homeItems);
            } else {
                ToastUtil.showToast("请重新刷新数据");
            }
        }

        //得到数据后，主线程更新数据
        Utils.runOnUIThread(new Runnable() {
            @Override
            public void run() {
                //更新数据
                mHomeAdapter.notifyDataSetChanged();
                //停止刷新
                mPullRefreshList.onRefreshComplete();
            }
        });
        return mShowItems;
    }

    //初始化listview
    private void initView() {
        //给主界面的listview设置适配器
        mHomeAdapter = new HomeAdapter(mShowItems);
        ListView listView = mPullRefreshList.getRefreshableView();
        //设置刷新模式
        mPullRefreshList.setMode(PullToRefreshBase.Mode.BOTH);

        //设置刷新监听
        mPullRefreshList.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {
            @Override
            public void onRefresh(PullToRefreshBase<ListView> refreshView) {
                //下拉时重新请求数据
                mLoadPager.showPager();
                ToastUtil.showToast("下拉刷新");
            }
        });

        listView.setAdapter(mHomeAdapter);

        //给listview条目设置点击事件
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //启动ShowActivity
                Intent intent = new Intent(getContext(), ShowActivity.class);
                intent.putExtra("className", DetailFragment.class);
                //使用Bundle传递数据
                Bundle bundle = new Bundle();
                bundle.putString("data", "HomeFragment数据");
                bundle.putInt("num", 18);
                intent.putExtra("bundle", bundle);

                startActivity(intent);
            }
        });
    }
}
