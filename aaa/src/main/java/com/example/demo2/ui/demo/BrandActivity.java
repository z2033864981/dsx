package com.example.demo2.ui.demo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.demo2.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BrandActivity extends AppCompatActivity {

    @BindView(R.id.iv_brand_pic)
    ImageView ivBrandPic;
    @BindView(R.id.tv_brand_name)
    TextView tvBrandName;
    @BindView(R.id.tv_brand_simple)
    TextView tvBrandSimple;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_brand);
        ButterKnife.bind(this);

        initView();
    }

    private void initView() {
        Intent intent = getIntent();

        String name = intent.getStringExtra("name");
        String simple = intent.getStringExtra("simple");
        String pic = intent.getStringExtra("pic");

        tvBrandName.setText(name);
        tvBrandSimple.setText(simple);
        Glide.with(this).load(pic).into(ivBrandPic);

    }
}