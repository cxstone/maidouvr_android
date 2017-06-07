package com.maidouvr.utils;

import android.content.Context;

/**
 * Created by xi.chen01 on 2016/11/23.
 * Project:maidouvr_android
 */

public class SPManager {

    public static void putHostFlag(Context context, boolean flag) {
        SPUtil.putBoolean(context, ConstantUtil.SP.Host.IS_CUSTOM_HOST, flag);
    }

    public static boolean getHostFlag(Context context) {
        return SPUtil.getBoolean(context, ConstantUtil.SP.Host.IS_CUSTOM_HOST, false);
    }

    public static void saveHostHybris(Context context, String host) {
        SPUtil.putString(context, ConstantUtil.SP.Host.HYBRIS, host);
    }

    public static String getHostHybris(Context context) {
        return SPUtil.getString(context, ConstantUtil.SP.Host.HYBRIS);
    }

    //保存用户token
    public static void saveUserToken(Context context, String token) {
        SPUtil.putString(context, ConstantUtil.SP.APP.ACCESS_TOKEN, token);
    }

    //获取用户token
    public static String getUserToken(Context context) {
        return SPUtil.getString(context, ConstantUtil.SP.APP.ACCESS_TOKEN);
    }

    //清除当前用户信息
    public static void clearUserInfo(Context context) {
        SPUtil.clearSP(context);
    }
}
