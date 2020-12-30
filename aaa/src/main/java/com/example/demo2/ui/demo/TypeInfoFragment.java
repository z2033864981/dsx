package com.example.demo2.ui.demo;

import android.content.Intent;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.demo2.R;
import com.example.demo2.adapter.TypeAdapter;
import com.example.demo2.app.Myapp;
import com.example.demo2.base.BaseAdapter;
import com.example.demo2.base.BaseFragment;
import com.example.demo2.bean.SortDataBean;
import com.example.demo2.interfaces.demo.ISortData;
import com.example.demo2.presenter.SortDataPresenter;


import java.util.List;

import butterknife.BindView;

public class TypeInfoFragment extends BaseFragment<ISortData.Presenter> implements ISortData.View {
    @BindView(R.id.iv_typeinfo_head_img)
    ImageView iv_Img;
    @BindView(R.id.tv_typeinfo_head_desc)
    TextView tv_Desc;
    @BindView(R.id.tv_typeinfo_title)
    TextView tv_Title;
    @BindView(R.id.mRlv_info)
    RecyclerView mRlv;


    private int id;

    @Override
    protected int getLayout() {
        return R.layout.fragment_type_info;
    }

    @Override
    protected ISortData.Presenter createPrenter() {
        return new SortDataPresenter();
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        presenter.getSortData(id);
    }


    @Override
    public void getSortDataReturn(SortDataBean result) {
        //TODO 分类数据上面的数据
        Glide.with(getContext()).load(result.getData().getCurrentCategory().getWap_banner_url()).into(iv_Img);
        tv_Desc.setText(result.getData().getCurrentCategory().getFront_desc());
        tv_Title.setText("————" + result.getData().getCurrentCategory().getName() + "分类————");

        //TODO 分类数据
        mRlv.setLayoutManager(new GridLayoutManager(getContext(), 3));
        TypeAdapter typeAdapter = new TypeAdapter(getContext(), result.getData().getCurrentCategory().getSubCategoryList());
        mRlv.setAdapter(typeAdapter);
        typeAdapter.notifyDataSetChanged();

        List<SortDataBean.DataBean.CurrentCategoryBean.SubCategoryListBean> subCategoryList =
                result.getData().getCurrentCategory().getSubCategoryList();
        typeAdapter.addListClick(new BaseAdapter.IListClick() {
            @Override
            public void itemClick(int pos) {
                Intent intent = new Intent(getActivity(), TypeInfoListActivity.class);
                Myapp.getMap().put("typelist", subCategoryList);

                String name = result.getData().getCurrentCategory().getSubCategoryList().get(pos).getName();
                Myapp.getMap().put("name", name);

                startActivity(intent);
            }
        });
    }

    public void getId(int id) {
        this.id = id;
    }


}
