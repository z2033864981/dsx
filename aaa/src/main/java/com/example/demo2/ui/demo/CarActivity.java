package com.example.demo2.ui.demo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.demo2.R;
import com.example.demo2.adapter.CategoryBigImageAdapter;
import com.example.demo2.adapter.CategoryButtomInfoAdapter;
import com.example.demo2.adapter.CategoryIssueAdapter;
import com.example.demo2.adapter.CategoryParameterAdapter;
import com.example.demo2.base.BaseActivity;
import com.example.demo2.base.BaseAdapter;
import com.example.demo2.bean.AddCarBean;
import com.example.demo2.bean.CategoryBottomInfoBean;
import com.example.demo2.bean.GoodDetailBean;
import com.example.demo2.interfaces.demo.IShop;
import com.example.demo2.presenter.ShopPresenter;
import com.example.demo2.utils.ItemDecoration;
import com.example.demo2.utils.SpUtils;
import com.example.demo2.utils.ToastUtils;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CarActivity extends BaseActivity<IShop.Presenter> implements IShop.View {
//    @BindView(R.id.webView_category)
//    WebView webView;
    @BindView(R.id.mRlv_category_all)
    RecyclerView mRlv_all;//底部列表数据
    @BindView(R.id.mRlv_category_issue)
    RecyclerView mRlv_issue;//常见问题
    @BindView(R.id.mRlv_category_parameter)
    RecyclerView mRlv_parameter;//商品参数
    @BindView(R.id.banner_category)
    Banner banner;//Banner
    @BindView(R.id.tv_category_info_title)
    TextView tv_title;
    @BindView(R.id.tv_category_info_desc)
    TextView tv_desc;
    @BindView(R.id.tv_category_info_price)
    TextView tv_price;

    @BindView(R.id.tv_category_info_comment_head_name)
    TextView tv_head_name;
    @BindView(R.id.tv_category_info_comment_head_desc)
    TextView tv_head_desc;
    @BindView(R.id.tv_category_info_comment_head_date)
    TextView tv_head_date;
    @BindView(R.id.iv_category_info_comment_head_img)
    ImageView iv_head_img;
    @BindView(R.id.iv_category_info_comment_img)
    ImageView iv_img;

    @BindView(R.id.mRlv_categroy_bigimage)
    RecyclerView mRlv_bigimage;

    @BindView(R.id.CI_assess)
    ConstraintLayout mCl_assess;
    @BindView(R.id.CI_comment)
    ConstraintLayout mCl_comment;

    @BindView(R.id.tv_category_addCar)
    TextView tv_category_addCar;

    @BindView(R.id.tv_category_number)
    TextView txtNumber;

    public static final int RECOMMEND_CAR = 1000; //打开购物车的指令


    private String h5 = "<html>\n" +
            "            <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no\"/>\n" +
            "            <head>\n" +
            "                <style>\n" +
            "                    p{\n" +
            "                        margin:0px;\n" +
            "                    }\n" +
            "                    img{\n" +
            "                        width:100%;\n" +
            "                        height:auto;\n" +
            "                    }\n" +
            "                </style>\n" +
            "            </head>\n" +
            "            <body>\n" +
            "                word\n" +
            "            </body>\n" +
            "        </html>";


    private ArrayList<CategoryBottomInfoBean.DataBean.GoodsListBean> goodsList;//底部列表集合
    private ArrayList<GoodDetailBean.DataBeanX.IssueBean> issuelist;//常见问题集合
    private ArrayList<GoodDetailBean.DataBeanX.AttributeBean> attributeList;//商品参数集合
    private CategoryButtomInfoAdapter categoryButtomInfoAdapter;
    private CategoryIssueAdapter categoryIssueAdapter;
    private CategoryParameterAdapter categoryParameterAdapter;
    private Intent intent;

    //购物车
    private Button btn_add;
    private Button btn_minus;
    private TextView tv_count;

    private boolean isSelect = false;
    int count;
    private GoodDetailBean.DataBeanX.InfoBean info;
    private PopupWindow popupWindow;

    GoodDetailBean goodDetailBean;



    @Override
    protected int getLayout() {
        return R.layout.activity_car;
    }

    @Override
    protected IShop.Presenter createPrenter() {
        return new ShopPresenter(this);
    }


    @Override
    protected void initView() {
        initViewIssue();//常见问题布局
        initBottomInfo();//底部列表数据
        initViewParameter();//商品参数

        //判断购物车选中状态
        if (isSelect == false) {
            isSelect = true;
        } else {
            isSelect = false;
        }

//        tv_category_addCar.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                //弹出添加购物车
//                initPopu();
//            }
//        });
    }

    @OnClick({R.id.fl_collect, R.id.fl_car, R.id.tv_category_buy, R.id.tv_category_addCar})
    public void onClick(View view) {
        if (!TextUtils.isEmpty(SpUtils.getInstance().getString("token"))) {
            switch (view.getId()) {
                case R.id.fl_collect:

                    break;
                case R.id.fl_car:
                    openGoodCar();
                    break;
                case R.id.tv_category_buy:

                    break;
                case R.id.tv_category_addCar:

                    //TODO 点击加入购物车弹出购物车弹框
                    if(isSelect){ //购物车进行显示隐藏
                        initPopu();//添加时
                    }else {
                        initPopu_ok();//添加成功关闭弹窗
                    }
                    break;
            }
        } else {
            Intent intent = new Intent(CarActivity.this, LoginActivity.class);
            startActivity(intent);
        }
    }

    //TODO 添加成功关闭弹窗
    private void initPopu_ok() {
        popupWindow.dismiss();

        View join_view = LayoutInflater.from(CarActivity.this).inflate(R.layout.layout_shoppingcar_popu_ok, null);
        PopupWindow popupWindow1 = new PopupWindow(join_view, 200, 200);

        WindowManager.LayoutParams attributes = getWindow().getAttributes();
        attributes.alpha = 0.5f;
        getWindow().setAttributes(attributes);

        popupWindow1.showAtLocation(tv_category_addCar, Gravity.CENTER, 0, 0);

        //两秒自动关闭
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        popupWindow1.dismiss();
                        WindowManager.LayoutParams attributes = getWindow().getAttributes();
                        attributes.alpha = 1f;
                        getWindow().setAttributes(attributes);
                    }
                });
            }
        },1000,2000);

        isSelect = true;
    }

    //TODO 购物车弹窗
    private void initPopu() {
        //pop
        View join_view = LayoutInflater.from(CarActivity.this).inflate(R.layout.layout_shoppingcar_popu, null);
        popupWindow = new PopupWindow(join_view, GridLayout.LayoutParams.MATCH_PARENT, 380);

        popupWindow.showAtLocation(tv_category_addCar, Gravity.BOTTOM, 0, 160);

        ImageView image_pop = join_view.findViewById(R.id.iv_image_shoppingcar_pop);
        TextView price_pop = join_view.findViewById(R.id.tv_price_shoppingcar_pop);
        btn_add = join_view.findViewById(R.id.btn_add_shoppingcar_pop);
        btn_minus = join_view.findViewById(R.id.btn_minus_shoppingcar_pop);
        tv_count = join_view.findViewById(R.id.tv_count_shoppingcar_pop);
        TextView tv_back = join_view.findViewById(R.id.tv_return_shopping_pop);

        Glide.with(CarActivity.this).load(info.getList_pic_url()).into(image_pop);
        price_pop.setText("价格:  ￥"+ info.getRetail_price() + "");
        count = 1;

        ClickListener clickListener = new ClickListener();
        btn_add.setOnClickListener(clickListener);
        btn_minus.setOnClickListener(clickListener);


        tv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popupWindow.dismiss();//关闭弹窗
            }
        });
        isSelect=false;

        //添加购物车
        if (count<=0){
            ToastUtils.s(this,getString(R.string.tips_buynumber));
            return;
        }
        if(goodDetailBean.getData().getProductList().size() > 0){
            int goodsId = this.goodDetailBean.getData().getProductList().get(0).getGoods_id();
            int productid = this.goodDetailBean.getData().getProductList().get(0).getId();
            Map<String,String> map = new HashMap<>();
            map.put("goodsId",String.valueOf(goodsId));
            map.put("number",String.valueOf(count));
            map.put("productId",String.valueOf(productid));
            presenter.addGoodCar(map);
        }

    }

    /**
     * 打开购物车
     */
    private void openGoodCar(){
        setResult(RECOMMEND_CAR);
        finish();
    }

    //TODO 点击+ -的时候对里面的数字进行修改
    class ClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.btn_add_shoppingcar_pop:
                    count++;
                    if (count > 0) {
                        tv_count.setText(count + "");
                    }
                    break;
                case R.id.btn_minus_shoppingcar_pop:
                    count--;
                    if (count > 0) {
                        tv_count.setText(count + "");
                    }
                    break;
            }
        }
    }

    //TODO 商品参数布局
    private void initViewParameter() {
        attributeList = new ArrayList<>();
        mRlv_parameter.setLayoutManager(new LinearLayoutManager(this));
        categoryParameterAdapter = new CategoryParameterAdapter(this, attributeList);
        mRlv_parameter.setAdapter(categoryParameterAdapter);
    }

    //TODO 常见问题布局
    private void initBottomInfo() {
        issuelist = new ArrayList<>();
        mRlv_issue.setLayoutManager(new LinearLayoutManager(this));
        categoryIssueAdapter = new CategoryIssueAdapter(this, issuelist);
        mRlv_issue.setAdapter(categoryIssueAdapter);
    }

    //TODO 底部列表布局
    private void initViewIssue() {
        goodsList = new ArrayList<>();
        mRlv_all.setLayoutManager(new GridLayoutManager(this, 2));
        mRlv_all.addItemDecoration(new ItemDecoration(this));
        categoryButtomInfoAdapter = new CategoryButtomInfoAdapter(this, goodsList);
        mRlv_all.setAdapter(categoryButtomInfoAdapter);
    }

    @Override
    protected void initData() {
        intent = getIntent();
        if (intent.hasExtra("goodid")) {
            int id = intent.getIntExtra("goodid", 0);
            if (id > 0) {
                presenter.getGoodDetail(id);
                presenter.getCategoryBottomInfo(id);
            } else {
                showToast("错误的商品id");
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    public void getGoodDetail(GoodDetailBean result) {
        this.goodDetailBean = result;

        if (result != null) {
            //Banner
            initBanner(result.getData());
            //Banner下面的展示数据
            initInfo(result.getData().getInfo());
            //评论
            initComment(result.getData().getComment().getData());
            //常见问题数据
            initIssue(result.getData().getIssue());
            //h5 商品详情
            initGoodDetail(result.getData().getInfo().getGoods_desc());
            //商品参数
            initParameter(result.getData().getAttribute());

            //展示goods_desc
            showImage(result.getData().getInfo().getGoods_desc());
        }
    }

    private void showImage(String goods_desc) {
        ArrayList<String> listUrl = new ArrayList<>();
        String img = "<img[\\s\\S]*?>";
        Pattern pattern = Pattern.compile(img);
        Matcher matcher = pattern.matcher(goods_desc);

        while (matcher.find()) {
            String word = matcher.group();
            int start = word.indexOf("\"") + 1;
            int end = word.indexOf(".jpg");
            if (end > 0) {//如果是jpg格式的就截取jpg
                String url = word.substring(start, end);
                if (url != null) {
                    url = url + ".jpg";
                    listUrl.add(url);
                } else {
                    return;
                }
            } else {
                int end1 = word.indexOf(".png");//如果是png格式的就截取png
                String url = word.substring(start, end1);
                if (url != null) {
                    url = url + ".png";
                    listUrl.add(url);
                } else {
                    return;
                }
            }
        }

        mRlv_bigimage.setLayoutManager(new LinearLayoutManager(this));
        CategoryBigImageAdapter categoryBigImageAdapter = new CategoryBigImageAdapter(this, listUrl);
        mRlv_bigimage.setAdapter(categoryBigImageAdapter);

        //点击条目跳转
        categoryBigImageAdapter.addListClick(new BaseAdapter.IListClick() {
            @Override
            public void itemClick(int pos) {
                Bundle bundle = new Bundle();
                bundle.putStringArrayList("image",listUrl);
                Intent intent = new Intent(CarActivity.this, BigImageActivity.class);
                intent.putExtra("bundle", bundle);
                startActivity(intent);
            }
        });
    }

    //TODO 评论
    private void initComment(GoodDetailBean.DataBeanX.CommentBean.DataBean data) {

        if (data != null) {
            mCl_assess.setVisibility(View.VISIBLE);//进行显示评论
            mCl_comment.setVisibility(View.VISIBLE);//显示商品文字

            //时间
            tv_head_date.setText(data.getAdd_time());
            //名字
            tv_head_name.setText(data.getNickname());
            //评论内容
            tv_head_desc.setText(data.getContent());
            //底部图片
            if (data.getPic_list() != null && data.getPic_list().size() > 0) {
                com.example.demo2.utils.ImageLoader.loadImage(data.getPic_list().get(0).getPic_url(), iv_img);
            } else {
                mCl_comment.setVisibility(View.GONE);//隐藏下面的图片
            }
        } else {
            Log.i("TAG", "该详情没有评论");
        }

    }

    //TODO Banner下面的展示数据
    private void initInfo(GoodDetailBean.DataBeanX.InfoBean info) {
        this.info=info;
        tv_title.setText(info.getName());
        tv_desc.setText(info.getGoods_brief());
        tv_price.setText("￥"+info.getRetail_price());
    }

    //TODO Banner数据
    private void initBanner(GoodDetailBean.DataBeanX data) {
        banner.setImages(data.getGallery()).setImageLoader(new ImageLoader() {
            @Override
            public void displayImage(Context context, Object path, ImageView imageView) {
                GoodDetailBean.DataBeanX.GalleryBean bean = (GoodDetailBean.DataBeanX.GalleryBean) path;
                Glide.with(context).load(bean.getImg_url()).into(imageView);
            }
        }).start();
    }

    //TODO 商品参数数据
    private void initParameter(List<GoodDetailBean.DataBeanX.AttributeBean> attribute) {
        attributeList.addAll(attribute);
        categoryParameterAdapter.notifyDataSetChanged();
    }

    //TODO 常见问题数据
    private void initIssue(List<GoodDetailBean.DataBeanX.IssueBean> issue) {
        issuelist.addAll(issue);
        categoryIssueAdapter.notifyDataSetChanged();
    }

    //TODO h5 商品详情数据
    private void initGoodDetail(String webData) {
        String content = h5.replace("word", webData);
        Log.i("TAG", content);
        //webView.loadDataWithBaseURL("about:blank", content, "text/html", "utf-8", null);
    }

    //TODO 商品 详情购买页 底部数据列表
    @Override
    public void getCategoryBottomInfoReturn(CategoryBottomInfoBean result) {
        List<CategoryBottomInfoBean.DataBean.GoodsListBean> data = result.getData().getGoodsList();
        goodsList.addAll(data);
        categoryButtomInfoAdapter.notifyDataSetChanged();
    }

    //TODO 添加购物车返回
    @Override
    public void addGoodCarReturn(AddCarBean addCarBean) {
        //添加成功以后跟新数量显示
        int number = addCarBean.getData().getCartTotal().getGoodsCount();
        txtNumber.setText(number+"");
        //txtNumber.setVisibility(View.VISIBLE);
    }


}
