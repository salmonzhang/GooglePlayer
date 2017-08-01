package com.itheima.googleplaymark.ui.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.itheima.googleplaymark.R;
import com.itheima.googleplaymark.adapter.MainAdapter;
import com.itheima.googleplaymark.bean.FragmentInfo;
import com.itheima.googleplaymark.ui.fragment.CategoryFragment;
import com.itheima.googleplaymark.ui.fragment.HomeFragment;
import com.itheima.googleplaymark.ui.fragment.RecommendFragment;
import com.itheima.googleplaymark.ui.fragment.SubjectFragment;
import com.itheima.googleplaymark.utils.Utils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.ll_main_show_layout)
    LinearLayout mLlMainShowLayout;
    @BindView(R.id.fl_main_menu_layout)
    FrameLayout mFlMainMenuLayout;
    @BindView(R.id.dl_main_root_layout)
    DrawerLayout mDlMainRootLayout;
    @BindView(R.id.tb_main_title_layout)
    TabLayout mTbMainTitleLayout;
    @BindView(R.id.vp_main_show_content)
    ViewPager mVpMainShowContent;
    private ActionBarDrawerToggle mActionBarDrawerToggle;
    private List<FragmentInfo> mShowItems = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        init();
    }

    private void init() {

        //初始化actionbar
        initActionBar();
        //初始化主界面
        initMain();
    }

    private void initMain() {
        //初始化适配器数据
        String[] titles = Utils.getStringArray(R.array.tab_names);
//        for (int i = 0; i < titles.length; i++) {
//            mShowItems.add(new FragmentInfo(new HomeFragment(), titles[i]));
//        }

        mShowItems.add(new FragmentInfo(new HomeFragment(), titles[0]));
        mShowItems.add(new FragmentInfo(new SubjectFragment(), titles[1]));
        mShowItems.add(new FragmentInfo(new RecommendFragment(), titles[2]));
        mShowItems.add(new FragmentInfo(new CategoryFragment(), titles[3]));
        mShowItems.add(new FragmentInfo(new HomeFragment(), titles[4]));

        //初始化viewpager(给viewpager设置适配器)
        mVpMainShowContent.setAdapter(new MainAdapter(getSupportFragmentManager(), mShowItems));

        //页签与viewpager绑定
        mTbMainTitleLayout.setupWithViewPager(mVpMainShowContent);
        //设置颜色
        int normalColor = Color.parseColor("#8C8C8C");
        int selectedColor = Color.parseColor("#3949A3");
        mTbMainTitleLayout.setTabTextColors(normalColor,selectedColor);

        //指示器颜色
        mTbMainTitleLayout.setSelectedTabIndicatorColor(selectedColor);

        //设置模式
        mTbMainTitleLayout.setTabMode(TabLayout.GRAVITY_FILL);
    }

    private void initActionBar() {
        /**
         * 设置标题属性
         */
        //得到ActionBar
        ActionBar actionBar = getSupportActionBar();
        //更改标题
        actionBar.setTitle("谷歌市场");
        //显示箭头
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
        /**
         * 让actionbar与侧滑菜单进行联动
         */
        mActionBarDrawerToggle = new ActionBarDrawerToggle(this, mDlMainRootLayout, R.string.open, R.string.close);
        //同步
        mActionBarDrawerToggle.syncState();
        //设置监听
        mDlMainRootLayout.addDrawerListener(mActionBarDrawerToggle);
    }


    /**
     * 点击后菜单进行收缩（记住select这个单词）
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mActionBarDrawerToggle.onOptionsItemSelected(item);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
