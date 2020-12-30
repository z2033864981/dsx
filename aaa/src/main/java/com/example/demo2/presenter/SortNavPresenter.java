package com.example.demo2.presenter;

import com.example.demo2.base.BasePresenter;
import com.example.demo2.bean.SortNavBean;
import com.example.demo2.interfaces.Callback;
import com.example.demo2.interfaces.demo.ISortNav;
import com.example.demo2.model.SortNavModel;

public class SortNavPresenter extends BasePresenter<ISortNav.View> implements ISortNav.Presenter {

   ISortNav.Model model;

    public SortNavPresenter() {
       model = new SortNavModel();
    }

    @Override
    public void getSortNav() {
        model.getSortNav(new Callback() {
            @Override
            public void success(Object data) {
                if (mView!=null){
                    mView.getSortNavReturn((SortNavBean) data);
                }
            }

            @Override
            public void fail(String error) {

            }
        });
    }
}
