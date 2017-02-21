package com.for_futrue.zxg.znews.util;

import android.app.Application;
import android.util.DisplayMetrics;
import android.util.TypedValue;

import com.for_futrue.zxg.znews.app.ZNewsApplication;

/**
 * Created by zxg on 2017/1/18.
 */
public class DensityUtils {

    public static float dipToPx(float dip){
        DisplayMetrics metrics = ZNewsApplication.getApplication().getResources().getDisplayMetrics();
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,dip,metrics);
    }

    public static float pxToDip(float px){
        DisplayMetrics metrics = ZNewsApplication.getApplication().getResources().getDisplayMetrics();
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PX,px,metrics);
    }

    public static int getScreenWidth(){
        Application application = ZNewsApplication.getApplication();
        int screenWidth = -1;
        if(application != null){
            screenWidth = application.getResources().getDisplayMetrics().widthPixels;
        }
        return screenWidth;
    }

    public static int getScreenHeight(){
        Application application = ZNewsApplication.getApplication();
        int screenHeight = -1;
        if(application != null){
            screenHeight = application.getResources().getDisplayMetrics().heightPixels;
        }
        return screenHeight;
    }
}
