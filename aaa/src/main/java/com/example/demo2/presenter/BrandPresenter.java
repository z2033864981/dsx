package com.example.demo2.presenter;

import com.example.demo2.base.BasePresenter;
import com.example.demo2.bean.BrandBean;
import com.example.demo2.bean.BrandDetail1;
import com.example.demo2.bean.BrandDetail2;
import com.example.demo2.bean.TwoBean;
import com.example.demo2.interfaces.Callback;
import com.example.demo2.interfaces.demo.IBrand;
import com.example.demo2.model.BrandModel;

public class BrandPresenter extends BasePresenter<IBrand.View> implements IBrand.Presenter {

    IBrand.View view;
    IBrand.Model model;

    public BrandPresenter(IBrand.View view) {
        this.view = view;
        model=new BrandModel();
    }

    @Override
    public void getBrand() {
        if (view!=null){
            model.getBrand(new Callback() {
                @Override
                public void success(Object data) {
                    view.getBrandReturn((BrandBean) data);
                }

                @Override
                public void fail(String err) {
                    view.showToast(err);
                }
            });

        }
    }

    @Override
    public void getBrandDetail1(int id) {
        if (view!=null){
            model.getBrandDetail1(id,new Callback() {
                @Override
                public void success(Object data) {
                    view.getBrandDetail1Return((BrandDetail1) data);
                }

                @Override
                public void fail(String err) {
                    view.showToast(err);
                }
            });
        }
    }

    @Override
    public void getBrandDetail2(int brandId, String page, String size) {
        if (view!=null){
            model.getBrandDetail2(brandId, page, size, new Callback() {
                @Override
                public void success(Object data) {
                    view.getBrandDetail2Return((BrandDetail2) data);
                }

                @Override
                public void fail(String err) {
                    view.showToast(err);
                }
            });

        }
    }
}
