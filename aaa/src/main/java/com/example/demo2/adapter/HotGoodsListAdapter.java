package com.example.demo2.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.demo2.R;
import com.example.demo2.base.BaseAdapter;
import com.example.demo2.bean.OneBean;

import java.util.List;

public class HotGoodsListAdapter extends BaseAdapter {

    public HotGoodsListAdapter(Context context, List data) {
        super(context, data);
    }

    @Override
    protected int getLayout(int type) {
        return R.layout.hotgoods_item;
    }

    @Override
    protected void bindData(Object data, VH vh) {
        OneBean.DataBean.HotGoodsListBean bean= (OneBean.DataBean.HotGoodsListBean) data;
        ImageView ivhotgoodsimg = (ImageView) vh.getViewById(R.id.iv_hotgoods_img);
        TextView tvhotgoodsname = (TextView) vh.getViewById(R.id.tv_hotgoods_name);
        TextView tvhotgoodsgoodsbrief = (TextView) vh.getViewById(R.id.tv_hotgoods_goods_brief);
        TextView tvhotgoodsretailprice = (TextView) vh.getViewById(R.id.tv_hotgoods_retail_price);
        Glide.with(context).load(bean.getList_pic_url()).into(ivhotgoodsimg);
        tvhotgoodsname.setText(bean.getName());
        tvhotgoodsgoodsbrief.setText(bean.getGoods_brief());
        tvhotgoodsretailprice.setText("￥"+bean.getRetail_price());
    }
}
