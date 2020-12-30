package com.example.shopping.pansenter.car;

import android.util.Log;

import com.example.shopping.api.ICar;
import com.example.shopping.base.BasePresenter;
import com.example.shopping.interfaces.Callback;
import com.example.shopping.moudel.bean.CarBean;
import com.example.shopping.moudel.bean.DeleteCarBean;
import com.example.shopping.moudel.bean.UpdateCarBean;
import com.example.shopping.moudel.car.CarMoudel;

import java.util.Map;

public class CarPansenter extends BasePresenter<ICar.CarView> implements ICar.CarPabsenter {
    ICar.CarMoudel carMoudel;
    public CarPansenter(){
        carMoudel = new CarMoudel();
    }
    @Override
    public void getCars() {
        carMoudel.getCars(new Callback<CarBean>() {
            @Override
            public void success(CarBean data) {
                if (mView!=null){
                    mView.getCars(data);
                }

            }

            @Override
            public void fail(String err) {

            }
        });
    }

    //更新购物车
    @Override
    public void updateCar(Map<String, String> map) {
        carMoudel.updateCar(map,new Callback<UpdateCarBean>() {
            @Override
            public void success(UpdateCarBean data) {
                if(mView != null){
                    mView.updateCarReturn(data);
                }
            }

            @Override
            public void fail(String err) {

            }
        });
    }

    // 删除购物车列表
    @Override
    public void deleteCar(String pIds) {
        carMoudel.deleteCar(pIds,new Callback<DeleteCarBean>() {
            @Override
            public void success(DeleteCarBean data) {
                if(mView != null){
                    Log.i("TAG","CarPresenter delete return:");
                    mView.deleteCarReturn(data);
                }
            }

            @Override
            public void fail(String err) {

            }
        });
    }
}
