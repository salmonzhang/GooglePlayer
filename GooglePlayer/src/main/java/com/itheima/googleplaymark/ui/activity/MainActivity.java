package com.itheima.googleplaymark.ui.activity;

import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.itheima.googleplaymark.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.ll_main_show_layout)
    LinearLayout mLlMainShowLayout;
    @BindView(R.id.fl_main_menu_layout)
    FrameLayout mFlMainMenuLayout;
    @BindView(R.id.dl_main_root_layout)
    DrawerLayout mDlMainRootLayout;
    private ActionBarDrawerToggle mActionBarDrawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        initView();
    }

    private void initView() {


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
