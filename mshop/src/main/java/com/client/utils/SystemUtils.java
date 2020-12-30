package com.client.utils;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

public class SystemUtils {

    /**
     * 关闭键盘
     * @param context
     */
    public static void closeSoftKeyBoard(Activity context)
    {
        View view = context.getWindow().peekDecorView();
        if (view != null)
        {
            InputMethodManager imm = (InputMethodManager) context.getSystemService(Activity.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }
    /**
     * 打开键盘
     * @param context
     */
    public static void openSoftKeyBoard(Activity context)
    {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Activity.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
    }

}
