package com.example.demo2.adapter;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.demo2.R;
import com.example.demo2.base.BaseAdapter;
import com.example.demo2.bean.SortDataBean;
import com.example.demo2.utils.ImageLoader;
import com.example.demo2.utils.TxtUtils;

import java.util.List;

public class TypeAdapter extends BaseAdapter {

    public TypeAdapter(Context context, List Data) {
        super(context, Data);
    }

    @Override
    protected int getLayout(int type) {
        return R.layout.layout_type_item;
    }

    @Override
    protected void bindData(Object data, VH vh) {
        SortDataBean.DataBean.CurrentCategoryBean.SubCategoryListBean bean= (SortDataBean.DataBean.CurrentCategoryBean.SubCategoryListBean) data;
        ImageView img = (ImageView) vh.getViewById(R.id.iv_type_info_img);
        TextView name = (TextView) vh.getViewById(R.id.tv_type_info_name);

        ImageLoader.loadImage(bean.getWap_banner_url(),img);
        TxtUtils.setTextView(name,bean.getName());
    }
}
