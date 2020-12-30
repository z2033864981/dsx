package com.example.shopping.ui.home;

import android.content.Intent;
import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shopping.R;
import com.example.shopping.adapter.MakeAdapter;
import com.example.shopping.base.BaseActivity;
import com.example.shopping.base.BaseAdapter;
import com.example.shopping.interfaces.home.IHome;
import com.example.shopping.moudel.bean.MakeBean;
import com.example.shopping.pansenter.home.MakePansenter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MakeActivity extends BaseActivity<MakePansenter> implements IHome.MakeView {

    MakePansenter makePansenter;
    @BindView(R.id.rec)
    RecyclerView rec;

    @Override
    protected int getLayout() {
        return R.layout.activity_make;
    }

    @Override
    protected MakePansenter createPrenter() {
        makePansenter = new MakePansenter(this);
        return makePansenter;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        makePansenter.getMakeData();


    }

    @Override
    public void callMakeData(MakeBean makeBean) {
        MakeBean.DataBeanX data = makeBean.getData();
        List<MakeBean.DataBeanX.DataBean> data1 = data.getData();
        rec.setLayoutManager(new LinearLayoutManager(this));
        MakeAdapter makeAdapter = new MakeAdapter(this, data1);
//        rec.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        rec.setAdapter(makeAdapter);
        makeAdapter.addListClick(new BaseAdapter.IListClick() {
            @Override
            public void itemClick(int pos) {
                MakeBean.DataBeanX.DataBean dataBean = data1.get(pos);
                Intent intent = new Intent(MakeActivity.this, MakeChannelActivity.class);
                intent.putExtra("id",dataBean.getId());
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}