package com.example.demo2.presenter;

import com.example.demo2.base.BasePresenter;
import com.example.demo2.bean.OneBean;
import com.example.demo2.interfaces.Callback;
import com.example.demo2.interfaces.demo.ITest;
import com.example.demo2.model.TestModel;


public class TestPresenter extends BasePresenter<ITest.View> implements ITest.Presenter {

    ITest.Model model;
    ITest.View view;

    public TestPresenter(ITest.View mView){
        this.view=mView;
        model = new TestModel();
    }

    @Override
    public void getList() {
        if(view != null){
           model.getList(new Callback() {
               @Override
               public void success(Object data) {
                   view.getListReturn((OneBean) data);
                }

               @Override
               public void fail(String err) {
                   view.showToast(err);
               }
           });
        }
    }
}
