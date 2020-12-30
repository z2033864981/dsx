package com.client;

import android.content.Intent;

import com.client.base.BaseActivity;
import com.client.interfaces.IBasePresenter;

public class SplaceActivity extends BaseActivity {
    @Override
    protected int getLayout() {
        return R.layout.activity_splace;
    }

    @Override
    protected IBasePresenter createPrenter() {
        return null;
    }

    @Override
    protected void initView() {

        Intent intent = new Intent(SplaceActivity.this,MainActivity.class);
        startActivity(intent);

    }

    @Override
    protected void initData() {

    }
}
