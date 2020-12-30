package com.example.demo2.ui.home;


import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.demo2.R;
import com.example.demo2.base.BaseFragment;
import com.example.demo2.interfaces.IBasePresenter;
import com.example.demo2.ui.demo.LoginActivity;

import butterknife.BindView;

public class FiveFragment extends BaseFragment {

    @BindView(R.id.iv_five_yh)
    ImageView ivFiveYh;
    @BindView(R.id.tv_five_name)
    TextView tvFiveName;
    @BindView(R.id.iv_five_jt)
    ImageView ivFiveJt;
    @BindView(R.id.ll_five_dingdan)
    LinearLayout llFiveDingdan;
    @BindView(R.id.ll_five_youhuiquan)
    LinearLayout llFiveYouhuiquan;
    @BindView(R.id.ll_five_lipinka)
    LinearLayout llFiveLipinka;
    @BindView(R.id.ll_five_shoucang)
    LinearLayout llFiveShoucang;
    @BindView(R.id.ll_five_zuji)
    LinearLayout llFiveZuji;
    @BindView(R.id.ll_five_fuli)
    LinearLayout llFiveFuli;
    @BindView(R.id.ll_five_dizhi)
    LinearLayout llFiveDizhi;
    @BindView(R.id.ll_five_zhanghao)
    LinearLayout llFiveZhanghao;
    @BindView(R.id.ll_five_lianxi)
    LinearLayout llFiveLianxi;
    @BindView(R.id.ll_five_bangzhu)
    LinearLayout llFiveBangzhu;
    @BindView(R.id.ll_five_fankui)
    LinearLayout llFiveFankui;

    @Override
    protected int getLayout() {
        return R.layout.fragment_five;
    }

    @Override
    protected IBasePresenter createPrenter() {
        return null;
    }

    @Override
    protected void initView() {
        //点击跳转登录
        tvFiveName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getContext(), LoginActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void initData() {

    }
}