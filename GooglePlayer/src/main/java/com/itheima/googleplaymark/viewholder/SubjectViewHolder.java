package com.itheima.googleplaymark.viewholder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.itheima.googleplaymark.R;
import com.itheima.googleplaymark.bean.SubjectBean;
import com.itheima.googleplaymark.gloab.GooglePlay;
import com.itheima.googleplaymark.utils.Uris;
import com.itheima.googleplaymark.utils.Utils;

import butterknife.BindView;

/**
 * author:salmonzhang
 * Description:专题界面的viewHolder
 * Date:2017/7/21 0021 20:02
 */

public class SubjectViewHolder extends BaseViewHolder<SubjectBean> {
    @BindView(R.id.iv_image)
    ImageView mIvImage;
    @BindView(R.id.tv_des)
    TextView mTvDes;

    //创建布局
    @Override
    public View createItemView() {
        View view = View.inflate(GooglePlay.context, R.layout.adapter_subject, null);
        return view;
    }

    //绑定数据
    @Override
    public void bindView(SubjectBean subjectBean) {
        mTvDes.setText(subjectBean.getDes());
        Utils.SetFadeImage(Uris.IMAGEFOREAD+subjectBean.getUrl(),mIvImage);
    }
}
