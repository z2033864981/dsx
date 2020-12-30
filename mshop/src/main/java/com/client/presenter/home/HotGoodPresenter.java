package com.client.presenter.home;

import com.client.base.BasePresenter;
import com.client.interfaces.Callback;
import com.client.interfaces.home.IHotGood;
import com.client.model.home.HomeBean;
import com.client.model.home.HotGoodListBean;
import com.client.model.home.HotGoodModel;

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
}
