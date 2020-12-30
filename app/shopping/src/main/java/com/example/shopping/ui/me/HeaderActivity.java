package com.example.shopping.ui.me;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.alibaba.sdk.android.oss.ClientConfiguration;
import com.alibaba.sdk.android.oss.ClientException;
import com.alibaba.sdk.android.oss.OSSClient;
import com.alibaba.sdk.android.oss.ServiceException;
import com.alibaba.sdk.android.oss.callback.OSSCompletedCallback;
import com.alibaba.sdk.android.oss.callback.OSSProgressCallback;
import com.alibaba.sdk.android.oss.common.auth.OSSCredentialProvider;
import com.alibaba.sdk.android.oss.common.auth.OSSStsTokenCredentialProvider;
import com.alibaba.sdk.android.oss.internal.OSSAsyncTask;
import com.alibaba.sdk.android.oss.model.PutObjectRequest;
import com.alibaba.sdk.android.oss.model.PutObjectResult;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.shopping.R;
import com.example.shopping.base.BaseActivity;
import com.example.shopping.interfaces.IBasePresenter;
import com.example.shopping.utils.BitmapUtils;
import com.example.shopping.utils.Constants;
import com.example.shopping.utils.GlideEngine;
import com.example.shopping.utils.SpUtils;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import java.util.List;
import butterknife.BindView;
import io.reactivex.annotations.Nullable;

public class HeaderActivity extends BaseActivity {
/*    String bucketName = "2002a";String bucketName = "2002b1";
    String ossPoint = "http://oss-cn-beijing.aliyuncs.com";

    String key = "LTAI4GEd5Wd1sc4zKEdsTRJj";  //appkey
    String secret = "sVC7AvDoCHLmvtC4Awn9tVOLziHSaO";  //密码
    String ossPoint = "http://oss-cn-beijing.aliyuncs.com";
+
    String key = "LTAI4G1JvHB2FsXvDYMfY56i";  //appkey
    String secret = "gIwhFC9Sk4JEkfFR2mkcOz2Uwr6Vid";  //密码*/
String bucketName = "2002b1";
    String ossPoint = "http://oss-cn-beijing.aliyuncs.com";

    String key = "LTAI4GEd5Wd1sc4zKEdsTRJj";  //appkey
    String secret = "sVC7AvDoCHLmvtC4Awn9tVOLziHSaO";  //密码
/*    String bucketName = "jiyun2002b";
    String ossPoint = "http://oss-cn-beijing.aliyuncs.com";
    String key = "LTAI4GKWQtegZgtrAByCvvTe";
    String secret = "8XmmZpiLG8VEgPFhD9R1brDpyiZdrv";*/
    @BindView(R.id.img_arrow_lt)
    ImageView imgArrowLt;
    @BindView(R.id.txt_title)
    TextView txtTitle;
    @BindView(R.id.img_share)
    ImageView imgShare;
    @BindView(R.id.img_menu)
    ImageView imgMenu;
    @BindView(R.id.layout_menu)
    ConstraintLayout layoutMenu;
    @BindView(R.id.img_avatar)
    ImageView imgAvatar;
    @BindView(R.id.layout_avatar)
    ConstraintLayout layoutAvatar;
    @BindView(R.id.txt_username)
    TextView txtUsername;
    @BindView(R.id.txt_nickname)
    TextView txtNickname;
    @BindView(R.id.layout_nickname)
    ConstraintLayout layoutNickname;
    private OSSClient ossClient;

