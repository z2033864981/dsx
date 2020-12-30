package com.example.shopping.api;


import com.example.shopping.moudel.bean.BanBean;
import com.example.shopping.moudel.bean.ChannelBean;
import com.example.shopping.moudel.bean.ChannelDescBean;
import com.example.shopping.moudel.bean.GoosHotBean;
import com.example.shopping.moudel.bean.GoosListBean;
import com.example.shopping.moudel.bean.MakeBean;
import com.example.shopping.moudel.bean.MakeChannelBean;

import java.util.Map;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface ServiceApi {
    String BANURL="https://cdplay.cn/";
    @GET("api/index")
    Flowable<BanBean> getBanData();
    //https://cdplay.cn/
    @GET("goods/category?id=1005000")
    Flowable<ChannelBean>getChannelBean();
    //https://cdplay.cn/api/goods/list?categoryId=1005000&page=1&size=200x
    @GET("api/goods/list")
    Flowable<ChannelDescBean>getChannelDesc(@Query("categoryId")String id,@Query("page")int page,@Query("size")String size);
    //https://cdplay.cn/api/brand/list?page=1&size=1000
    @GET("api/brand/list?page=1&size=1000")
    Flowable<MakeBean>getMakeData();
    //新品首页图片
    //https://cdplay.cn/api/goods/hot
    @GET("api/goods/hot")
    Flowable<GoosHotBean>getGoosHotData();
    //新品商品
    //https://cdplay.cn/api/goods/list?isNew=1&page=1&size =1000&order=desc&sort=price&categoryId=0
    @GET("api/goods/list")
    Flowable<GoosListBean>getGoosList(@QueryMap Map<String,String> map);
    @GET("api/brand/detail")
    Flowable<MakeChannelBean>getMakeChannelData(@Query("id")String id);
}
