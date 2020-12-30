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

public class NewGoodsListAdapter extends BaseAdapter {

    public NewGoodsListAdapter(Context context, List data) {
        super(context, data);
    }

    @Override
    protected int getLayout(int type) {
        return R.layout.newgoods_item;
    }

    @Override
    protected void bindData(Object data, VH vh) {
        OneBean.DataBean.NewGoodsListBean bean= (OneBean.DataBean.NewGoodsListBean) data;
        ImageView ivnewgoodsimg = (ImageView) vh.getViewById(R.id.iv_newgoods_img);
        TextView tvnewgoodsname = (TextView) vh.getViewById(R.id.tv_newgoods_name);
        TextView tvnewgoodsretailprice = (TextView) vh.getViewById(R.id.tv_newgoods_retail_price);
        Glide.with(context).load(bean.getList_pic_url()).into(ivnewgoodsimg);
        tvnewgoodsname.setText(bean.getName());
        tvnewgoodsretailprice.setText("￥"+bean.getRetail_price());
    }
}
