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
 * Description:Ui切换框架
 * Date:2017/8/6 0018 10:05
 */

public abstract class LoadPager2 extends FrameLayout {

    private View mLoadingView;
    private View mSuccessView;
    private View mErrorView;

    public LoadPager2(@NonNull Context context) {
        this(context, null);
    }

    public LoadPager2(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LoadPager2(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    //初始化UI框架三个界面
    private void init() {
        //正在加载界面
        if (mLoadingView == null) {
            mLoadingView = View.inflate(getContext(), R.layout.page_loading, null);
        }

        //加载成功界面
        if (mSuccessView == null) {
            //成功的布局谁用谁传
            mSuccessView = createSuccessView();
            if (mSuccessView == null) {
                throw new RuntimeException("亲，请添加一个布局");
            }
        }

        //加载失败的界面
        if (mErrorView == null) {
            mErrorView = View.inflate(getContext(), R.layout.page_error, null);
        }

        //将三个布局添加在一起
        addView(mLoadingView);
        addView(mSuccessView);
        addView(mErrorView);

        //页面切换的方法
        changeView();

        //根据网络数据，自动切换页面
        autoShowPager();

    }

    //自动切换页面
    private void autoShowPager() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                //获取网络数据
                Object obj = getNetData();
                //校验数据，根据返回状态自动切换状态
                mCurrentState = checkData(obj);
                //根据返回的状态在主线程去修改界面
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
    private STATE checkData(Object obj) {
        if (obj == null) {//如果数据为空，则失败
            return STATE.ERROR;
        } else {
            if (obj instanceof List) {//如果返回的是数组
                List list = (List) obj;
                if (list.size() > 0) {
                    return STATE.SUCCESS;
                } else {
                    return STATE.ERROR;
                }
            } else {//如果是对象
                return STATE.SUCCESS;
            }
        }
    }

    //页面切换方法
    private void changeView() {
        //先将三个页面都隐藏
        mLoadingView.setVisibility(GONE);
        mSuccessView.setVisibility(GONE);
        mErrorView.setVisibility(GONE);

        //根据当前状态切换
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
    public enum STATE {
        LOADING,//正在加载中
        SUCCESS,//加载成功
        ERROR;//加载失败
    }

    //定义一个当前状态（默认正在加载中）
    private STATE mCurrentState = STATE.LOADING;

    //创建一个加载成功的界面
    public abstract View createSuccessView();

    //请求网络数据
    public abstract Object getNetData();

}
