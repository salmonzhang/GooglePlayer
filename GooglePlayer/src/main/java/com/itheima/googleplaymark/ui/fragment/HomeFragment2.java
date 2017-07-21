//package com.itheima.googleplaymark.ui.fragment;
//
//import android.view.LayoutInflater;
//import android.view.View;
//import android.widget.ListView;
//
//import com.handmark.pulltorefresh.library.PullToRefreshListView;
//import com.itheima.googleplaymark.R;
//import com.itheima.googleplaymark.adapter.BasicAdapter;
//import com.itheima.googleplaymark.bean.HomeBean;
//import com.itheima.googleplaymark.cachemanager.JsonCacheManager;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import butterknife.BindView;
//import butterknife.ButterKnife;
//
///**
// * author:salmonzhang
// * Description:主页面的Fragment
// * Date:2017/7/17 0017 23:54
// */
//
//public class HomeFragment extends BaseFragment {
//
//    @BindView(R.id.pull_refresh_list)
//    PullToRefreshListView mPullRefreshList;
//    //    @BindView(R.id.lv_home_list_layout)
////    ListView mLvHomeListLayout;
//    private BasicAdapter mHomeAdapter;
//    private List<HomeBean.HomeItem> mShowItems = new ArrayList<>();
//
//    @Override
//    public View createItemView() {
//        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_home, null);
//        ButterKnife.bind(HomeFragment.this, view);
//        initView();
//        return view;
//    }
//
//    @Override
//    public Object questData() {
//        String url = "http://127.0.0.1:8090/home?index=0";
//        HomeBean homeBean = JsonCacheManager.getInstance().getCacheData(url, HomeBean.class);
//        //获取到数据后，将数据添加到mShowItems集合中
//        //从homeBean中获取listview条目中的数据
//        List<HomeBean.HomeItem> homeItems = homeBean.getList();
//        mShowItems.addAll(homeItems);
//        return homeBean;
//    }
//
//    //初始化listview
//    private void initView() {
//        //给主界面的listview设置适配器
//        mHomeAdapter = new BasicAdapter(mShowItems);
//        ListView listView = mPullRefreshList.getRefreshableView();
//        listView.setAdapter(mHomeAdapter);
//    }
//}
