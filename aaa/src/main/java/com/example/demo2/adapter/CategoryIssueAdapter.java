package com.example.demo2.adapter;

import android.content.Context;
import android.widget.TextView;

import com.example.demo2.R;
import com.example.demo2.base.BaseAdapter;
import com.example.demo2.bean.GoodDetailBean;
import com.example.demo2.utils.TxtUtils;

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
