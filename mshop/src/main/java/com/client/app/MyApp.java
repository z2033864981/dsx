package com.client.app;

import android.app.Application;

import com.client.utils.SpUtils;

public class MyApp extends Application {
    public static MyApp app;
    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
        SpUtils.getInstance().setValue("image",true);
    }
}
