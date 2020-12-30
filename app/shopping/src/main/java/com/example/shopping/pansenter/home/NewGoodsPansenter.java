package com.example.shopping.pansenter.home;

import com.example.shopping.base.BasePresenter;
import com.example.shopping.interfaces.Callback;
import com.example.shopping.interfaces.home.IHome;
import com.example.shopping.moudel.home.NewGoodsMoudel;
import com.example.shopping.moudel.bean.GoosHotBean;
import com.example.shopping.moudel.bean.GoosListBean;
import com.example.shopping.ui.home.NewGoodActicity;

import java.util.Map;

public class NewGoodsPansenter extends BasePresenter<IHome.NewGoodView>implements IHome.NewGoodsPansenter {
    IHome.NewGoodView newGoodView;
    private NewGoodsMoudel newGoodsMoudel;

    public NewGoodsPansenter(NewGoodActicity newGoodActicity) {
        newGoodView=newGoodActicity;
        newGoodsMoudel = new NewGoodsMoudel();
    }


    @Override
    public void getGoodsListData(Map map) {
    newGoodsMoudel.getGoodsListData(map, new Callback<GoosListBean>() {
        @Override
        public void success(GoosListBean data) {
            newGoodView.callGoodsListData(data);
        }

        @Override
        public void fail(String err) {

        }
    });
    }

    @Override
    public void getGoodsHotData() {
        newGoodsMoudel.getGoodsHotData(new Callback<GoosHotBean>() {
            @Override
            public void success(GoosHotBean data) {
                newGoodView.callGoosHotData(data);
            }

            @Override
            public void fail(String err) {

            }
        });
    }
}
