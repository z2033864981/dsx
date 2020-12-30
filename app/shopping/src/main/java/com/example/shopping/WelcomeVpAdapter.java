package com.example.shopping;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import java.util.ArrayList;

public class WelcomeVpAdapter extends PagerAdapter {
    private ArrayList<View> mViews;
    private Context mContext;

    public WelcomeVpAdapter(ArrayList<View> mViews, Context mContext) {
        this.mViews = mViews;
        this.mContext = mContext;
    }


    @Override
    public int getCount() {
        return mViews.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }


    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view = mViews.get(position);
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        View view = mViews.get(position);
        container.removeView(view);

    }
}
