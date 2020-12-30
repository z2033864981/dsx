package com.example.demo2.base;

import android.os.Bundle;
import android.os.Looper;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.demo2.interfaces.IBasePresenter;
import com.example.demo2.interfaces.IBaseView;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseActivity<P extends IBasePresenter> extends AppCompatActivity implements IBaseView {


    protected P presenter;
    Unbinder unbinder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int layout = getLayout();
        if(layout <= 0){
            new RuntimeException("layout id not allow 0 or <0");
        }else{
            setContentView(getLayout());
        }
        unbinder = ButterKnife.bind(this);
        presenter = createPrenter();
        if(presenter != null){
            presenter.attachView(this);
        }
        initView();
        initData();
    }

    protected abstract int getLayout();
    protected abstract P createPrenter();
    protected abstract void initView();
    protected abstract  void initData();

    @Override
    public void showLoading(int visible) {

    }

    @Override
    public void showToast(String tips) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(presenter != null){
            presenter.unAttachView();
        }
        if(unbinder != null){
            unbinder.unbind();
        }
    }
}
