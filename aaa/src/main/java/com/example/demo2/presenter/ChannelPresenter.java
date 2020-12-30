package com.example.demo2.presenter;

import com.example.demo2.base.BasePresenter;
import com.example.demo2.bean.ChannelBean;
import com.example.demo2.bean.ChannelTypeBean;
import com.example.demo2.interfaces.Callback;
import com.example.demo2.interfaces.demo.IChannel;
import com.example.demo2.model.ChannelModel;

public class ChannelPresenter extends BasePresenter<IChannel.View> implements IChannel.Presenter {
    IChannel.View view;
    IChannel.BaseModel model;

    public ChannelPresenter(IChannel.View view) {
        this.view = view;
        this.model=new ChannelModel();
    }

    @Override
    public void getChannel(String id) {
        if(view!=null){
            this.model.getChannel(id,new Callback() {
                @Override
                public void fail(String msg) {
                    view.showToast(msg);
                }

                @Override
                public void success(Object o) {
                    view.getChannelReturn((ChannelBean) o);
                }
            });
        }
    }

    @Override
    public void getChannelType(String id) {
        if(view!=null){
            this.model.getChannelType(id, new Callback() {
                @Override
                public void fail(String msg) {
                    view.showToast(msg);
                }

                @Override
                public void success(Object o) {
                    view.getChannelTypeReturn((ChannelTypeBean) o);
                }
            });
        }
    }
}
