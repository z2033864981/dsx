package com.example.shopping.api;

import com.example.shopping.interfaces.Callback;
import com.example.shopping.interfaces.IBaseModel;
import com.example.shopping.interfaces.IBasePresenter;
import com.example.shopping.interfaces.IBaseView;
import com.example.shopping.moudel.bean.CarBean;
import com.example.shopping.moudel.bean.DeleteCarBean;
import com.example.shopping.moudel.bean.UpdateCarBean;

import java.util.Map;

public interface ICar {
    interface CarView extends IBaseView{
        void getCars(CarBean carBean);//更新 购物车
        void updateCarReturn(UpdateCarBean result);

        //删除购物车
        void deleteCarReturn(DeleteCarBean result);
    }
    interface CarPabsenter extends IBasePresenter<ICar.CarView>{
        void getCars();
        //更新购物车的数据
        void  updateCar(Map<String,String> map);

        //删除购物车列表
        void deleteCar(String pIds);
    }
    interface CarMoudel extends IBaseModel{
        void getCars(Callback<CarBean>carBeanCallback);

        void updateCar(Map<String,String> map,Callback callback);

        void deleteCar(String pIds,Callback callback);
    }
}
