package com.example.shopping.moudel.home;

import com.example.shopping.base.BaseModel;
import com.example.shopping.interfaces.Callback;
import com.example.shopping.interfaces.home.IHome;
import com.example.shopping.moudel.bean.GoosListBean;
import com.example.shopping.moudel.bean.MakeChannelBean;
import com.example.shopping.net.CommonSubscriber;
import com.example.shopping.net.HttpManager;
import com.example.shopping.utils.RxUtils;

import java.util.Map;

public class MakeChannelMoudel extends BaseModel implements IHome.MakeChannelMoudel {
    @Override
    public void getMakeChannelData(Map map, Callback<GoosListBean> channelBeanCallback) {
        HttpManager.getInstance()
                .getService()
                .getGoosList(map)
                .compose(RxUtils.rxScheduler())
                .subscribe(new CommonSubscriber<GoosListBean>(channelBeanCallback) {
                    @Override
                    public void onNext(GoosListBean o) {
                        channelBeanCallback.success(o);
                    }

                    @Override
                    public void onError(Throwable t) {
                        super.onError(t);
                        String s = t.toString();
                    }
                });
    }

    @Override
    public void getMakeChannelData(String id,Callback<MakeChannelBean> channelBeanCallback) {
        HttpManager.getInstance()
                .getService()
                .getMakeChannelData(id)
                .compose(RxUtils.rxScheduler())
                .subscribe(new CommonSubscriber<MakeChannelBean>(channelBeanCallback) {
                    @Override
                    public void onNext(MakeChannelBean makeChannelBean) {
                        channelBeanCallback.success(makeChannelBean);
                    }

                    @Override
                    public void onError(Throwable t) {
                        super.onError(t);
                    }
                });
    }

}
