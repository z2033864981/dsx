package com.example.shopping.app;

import android.app.Application;

import com.example.shopping.utils.SpUtils;

public class MyApp extends Application  {
    public static MyApp app;
    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
        SpUtils.getInstance().setValue("image",true);
    }

}
