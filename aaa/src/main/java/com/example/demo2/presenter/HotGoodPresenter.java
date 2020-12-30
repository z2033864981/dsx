package com.example.demo2.presenter;


import com.example.demo2.base.BasePresenter;
import com.example.demo2.bean.HotGoodListBean;
import com.example.demo2.bean.NewGoodsBean;
import com.example.demo2.interfaces.Callback;
import com.example.demo2.interfaces.demo.IHotGood;
import com.example.demo2.model.HotGoodModel;

import java.util.HashMap;

public class HotGoodPresenter extends BasePresenter<IHotGood.View> implements IHotGood.Presenter {
    IHotGood.Model model;
    public HotGoodPresenter(){
        model = new HotGoodModel();
    }

    @Override
    public void getHotGood(HashMap<String,String> map) {
        model.getHotGood(map,new Callback<HotGoodListBean>() {
            @Override
            public void success(HotGoodListBean data) {
                if(mView != null){
                    mView.getHotGood(data);
                }
            }

            @Override
            public void fail(String err) {

            }
        });
    }

    @Override
    public void getNewGood() {
            model.getNewGood(new Callback<NewGoodsBean>() {
                @Override
                public void success(NewGoodsBean data) {
                    mView.getNewGood(data);
                }

                @Override
                public void fail(String err) {
                    mView.showToast(err);
                }
            });

    }
}
