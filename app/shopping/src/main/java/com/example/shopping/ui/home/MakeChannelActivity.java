package com.example.shopping.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.shopping.R;
import com.example.shopping.adapter.MakeChannelAdapter;
import com.example.shopping.base.BaseActivity;
import com.example.shopping.interfaces.IBasePresenter;
import com.example.shopping.interfaces.home.IHome;
import com.example.shopping.moudel.bean.GoosListBean;
import com.example.shopping.moudel.bean.MakeChannelBean;
import com.example.shopping.pansenter.home.MakeChannelParsenter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MakeChannelActivity extends BaseActivity implements IHome.MakeChannelView {
    MakeChannelParsenter makeChannelParsenter;
    @BindView(R.id.ing_new_good)
    ImageView ingNewGood;
    @BindView(R.id.tv)
    TextView tv;
    @BindView(R.id.con)
    ConstraintLayout con;
    @BindView(R.id.tv_desc)
    TextView tvDesc;
    @BindView(R.id.view)
    View view;
    @BindView(R.id.rec)
    RecyclerView rec;
    private int id;

    @Override
    protected int getLayout() {
        return R.layout.activity_makechannel;
    }

    @Override
    protected IBasePresenter createPrenter() {
        if (makeChannelParsenter == null) {
            makeChannelParsenter = new MakeChannelParsenter(this);
        }
        return makeChannelParsenter;
    }

    @Override
    protected void initView() {
        Intent intent = getIntent();
        id = intent.getIntExtra("id", 0);
        initMap();
    }

    private Map<String, String> initMap() {
        Map<String, String> map = new HashMap();
        map.put("id", String.valueOf(id));
        map.put("page", String.valueOf(1));
        map.put("size", String.valueOf(50));
        return map;
    }

    @Override
    protected void initData() {
        makeChannelParsenter.getMakeChannelData(initMap());
        makeChannelParsenter.getMakeChannelData(id+"");
    }

    @Override
    public void callMakeChannel(GoosListBean makeChannelBean) {
        List<GoosListBean.DataBeanX.GoodsListBean> goodsList = makeChannelBean.getData().getGoodsList();
        MakeChannelAdapter makeChannelAdapter = new MakeChannelAdapter(this, goodsList);
        rec.setLayoutManager(new GridLayoutManager(this,2));
        rec.setAdapter(makeChannelAdapter);
    }

    @Override
    public void callMakeChannelData(MakeChannelBean makeChannelBean) {
        Glide.with(this).load(makeChannelBean.getData().getBrand().getList_pic_url()).into(ingNewGood);
        tv.setText(makeChannelBean.getData().getBrand().getName());
        tvDesc.setText(makeChannelBean.getData().getBrand().getSimple_desc());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
