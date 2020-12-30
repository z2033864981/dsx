package com.example.shopping.ui.me;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.shopping.R;
import com.example.shopping.base.BaseFragment;
import com.example.shopping.interfaces.IBasePresenter;
import com.example.shopping.utils.BitmapUtils;
import com.example.shopping.utils.GlideEngine;
import com.example.shopping.utils.SpUtils;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;

import java.util.List;

import butterknife.BindView;

public class MeFragment extends BaseFragment implements View.OnClickListener{


    @BindView(R.id.iv_five_yh)
    ImageView ivFiveYh;
    @BindView(R.id.tv_five_name)
    TextView tvFiveName;
    @BindView(R.id.iv_five_jt)
    ImageView ivFiveJt;
    @BindView(R.id.ll_five_dingdan)
    LinearLayout llFiveDingdan;
    @BindView(R.id.ll_five_youhuiquan)
    LinearLayout llFiveYouhuiquan;
    @BindView(R.id.ll_five_lipinka)
    LinearLayout llFiveLipinka;
    @BindView(R.id.ll_five_shoucang)
    LinearLayout llFiveShoucang;
    @BindView(R.id.ll_five_zuji)
    LinearLayout llFiveZuji;
    @BindView(R.id.ll_five_fuli)
    LinearLayout llFiveFuli;
    @BindView(R.id.ll_five_dizhi)
    LinearLayout llFiveDizhi;
    @BindView(R.id.ll_five_zhanghao)
    LinearLayout llFiveZhanghao;
    @BindView(R.id.ll_five_lianxi)
    LinearLayout llFiveLianxi;
    @BindView(R.id.ll_five_bangzhu)
    LinearLayout llFiveBangzhu;
    @BindView(R.id.ll_five_fankui)
    LinearLayout llFiveFankui;

    @Override
    protected int getLayout() {
        return R.layout.fragment_me;
    }

    @Override
    protected IBasePresenter createPrenter() {
        return null;
    }

    @Override
    protected void initView() {
        String img = SpUtils.getInstance().getString("img");
        if (!img.isEmpty()){
            Glide.with(getActivity()).load(img).into(ivFiveYh);
        }
        ivFiveYh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), HeaderActivity.class);
                startActivityForResult(intent,100);
            }
        });
    }



    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==100){
            String img = SpUtils.getInstance().getString("img");
            if (!img.isEmpty()){
                Glide.with(getActivity()).load(img).into(ivFiveYh);
            }
        }
    }

    private void openPhoto() {
        PictureSelector.create(getActivity())
                .openGallery(PictureMimeType.ofImage())
                .loadImageEngine(GlideEngine.createGlideEngine())
                .maxSelectNum(1)
                .imageSpanCount(4)
                .selectionMode(PictureConfig.MULTIPLE)
                .forResult(PictureConfig.CHOOSE_REQUEST);
    }
    /*private void openPhoto(){
    PictureSelector.create(this)
            .openGallery(PictureMimeType.ofImage())
            .loadImageEngine(GlideEngine.createGlideEngine()) // Please refer to the Demo GlideEngine.java
            .maxSelectNum(1)
            .imageSpanCount(4)
            .selectionMode(PictureConfig.MULTIPLE)
            .forResult(PictureConfig.CHOOSE_REQUEST);
}*/

/*@Override
public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
    super.onActivityResult(requestCode, resultCode, data);
    switch (requestCode) {
        case PictureConfig.CHOOSE_REQUEST:
            // onResult Callback
            List<LocalMedia> selectList = PictureSelector.obtainMultipleResult(data);
            if (selectList.size() == 0) return;
            //获取本地图片的选择地址，上传到服务器
            //头像的压缩和二次采样
            //把选中的图片插入到列表
            for(LocalMedia item:selectList){
                ivFiveYh.post(new Runnable() {
                    @Override
                    public void run() {
                        ivFiveYh.setScaleType(ImageView.ScaleType.CENTER_CROP);
                        String path = item.getPath();
                        BitmapUtils.getBitmap(path, 200, 200);
                        RequestOptions requestOptions = new RequestOptions();
                        requestOptions.circleCrop();
                        Glide.with(getContext()).load(path).apply(requestOptions).into(ivFiveYh);
//                        uploadHead(path);
                    }
                });
            }
            break;
        default:
            break;
    }
}*/
    @Override
    protected void initData() {

    }

    @Override
    public void onClick(View v) {
/*        switch (v.getId()){
            case R.id.iv_five_yh:
//                openPhoto();
                Intent intent = new Intent(getContext(), HeaderActivity.class);
                break;
        }*/
    }


}