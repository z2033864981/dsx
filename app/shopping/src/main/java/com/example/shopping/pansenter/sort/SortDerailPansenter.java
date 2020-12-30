package com.example.shopping.pansenter.sort;

import com.example.shopping.base.BasePresenter;
import com.example.shopping.interfaces.Callback;
import com.example.shopping.interfaces.sort.ISort;
import com.example.shopping.moudel.bean.SortTabDescBean;
import com.example.shopping.moudel.sort.SortDetailMoudel;
import com.example.shopping.ui.sort.SortDerailChannelFragment;
import com.example.shopping.ui.sort.SortDetailActivity;

public class SortDerailPansenter extends BasePresenter<ISort.SortDetailView>implements ISort.SortDetailPansenter {
    ISort.SortDetailView sortDetailView;
    ISort.SortDetailMoudel sortDetailMoudel;
    public SortDerailPansenter(SortDetailActivity sortDetailActivity) {
        sortDetailView=sortDetailActivity;
        sortDetailMoudel=new SortDetailMoudel();
    }

    public SortDerailPansenter(SortDerailChannelFragment sortDerailChannelFragment) {
        sortDetailView=sortDerailChannelFragment;
        sortDetailMoudel=new SortDetailMoudel();
    }

    @Override
    public void getSortData(int id) {
        sortDetailMoudel.getSrt(id, new Callback<SortTabDescBean>() {
            @Override
            public void success(SortTabDescBean data) {
                sortDetailView.getSortData(data);
            }

            @Override
            public void fail(String err) {
            }
        });
    }

    @Override
    public void getSortPansenterData(int id) {

    }
}
