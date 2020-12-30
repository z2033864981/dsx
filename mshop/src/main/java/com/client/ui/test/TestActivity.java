package com.client.ui.test;

import android.view.View;
import android.widget.Button;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.client.R;
import com.client.base.BaseActivity;
import com.client.interfaces.IBasePresenter;
import com.client.interfaces.test.ITest;
import com.client.model.test.TestBean;
import com.client.presenter.test.TestPresenter;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class TestActivity extends BaseActivity<ITest.Presenter> implements ITest.View {

    @BindView(R.id.recy)
    RecyclerView recyclerView;
    @BindView(R.id.btn_commit)
    Button btnSubmit;

    private List<TestBean.DataBean> myList;
    private MyAdapter myAdapter;

    @Override
    protected int getLayout() {
        return R.layout.activity_test;
    }

    @Override
    protected TestPresenter createPrenter() {
        return new TestPresenter();
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        myList = new ArrayList<>();
        myAdapter = new MyAdapter(this,myList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(myAdapter);
        presenter.getList();
    }

    @OnClick
    protected void onClick(View view){
        switch (view.getId()){
            case R.id.btn_commit:
                getChangeData();
                changeDataByMap();
                break;
        }
    }

    @Override
    public void getListReturn(TestBean result) {
        myList.addAll(result.getData());
        myAdapter.notifyDataSetChanged();
    }

    private List<TestBean.DataBean> getChangeData(){
        List<TestBean.DataBean> list = new ArrayList<>();

        for(TestBean.DataBean item:myList){
            if(item.isSelect() != item.currentSelect){
                list.add(item);
            }
        }
        return list;
    }

    private void changeDataByMap(){
        HashMap<String, TestBean.DataBean> map = myAdapter.getMap();
        map.keySet();
    }
}
