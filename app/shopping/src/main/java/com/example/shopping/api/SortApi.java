package com.example.shopping.api;

import com.example.shopping.moudel.bean.SortBean;
import com.example.shopping.moudel.bean.SortDescBean;
import com.example.shopping.moudel.bean.SortTabBean;
import com.example.shopping.moudel.bean.SortTabDescBean;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface SortApi {
    String URl="https://cdplay.cn/";
    @GET("api/catalog/index")
    Flowable<SortTabBean>getSortTabData();
    //api/catalog/current?id=1005001
    @GET("api/catalog/current")
    Flowable<SortDescBean>getSortDescData(@Query("id")int id);
    @GET("goods/category")
    Flowable<SortTabDescBean>getTabBean(@Query("id")int id);
    @GET("api/goods/list?categoryId=1008002&page=1&size=100")
    Flowable<SortBean>getSortBean(@Query("id")int id,@Query("page")int page,@Query("size")int size);
}
