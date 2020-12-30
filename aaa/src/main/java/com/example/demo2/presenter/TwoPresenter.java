package com.example.demo2.presenter;

import com.example.demo2.base.BasePresenter;
import com.example.demo2.bean.TwoBean;
import com.example.demo2.bean.TwooBean;
import com.example.demo2.interfaces.Callback;
import com.example.demo2.interfaces.IBaseView;
import com.example.demo2.interfaces.demo.ITwo;
import com.example.demo2.model.TwoModel;

public class TwoPresenter extends BasePresenter<ITwo .View> implements ITwo.Presenter{
    ITwo.Model model;
    ITwo.View view;

    public TwoPresenter(ITwo.View view) {
        this.view = view;
        model=new TwoModel();
    }


    @Override
    public void getTwo() {
        if (view!=null){
            model.getTwo(new Callback() {
                @Override
                public void success(Object data) {
                    view.getTwoReturn((TwoBean) data);
                }

                @Override
                public void fail(String err) {
                    view.showToast(err);
                }
            });
        }
    }

    @Override
    public void getTwoo() {
        if (view!=null){
            model.getTwoo(new Callback() {
                @Override
                public void success(Object data) {
                    view.getTwooReturn((TwooBean) data);
                }

                @Override
                public void fail(String err) {
                    view.showToast(err);
                }
            });
        }
    }
}
