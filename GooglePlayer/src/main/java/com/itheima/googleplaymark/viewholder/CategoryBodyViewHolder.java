package com.itheima.googleplaymark.viewholder;

import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.itheima.googleplaymark.R;
import com.itheima.googleplaymark.bean.CategroyBean;
import com.itheima.googleplaymark.gloab.GooglePlay;
import com.itheima.googleplaymark.utils.Uris;
import com.itheima.googleplaymark.utils.Utils;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * author:salmonzhang
 * Description:
 * Date:2017/8/1 0001 21:15
 */

public class CategoryBodyViewHolder extends BaseViewHolder<CategroyBean.InfosBean> {


    @BindView(R.id.iv_image1)
    ImageView mIvImage1;
    @BindView(R.id.tv_name1)
    TextView mTvName1;
    @BindView(R.id.ll_info_info01)
    LinearLayout mLlInfoInfo01;
    @BindView(R.id.iv_image2)
    ImageView mIvImage2;
    @BindView(R.id.tv_name2)
    TextView mTvName2;
    @BindView(R.id.ll_info2)
    LinearLayout mLlInfo2;
    @BindView(R.id.iv_image3)
    ImageView mIvImage3;
    @BindView(R.id.tv_name3)
    TextView mTvName3;
    @BindView(R.id.ll_info3)
    LinearLayout mLlInfo3;
    @BindView(R.id.ll_info_root_layout)
    LinearLayout mLlInfoRootLayout;

    @Override
    public View createItemView() {
        View view = View.inflate(GooglePlay.context, R.layout.adapter_info, null);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void bindView(CategroyBean.InfosBean itemBean) {
        mLlInfoInfo01.setVisibility(View.INVISIBLE);
        mLlInfo2.setVisibility(View.INVISIBLE);
        mLlInfo3.setVisibility(View.INVISIBLE);

        if (!TextUtils.isEmpty(itemBean.getName1())) {
            mTvName1.setText(itemBean.getName1());
            Utils.SetFadeImage(Uris.IMAGEFOREAD+ itemBean.getUrl1(), mIvImage1);
            mLlInfoInfo01.setVisibility(View.VISIBLE);
        }
        if (!TextUtils.isEmpty(itemBean.getName2())) {
            mTvName2.setText(itemBean.getName2());
            Utils.SetFadeImage(Uris.IMAGEFOREAD+ itemBean.getUrl2(), mIvImage2);
            mLlInfo2.setVisibility(View.VISIBLE);
        }

        if (!TextUtils.isEmpty(itemBean.getName3())) {
            mTvName3.setText(itemBean.getName3());
            Utils.SetFadeImage(Uris.IMAGEFOREAD + itemBean.getUrl3(), mIvImage3);
            mLlInfo3.setVisibility(View.VISIBLE);
        }
    }
}
