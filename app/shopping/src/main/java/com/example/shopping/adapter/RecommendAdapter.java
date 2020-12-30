package com.example.shopping.adapter;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.shopping.R;
import com.example.shopping.base.BaseAdapter;
import com.example.shopping.moudel.bean.BanBean;

import java.util.List;

public class RecommendAdapter extends BaseAdapter<BanBean.DataBean.HotGoodsListBean> {
    private final Context context;

    public RecommendAdapter(Context context, List<BanBean.DataBean.HotGoodsListBean> data) {
        super(context, data);
        this.context = context;
    }

    @Override
    protected int getLayout(int type) {
        return R.layout.recomm;
    }

    @Override
    protected void bindData(BanBean.DataBean.HotGoodsListBean data, VH vh) {
        ImageView imageView=vh.itemView.findViewById(R.id.img);
        TextView tv=vh.itemView.findViewById(R.id.tv1);
        TextView tv2=vh.itemView.findViewById(R.id.tv2);
        TextView tv3=vh.itemView.findViewById(R.id.tv3);
        Glide.with(context).load(data.getList_pic_url()).into(imageView);
        tv.setText(data.getName());
        tv2.setText(data.getGoods_brief());
        tv3.setText(data.getRetail_price());
    }
}
