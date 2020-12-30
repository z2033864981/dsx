package com.example.demo2.ui.home;

import android.content.Intent;
import android.view.View;
import android.widget.Button;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.demo2.R;
import com.example.demo2.adapter.TwoAdapter;
import com.example.demo2.base.BaseFragment;
import com.example.demo2.bean.TwoBean;
import com.example.demo2.bean.TwooBean;
import com.example.demo2.interfaces.demo.ITwo;
import com.example.demo2.presenter.TwoPresenter;
import com.example.demo2.ui.demo.TwooActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class TwoFragment extends BaseFragment<ITwo.Presenter> implements ITwo.View {

    @BindView(R.id.recycler_main)
    RecyclerView recyclerMain;
    @BindView(R.id.bnt_one)
    Button bntOne;
    @BindView(R.id.bnt_two)
    Button bntTwo;
    private TwoAdapter twoAdapter;
    private List<TwoBean.DataBeanX.DataBean> list;

    @Override
    protected int getLayout() {
        return R.layout.fragment_twoo;
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
            }
        });
        bntTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //bntTwo.setTextColor(0xCFCFCC);
                Intent intent = new Intent(getContext(), TwooActivity.class);
                startActivity(intent);
            }
        });

        list = new ArrayList<>();

        recyclerMain.setLayoutManager(new LinearLayoutManager(getContext()));
        twoAdapter = new TwoAdapter(getContext(), list);
        recyclerMain.setAdapter(twoAdapter);
    }

    @Override
    protected void initData() {
        presenter.getTwo();
    }


    @Override
    public void getTwoReturn(TwoBean twoBean) {
        list.clear();
        list.addAll(twoBean.getData().getData());
        twoAdapter.notifyDataSetChanged();
    }

    @Override
    public void getTwooReturn(TwooBean twooBean) {

    }
}