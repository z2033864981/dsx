package com.example.shopping.ui.shop;

import android.view.View;
import android.widget.Button;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shopping.R;
import com.example.shopping.adapter.ShopAdapter;
import com.example.shopping.base.BaseFragment;
import com.example.shopping.interfaces.IBasePresenter;
import com.example.shopping.interfaces.shop.IShop;
import com.example.shopping.moudel.bean.ShopBean;
import com.example.shopping.pansenter.shop.ShopPansenter;

import java.util.List;

import butterknife.BindView;

public class ShopFragment extends BaseFragment implements IShop.ShopView, View.OnClickListener {

    ShopPansenter shopPansenter;
    @BindView(R.id.rec)
    RecyclerView rec;
    @BindView(R.id.btn_top)
    Button btnTop;
    @BindView(R.id.btn_bottom)
    Button btnBottom;

    @Override
    protected int getLayout() {
        return R.layout.fragment_dashboard;
    }

    @Override
    protected IBasePresenter createPrenter() {
        return shopPansenter = new ShopPansenter(this);
    }

    @Override
    protected void initView() {
        btnTop.setOnClickListener(this::onClick);
        btnBottom.setOnClickListener(this::onClick);
    }

    int page = 1;

    @Override
    protected void initData() {
        shopPansenter.getShopData(10, page);
    }


    @Override
    public void getShopData(ShopBean shopBean) {
        if (shopBean!=null&&rec!=null){
            List<ShopBean.DataBeanX.DataBean> data = shopBean.getData().getData();
            rec.setLayoutManager(new LinearLayoutManager(getContext()));
            rec.setAdapter(new ShopAdapter(getContext(),data));
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_top:

                    page=1;
                    shopPansenter.getShopData(10,page);

                break;
            case R.id.btn_bottom:

                    page=2;
                    shopPansenter.getShopData(10,page);

                break;
        }
    }
}
