package com.example.shopping.interfaces.sort;

import com.example.shopping.interfaces.Callback;
import com.example.shopping.interfaces.IBaseModel;
import com.example.shopping.interfaces.IBasePresenter;
import com.example.shopping.interfaces.IBaseView;
import com.example.shopping.moudel.bean.SortBean;
import com.example.shopping.moudel.bean.SortDescBean;
import com.example.shopping.moudel.bean.SortTabBean;
import com.example.shopping.moudel.bean.SortTabDescBean;

public interface ISort {
    interface SortView extends IBaseView{
       void getTabData(SortTabBean sortTabBean);
       void getSortDescData(SortDescBean sortDescBean);
    }
    interface SortDetailView extends IBaseView{
        void getSortData(SortTabDescBean sortTabDescBean);
        void getSortPansenterData(SortBean sortBean);
    }
    interface SortPansenter extends IBasePresenter<SortView>{
        void getTabData();
        void getSortDescData(int id);
    }
    interface SortDetailPansenter extends IBasePresenter<SortDetailView>{
        void getSortData(int id);
        void getSortPansenterData(int id);
    }
    interface SortMoudel extends IBaseModel {
        void getTabData(Callback<SortTabBean> callback);
        void getSortDescData(int id,Callback<SortDescBean>callback);
    }
    interface SortDetailMoudel extends IBaseModel{
        void getSrt(int id,Callback<SortTabDescBean>callback);
        void getSortPansenterData(int id,Callback<SortBean>callback);
    }
}
