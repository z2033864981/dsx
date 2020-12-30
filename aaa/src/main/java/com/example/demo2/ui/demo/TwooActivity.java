package com.example.demo2.ui.demo;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Button;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.demo2.R;
import com.example.demo2.adapter.TwoAdapter;
import com.example.demo2.adapter.TwooAdapter;
import com.example.demo2.base.BaseActivity;
import com.example.demo2.base.BaseFragment;
import com.example.demo2.bean.TwoBean;
import com.example.demo2.bean.TwooBean;
import com.example.demo2.interfaces.demo.ITwo;
import com.example.demo2.presenter.TwoPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class TwooActivity extends BaseActivity<ITwo.Presenter> implements ITwo.View {
    @BindView(R.id.recycler_main)
    RecyclerView recyclerMain;
    @BindView(R.id.bnt_one)
    Button bntOne;
    @BindView(R.id.bnt_two)
    Button bntTwo;

    private List<TwooBean.DataBeanX.DataBean> list;
    private TwooAdapter twooAdapter;

    @Override
    protected int getLayout() {
        return R.layout.fragment_two;
    }

    @Override
    protected ITwo.Presenter createPrenter() {
        return new TwoPresenter(this);
    }


    @Override
    protected void initView() {

        bntOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bntOne.setTextColor(0x151515);
                finish();
            }
        });
        bntTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bntTwo.setTextColor(0xCFCFCC);
            }
        });

        list = new ArrayList<>();

        recyclerMain.setLayoutManager(new LinearLayoutManager(this));
        twooAdapter = new TwooAdapter(this, list);
        recyclerMain.setAdapter(twooAdapter);
    }

    @Override
    protected void initData() {
        presenter.getTwoo();
    }

    @Override
    public void getTwoReturn(TwoBean twoBean) {

    }

    @Override
    public void getTwooReturn(TwooBean twooBean) {
        list.clear();
        list.addAll(twooBean.getData().getData());
        twooAdapter.notifyDataSetChanged();
    }
}
