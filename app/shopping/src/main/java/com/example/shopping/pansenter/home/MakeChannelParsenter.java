package com.example.shopping.pansenter.home;

import com.example.shopping.base.BasePresenter;
import com.example.shopping.interfaces.Callback;
import com.example.shopping.interfaces.home.IHome;
import com.example.shopping.moudel.home.MakeChannelMoudel;
import com.example.shopping.moudel.bean.GoosListBean;
import com.example.shopping.moudel.bean.MakeChannelBean;
import com.example.shopping.ui.home.MakeChannelActivity;

import java.util.Map;

public class MakeChannelParsenter extends BasePresenter<IHome.MakeChannelView> implements IHome.MakeChannelParsenter {
    IHome.MakeChannelView makeChannelView;
    IHome.MakeChannelMoudel makeChannelMoudel;
    public MakeChannelParsenter(MakeChannelActivity makeChannelActivity) {
        makeChannelView=makeChannelActivity;
        makeChannelMoudel=new MakeChannelMoudel();
    }

    @Override
    public void getMakeChannelData(Map map) {
        makeChannelMoudel.getMakeChannelData(map, new Callback<GoosListBean>() {
            @Override
            public void success(GoosListBean data) {
                makeChannelView.callMakeChannel(data);
            }

            @Override
            public void fail(String err) {

            }
        });
    }

    @Override
    public void getMakeChannelData(String id) {
        makeChannelMoudel.getMakeChannelData(id,new Callback<MakeChannelBean>() {
            @Override
            public void success(MakeChannelBean data) {
                makeChannelView.callMakeChannelData(data);
            }

            @Override
            public void fail(String err) {

            }
        });
    }


}
