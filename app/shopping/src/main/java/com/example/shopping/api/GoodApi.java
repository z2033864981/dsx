package com.example.shopping.api;

import com.example.shopping.moudel.bean.AddCarBean;
import com.example.shopping.moudel.bean.CategoryBottomInfoBean;
import com.example.shopping.moudel.bean.GoodDetailBean;
import com.example.shopping.moudel.bean.LoginBean;

import java.util.Map;

import io.reactivex.Flowable;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface GoodApi {
    //https://cdplay.cn/api/goods/detail?id=1155000
    static String URL="https://cdplay.cn/";
    @GET("api/goods/detail")
    Flowable<GoodDetailBean> getGoodData(@Query("id") int id);
    @POST("api/auth/login")
    @FormUrlEncoded
    Flowable<LoginBean>getLogin(@Field("username")String name,@Field("password")String pas);
    //添加到购物车
    @POST("api/cart/add")
    @FormUrlEncoded
    Flowable<AddCarBean> addCar(@FieldMap Map<String,String> map);
    //商品 详情购买页 底部数据列表 api/goods/related?id=1155000
    @GET("api/goods/related")
    Flowable<CategoryBottomInfoBean> getCategoryBottomInfo(@Query("id")int id);
}
