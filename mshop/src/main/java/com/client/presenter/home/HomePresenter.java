package com.client.presenter.home;

import com.client.base.BasePresenter;
import com.client.interfaces.Callback;
import com.client.interfaces.home.IHome;
import com.client.model.home.HomeBean;
import com.client.model.home.HomeModel;

public class HomePresenter extends BasePresenter<IHome.View> implements IHome.Presenter {

    IHome.Model model;
    public HomePresenter(){
        model = new HomeModel();
    }

    @Override
    public void getHome() {
        model.getHome(new Callback<HomeBean>() {
            @Override
            public void success(HomeBean data) {
                if(mView != null){
                    mView.getHomeReturn(data);
                }
            }

            @Override
            public void fail(String err) {

            }
        });
    }
}
