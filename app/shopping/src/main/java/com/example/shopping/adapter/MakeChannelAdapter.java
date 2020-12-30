package com.example.shopping.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.shopping.R;
import com.example.shopping.base.BaseAdapter;
import com.example.shopping.moudel.bean.GoosListBean;
import com.example.shopping.moudel.bean.MakeChannelBean;

import org.w3c.dom.Text;

import java.util.List;

import butterknife.BindView;

public class MakeChannelAdapter extends BaseAdapter<GoosListBean.DataBeanX.GoodsListBean> {

    private Context context;

    public MakeChannelAdapter(Context context, List<GoosListBean.DataBeanX.GoodsListBean> data) {
        super(context, data);
        this.context = context;
    }

    @Override
    protected int getLayout(int type) {
        return R.layout.item_activity_makechannel;
    }

    @Override
    protected void bindData(GoosListBean.DataBeanX.GoodsListBean data, VH vh) {
        TextView tvPrice = vh.itemView.findViewById(R.id.tv_price);
        ImageView img = vh.itemView.findViewById(R.id.img);
        TextView tv = vh.itemView.findViewById(R.id.tv);
        Glide.with(context).load(data.getList_pic_url()).into(img);
        tv.setText(data.getName());
        tvPrice.setText(data.getRetail_price()+"");
    }
}
