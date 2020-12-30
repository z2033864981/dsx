package com.example.demo2.utils;

import android.content.Context;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.bm.library.PhotoView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;

public class TxtUtils {

    //文字的封装
    public static void setTextView(TextView textView, String word){
        if(textView != null && !TextUtils.isEmpty(word)){
            textView.setText(word);
        }
    }

    public static void setImageView(ImageView image, String url){
        if(image != null && !TextUtils.isEmpty(url)){
            Glide.with(image).load(url).apply(new RequestOptions().bitmapTransform(new RoundedCorners(20))).into(image);
        }
    }

    public static void setImageView(Context context, ImageView imageView, String image) {
        if (imageView != null && !TextUtils.isEmpty(image)) {
            Glide.with(context).load(image).apply(RequestOptions.bitmapTransform(new RoundedCorners(20))).into(imageView);
        }
    }

    public static void setImageView(Context context, ImageView imageView, int image) {
        if (imageView != null) {
            Glide.with(context).load(image).into(imageView);
        }
    }

    //更改背景
//    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
//    public static void setConstraintLayoutView(ConstraintLayout constraintLayout, Context context, int labelId){
//        if(constraintLayout != null){
//            if(labelId %2 ==0){
//                constraintLayout.setBackgroundDrawable(context.getDrawable(R.drawable.shape_topic_bg));
//            }else if(labelId %3 == 1){
//                constraintLayout.setBackgroundDrawable(context.getDrawable(R.drawable.shape_dicussed_bg));
//            }else{
//                constraintLayout.setBackgroundDrawable(context.getDrawable(R.drawable.shape_topic_bg1));
//            }
//        }
//    }

    //手势
    public static void setPhotoView(Context context, PhotoView photoView, String image) {
        if (photoView != null && !TextUtils.isEmpty(image)) {
            Glide.with(context).load(image)
                    .apply(RequestOptions.bitmapTransform(new RoundedCorners(20)))
                    .into(photoView);
        }
    }

}
