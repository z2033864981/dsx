package com.client.interfaces.shop;

import com.client.interfaces.Callback;
import com.client.interfaces.IBaseModel;
import com.client.interfaces.IBasePresenter;
import com.client.interfaces.IBaseView;
import com.client.model.shop.AddCarBean;
import com.client.model.shop.GoodDetailBean;

import java.util.Map;

public interface IShop {
    interface View extends IBaseView {
        //获取商品详情数据
        void getGoodDetail(GoodDetailBean goodDetailBean);

        void addGoodCarReturn(AddCarBean addCarBean);
    }

    interface Presenter extends IBasePresenter<View> {
        void getGoodDetail(int id);

        //添加进购物车
        void addGoodCar(Map<String,String> map);
    }


    interface Model extends IBaseModel {

        void getGoodDetail(int id,Callback callback);

        //添加进购物车
        void addGoodCar(Map<String,String> map,Callback callback);
    }
}
