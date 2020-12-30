package com.example.demo2.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.demo2.R;
import com.example.demo2.base.BaseAdapter;
import com.example.demo2.bean.BrandDetail2;

import java.util.List;

public class BrandDetailAdapter extends BaseAdapter {

    public BrandDetailAdapter(Context context, List data) {
        super(context, data);
    }

    @Override
    protected int getLayout(int type) {
        return R.layout.layout_branddetail_item;
    }

    @Override
    protected void bindData(Object data, VH vh) {
        BrandDetail2.DataBeanX.DataBean bean= (BrandDetail2.DataBeanX.DataBean) data;
        ImageView ivbranddetailurl = (ImageView) vh.getViewById(R.id.iv_branddetail_url);
        TextView tvbranddetailname = (TextView) vh.getViewById(R.id.tv_branddetail_name);
        TextView tvbranddetailfloorprice = (TextView) vh.getViewById(R.id.tv_branddetail_floor_price);
        Glide.with(context).load(bean.getList_pic_url()).into(ivbranddetailurl);
        tvbranddetailname.setText(bean.getName());
        tvbranddetailfloorprice.setText("￥"+bean.getRetail_price());
    }
}
