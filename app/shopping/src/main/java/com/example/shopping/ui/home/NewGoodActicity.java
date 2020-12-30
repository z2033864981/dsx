package com.example.shopping.ui.home;

import android.annotation.SuppressLint;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.shopping.R;
import com.example.shopping.adapter.NewGoodsAdapter;
import com.example.shopping.base.BaseActivity;
import com.example.shopping.interfaces.home.IHome;
import com.example.shopping.moudel.bean.GoosHotBean;
import com.example.shopping.moudel.bean.GoosListBean;
import com.example.shopping.pansenter.home.NewGoodsPansenter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NewGoodActicity extends BaseActivity<NewGoodsPansenter> implements IHome.NewGoodView, View.OnClickListener {
    @BindView(R.id.ing_new_good)
    ImageView ingNewGood;
    @BindView(R.id.tv)
    TextView tv;
    @BindView(R.id.con)
    ConstraintLayout con;
    @BindView(R.id.tv_newgood_all)
    TextView tvNewgoodAll;
    @BindView(R.id.tv_newgood_price)
    TextView tvNewgoodPrice;
    @BindView(R.id.img_icno)
    ImageView imgIcno;
    @BindView(R.id.tv_newgood_sort)
    TextView tvNewgoodSort;
    @BindView(R.id.li)
    LinearLayout li;
    @BindView(R.id.rec)
    RecyclerView rec;
    NewGoodsPansenter newGoodsPansenter;
    private Map<String, String> map;
    private static final String ASC = "asc";
    private static final String DESC = "desc";
    private static final String DEFAULT = "default";
    private static final String PRICE = "price";
    private static final String CATEGORY = "category";
    private List<GoosListBean.DataBeanX.DataBean> data;
    private NewGoodsAdapter newGoodsAdapter;
    private List<GoosListBean.DataBeanX.FilterCategoryBean> filterCategory;
    private List<GoosListBean.DataBeanX.GoodsListBean> goodsList;
    private PopupWindow popupWindow;

    @Override
    protected int getLayout() {
        return R.layout.activity_newgood;
    }

    @Override
    protected NewGoodsPansenter createPrenter() {
        if (newGoodsPansenter==null){
            newGoodsPansenter=new NewGoodsPansenter(this);
        }
        return newGoodsPansenter;
    }


    @Override
    protected void initView() {
        tvNewgoodAll.setOnClickListener(this);
        tvNewgoodPrice.setOnClickListener(this);
        tvNewgoodSort.setOnClickListener(this);
        setIconType(type);
        data = new ArrayList<>();
        newGoodsAdapter = new NewGoodsAdapter(this, data);
        rec.setLayoutManager(new GridLayoutManager(this,2));
        rec.setAdapter(newGoodsAdapter);
    }
    //https://cdplay.cn/api/goods/list?isNew=1&page=1&size =100x&order=asc&sort=default&categoryId=0
    private int isNew = 1;
    private int page = 1;
    private int size = 100;
    private String order=ASC;
    private String sort=DEFAULT;
    private int categoryId=0;
    private Map<String,String> initMap() {
        map = new HashMap();
        map.put("isNew",String.valueOf(isNew));
        map.put("page",String.valueOf(page));
        map.put("size",String.valueOf(size));
        map.put("order",order);
        map.put("sort",sort);
        map.put("category",String.valueOf(categoryId));
        return map;
    }

    @Override
    protected void initData() {
        newGoodsPansenter.getGoodsHotData();
        newGoodsPansenter.getGoodsListData(initMap());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    int type=0;
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_newgood_all:
                initText(tvNewgoodAll);
                type=0;
                setIconType(type);
                categoryId=1005001;
                sort=DEFAULT;
                order=ASC;
                newGoodsPansenter.getGoodsListData(initMap());
                if (popupWindow!=null){
                    popupWindow.dismiss();
                }
                break;
            case R.id.tv_newgood_price:
                initText(tvNewgoodPrice);
                if (type==1){
                    type=2;
                    setIconType(type);
                    sort=PRICE;
                    order=DESC;
                    newGoodsPansenter.getGoodsListData(initMap());
                }else if (type==0){
                    type=1;
                    setIconType(type);
                    order=ASC;
                    sort=PRICE;
                    newGoodsPansenter.getGoodsListData(initMap());
                }else {
                    type=1;
                    setIconType(type);
                    order=ASC;
                    sort=PRICE;
                    newGoodsPansenter.getGoodsListData(initMap());
                }
                if (popupWindow!=null){
                    popupWindow.dismiss();
                }
                break;
            case R.id.tv_newgood_sort:
                initText(tvNewgoodSort);
                type=0;
                setIconType(type);
                if (data!=null&&data.size()>0){
                    if (count==0){
                        setPopw();
                    }

                }
                break;
        }
    }
    int count=0;
    Boolean b=true;
    private void setPopw() {
        count++;
        Resources res = getResources();
        View inflate = LayoutInflater.from(this).inflate(R.layout.popw, null);
        popupWindow = new PopupWindow(inflate, ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        LinearLayout li1 = inflate.findViewById(R.id.li1);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1.0f);
        inflate.setVisibility(View.VISIBLE);
        ArrayList<Integer> integers = new ArrayList<>();
        integers.clear();
        for (int i = 0; i < filterCategory.size(); i++) {
            View inflate1 = LayoutInflater.from(this).inflate(R.layout.popw_item, null);
            TextView tv = inflate1.findViewById(R.id.item_tv);
            tv.setText(filterCategory.get(i).getName());
            tv.setLayoutParams(layoutParams);
            tv.setGravity(Gravity.CENTER);
            int finalI = i;
            if (!b){
                b=true;
            }
            inflate1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    GoosListBean.DataBeanX.FilterCategoryBean filterCategoryBean = filterCategory.get(finalI);
                    categoryId=filterCategoryBean.getId();
                    HashMap<String, String> stringStringHashMap = new HashMap<>();
                    stringStringHashMap.put("categoryId",filterCategoryBean.getId()+"");
                    stringStringHashMap.put("isNew",1+"");
                    newGoodsPansenter.getGoodsListData(stringStringHashMap);
                    count--;
                    popupWindow.dismiss();
//                    inflate.setVisibility(View.GONE);
                }
            });

