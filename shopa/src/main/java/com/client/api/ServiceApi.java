package com.client.api;

import com.client.model.test.TestBean;

import io.reactivex.Flowable;
import retrofit2.http.GET;

public interface ServiceApi {
    String BASE_URL = "http://cdwan.cn:7000/";

    @GET("tongpao/list.json")
    Flowable<TestBean> getList();

}
