package com.itheima.googleplaymark.uimanager;

import android.content.Context;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

import com.itheima.googleplaymark.R;
import com.itheima.googleplaymark.utils.Utils;

import java.util.List;

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
        if (mLoadingView == null) {
            mLoadingView = View.inflate(getContext(), R.layout.page_loading, null);
        }
        //加载成功界面，（谁用谁传）
        if (mSuccessView == null) {
            mSuccessView = createSuccessView();
            if (mSuccessView == null) {
                throw new RuntimeException("需要添加一个布局");
            }
        }
        //加载错误界面
        if (mErrorView == null) {
            mErrorView = View.inflate(getContext(), R.layout.page_error, null);
        }

        //添加三个布局
        addView(mLoadingView);
        addView(mSuccessView);
        addView(mErrorView);

        //定义一个页面切换的方法
        changeView();

        //根据网络数据，自动切换界面
        showPager();
    }

    public void showPager(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                //获取网络数据
                Object obj = getNetData();
                //校验数据，更换状态
                mCurrentState = checkData(obj);
                //切换UI界面
                Utils.runOnUIThread(new Runnable() {
                    @Override
                    public void run() {
                        changeView();
                    }
                });
            }
        }).start();

    }

    //校验数据
    private int checkData(Object obj) {
        //如果数据为空，则失败
        if (obj == null) {
            return ERROR;
        } else {
            if (obj instanceof List) {//如果是数组
                List list = (List) obj;
                if (list.size() > 0) {
                    return SUCCESS;
                } else {
                    return ERROR;
                }
            } else {//如果是对象
                return SUCCESS;
            }
        }
    }

    //切换页面
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
    //获取网络数据
    protected abstract Object getNetData();
    //创建一个成功的View界面
    public abstract View createSuccessView();
}
