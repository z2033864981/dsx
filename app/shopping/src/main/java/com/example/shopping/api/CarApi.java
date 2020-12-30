package com.example.shopping.api;

import com.example.shopping.moudel.bean.CarBean;
import com.example.shopping.moudel.bean.DeleteCarBean;
import com.example.shopping.moudel.bean.UpdateCarBean;

import java.util.Map;

import io.reactivex.Flowable;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface CarApi {
    String URL="https://cdplay.cn/";
    @GET("api/cart/index")
    Flowable<CarBean>getCars();
    //更新购物车的数据
    @POST("api/cart/update")
    @FormUrlEncoded
    Flowable<UpdateCarBean> updateCar(@FieldMap Map<String,String> map);

    //删除购物车数据
    @POST("api/cart/delete")
    @FormUrlEncoded
    Flowable<DeleteCarBean> deleteCar(@Field("productIds") String productIds);

}
