package com.itheima.googleplaymark.ui.fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;

import com.itheima.googleplaymark.R;
import com.itheima.googleplaymark.adapter.SubjectAdapter;
import com.itheima.googleplaymark.bean.SubjectBean;
import com.itheima.googleplaymark.cachemanager.JsonCacheManager;
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
    private List<SubjectBean> mShowItems = new ArrayList<>();
    @BindView(R.id.lv_subject_list_layout)
    ListView mLvSubjectListLayout;
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
        List<SubjectBean> beanList = JsonCacheManager.getInstance().getCacheList("http://127.0.0.1:8090/subject?index=0", SubjectBean.class);
        //将获取到的数据全部添加到mShowItems中
        mShowItems.addAll(beanList);
        //通知主线程刷新界面
        Utils.runOnUIThread(new Runnable() {
            @Override
            public void run() {
                mSubjectAdapter.notifyDataSetChanged();
            }
        });
        return beanList;
    }

    //舒适化界面
    private void init() {
        //给listview设置适配器
        mSubjectAdapter = new SubjectAdapter(mShowItems);
        mLvSubjectListLayout.setAdapter(mSubjectAdapter);
    }
}
