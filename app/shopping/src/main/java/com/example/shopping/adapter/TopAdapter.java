package com.example.shopping.adapter;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.shopping.R;
import com.example.shopping.base.BaseAdapter;
import com.example.shopping.moudel.bean.BanBean;

import java.util.List;

public class TopAdapter extends BaseAdapter<BanBean.DataBean.TopicListBean> {
    private Context context;

    public TopAdapter(Context context, List<BanBean.DataBean.TopicListBean> data) {
        super(context, data);
        this.context = context;
    }

    @Override
    protected int getLayout(int type) {
        return R.layout.item_top;
    }

    @Override
    protected void bindData(BanBean.DataBean.TopicListBean data, VH vh) {
        ImageView imageView=vh.itemView.findViewById(R.id.img);
        TextView tv=vh.itemView.findViewById(R.id.tv);
        TextView tv2=vh.itemView.findViewById(R.id.tv2);
        TextView tv3=vh.itemView.findViewById(R.id.tv3);
        Glide.with(context).load(data.getItem_pic_url()).into(imageView);
        tv.setText(data.getTitle());
        tv2.setText(data.getPrice_info());
        tv3.setText(data.getSubtitle());
    }
}
