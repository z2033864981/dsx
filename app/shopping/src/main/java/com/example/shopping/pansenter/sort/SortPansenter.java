package com.example.shopping.pansenter.sort;

import com.example.shopping.base.BasePresenter;
import com.example.shopping.interfaces.Callback;
import com.example.shopping.interfaces.sort.ISort;
import com.example.shopping.moudel.bean.SortDescBean;
import com.example.shopping.moudel.bean.SortTabBean;
import com.example.shopping.moudel.sort.SortMoudel;
import com.example.shopping.ui.sort.SortChannlFragment;
import com.example.shopping.ui.sort.SortFragment;

public class SortPansenter extends BasePresenter<ISort.SortView> implements ISort.SortPansenter {
    ISort.SortView sortView;
    ISort.SortMoudel sortMoudel;
    public SortPansenter(SortFragment sortFragment) {
        sortView=sortFragment;
        sortMoudel=new SortMoudel();
    }

    public SortPansenter(SortChannlFragment sortChannlFragment) {
        sortView=sortChannlFragment;
        sortMoudel=new SortMoudel();
    }

    @Override
    public void getTabData() {
        sortMoudel.getTabData(new Callback<SortTabBean>() {
            @Override
            public void success(SortTabBean data) {
                sortView.getTabData(data);
            }

            @Override
            public void fail(String err) {

            }
        });
    }

    @Override
    public void getSortDescData(int id) {
        sortMoudel.getSortDescData(id, new Callback<SortDescBean>() {
            @Override
            public void success(SortDescBean data) {
                sortView.getSortDescData(data);
            }

            @Override
            public void fail(String err) {

            }
        });
    }
}
