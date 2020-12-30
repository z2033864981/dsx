package com.example.shopping.interfaces.home;

import com.example.shopping.interfaces.Callback;
import com.example.shopping.interfaces.IBaseModel;
import com.example.shopping.interfaces.IBasePresenter;
import com.example.shopping.interfaces.IBaseView;
import com.example.shopping.moudel.bean.BanBean;
import com.example.shopping.moudel.bean.ChannelBean;
import com.example.shopping.moudel.bean.ChannelDescBean;
import com.example.shopping.moudel.bean.GoosHotBean;
import com.example.shopping.moudel.bean.GoosListBean;
import com.example.shopping.moudel.bean.MakeBean;
import com.example.shopping.moudel.bean.MakeChannelBean;

import java.util.Map;

public interface IHome {
    interface BanView extends IBaseView {
        void callBanData(BanBean banBean);
    }
    interface  ChannelView extends IBaseView{
       void callChannelDesc(ChannelDescBean channelDescBean);
        void callChannelView(ChannelBean channelBean);
    }
    interface MakeView extends IBaseView{
        void callMakeData(MakeBean makeBean);
    }
     interface NewGoodView extends IBaseView{
        void callGoodsListData(GoosListBean goosListBean);
        void callGoosHotData(GoosHotBean goosHotBean);
    }
    interface MakeChannelView extends IBaseView{
        void callMakeChannel(GoosListBean makeChannelBean);
        void callMakeChannelData(MakeChannelBean makeChannelBean);
    }



    interface NewGoodsPansenter extends IBasePresenter<NewGoodView>{
        void getGoodsListData(Map map);
        void getGoodsHotData();
    }
    interface BanParsenter extends IBasePresenter<BanView>{
        void getBanData();
    }
    interface ChannelParsenter extends IBasePresenter<ChannelView>{
        void getChannelData(String id);
        void getChannelDescBean(String id);
    }
    interface MakeParsenter extends IBasePresenter<MakeView>{
        void getMakeData();
    }
    interface MakeChannelParsenter extends IBasePresenter<MakeChannelView>{
        void getMakeChannelData(Map map);
        void getMakeChannelData(String id);
    }

    interface NewGoodsMoudel extends IBaseModel{
        void getGoodsListData(Map map,Callback<GoosListBean> callback);
        void getGoodsHotData(Callback<GoosHotBean>callback);
    }
    interface BanMoudel extends IBaseModel{
        void getBanData(Callback<BanBean> callback);
        void getChannelData(Callback<ChannelBean> callback,String id);
        void getChannelDescData(String id,Callback<ChannelDescBean>callback);
    }
    interface MakeMoudel extends IBaseModel{
        void getMakeData(Callback<MakeBean> callback);
    }
    interface MakeChannelMoudel extends IBaseModel{
        void getMakeChannelData(Map map,Callback<GoosListBean> channelBeanCallback);
        void getMakeChannelData(String id,Callback<MakeChannelBean>channelBeanCallback);
    }

}
