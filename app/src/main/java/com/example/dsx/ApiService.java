package com.example.dsx;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface ApiService {
    String URL="http://cdwan.cn:7000/tongpao/";
    @GET("list.json")
    Observable<Bean>getData();
}
