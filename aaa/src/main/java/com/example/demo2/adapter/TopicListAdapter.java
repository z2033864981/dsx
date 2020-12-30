package com.example.demo2.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.demo2.R;
import com.example.demo2.base.BaseAdapter;
import com.example.demo2.bean.OneBean;

import java.util.List;

public class TopicListAdapter extends BaseAdapter {
    public TopicListAdapter(Context context, List data) {
        super(context, data);
    }

    @Override
    protected int getLayout(int type) {
        return R.layout.topic_item;
    }

    @Override
    protected void bindData(Object data, VH vh) {
        OneBean.DataBean.TopicListBean bean= (OneBean.DataBean.TopicListBean) data;
        ImageView ivtopicimg = (ImageView) vh.getViewById(R.id.iv_topic_img);
        TextView tvtopicname = (TextView) vh.getViewById(R.id.tv_topic_name);
        TextView tvtopicprice = (TextView) vh.getViewById(R.id.tv_topic_price);
        TextView tvtopicsubtitle = (TextView) vh.getViewById(R.id.tv_topic_subtitle);
        Glide.with(context).load(bean.getItem_pic_url()).into(ivtopicimg);
        tvtopicname.setText(bean.getTitle());
        tvtopicprice.setText("￥"+bean.getPrice_info()+"元起");
        tvtopicsubtitle.setText(bean.getSubtitle());
    }
}
