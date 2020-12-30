package com.example.shopping.pansenter.home;

import com.example.shopping.base.BasePresenter;
import com.example.shopping.interfaces.Callback;
import com.example.shopping.interfaces.good.IGood;
import com.example.shopping.moudel.bean.AddCarBean;
import com.example.shopping.moudel.bean.CategoryBottomInfoBean;
import com.example.shopping.moudel.home.GoodMoudel;
import com.example.shopping.moudel.bean.GoodDetailBean;
import com.example.shopping.ui.home.GoodDetailActivity;

import java.util.Map;

public class GoodPansenter extends BasePresenter<IGood.View> implements IGood.Presenter {
    IGood.View view;
    IGood.Model model;
    public GoodPansenter(GoodDetailActivity goodDetailActivity) {
        view=goodDetailActivity;
        model=new GoodMoudel();
    }

    @Override
    public void getGoodDetail(int id) {
        model.getGoodDetail(id, new Callback() {
            @Override
            public void success(Object data) {
                view.getGoodDetail((GoodDetailBean) data);
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
