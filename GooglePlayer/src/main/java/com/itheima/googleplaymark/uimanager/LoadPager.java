package com.itheima.googleplaymark.uimanager;

import android.content.Context;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

import com.itheima.googleplaymark.R;

/**
 * author:salmonzhang
 * Description:Ui框架
 * Date:2017/7/18 0018 10:05
 */

public abstract class LoadPager extends FrameLayout {

    private View mLoadingView;
    private View mSuccessView;
    private View mErrorView;

    public LoadPager(@NonNull Context context) {
        this(context, null);
    }

    public LoadPager(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LoadPager(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    //初始化UI框架三个界面
    private void init() {

        //正在加载界面
        mLoadingView = View.inflate(getContext(), R.layout.page_loading, null);
        //加载成功界面，（谁用谁传）
        mSuccessView = createSuccessView();
        //加载错误界面
        mErrorView = View.inflate(getContext(), R.layout.page_error, null);

        addView(mLoadingView);
        addView(mSuccessView);
        addView(mErrorView);

        //定义一个页面切换的方法
        changeView();
    }

    private void changeView() {
        mLoadingView.setVisibility(GONE);
        mSuccessView.setVisibility(GONE);
        mErrorView.setVisibility(GONE);

        switch (mCurrentState) {
            case LOADING:
                mLoadingView.setVisibility(VISIBLE);
                break;
            case SUCCESS:
                mSuccessView.setVisibility(VISIBLE);
                break;
            case ERROR:
                mErrorView.setVisibility(VISIBLE);
                break;
        }
    }

    //定义三个状态
    public static final int LOADING = 101;
    public static final int SUCCESS = 102;
    public static final int ERROR = 103;

    //定义一个当前状态
    private int mCurrentState = LOADING;

    public abstract View createSuccessView();
}
