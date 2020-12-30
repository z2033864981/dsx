package com.client.ui.test;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.client.MainActivity;
import com.client.R;
import com.client.threads.MyThread;

public class DemoActivity extends AppCompatActivity implements View.OnClickListener {
    Button btnInit,btnSend,btnTest;

    MyThread myThread1; //线程1

    private Handler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo);

        btnInit = findViewById(R.id.btn_createThread);
        btnSend = findViewById(R.id.btn_send);
        btnTest = findViewById(R.id.btn_test);
        btnInit.setOnClickListener(this);
        btnSend.setOnClickListener(this);
        btnTest.setOnClickListener(this);

        handler = new Handler(){
            @Override
            public void handleMessage(@NonNull Message msg) {
                super.handleMessage(msg);
                long _id = Thread.currentThread().getId();
                String _name = Thread.currentThread().getName();
                Log.i("TAG","id:"+_id+" name:"+_name);

            }
        };
    }

    private void initThread(){
        myThread1 = new MyThread();
        myThread1.start();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_createThread:
                initThread();
                break;
            case R.id.btn_send:
                send();
                break;
            case R.id.btn_test:
                Intent intent = new Intent(DemoActivity.this, TestActivity.class);
                startActivity(intent);
                break;
        }
    }

    private void send(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                long id = Thread.currentThread().getId();
                String name = Thread.currentThread().getName();
                Log.i("TAG","id:"+id+" name:"+name);
                Message msg = new Message();
                msg.what = MyThread.MSG_1;
                Handler handler = myThread1.getHandler();
                handler.sendMessage(msg);
            }
        }).start();

    }

    private void initHandlerThread(){
        HandlerThread handlerThread = new HandlerThread("1");
    }

}