//                Drawable drawable = res.getDrawable(R.drawable.stroke_black);
//                tv.setBackground(drawable);
//                tv.setTextColor(Color.BLACK);

            li1.addView(tv);
        }
        popupWindow.showAsDropDown(li,0,10);
    }

    @SuppressLint("ResourceType")
    private void initText(TextView tv) {
        if (tv.equals(tvNewgoodAll)){
            tv.setTextColor(Color.parseColor(getString(R.color.colorRed)));
            tvNewgoodPrice.setTextColor(Color.parseColor(getString(R.color.colorBlack)));
            tvNewgoodSort.setTextColor(Color.parseColor(getString(R.color.colorBlack)));
        }else if (tv.equals(tvNewgoodPrice)){
            tv.setTextColor(Color.parseColor(getString(R.color.colorRed)));
            tvNewgoodAll.setTextColor(Color.parseColor(getString(R.color.colorBlack)));
            tvNewgoodSort.setTextColor(Color.parseColor(getString(R.color.colorBlack)));
        }else {
            tv.setTextColor(Color.parseColor(getString(R.color.colorRed)));
            tvNewgoodAll.setTextColor(Color.parseColor(getString(R.color.colorBlack)));
            tvNewgoodPrice.setTextColor(Color.parseColor(getString(R.color.colorBlack)));
        }

    }

    private void setIconType(int type) {
        switch (type){
            case 0:
                Glide.with(this).load(R.mipmap.aa).into(imgIcno);
                break;
            case 1:
                Glide.with(this).load(R.mipmap.up).into(imgIcno);
                break;
            case 2:
                Glide.with(this).load(R.mipmap.dwon).into(imgIcno);
                break;
        }
    }

    @Override
    public void callGoodsListData(GoosListBean goosListBean) {
        GoosListBean.DataBeanX data = goosListBean.getData();
        filterCategory = goosListBean.getData().getFilterCategory();
        goodsList = goosListBean.getData().getGoodsList();
        this.data.clear();
        List<GoosListBean.DataBeanX.DataBean> data1 = data.getData();
        this.data.addAll(data1);
        newGoodsAdapter.notifyDataSetChanged();
    }

    @Override
    public void callGoosHotData(GoosHotBean goosHotBean) {
        Glide.with(this).load(goosHotBean.getData().getBannerInfo().getImg_url()).into(ingNewGood);
        tv.setText(goosHotBean.getData().getBannerInfo().getName());
    }
}
