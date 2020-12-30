package com.client.ui.home;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.client.R;
import com.client.base.BaseActivity;
import com.client.interfaces.IBasePresenter;
import com.client.interfaces.home.IHotGood;
import com.client.model.home.HotGoodListBean;
import com.client.presenter.home.HotGoodPresenter;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

public class HotGoodActivity extends BaseActivity<IHotGood.Presenter> implements IHotGood.View {

    private static final String ASC = "asc";
    private static final String DESC = "desc";
    private static final String DEFAULT = "default";
    private static final String PRICE = "price";
    private static final String CATEGORY = "category";

    @BindView(R.id.img_hotgood)
    ImageView imgHotGood;
    @BindView(R.id.txt_info)
    TextView txtInfo;
    @BindView(R.id.txt_all)
    TextView txtAll;
    @BindView(R.id.layout_price)
    LinearLayout layoutPrice;
    @BindView(R.id.img_arrow_up)
    ImageView imgArrowUp;
    @BindView(R.id.img_arrow_down)
    ImageView imgArrowDown;
    @BindView(R.id.txt_price)
    TextView txtPrice;
    @BindView(R.id.txt_sort)
    TextView txtSort;
    @BindView(R.id.recy_goodList)
    RecyclerView recyHotGood;

    //是否是新品
    private int isNew = 1;
    private int page = 1;
    private int size = 100;
    private String order;
    private String sort;
    private int categoryId;

    @Override
    protected int getLayout() {
        return R.layout.activity_hotgood;
    }

    @Override
    protected IHotGood.Presenter createPrenter() {
        return new HotGoodPresenter();
    }

    @SuppressLint("ResourceType")
    @Override
    protected void initView() {
        order = ASC;
        sort = DEFAULT;
        categoryId = 0;
        layoutPrice.setTag(0);  //
        txtAll.setTextColor(Color.parseColor(HotGoodActivity.this.getString(R.color.red)));
        presenter.getHotGood(getParam());
    }

    @Override
    protected void initData() {

    }

    @SuppressLint("ResourceType")
    @OnClick({R.id.layout_price,R.id.txt_all,R.id.txt_sort})
    public void onClick(View view){

        switch (view.getId()){
            case R.id.layout_price:
                int tag = (int) layoutPrice.getTag();
                if(tag == 0){
                    resetPriceState();
                    priceStateUp();
                    layoutPrice.setTag(1);
                    order = ASC;
                }else if(tag == 1){
                    resetPriceState();
                    priceStateDown();
                    layoutPrice.setTag(0);
                    order = DESC;
                }
                sort = PRICE;
                presenter.getHotGood(getParam());
                break;
            case R.id.txt_all:
                resetPriceState();
                txtAll.setTextColor(Color.parseColor(HotGoodActivity.this.getString(R.color.red)));
                sort = DEFAULT;
                categoryId = 0;
                presenter.getHotGood(getParam());
                break;
            case R.id.txt_sort:
                resetPriceState();
                txtSort.setTextColor(Color.parseColor(HotGoodActivity.this.getString(R.color.red)));
                sort = CATEGORY;
                presenter.getHotGood(getParam());
                break;
        }
    }

    /**
     * 组装当前的接口参数
     * @return
     */
    private HashMap<String,String> getParam(){
        HashMap<String,String> map = new HashMap<>();
        map.put("isNew",String.valueOf(isNew));
        map.put("page",String.valueOf(page));
        map.put("size",String.valueOf(size));
        map.put("order",order);
        map.put("sort",sort);
        map.put("category",String.valueOf(categoryId));
        return map;
    }

    /**
     * 按价格升序排序
     */
    @SuppressLint("ResourceType")
    private void priceStateUp(){
        imgArrowUp.setImageResource(R.mipmap.ic_arrow_up_select);
        imgArrowDown.setImageResource(R.mipmap.ic_arrow_down_normal);
        txtPrice.setTextColor(Color.parseColor(getString(R.color.red)));
    }

    /**
     * 价格的降序排列
     */
    @SuppressLint("ResourceType")
    private void priceStateDown(){
        imgArrowUp.setImageResource(R.mipmap.ic_arrow_up_normal);
        imgArrowDown.setImageResource(R.mipmap.ic_arrow_down_select);
        txtPrice.setTextColor(Color.parseColor(getString(R.color.red)));
    }

    /**
     * 重置条件选择的所有状态
     */
    @SuppressLint("ResourceType")
    private void resetPriceState(){
        imgArrowUp.setImageResource(R.mipmap.ic_arrow_up_normal);
        imgArrowDown.setImageResource(R.mipmap.ic_arrow_down_normal);
        txtPrice.setTextColor(Color.parseColor(getString(R.color.black)));
        txtAll.setTextColor(Color.parseColor(getString(R.color.black)));
        txtSort.setTextColor(Color.parseColor(getString(R.color.black)));
        layoutPrice.setTag(0);
    }

    @Override
    public void getHotGood(HotGoodListBean result) {

    }
}
