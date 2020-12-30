package com.example.demo2.ui.demo;

import android.annotation.SuppressLint;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.bumptech.glide.Glide;
import com.example.demo2.R;
import com.example.demo2.adapter.HotGoodAdapter;
import com.example.demo2.base.BaseActivity;
import com.example.demo2.bean.HotGoodListBean;
import com.example.demo2.bean.NewGoodsBean;
import com.example.demo2.interfaces.demo.IHotGood;
import com.example.demo2.presenter.HotGoodPresenter;

import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
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
    RecyclerView recyGoodList;
//    @BindView(R.id.recy_hotgood)
//    RecyclerView recyHotGood;

    //是否是新品
    private int isNew = 1;
    private int page = 1;
    private int size = 1000;
    private String order;
    private String sort;
    private int categoryId;
    private HotGoodAdapter hotGoodAdapter;
    private PopupWindow popupWindow;
    private List<HotGoodListBean.DataBeanX.FilterCategoryBean> filterCategory;

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

    }

    @Override
    protected void initData() {
        presenter.getHotGood(getParam());
        presenter.getNewGood();
    }

    @SuppressLint("ResourceType")
    @OnClick({R.id.layout_price, R.id.txt_all, R.id.txt_sort})
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.layout_price:
                int tag = (int) layoutPrice.getTag();
                if (tag == 0) {
                    resetPriceState();
                    priceStateUp();
                    layoutPrice.setTag(1);
                    order = ASC;
                } else if (tag == 1) {
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
//                presenter.getHotGood(getParam());
                if (filterCategory!=null){
                    setPopw();
                }

                break;
        }
    }

    private void setPopw() {
        Resources resources = getResources();
        //PopWindow条目布局
        View inflate = LayoutInflater.from(this).inflate(R.layout.popw, null);
        popupWindow = new PopupWindow(inflate, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        //获取条目id
        LinearLayout li1 = inflate.findViewById(R.id.li1);
        //设置条目边框宽高
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1.0f);
        //循环放入条目
        for (int i = 0; i < filterCategory.size(); i++) {
            View inflate1 = LayoutInflater.from(this).inflate(R.layout.popw_item, null);
            TextView tv = inflate1.findViewById(R.id.item_tv);
            tv.setText(filterCategory.get(i).getName());
            tv.setLayoutParams(layoutParams);
            //文字设为居中
            tv.setGravity(Gravity.CENTER);
            int finalI = i;
            tv.setTag(i);

            inflate1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    HotGoodListBean.DataBeanX.FilterCategoryBean filterCategoryBean = filterCategory.get(finalI);
                    categoryId=filterCategoryBean.getId();
                    HashMap<String, String> stringStringHashMap = new HashMap<>();
                    stringStringHashMap.put("categoryId",filterCategoryBean.getId()+"");
                    stringStringHashMap.put("isNew",1+"");
                    presenter.getHotGood(stringStringHashMap);
                    popupWindow.dismiss();
                }
            });
            //给边框设置颜色
            Drawable drawable = resources.getDrawable(R.drawable.stroke_black);
            tv.setBackground(drawable);
            //添加view的Textview的条目
            li1.addView(tv);
        }
        //绑定popwindow
        popupWindow.showAsDropDown(layoutPrice,0,10);
    }

    /**
     * 组装当前的接口参数
     *
     * @return
     */
    private HashMap<String, String> getParam() {
        HashMap<String, String> map = new HashMap<>();
        map.put("isNew", String.valueOf(isNew));
        map.put("page", String.valueOf(page));
        map.put("size", String.valueOf(size));
        map.put("order", order);
        map.put("sort", sort);
        map.put("category", String.valueOf(categoryId));
        return map;
    }

    /**
     * 按价格升序排序
     */
    @SuppressLint("ResourceType")
    private void priceStateUp() {
        imgArrowUp.setImageResource(R.mipmap.ic_arrow_up_select);
        imgArrowDown.setImageResource(R.mipmap.ic_arrow_down_normal);
        txtPrice.setTextColor(Color.parseColor(getString(R.color.red)));
    }

    /**
     * 价格的降序排列
     */
    @SuppressLint("ResourceType")
    private void priceStateDown() {
        imgArrowUp.setImageResource(R.mipmap.ic_arrow_up_normal);
        imgArrowDown.setImageResource(R.mipmap.ic_arrow_down_select);
        txtPrice.setTextColor(Color.parseColor(getString(R.color.red)));
    }

    /**
     * 重置条件选择的所有状态
     */
    @SuppressLint("ResourceType")
    private void resetPriceState() {
        imgArrowUp.setImageResource(R.mipmap.ic_arrow_up_normal);
        imgArrowDown.setImageResource(R.mipmap.ic_arrow_down_normal);
        txtPrice.setTextColor(Color.parseColor(getString(R.color.black)));
        txtAll.setTextColor(Color.parseColor(getString(R.color.black)));
        txtSort.setTextColor(Color.parseColor(getString(R.color.black)));
        layoutPrice.setTag(0);
    }

    @Override
    public void getHotGood(HotGoodListBean result) {
        filterCategory = result.getData().getFilterCategory();

        recyGoodList.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
        hotGoodAdapter = new HotGoodAdapter(this,result.getData().getData());
        recyGoodList.setAdapter(hotGoodAdapter);

    }

    @Override
    public void getNewGood(NewGoodsBean result) {
        Glide.with(this).load(result.getData().getBannerInfo().getImg_url()).into(imgHotGood);
        txtInfo.setText(result.getData().getBannerInfo().getName());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