    /*   @Override
       protected void onCreate(Bundle savedInstanceState) {
           super.onCreate(savedInstanceState);
           setContentView(R.layout.activity_header);
           ButterKnife.bind(this);
       }*/
 private void openPhoto() {
     PictureSelector.create(this)
             .openGallery(PictureMimeType.ofImage())
             .loadImageEngine(GlideEngine.createGlideEngine())
             .maxSelectNum(1)
             .imageSpanCount(4)
             .selectionMode(PictureConfig.MULTIPLE)
             .forResult(PictureConfig.CHOOSE_REQUEST);
 }
    @Override
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
                try {
                    Bitmap scaleBitmp = BitmapUtils.getScaleBitmap(selectList.get(0).getPath(), Constants.HEAD_WIDTH, Constants.HEAD_HEIGHT);
                    // 上传图片
                    Uri uri = Uri.parse(MediaStore.Images.Media.insertImage(getContentResolver(), scaleBitmp, null, null));
                    //uri转字符串
                    String path = getRealPathFromUri(HeaderActivity.this, uri);
                    uploadHead(path);
//                    uploadHead(bytes);
                }catch (Exception e){
                    e.printStackTrace();
                }
                break;
            default:
                break;
        }
    }
    //uri转字符串的方法
    public static String getRealPathFromUri(HeaderActivity context, Uri contentUri) {
        Cursor cursor = null;
        try {
            String[] proj = {MediaStore.Images.Media.DATA};
            cursor = context.getContentResolver().query(contentUri, proj, null, null, null);
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            return cursor.getString(column_index);
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
    }

    //TODO 给头像赋值
    private void updateHead(String url) {

        imgAvatar.post(new Runnable() {
            @Override
            public void run() {
                Glide.with(imgAvatar).load(url).apply(new RequestOptions().circleCrop()).into(imgAvatar);
            }
        });

    }
    private void initOss() {
        OSSCredentialProvider credentialProvider = new OSSStsTokenCredentialProvider(key, secret, "");
        // 配置类如果不设置，会有默认配置。
        ClientConfiguration conf = new ClientConfiguration();
        conf.setConnectionTimeout(15 * 1000); // 连接超时，默认15秒。
        conf.setSocketTimeout(15 * 1000); // socket超时，默认15秒。
        conf.setMaxConcurrentRequest(5); // 最大并发请求数，默认5个。
        conf.setMaxErrorRetry(2); // 失败后最大重试次数，默认2次。
        ossClient = new OSSClient(getApplicationContext(), ossPoint, credentialProvider);
    }
    /**
     * oss上传
     */
    private void uploadHead(String path){
        String fileName = path.substring(path.lastIndexOf("/") + 1, path.length());
        PutObjectRequest put = new PutObjectRequest(bucketName, fileName, path);
        put.setProgressCallback(new OSSProgressCallback<PutObjectRequest>() {
            @Override
            public void onProgress(PutObjectRequest request, long currentSize, long totalSize) {
                //上次进度
                Log.i("oss_upload", currentSize + "/" + totalSize);
                // 进度百分比的计算
                // int p = (int) (currentSize/totalSize*100);
                if (currentSize == totalSize) {
                    //完成
                    String headUrl = request.getUploadFilePath();
                    //
                    Log.i("HeadUrl", headUrl);

                }

            }
        });
        OSSAsyncTask task = ossClient.asyncPutObject(put, new OSSCompletedCallback<PutObjectRequest, PutObjectResult>() {
            @Override
            public void onSuccess(PutObjectRequest request, PutObjectResult result) {
                Log.d("PutObject", "UploadSuccess");
                Log.d("ETag", result.getETag());
                Log.d("RequestId", result.getRequestId());
                //成功的回调中读取相关的上传文件的信息  生成一个url地址
                String url = ossClient.presignPublicObjectURL(request.getBucketName(), request.getObjectKey());
                //TODO 刷新显示到界面上
                updateHead(url);
                //调用服务器接口，把url上传到服务器的接口
                SpUtils.getInstance().setValue("in", url);
            }

            @Override
            public void onFailure(PutObjectRequest request, ClientException clientExcepion, ServiceException serviceException) {
                // 请求异常。
                if (clientExcepion != null) {
                    // 本地异常，如网络异常等。
                    clientExcepion.printStackTrace();
                }
                if (serviceException != null) {
                    // 服务异常。
                    Log.e("ErrorCode", serviceException.getErrorCode());
                    Log.e("RequestId", serviceException.getRequestId());
                    Log.e("HostId", serviceException.getHostId());
                    Log.e("RawMessage", serviceException.getRawMessage());
                }
            }
        });
    }
    @Override
    protected int getLayout() {
        return R.layout.activity_header;
    }

    @Override
    protected IBasePresenter createPrenter() {
        return null;
    }

    @Override
    protected void initView() {
        String img = SpUtils.getInstance().getString("img");
        if (!img.isEmpty()){
            Glide.with(this).load(img).into(imgAvatar);
        }
     imgAvatar.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             openPhoto();
         }
     });
    }
/*    *//**
     * 更新用户信息返回
     * @param
     *//*
    @OverrideUserInfoBean
    public void OnEnter(UserInfoBean userInfoBean) {
        if(userInfoBean.getErrno() == 0){UserInfoBean
            SystemUtils.closeSoftKeyBoard(this);
            mLayoutInput.setVisibility(View.GONE);
        }
    }

    private void showInput(){
        mLayoutInput.setVisibility(View.VISIBLE);
        mTxtInput.setFocusable(true);
        SystemUtils.openSoftKeyBoard(this);
    }*/
    @Override
    protected void initData() {
        initOss();
    }
}