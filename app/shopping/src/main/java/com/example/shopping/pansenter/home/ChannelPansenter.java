package com.example.shopping.pansenter.home;

import com.example.shopping.base.BasePresenter;
import com.example.shopping.interfaces.Callback;
import com.example.shopping.interfaces.home.IHome;
import com.example.shopping.moudel.bean.ChannelBean;
import com.example.shopping.moudel.bean.ChannelDescBean;
import com.example.shopping.moudel.home.ChannelMoudel;
import com.example.shopping.ui.home.ChannelActivity;
import com.example.shopping.ui.home.ChannelFragment;

public class ChannelPansenter extends BasePresenter<IHome.ChannelView> implements IHome.ChannelParsenter {
    IHome.ChannelView channelView;
    ChannelMoudel channelMoudel;
    private ChannelFragment channelFragment;

    public ChannelPansenter(ChannelActivity channelActivity) {
        channelView=channelActivity;
        channelMoudel=new ChannelMoudel();
    }

    public ChannelPansenter(ChannelFragment channelFragment) {
        this.channelFragment = channelFragment;
        channelMoudel=new ChannelMoudel();
    }

    @Override
    public void getChannelData(String id) {
        channelMoudel.getChannelData(new Callback<ChannelBean>() {
            @Override
            public void success(ChannelBean data) {
                if (channelView!=null){
                    channelView.callChannelView(data);
                }
                if (channelFragment!=null){
                    channelFragment.callChannelView(data);
                }
            }
            @Override
            public void fail(String err) {

            }
        },id);
    }

    @Override
    public void getChannelDescBean(String id) {
        channelMoudel.getChannelDescData(id, new Callback<ChannelDescBean>() {
            @Override
            public void success(ChannelDescBean data) {
                if (channelView!=null){
                    channelView.callChannelDesc(data);
                }
                if (channelFragment!=null){
                    channelFragment.callChannelDesc(data);
                }
            }

            @Override
            public void fail(String err) {

            }
        });
    }
}
