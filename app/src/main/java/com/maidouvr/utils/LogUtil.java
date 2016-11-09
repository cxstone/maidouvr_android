package com.maidouvr.utils;

import android.util.Log;

/**
 * Created by xi.chen01 on 2016/11/8.
 * Project:Maidouvr
 * Company:Lodestone
 * Email:Xi.chen01@lodestonemc.com
 */

public class LogUtil {
    private static final String PREFIX = "[Maidouvr]-->";

    public static void d(String tag, String msg) {
        if (ConstantUtil.System.IS_DEBUG_MODE)
            Log.d(PREFIX + tag, msg);
    }

    public static void e(String tag, String msg) {
        if (ConstantUtil.System.IS_DEBUG_MODE)
            Log.e(PREFIX + tag, msg);
    }

    public static void i(String tag, String msg) {
        if (ConstantUtil.System.IS_DEBUG_MODE)
            Log.i(PREFIX + tag, msg);
    }

    public static void v(String tag, String msg) {
        if (ConstantUtil.System.IS_DEBUG_MODE)
            Log.v(PREFIX + tag, msg);
    }

    public static void w(String tag, String msg) {
        if (ConstantUtil.System.IS_DEBUG_MODE)
            Log.w(PREFIX + tag, msg);
    }

    public static void wtf(String tag, String msg) {
        if (ConstantUtil.System.IS_DEBUG_MODE)
            Log.wtf(PREFIX + tag, msg);
    }
}
