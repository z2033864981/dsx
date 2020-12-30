package com.example.demo2.app;

import android.app.Application;
import android.content.Context;

import java.util.HashMap;

public class Myapp extends Application {
    public static Myapp app;
    public static HashMap<String,Object> map;
    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
        map=new HashMap<>();
    }

    public static Myapp getInstance() {
        return app;
    }

    private static Context mAppContext = null;

    public static HashMap<String, Object> getMap() {
        return map;
    }


}
