package com.example.demo2.ui.demo;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.ArrayRes;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.bumptech.glide.Glide;
import com.example.demo2.R;
import com.example.demo2.adapter.BrandDetailAdapter;
import com.example.demo2.app.Myapp;
import com.example.demo2.base.BaseActivity;
import com.example.demo2.bean.BrandBean;
import com.example.demo2.bean.BrandDetail1;
import com.example.demo2.bean.BrandDetail2;
import com.example.demo2.interfaces.IBasePresenter;
import com.example.demo2.interfaces.demo.IBrand;
import com.example.demo2.presenter.BrandPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BrandDetailActivity extends BaseActivity<IBrand.Presenter> implements IBrand.View {
    @BindView(R.id.iv_brand_pic)
    ImageView ivBrandPic;
    @BindView(R.id.tv_brand_name)
    TextView tvBrandName;
    @BindView(R.id.tv_brand_simple)
    TextView tvBrandSimple;
    @BindView(R.id.recycler_branddetail)
    RecyclerView recyclerBranddetail;
    private BrandDetailAdapter brandDetailAdapter;
    private List<BrandDetail2.DataBeanX.DataBean> list;
    private int id;


    @Override
    protected int getLayout() {
        return R.layout.activity_branddetail;
    }

    @Override
    protected IBrand.Presenter createPrenter() {
        return new BrandPresenter(this);
    }


    @Override
    protected void initView() {
        Intent intent = getIntent();
        id = (int) Myapp.getMap().get("id");


        list = new ArrayList<>();
        recyclerBranddetail.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
        brandDetailAdapter = new BrandDetailAdapter(this, list);
        recyclerBranddetail.setAdapter(brandDetailAdapter);

    }

    @Override
    protected void initData() {
        presenter.getBrandDetail1(id);
        presenter.getBrandDetail2(id,"1","1000");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    public void getBrandReturn(BrandBean result) {

    }

    @Override
    public void getBrandDetail1Return(BrandDetail1 result) {
        Glide.with(this).load(result.getData().getBrand().getList_pic_url()).into(ivBrandPic);
        tvBrandName.setText(result.getData().getBrand().getName());
        tvBrandSimple.setText(result.getData().getBrand().getSimple_desc());
    }

    @Override
    public void getBrandDetail2Return(BrandDetail2 result) {
        list.clear();
        list.addAll(result.getData().getData());
        brandDetailAdapter.notifyDataSetChanged();
    }
}
