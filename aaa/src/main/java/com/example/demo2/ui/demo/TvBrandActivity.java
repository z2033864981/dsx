package com.example.demo2.ui.demo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.demo2.R;
import com.example.demo2.adapter.TvBrandAdapter;
import com.example.demo2.app.Myapp;
import com.example.demo2.base.BaseActivity;
import com.example.demo2.base.BaseAdapter;
import com.example.demo2.bean.BrandBean;
import com.example.demo2.bean.BrandDetail1;
import com.example.demo2.bean.BrandDetail2;
import com.example.demo2.interfaces.demo.IBrand;
import com.example.demo2.presenter.BrandPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TvBrandActivity extends BaseActivity<IBrand.Presenter> implements IBrand.View {
    @BindView(R.id.recycler_brand)
    RecyclerView recyclerBrand;
    private List<BrandBean.DataBeanX.DataBean> list;
    private TvBrandAdapter tvBrandAdapter;

    @Override
    protected int getLayout() {
        return R.layout.activity_tvbrand;
    }

    @Override
    protected IBrand.Presenter createPrenter() {
        return new BrandPresenter(this);
    }

    @Override
    protected void initView() {
        list = new ArrayList<>();
        recyclerBrand.setLayoutManager(new LinearLayoutManager(this));
        tvBrandAdapter = new TvBrandAdapter(this,list);
        recyclerBrand.setAdapter(tvBrandAdapter);

        tvBrandAdapter.addListClick(new BaseAdapter.IListClick() {
            @Override
            public void itemClick(int pos) {
                Intent intent=new Intent(TvBrandActivity.this,BrandDetailActivity.class);
                Myapp.getMap().put("id",list.get(pos).getId());
                startActivity(intent);
            }
        });

    }

    @Override
    protected void initData() {
        presenter.getBrand();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    public void getBrandReturn(BrandBean result) {
        list.clear();
        list.addAll(result.getData().getData());
        tvBrandAdapter.notifyDataSetChanged();
    }

    @Override
    public void getBrandDetail1Return(BrandDetail1 result) {

    }

    @Override
    public void getBrandDetail2Return(BrandDetail2 result) {

    }
}
