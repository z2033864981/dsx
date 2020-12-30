package com.example.demo2.ui.demo;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;


import com.example.demo2.R;
import com.example.demo2.adapter.BigImageAdapter;
import com.example.demo2.utils.TxtUtils;

import java.util.ArrayList;
import java.util.List;

public class BigImageActivity extends AppCompatActivity {


    private TextView tv_count;
    private TextView tv_return;
    private ViewPager mVp;
    private BigImageAdapter adapter;

    private int currentPos;//当前操作的图片的位置
    ArrayList<String> list ;//当前需要查看的所有图片的路径


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);
        initView();
        initData();
    }


    private void initView() {
        tv_count = (TextView) findViewById(R.id.tv_big_image_count);
        tv_return = (TextView) findViewById(R.id.tv_big_image_return);
        mVp = (ViewPager) findViewById(R.id.mVp_big_image);

        mVp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {//停止时
                currentPos = position;
                updatePage();
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        mVp.setCurrentItem(currentPos);

        tv_return.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                finishAndRemoveTask();//关闭当前页面返回之前页面
            }
        });
    }

    private void updatePage() {//更换下标
       //TxtUtils.setTextView(tv_count,(currentPos+1)+"/"+list.size());
        String page = String.valueOf(currentPos+1);
        TxtUtils.setTextView(tv_count,page);
    }

    private void initData() {//获取H5的图片
        Intent intent = getIntent();
        //bundle存放图片数据  bundle存放图片数据和当前操作下标
        if (intent != null && intent.hasExtra("bundle")) {
            Bundle bundle = intent.getBundleExtra("bundle");
            if (bundle != null) {
                list = bundle.getStringArrayList("image");
                currentPos = bundle.getInt("postion");//存放此下标
                updatePage();//调用修改Page页的方法
            }
        }

        BigImageAdapter adapter = new BigImageAdapter( this,list);
        mVp.setAdapter(adapter);//绑定适配器
        mVp.setCurrentItem(currentPos);//通过下标来改变集合里面的ViewPager的页面


    }
}