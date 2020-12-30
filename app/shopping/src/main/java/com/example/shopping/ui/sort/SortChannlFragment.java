package com.example.shopping.ui.sort;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.shopping.R;
import com.example.shopping.adapter.SortAdapter;
import com.example.shopping.base.BaseAdapter;
import com.example.shopping.base.BaseFragment;
import com.example.shopping.interfaces.IBasePresenter;
import com.example.shopping.interfaces.sort.ISort;
import com.example.shopping.moudel.bean.SortDescBean;
import com.example.shopping.moudel.bean.SortTabBean;
import com.example.shopping.pansenter.sort.SortPansenter;
import com.example.shopping.ui.home.ChannelActivity;

import java.io.Serializable;
import java.util.List;

import butterknife.BindView;

public class SortChannlFragment extends BaseFragment implements ISort.SortView {
    @BindView(R.id.tv_eco_sort)
    TextView tvEcoSort;
    @BindView(R.id.img_sort_img)
    ImageView imgSortImg;
    @BindView(R.id.sort_title)
    TextView sortTitle;
    @BindView(R.id.rl_sort_top)
    RelativeLayout rlSortTop;
    @BindView(R.id.tv_sort_desc)
    TextView tvSortDesc;
    @BindView(R.id.rcy_sort_data)
    RecyclerView rcySortData;
    private int id;


    public SortChannlFragment() {

    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_sort_channl;
    }
    SortPansenter sortPansenter;
    @Override
    protected IBasePresenter createPrenter() {
        sortPansenter=new SortPansenter(this);
        return sortPansenter;
    }

    @Override
    protected void initView() {
        Bundle arguments = getArguments();
        id = arguments.getInt("id");


    }

    @Override
    protected void initData() {
        sortPansenter.getSortDescData(id);
    }

    @Override
    public void getTabData(SortTabBean sortTabBean) {

    }

    @Override
    public void getSortDescData(SortDescBean sortDescBean) {
        List<SortDescBean.DataBean.CurrentCategoryBean.SubCategoryListBean> subCategoryList = sortDescBean.getData().getCurrentCategory().getSubCategoryList();
        sortTitle.setText(sortDescBean.getData().getCurrentCategory().getFront_name());
        Glide.with(getContext()).load(sortDescBean.getData().getCurrentCategory().getWap_banner_url()).into(imgSortImg);
        String name = sortDescBean.getData().getCurrentCategory().getName();
        tvSortDesc.setText("----"+name+"分类----");
        SortAdapter sortAdapter = new SortAdapter(getContext(), subCategoryList);
        rcySortData.setLayoutManager(new GridLayoutManager(getContext(),3));
        rcySortData.setAdapter(sortAdapter);
        sortAdapter.addListClick(new BaseAdapter.IListClick() {
            @Override
            public void itemClick(int pos) {
                Intent intent = new Intent(getContext(), ChannelActivity.class);
                intent.putExtra("id",sortDescBean.getData().getCurrentCategory().getId());
                intent.putExtra("type",false);
                startActivity(intent);
            }
        });
    }
}
