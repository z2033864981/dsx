package com.client.threads;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

import androidx.annotation.NonNull;

import java.io.IOException;

public class MyThread extends Thread {

    public static final int MSG_1 = 1; //消息1
    public static final int MSG_2 = 2; //消息2


    private static String TAG = MyThread.class.getSimpleName();
    private long id;
    private String name;

    private Looper looper;
    private Handler handler;

    public MyThread(){
        id = Thread.currentThread().getId();
        name = Thread.currentThread().getName();
        Log.i(TAG,"init thread id:"+id+" name"+name);
    }

    public Handler getHandler(){
        return handler;
    }

    /**
     * 子线程的执行逻辑
     */
    @Override
    public void run() {
        super.run();
        //读取当前线程的id和name
        id = Thread.currentThread().getId();
        name = Thread.currentThread().getName();
        Log.i(TAG,"init thread id:"+id+" name"+name);
        Looper.prepare();
        looper = Looper.myLooper();

        Log.i(TAG,id+"-"+name);
        handler = new Handler(looper){
            /**
             * 子线程的消息处理
             * @param msg
             */
            @Override
            public void handleMessage(@NonNull Message msg) {
                super.handleMessage(msg);
                switch (msg.what){
                    case MSG_1:
                        Log.i(TAG,"msg1 id:"+id+" name:"+name);
                        break;
                    case MSG_2:
                        Log.i(TAG,"msg2 id:"+id+" name:"+name);
                        break;
                }
            }
        };
        Looper.loop();


    }
}
