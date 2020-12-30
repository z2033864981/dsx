package com.example.demo2.api;

import com.example.demo2.bean.AddCarBean;
import com.example.demo2.bean.BrandBean;
import com.example.demo2.bean.BrandDetail1;
import com.example.demo2.bean.BrandDetail2;
import com.example.demo2.bean.CarBean;
import com.example.demo2.bean.CategoryBottomInfoBean;
import com.example.demo2.bean.ChannelBean;
import com.example.demo2.bean.ChannelTypeBean;
import com.example.demo2.bean.DeleteCarBean;
import com.example.demo2.bean.GoodDetailBean;
import com.example.demo2.bean.HotGoodListBean;
import com.example.demo2.bean.LoginBean;
import com.example.demo2.bean.NewGoodsBean;
import com.example.demo2.bean.OneBean;
import com.example.demo2.bean.RegisterBean;
import com.example.demo2.bean.SortDataBean;
import com.example.demo2.bean.SortNavBean;
import com.example.demo2.bean.TwoBean;
import com.example.demo2.bean.TwooBean;
import com.example.demo2.bean.UpdateCarBean;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Flowable;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface ServiceApi {
    String BASE_URL = "https://cdplay.cn/";

    //首页接口
    @GET("api/index")
    Flowable<OneBean> getList();

    //动态栏详情
    @GET("api/catalog/index/")
    Flowable<ChannelBean> getChannel(@Query("id") String url);//这个分类

    //分类数据
    @GET("api/goods/list")
    Flowable<ChannelTypeBean> getChannelType(@Query("categoryId") String id);

    //专题页面
    @GET("api/topic/list")
    Flowable<TwoBean> getTwo();

    //专题第二页
    @GET("api/topic/list?page=2")
    Flowable<TwooBean> getTwoo();

    //品牌制造商页面
    @GET("api/brand/list?page=1&size=1000")
    Flowable<BrandBean> getBrand();

    //品牌制造商详情
    @GET("api/brand/detail")
    Flowable<BrandDetail1> getBrandDetail1(@Query("id")int id);

    //品牌制造商详情
    @GET("api/goods/list")
    Flowable<BrandDetail2> getBrandDetail2(@Query("brandId")int brandId,@Query("page")String page,@Query("size")String size);

    //新品发布的条件筛选数据接口
    @GET("api/goods/list")
    Flowable<HotGoodListBean> getHotGoodList(@QueryMap HashMap<String,String> map);

    //新品首发
    @GET("api/goods/hot")
    Flowable<NewGoodsBean> getNewGoods();

    //商品详情购买页
    @GET("api/goods/detail")
    Flowable<GoodDetailBean> getGoodDetail(@Query("id") int id);

    //商品 详情购买页 底部数据列表 api/goods/related?id=1155000
    @GET("api/goods/related")
    Flowable<CategoryBottomInfoBean> getCategoryBottomInfo(@Query("id")int id);

    //https://cdplay.cn/api/catalog/index 分类竖着导航
    @GET("api/catalog/index")
    Flowable<SortNavBean> getSortNav();

    // https://cdplay.cn/api/  用来请求当前分类的列表数据
    @GET("catalog/current")
    Flowable<SortDataBean> getSortData(@Query("id") int id);

    //用户登录
    @POST("api/auth/login")
    @FormUrlEncoded
    Flowable<LoginBean> login(@Field("username") String username, @Field("password") String pw);

    //添加到购物车
    @POST("api/cart/add")
    @FormUrlEncoded
    Flowable<LoginBean> addCar(@FieldMap HashMap<String,String> map);

    //注册
    //用户注册
    @POST("api/auth/register")
    @FormUrlEncoded
    Flowable<RegisterBean> getreister(@Field("username") String username, @Field("password") String pw);

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


}
