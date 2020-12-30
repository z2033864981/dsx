package com.example.demo2.adapter;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.demo2.R;
import com.example.demo2.base.BaseAdapter;
import com.example.demo2.bean.ChannelTypeBean;
import com.example.demo2.utils.ImageLoader;

import java.util.List;

public class ChannelAdapter extends BaseAdapter {

    private Context context;
    public ChannelAdapter(Context context, List Data) {
        super(context, Data);
        this.context=context;
    }

    @Override
    protected int getLayout(int type) {
        return R.layout.layout_channel_item_1;
    }

    @Override
    protected void bindData(Object data, VH vh) {
        ChannelTypeBean.DataBeanX.GoodsListBean bean= (ChannelTypeBean.DataBeanX.GoodsListBean) data;
        TextView name= (TextView) vh.getViewById(R.id.tv_channel1_name);
        TextView price= (TextView) vh.getViewById(R.id.tv_channel1_price);
        ImageView img= (ImageView) vh.getViewById(R.id.iv_channel1_img);

        //Glide.with(context).load(bean.getList_pic_url()).into(img);
        ImageLoader.loadImage(bean.getList_pic_url(),img);
        name.setText(bean.getName());
        price.setText("￥"+bean.getRetail_price());
    }
}
