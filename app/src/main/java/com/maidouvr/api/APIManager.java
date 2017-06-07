package com.maidouvr.api;

import android.content.Context;

import com.maidouvr.utils.ConstantUtil;
import com.maidouvr.utils.SPManager;

/**
 * Created by stone on 2017/6/7.
 * Project:maidouvr_android
 */

public class APIManager {

    private static String getHostHybris(Context context) {
        if (SPManager.getHostFlag(context)) {
            return SPManager.getHostHybris(context);
        } else {
            return ConstantUtil.HOST.HOST_HYBRIS;
        }
    }

    public static class User {
        public static String API_LOGIN(Context context) {
            return getHostHybris(context) + "/yxtws/v2/hxyxt/app/customer/login";
        }
    }

}
