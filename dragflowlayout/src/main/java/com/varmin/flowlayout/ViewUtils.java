package com.varmin.flowlayout;

import android.content.Context;
import android.view.View;

public class ViewUtils {

    public static int getStatusHeight(Context context) {
        int statusHeight = 25; //default is 25
        try
        {
            Class<?> clazz = Class.forName("com.android.internal.R$dimen");
            Object object = clazz.newInstance();
            int height = Integer.parseInt(clazz.getField("status_bar_height")
                    .get(object).toString());
            statusHeight = context.getResources().getDimensionPixelSize(height);
        } catch (Exception e) {
        }
        return statusHeight;
    }

    public static boolean isViewIntersect(View view,int x, int y) {
        if (x >= view.getLeft() && x < view.getRight() &&
                y >= view.getTop() && y < view.getBottom()) {
            return true;
        }
        return false;
    }
    public static boolean isViewUnderInScreen(View view, int x, int y) {
        if (view == null) {
            return false;
        }
        int [] mTempLocation = new int[2];
        view.getLocationOnScreen(mTempLocation);
        int w = view.getWidth();
        int h = view.getHeight();
        int viewX = mTempLocation[0];
        int viewY = mTempLocation[1];
        return x >= viewX && x < viewX + w
                && y >= viewY && y < viewY + h;
    }
}
