package com.example.demo2.interfaces.demo;

import com.example.demo2.bean.CarBean;
import com.example.demo2.bean.DeleteCarBean;
import com.example.demo2.bean.UpdateCarBean;
import com.example.demo2.interfaces.Callback;
import com.example.demo2.interfaces.IBaseModel;
import com.example.demo2.interfaces.IBasePresenter;
import com.example.demo2.interfaces.IBaseView;

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
