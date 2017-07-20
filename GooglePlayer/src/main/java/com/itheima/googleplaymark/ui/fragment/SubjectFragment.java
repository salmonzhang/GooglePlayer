package com.itheima.googleplaymark.ui.fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.itheima.googleplaymark.R;
import com.itheima.googleplaymark.adapter.SubjectAdapter;
import com.itheima.googleplaymark.bean.SubjectBean;
import com.itheima.googleplaymark.cachemanager.JsonCacheManager;
import com.itheima.googleplaymark.utils.ToastUtil;
import com.itheima.googleplaymark.utils.Uris;
import com.itheima.googleplaymark.utils.Utils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * author:salmonzhang
 * Description:
 * Date:2017/7/20 0020 00:13
 */

public class SubjectFragment extends BaseFragment {
    @BindView(R.id.pull_refresh_list)
    PullToRefreshListView mPullRefreshList;
    private List<SubjectBean> mShowItems = new ArrayList<>();
    private SubjectAdapter mSubjectAdapter;

    @Override
    public View createItemView() {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_subject, null);
        ButterKnife.bind(this, view);
        init();
        return view;
    }

    @Override
    public Object questData() {

        //判断当前所处的模式，如果是下拉刷新则清空数据，重新加载
        if (mPullRefreshList.getCurrentMode() == PullToRefreshBase.Mode.PULL_FROM_START) {
            mShowItems.clear();
        }

        List<SubjectBean> beanList = JsonCacheManager.getInstance().getCacheList(Uris.SUBJECTADDRESS, SubjectBean.class);
        /**
         * 下拉刷新的逻辑：
         * 1：集合长度为0时：
         *      1.1 如果返回的数据不为空，则正常加载数据
         *      1.2 如果返回的数据为空，则返回null
         * 2：集合长度不为0时：
         *      2.1 如果返回的数据不为空，则正常加载数据
         *      2.2 如果返回的数据为空，则提示用户重新加载
         */
        if (mShowItems.size() == 0) {
            if (beanList != null && beanList.size() > 0) {
                //正常加载
                //将获取到的数据全部添加到mShowItems中
                mShowItems.addAll(beanList);
            } else {
                return null;
            }
        } else {
            if (beanList != null && beanList.size() > 0) {
                //将获取到的数据全部添加到mShowItems中
                mShowItems.addAll(beanList);
                //正常加载
            } else {
                ToastUtil.showToast("请重新刷新！");
            }
        }


        //通知主线程刷新界面
        Utils.runOnUIThread(new Runnable() {
            @Override
            public void run() {
                //刷新数据
                mSubjectAdapter.notifyDataSetChanged();
                //停止下拉刷新
                mPullRefreshList.onRefreshComplete();
            }
        });
        return beanList;
    }

    //初始化界面
    private void init() {
        //给listview设置适配器
        mSubjectAdapter = new SubjectAdapter(mShowItems);
        mPullRefreshList.getRefreshableView().setAdapter(mSubjectAdapter);

        //设置刷新模式
        mPullRefreshList.setMode(PullToRefreshBase.Mode.BOTH);

        //设置刷新监听
        mPullRefreshList.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {
            @Override
            public void onRefresh(PullToRefreshBase<ListView> refreshView) {
                //下拉刷新时，重新请求数据
                mLoadPager.showPager();
            }
        });
    }
}
