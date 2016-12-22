package com.cx.popupwindow;

import android.content.Context;
import android.util.TypedValue;

/**
 * Created by CX on 2016/9/29.
 */

public class DisplayUtil {
    private DisplayUtil(){}
    public static int dp2px(Context context, int dp ){
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,dp,context.getResources().getDisplayMetrics());
    }
    public static int sp2px(Context context, int sp){
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,sp,context.getResources().getDisplayMetrics());
    }
    public static int px2dp(Context context, float px ){
     float scale=context.getResources().getDisplayMetrics().density;
        return (int)(px/scale+0.5f);
    }
}
