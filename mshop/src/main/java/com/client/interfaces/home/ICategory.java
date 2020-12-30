package com.client.interfaces.home;

import com.client.interfaces.Callback;
import com.client.interfaces.IBaseModel;
import com.client.interfaces.IBasePresenter;
import com.client.interfaces.IBaseView;
import com.client.model.home.CategoryBean;
import com.client.model.home.CategoryGoodBean;

public interface ICategory {

    interface View extends IBaseView{
        void getCategoryTabReturn(CategoryBean result);

        void getCategoryGoodReturn(CategoryGoodBean result);
    }

    interface Presenter extends IBasePresenter<View>{
        void getCategoryTab(int categoryId);

        void getCategoryGood(int categoryId,int page,int size);
    }

    interface Model extends IBaseModel{
        void getCategory(int categoryid,Callback callback);

        void getCategoryGood(int categoryId,int page,int size,Callback callback);
    }

}
