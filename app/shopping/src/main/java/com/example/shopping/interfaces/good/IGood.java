package com.example.shopping.interfaces.good;

import com.example.shopping.interfaces.Callback;
import com.example.shopping.interfaces.IBaseModel;
import com.example.shopping.interfaces.IBasePresenter;
import com.example.shopping.interfaces.IBaseView;
import com.example.shopping.moudel.bean.AddCarBean;
import com.example.shopping.moudel.bean.CategoryBottomInfoBean;
import com.example.shopping.moudel.bean.GoodDetailBean;

import java.util.Map;

public interface IGood {
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
