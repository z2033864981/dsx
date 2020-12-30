package com.client.interfaces.shop;

import com.client.interfaces.Callback;
import com.client.interfaces.IBaseModel;
import com.client.interfaces.IBasePresenter;
import com.client.interfaces.IBaseView;
import com.client.model.shop.CarBean;
import com.client.model.shop.DeleteCarBean;
import com.client.model.shop.GoodDetailBean;
import com.client.model.shop.UpdateCarBean;

import java.util.Map;

public interface ICar {
    interface View extends IBaseView {
        void getCarListReturn(CarBean carBean);

        //更新 购物车
        void updateCarReturn(UpdateCarBean result);

        //删除购物车
        void deleteCarReturn(DeleteCarBean result);
    }

    interface Presenter extends IBasePresenter<View> {
        void getCarList();
        //更新购物车的数据
        void  updateCar(Map<String,String> map);

        //删除购物车列表
        void deleteCar(String pIds);
    }


    interface Model extends IBaseModel {
        void getCarList(Callback callback);

        void updateCar(Map<String,String> map,Callback callback);

        void deleteCar(String pIds,Callback callback);
    }

}
