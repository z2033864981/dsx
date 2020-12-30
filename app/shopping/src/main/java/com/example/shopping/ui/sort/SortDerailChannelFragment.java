package com.example.shopping.ui.sort;

import com.example.shopping.R;
import com.example.shopping.base.BaseFragment;
import com.example.shopping.interfaces.IBasePresenter;
import com.example.shopping.interfaces.sort.ISort;
import com.example.shopping.moudel.bean.SortBean;
import com.example.shopping.moudel.bean.SortTabDescBean;
import com.example.shopping.pansenter.sort.SortDerailPansenter;

import java.util.List;

public class SortDerailChannelFragment extends BaseFragment implements ISort.SortDetailView {
    SortDerailPansenter sortDerailPansenter;
    private int id;

    public SortDerailChannelFragment(int id) {

        this.id = id;
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_blank;
    }

    @Override
    protected IBasePresenter createPrenter() {
        sortDerailPansenter=new SortDerailPansenter(this);
        return sortDerailPansenter;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        sortDerailPansenter.getSortData(id);
    }

    @Override
    public void getSortData(SortTabDescBean sortTabDescBean) {

    }

    @Override
    public void getSortPansenterData(SortBean sortBean) {
        List<SortBean.DataBeanX.DataBean> data = sortBean.getData().getData();

    }
}
