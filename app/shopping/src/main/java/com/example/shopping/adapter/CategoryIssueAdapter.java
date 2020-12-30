package com.example.shopping.adapter;

import android.content.Context;
import android.widget.TextView;

import com.example.shopping.R;
import com.example.shopping.base.BaseAdapter;
import com.example.shopping.moudel.bean.GoodDetailBean;
import com.example.shopping.utils.TxtUtils;

import java.util.List;

public class CategoryIssueAdapter extends BaseAdapter {

    public CategoryIssueAdapter(Context context, List Data) {
        super(context, Data);
    }

    @Override
    protected int getLayout(int type) {
        return R.layout.layout_category_issue_item;
    }

    @Override
    protected void bindData(Object data, VH vh) {
        GoodDetailBean.DataBeanX.IssueBean bean = (GoodDetailBean.DataBeanX.IssueBean) data;
        TextView key= (TextView) vh.getViewById(R.id.tv_category_issue_key);
        TextView value= (TextView) vh.getViewById(R.id.tv_category_issue_value);

        TxtUtils.setTextView(key,bean.getQuestion());
        TxtUtils.setTextView(value,bean.getAnswer());
    }
}