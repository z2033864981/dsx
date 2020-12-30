package com.example.shopping.api;

import com.example.shopping.moudel.bean.ShopBean;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ShopApi {
    //https://cdplay.cn/api/topic/list?size=18&page=1
    String URL="https://cdplay.cn/";
    @GET("api/topic/list")
    Flowable<ShopBean>getShop(@Query("size")int size,@Query("page")int page);
}
