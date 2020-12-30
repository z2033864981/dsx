package com.example.demo2.interfaces.demo;


import com.example.demo2.bean.AddCarBean;
import com.example.demo2.bean.CategoryBottomInfoBean;
import com.example.demo2.bean.GoodDetailBean;
import com.example.demo2.interfaces.Callback;
import com.example.demo2.interfaces.IBaseModel;
import com.example.demo2.interfaces.IBasePresenter;
import com.example.demo2.interfaces.IBaseView;

import java.util.Map;

public interface
IShop {
    interface View extends IBaseView {
        void getGoodDetail(GoodDetailBean goodDetailBean);
        void getCategoryBottomInfoReturn(CategoryBottomInfoBean result);
        void addGoodCarReturn(AddCarBean addCarBean);
    }

    interface Presenter extends IBasePresenter<View> {
        void getGoodDetail(int id);
        void getCategoryBottomInfo(int id);
        //添加进购物车
        void addGoodCar(Map<String,String> map);
    }


    interface Model extends IBaseModel {
        void getGoodDetail(int id, Callback callback);
        void getCategoryBottomInfo(int id,Callback callback);
        //添加进购物车
        void addGoodCar(Map<String,String> map,Callback callback);
    }
}
