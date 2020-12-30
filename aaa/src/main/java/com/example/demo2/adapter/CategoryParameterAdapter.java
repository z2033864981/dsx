package com.example.demo2.adapter;

import android.content.Context;
import android.widget.TextView;

import com.example.demo2.R;
import com.example.demo2.base.BaseAdapter;
import com.example.demo2.bean.GoodDetailBean;
import com.example.demo2.utils.TxtUtils;

import java.util.List;

public class CategoryParameterAdapter extends BaseAdapter {

    public CategoryParameterAdapter(Context context, List Data) {
        super(context, Data);
    }

    @Override
    protected int getLayout(int type) {
        return R.layout.layout_category_parameter_item;
    }

    @Override
    protected void bindData(Object data, VH vh) {
        GoodDetailBean.DataBeanX.AttributeBean bean = (GoodDetailBean.DataBeanX.AttributeBean) data;
        TextView key= (TextView) vh.getViewById(R.id.tv_category_parameter_key);
        TextView value= (TextView) vh.getViewById(R.id.tv_category_parameter_value);

        TxtUtils.setTextView(key,bean.getName());
        TxtUtils.setTextView(value,bean.getValue());
    }
}
