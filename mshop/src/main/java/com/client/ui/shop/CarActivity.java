package com.client.ui.shop;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.client.R;
import com.client.base.BaseActivity;
import com.client.interfaces.IBasePresenter;
import com.client.interfaces.shop.IShop;
import com.client.model.shop.AddCarBean;
import com.client.model.shop.GoodDetailBean;
import com.client.presenter.shop.ShopPresenter;
import com.client.ui.login.LoginActivity;
import com.client.utils.SpUtils;
import com.luck.picture.lib.tools.ToastUtils;

import org.intellij.lang.annotations.RegExp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.OnClick;

public class CarActivity extends BaseActivity<IShop.Presenter> implements IShop.View {

    public static final int RECOMMEND_CAR = 1000; //打开购物车的指令


    @BindView(R.id.webView)
    WebView webView;
    @BindView(R.id.txt_addCar)
    TextView txtAddCar;
    @BindView(R.id.txt_number)
    TextView txtNumber;

    GoodDetailBean goodDetailBean;
    int buyNumber=1; //购买的数量


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

    @Override
    protected int getLayout() {
        return R.layout.activity_car;
    }

    @Override
    protected IShop.Presenter createPrenter() {
        return new ShopPresenter();
    }

    @Override
    protected void initView() {

    }

    @OnClick({R.id.layout_collect,R.id.layout_car,R.id.txt_buy,R.id.txt_addCar})
    public void onClick(View view){
        if(!TextUtils.isEmpty(SpUtils.getInstance().getString("token"))){
            switch (view.getId()){
                case R.id.layout_collect:
                    break;
                case R.id.layout_car:
                    openGoodCar();
                    break;
                case R.id.txt_buy:

                    break;
                case R.id.txt_addCar:
                    addCar();
                    break;
            }
        }else{
            Intent intent = new Intent(CarActivity.this, LoginActivity.class);
            startActivity(intent);
        }
    }

    @Override
    protected void initData() {
        Intent intent = getIntent();
        if(intent.hasExtra("goodid")){
            int id = getIntent().getIntExtra("goodid",0);
            if(id > 0){
                presenter.getGoodDetail(id);
            }else{
                showToast(getString(R.string.tips_error_goodid));
            }
        }


    }

    /**
     * 添加进购物车
     */
    private void addCar(){
        if(buyNumber <= 0){
            ToastUtils.s(this,getString(R.string.tips_buynumber));
            return;
        }
        if(goodDetailBean.getData().getProductList().size() > 0){
            int goodsId = this.goodDetailBean.getData().getProductList().get(0).getGoods_id();
            int productid = this.goodDetailBean.getData().getProductList().get(0).getId();
            Map<String,String> map = new HashMap<>();
            map.put("goodsId",String.valueOf(goodsId));
            map.put("number",String.valueOf(buyNumber));
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

    @Override
    public void getGoodDetail(GoodDetailBean goodDetailBean) {
        this.goodDetailBean = goodDetailBean;
        //h5 商品详情
        initGoodDetail(goodDetailBean.getData().getInfo().getGoods_desc());

        if(goodDetailBean.getData().getProductList().size() > 0){
            int num = goodDetailBean.getData().getProductList().get(0).getGoods_number();
            txtNumber.setText(String.valueOf(num));
            txtNumber.setVisibility(View.VISIBLE);
        }
    }

    //添加购物车返回
    @Override
    public void addGoodCarReturn(AddCarBean addCarBean) {
        //添加成功以后跟新数量显示
        int number = addCarBean.getData().getCartTotal().getGoodsCount();
        txtNumber.setText(String.valueOf(number));
        txtNumber.setVisibility(View.VISIBLE);
    }

    /**
     * 商品详情数据  h5
     * @param webData
     */
    private void initGoodDetail(String webData){
        getHtmlImgs(webData);
        String content = h5.replace("word",webData);
        Log.i("TAG",content);
        webView.loadDataWithBaseURL("about:blank", content, "text/html", "utf-8", null);
    }

    private void getHtmlImgs(String content){
        String img = "<img[\\s\\S]*?>";
        Pattern pattern = Pattern.compile(img);
        Matcher matcher = pattern.matcher(content);
        List<String> list = new ArrayList<>();
        while(matcher.find()){
            String word = matcher.group();
            String fileType = ".jpg";
            int start = word.indexOf("\"")+1;
            int end = word.indexOf(".jpg");
            if(end == -1){
                end = word.indexOf(".png");
                fileType = ".png";
            }
            if(end == -1) continue;
            String url = word.substring(start,end);
            url = url + fileType;
            list.add(url);
        }

    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("CarActivity","onCreate");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i("CarActivity","onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("CarActivity","onResume");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i("CarActivity","onRestart");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("CarActivity","onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("CarActivity","onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("CarActivity","onDestroy");
    }
}
