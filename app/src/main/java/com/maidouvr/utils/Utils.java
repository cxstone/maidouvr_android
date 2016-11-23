package com.maidouvr.utils;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.text.TextUtils;
import android.view.WindowManager;

/**
 * Created by xi.chen01 on 2016/11/23.
 * Project:maidouvr_android
 */

public class Utils {

    //手机网络检测
    public static boolean isNetworkConnected(Context context) {
        ConnectivityManager mConnectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();
        if (mNetworkInfo != null && mNetworkInfo.isAvailable()) {
            return true;
        }
        ToastUtil.show(context, "请检查您的网络连接！");
        return false;
    }

    //拨打电话
    public static void callPhone(Context context, String number) {
        if (TextUtils.isEmpty(number)) {
            return;
        }
        try {
            Intent intent = new Intent(Intent.ACTION_DIAL);
            intent.setData(Uri.parse("tel:" + number));
            context.startActivity(intent);
        } catch (ActivityNotFoundException ex) {
            ToastUtil.show(context, "您的设备不支持拨打电话！");
        }
    }

    //将DP转换为PX
    public static int dp2pxInt(Context context, float value) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) (value * scale + 0.5f);
    }

    //获得屏幕宽度
    public static int getScreenWidth(Context context) {
        Point point = getScreenPoint(context);
        return point.x;
    }

    // 获得屏幕高度
    public static int getScreenHeight(Context context) {
        Point point = getScreenPoint(context);
        return point.y;
    }

    private static Point getScreenPoint(Context context) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Point point = new Point();
        wm.getDefaultDisplay().getSize(point);
        return point;
    }
}
