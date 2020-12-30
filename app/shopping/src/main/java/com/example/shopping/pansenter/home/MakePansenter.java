package com.example.shopping.pansenter.home;

import com.example.shopping.base.BasePresenter;
import com.example.shopping.interfaces.Callback;
import com.example.shopping.interfaces.home.IHome;
import com.example.shopping.moudel.home.MakeMoudel;
import com.example.shopping.moudel.bean.MakeBean;
import com.example.shopping.ui.home.MakeActivity;

public class MakePansenter extends BasePresenter<IHome.MakeView>implements IHome.MakeParsenter {
    IHome.MakeView makeView;
    private final IHome.MakeMoudel makeMoudel;

    public MakePansenter(MakeActivity makeActivity) {
        makeView=makeActivity;
        makeMoudel = new MakeMoudel();
    }

    @Override
    public void getMakeData() {
        makeMoudel.getMakeData(new Callback<MakeBean>() {
            @Override
            public void success(MakeBean data) {
                makeView.callMakeData(data);
            }

            @Override
            public void fail(String err) {

            }
        });
    }

}
