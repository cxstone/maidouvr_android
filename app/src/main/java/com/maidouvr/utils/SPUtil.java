package com.maidouvr.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by xi.chen01 on 2016/11/23.
 * Project:maidouvr_android
 * Company:Lodestone
 * Email:Xi.chen01@lodestonemc.com
 */

public class SPUtil {
    private static final String SP_NAME = "hxyxt_yxdj";

    private static SharedPreferences.Editor getEditor(Context context, int mode) {
        return context.getSharedPreferences(SP_NAME, mode).edit();
    }

    private static SharedPreferences.Editor getEditor(Context context) {
        return getEditor(context, Context.MODE_PRIVATE);
    }

    private static SharedPreferences getSharedPreferences(Context context) {
        return context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
    }

    //写入String类型
    public static void putString(Context context, String key, String value) {
        getEditor(context).putString(key, value).commit();
    }

    //返回String类型，有默认值
    public static String getString(Context context, String key) {
        return getString(context, key, "");
    }

    //返回String类型，无默认值
    public static String getString(Context context, String key, String defaultValue) {
        return getSharedPreferences(context).getString(key, defaultValue);
    }

    //写入boolean类型
    public static void putBoolean(Context context, String key, boolean value) {
        getEditor(context).putBoolean(key, value).commit();
    }

    //返回boolean类型
    public static boolean getBoolean(Context context, String key, boolean defaultValue) {
        return getSharedPreferences(context).getBoolean(key, defaultValue);
    }

    //写入int类型
    public static void putInt(Context context, String key, int value) {
        getEditor(context).putInt(key, value).commit();
    }

    //返回int类型
    public static int getInt(Context context, String key, int defaultValue) {
        return getSharedPreferences(context).getInt(key, defaultValue);
    }

    //写入long类型
    public static void putLong(Context context, String key, long value) {
        getEditor(context).putLong(key, value).commit();
    }

    //返回long类型
    public static long getLong(Context context, String key, long defaultValue) {
        return getSharedPreferences(context).getLong(key, defaultValue);
    }

    //清除SP中所有数据
    public static void clearSP(Context context) {
        getEditor(context).clear().commit();
    }

    //清除SP中对应key的数据
    public static void removeByKey(Context context, String key) {
        getEditor(context).remove(key).commit();
    }

}
