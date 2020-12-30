package com.client.presenter.shop;

import android.util.Log;

import com.client.base.BasePresenter;
import com.client.interfaces.Callback;
import com.client.interfaces.shop.ICar;
import com.client.interfaces.shop.IShop;
import com.client.model.shop.CarBean;
import com.client.model.shop.CarModel;
import com.client.model.shop.DeleteCarBean;
import com.client.model.shop.GoodDetailBean;
import com.client.model.shop.ShopModel;
import com.client.model.shop.UpdateCarBean;

import java.util.Map;

public class CarPresenter extends BasePresenter<ICar.View> implements ICar.Presenter {

    ICar.Model model;
    public CarPresenter(){
        model = new CarModel();
    }
    @Override
    public void getCarList() {
        model.getCarList(new Callback<CarBean>() {
            @Override
            public void success(CarBean data) {
                if(mView != null){
                    mView.getCarListReturn(data);
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
        model.updateCar(map,new Callback<UpdateCarBean>() {
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
        model.deleteCar(pIds,new Callback<DeleteCarBean>() {
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
