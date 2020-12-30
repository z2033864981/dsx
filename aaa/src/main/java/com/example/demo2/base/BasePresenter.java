package com.example.demo2.base;


import com.example.demo2.interfaces.IBaseModel;
import com.example.demo2.interfaces.IBasePresenter;
import com.example.demo2.interfaces.IBaseView;

import java.lang.ref.WeakReference;

public abstract class BasePresenter<V extends IBaseView> implements IBasePresenter<V> {

    protected V mView;
    //通过弱引用把V层关联
    WeakReference<V> weakReference;

    IBaseModel model;
    //绑定
    @Override
    public void attachView(V view) {
        weakReference=new WeakReference<V>(view);
        mView=weakReference.get();
    }

    //解除绑定
    @Override
    public void unAttachView() {
        if(weakReference!=null){
            weakReference.clear();
        }
        if(mView!=null){
            mView=null;
        }
        if(model!=null){
            model.clear();//清除缓存
        }
    }
}
