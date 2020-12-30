package com.example.demo2.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.demo2.R;
import com.example.demo2.base.BaseAdapter;
import com.example.demo2.bean.BrandBean;

import java.util.List;

public class TvBrandAdapter extends BaseAdapter {
    public TvBrandAdapter(Context context, List data) {
        super(context, data);
    }

    @Override
    protected int getLayout(int type) {
        return R.layout.layout_brand_item;
    }

    @Override
    protected void bindData(Object data, VH vh) {
        BrandBean.DataBeanX.DataBean bean= (BrandBean.DataBeanX.DataBean) data;
        ImageView ivtvbrandimg = (ImageView) vh.getViewById(R.id.iv_tvbrand_img);
        TextView tvtvbrandname = (TextView) vh.getViewById(R.id.tv_tvbrand_name);
        TextView tvtvbrandprice = (TextView) vh.getViewById(R.id.tv_tvbrand_price);
        Glide.with(context).load(bean.getApp_list_pic_url()).into(ivtvbrandimg);
        tvtvbrandname.setText(bean.getName());
        tvtvbrandprice.setText(bean.getFloor_price()+"元起");
    }
}
