package com.example.shopping.adapter;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.shopping.R;
import com.example.shopping.base.BaseAdapter;
import com.example.shopping.moudel.bean.SortDescBean;
import com.example.shopping.moudel.bean.SortTabBean;

import java.util.List;

import butterknife.BindView;

public class SortAdapter extends BaseAdapter<SortDescBean.DataBean.CurrentCategoryBean.SubCategoryListBean> {

    ImageView img;

    TextView tv;
    private Context context;
    private List<SortDescBean.DataBean.CurrentCategoryBean.SubCategoryListBean> data;

    public SortAdapter(Context context, List<SortDescBean.DataBean.CurrentCategoryBean.SubCategoryListBean> data) {
        super(context, data);
        this.context = context;
        this.data = data;
    }

    @Override
    protected int getLayout(int type) {
        return R.layout.item_sort_channel;
    }

    @Override
    protected void bindData(SortDescBean.DataBean.CurrentCategoryBean.SubCategoryListBean data, VH vh) {
        img=vh.itemView.findViewById(R.id.img);
        tv=vh.itemView.findViewById(R.id.tv);
        tv.setText(data.getName());
        Glide.with(context).load(data.getWap_banner_url()).into(img);
    }
}
