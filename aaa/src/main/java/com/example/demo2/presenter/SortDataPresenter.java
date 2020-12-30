package com.example.demo2.presenter;

import com.example.demo2.base.BasePresenter;
import com.example.demo2.bean.SortDataBean;
import com.example.demo2.interfaces.Callback;
import com.example.demo2.interfaces.demo.ISortData;
import com.example.demo2.model.SortDataModel;

public class SortDataPresenter extends BasePresenter<ISortData.View> implements ISortData.Presenter {

    ISortData.Model model;

    public SortDataPresenter() {
        model = new SortDataModel();
    }

    @Override
    public void getSortData(int id) {
        model.getSortData(id, new Callback() {
            @Override
            public void success(Object data) {
                if (mView!=null){
                    mView.getSortDataReturn((SortDataBean) data);
                }
            }

            @Override
            public void fail(String error) {

            }
        });
    }
}
