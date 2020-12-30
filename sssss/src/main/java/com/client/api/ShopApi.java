package com.client.api;

import com.client.model.home.HomeBean;

import io.reactivex.Flowable;
import retrofit2.http.GET;

public interface ShopApi {

    String BASE_URL = "http://cdplay.cn/";

    @GET("api/index")
    Flowable<HomeBean> getHome();
}
