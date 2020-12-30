package com.example.shopping.adapter;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.shopping.R;
import com.example.shopping.base.BaseAdapter;
import com.example.shopping.moudel.bean.SortBean;

import java.util.List;

import butterknife.BindView;

public class SortDetailFragmentAdapter extends BaseAdapter<SortBean.DataBeanX.DataBean> {
    ImageView img;
    TextView tv;
    TextView tv2;
    private Context context;

    public SortDetailFragmentAdapter(Context context, List<SortBean.DataBeanX.DataBean> data) {
        super(context, data);
        this.context = context;
    }

    @Override
    protected int getLayout(int type) {
        return R.layout.channel_item;
    }

    @Override
    protected void bindData(SortBean.DataBeanX.DataBean data, VH vh) {
        img=vh.itemView.findViewById(R.id.img);
        tv=vh.itemView.findViewById(R.id.tv);
        tv2=vh.itemView.findViewById(R.id.tv2);
        Glide.with(context).load(data.getList_pic_url()).into(img);
        tv.setText(data.getName());
        tv2.setText(data.getRetail_price()+"元");
    }
}
