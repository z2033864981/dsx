package com.client.api;

import com.client.model.home.CategoryBean;
import com.client.model.home.CategoryGoodBean;
import com.client.model.home.HomeBean;
import com.client.model.home.HotGoodListBean;
import com.client.model.login.LoginBean;
import com.client.model.me.UserInfoBean;
import com.client.model.shop.AddCarBean;
import com.client.model.shop.CarBean;
import com.client.model.shop.DeleteCarBean;
import com.client.model.shop.GoodDetailBean;
import com.client.model.shop.UpdateCarBean;

import org.intellij.lang.annotations.Flow;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Flowable;
import retrofit2.Response;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface ShopApi {

    String BASE_URL = "https://cdplay.cn/";

    @GET("api/index")
    Flowable<HomeBean> getHome();

    @GET("goods/category")
    Flowable<CategoryBean> getCategory(@Query("id") int id);

    @GET("api/goods/list")
    Flowable<CategoryGoodBean> getGoodList(@Query("categoryId") int categoryId, @Query("page") int page, @Query("size") int size);

    //新品发布的条件筛选数据接口
    @GET("api/goods/list")
    Flowable<HotGoodListBean> getHotGoodList(@QueryMap HashMap<String,String> map);

    //商品详情购买页
    @GET("api/goods/detail")
    Flowable<GoodDetailBean> getGoodDetail(@Query("id") int id);


    @POST("api/auth/login")
    @FormUrlEncoded
    Flowable<LoginBean> login(@Field("username") String username,@Field("password") String pw);


    //添加到购物车
    @POST("api/cart/add")
    @FormUrlEncoded
    Flowable<AddCarBean> addCar(@FieldMap Map<String,String> map);

    //更新购物车的数据
    @POST("api/cart/update")
    @FormUrlEncoded
    Flowable<UpdateCarBean> updateCar(@FieldMap Map<String,String> map);

    //删除购物车数据
    @POST("api/cart/delete")
    @FormUrlEncoded
    Flowable<DeleteCarBean> deleteCar(@Field("productIds") String productIds);

    //购物车列表
    @GET("api/cart/index")
    Flowable<CarBean> getCarList();

    //用户信息更新
    @POST("api/user/updateUserInfo")
    Flowable<UserInfoBean> updateUserInfo(@FieldMap Map<String,String> map);


}
