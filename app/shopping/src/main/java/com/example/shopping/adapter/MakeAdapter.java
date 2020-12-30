package com.example.shopping.adapter;

import android.content.Context;
import android.text.TextPaint;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.shopping.R;
import com.example.shopping.base.BaseAdapter;
import com.example.shopping.moudel.bean.MakeBean;

import java.util.List;

public class MakeAdapter extends BaseAdapter<MakeBean.DataBeanX.DataBean> {

    private Context context;

    public MakeAdapter(Context context, List<MakeBean.DataBeanX.DataBean> data) {
        super(context, data);
        this.context = context;
    }

    @Override
    protected int getLayout(int type) {
        return R.layout.male_item;
    }

    @Override
    protected void bindData(MakeBean.DataBeanX.DataBean data, VH vh) {
        TextView tv=vh.itemView.findViewById(R.id.tv);
        ImageView img=vh.itemView.findViewById(R.id.img);
        TextPaint tp = tv.getPaint();
        tp.setFakeBoldText(true);
        Glide.with(context).load(data.getApp_list_pic_url()).into(img);
        tv.setText(data.getName()+"|"+data.getFloor_price()+"元起");
    }
}
