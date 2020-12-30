package com.example.demo2.presenter;


import com.example.demo2.base.BasePresenter;
import com.example.demo2.bean.AddCarBean;
import com.example.demo2.bean.CategoryBottomInfoBean;
import com.example.demo2.bean.GoodDetailBean;
import com.example.demo2.interfaces.Callback;
import com.example.demo2.interfaces.demo.IShop;
import com.example.demo2.model.ShopModel;

import java.util.Map;

public class ShopPresenter extends BasePresenter<IShop.View> implements IShop.Presenter {
    IShop.Model model;
    IShop.View view;
    public ShopPresenter(IShop.View view){
        this.view=view;
        model = new ShopModel();
    }
    @Override
    public void getGoodDetail(int id) {
        model.getGoodDetail(id,new Callback<GoodDetailBean>() {
            @Override
            public void success(GoodDetailBean data) {
                if(view != null){
                    view.getGoodDetail(data);
                }
            }

            @Override
            public void fail(String err) {

            }
        });
    }

    @Override
    public void getCategoryBottomInfo(int id) {
        if(view!=null){
            model.getCategoryBottomInfo(id, new Callback() {
                @Override
                public void fail(String msg) {
                    view.showToast(msg);
                }

                @Override
                public void success(Object o) {
                    view.getCategoryBottomInfoReturn((CategoryBottomInfoBean) o);
                }
            });
        }
    }

    // 添加到购物车
    @Override
    public void addGoodCar(Map<String, String> map) {
        model.addGoodCar(map, new Callback<AddCarBean>() {
            @Override
            public void success(AddCarBean data) {
                if(mView != null){
                    mView.addGoodCarReturn(data);
                }
            }

            @Override
            public void fail(String err) {

            }
        });
    }
}
