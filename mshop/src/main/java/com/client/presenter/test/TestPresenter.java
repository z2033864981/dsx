package com.client.presenter.test;

import android.telecom.Call;

import com.client.base.BasePresenter;
import com.client.interfaces.Callback;
import com.client.interfaces.test.ITest;
import com.client.model.test.TestBean;
import com.client.model.test.TestModel;

public class TestPresenter extends BasePresenter<ITest.View> implements ITest.Presenter {

    ITest.Model model;

    public TestPresenter(){
        model = new TestModel();
    }

    @Override
    public void getList() {
        if(mView != null){
            model.getList(new Callback<TestBean>() {
                @Override
                public void success(TestBean data) {
                    mView.getListReturn(data);
                }

                @Override
                public void fail(String err) {
                    mView.showToast(err);
                }
            });
        }
    }
}
