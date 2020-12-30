package com.example.shopping.adapter;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.shopping.R;
import com.example.shopping.base.BaseAdapter;
import com.example.shopping.moudel.bean.ChannelDescBean;

import java.util.List;

public class ChannelAdapter extends BaseAdapter<ChannelDescBean.DataBeanX.DataBean> {

    private Context context;

    public ChannelAdapter(Context context, List<ChannelDescBean.DataBeanX.DataBean> data) {
        super(context, data);
        this.context = context;
    }

    @Override
    protected int getLayout(int type) {
        return R.layout.channel_item;
    }

    @Override
    protected void bindData(ChannelDescBean.DataBeanX.DataBean data, VH vh) {
        ImageView img = vh.itemView.findViewById(R.id.img);
        TextView tv = vh.itemView.findViewById(R.id.tv);
        TextView tv2 = vh.itemView.findViewById(R.id.tv2);
        Glide.with(context).load(data.getList_pic_url()).into(img);
        tv.setText(data.getName());
        tv2.setText("￥"+data.getRetail_price());
    }
}
