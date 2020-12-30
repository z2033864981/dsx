package com.example.demo2.ui.demo;


import android.content.Intent;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.demo2.R;
import com.example.demo2.adapter.SortDataAdapter;
import com.example.demo2.app.Myapp;
import com.example.demo2.base.BaseAdapter;
import com.example.demo2.base.BaseFragment;
import com.example.demo2.bean.OneBean;
import com.example.demo2.bean.SortDataBean;
import com.example.demo2.interfaces.demo.ISortData;
import com.example.demo2.presenter.SortDataPresenter;
import com.example.demo2.utils.CustomViewPager;

import butterknife.BindView;

public class CategoryTabFragment extends BaseFragment<ISortData.Presenter> implements ISortData.View{


    @BindView(R.id.sortData_img)
    ImageView sortDataImg;
    @BindView(R.id.sort_desc)
    TextView sortDesc;
    @BindView(R.id.fenleiname)
    TextView fenleiname;
    @BindView(R.id.rlv_sort)
    RecyclerView rlvSort;
    private int id;
    private SortDataAdapter sortDataAdapter;

    @Override
    protected int getLayout() {
        return R.layout.sort_data_item;
    }

    @Override
    protected ISortData.Presenter createPrenter() {
        return new SortDataPresenter();
    }

    @Override
    protected void initView() {
        id=getArguments().getInt("id");

    }



    @Override
    protected void initData() {
        presenter.getSortData(id);
    }

    //TODO 分类数据
    @Override
    public void getSortDataReturn(SortDataBean sortDataBean) {
        Glide.with(getContext()).load(sortDataBean.getData().getCurrentCategory().getWap_banner_url()).into(sortDataImg);
        sortDesc.setText(sortDataBean.getData().getCurrentCategory().getFront_desc());
        fenleiname.setText("————"+sortDataBean.getData().getCurrentCategory().getName()+"分类————");
        rlvSort.setLayoutManager(new GridLayoutManager(getContext(), 3));
        sortDataAdapter = new SortDataAdapter(getContext(),sortDataBean.getData().getCurrentCategory().getSubCategoryList());
        rlvSort.setAdapter(sortDataAdapter);
        sortDataAdapter.notifyDataSetChanged();

        //点击条目进入详情页
        sortDataAdapter.addListClick(new BaseAdapter.IListClick() {
            @Override
            public void itemClick(int pos) {
                Intent intent = new Intent(getContext(),TypeInfoListActivity.class);
                Myapp.getMap().put("typelist", sortDataBean.getData().getCurrentCategory().getSubCategoryList());
                Myapp.getMap().put("name", sortDataBean.getData().getCurrentCategory().getSubCategoryList().get(pos).getName());
                startActivity(intent);
            }
        });
    }
}