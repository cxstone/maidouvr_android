package com.maidouvr.net;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.maidouvr.utils.LogUtil;

/**
 * 基于Volley网络请求框架工具类封装
 * Created by xi.chen01 on 2016/11/9.
 * Project:Maidouvr
 * Company:Lodestone
 * Email:Xi.chen01@lodestonemc.com
 */

public class HttpUtil {
    private static final String TAG = "Network Request";

    private static HttpUtil mInstance;
    private static RequestQueue mRequestQueue;
    private static Context mCtx;

    private HttpUtil(Context context) {
        mCtx = context;
        mRequestQueue = getRequestQueue();
    }

    private RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(mCtx.getApplicationContext());
        }
        return mRequestQueue;
    }

    public static synchronized HttpUtil getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new HttpUtil(context);
        }
        return mInstance;
    }

    public <T> void request(String tag, Request<T> request) {
        if (request != null) {
            request.setTag(tag);
            getRequestQueue().add(request);
            LogUtil.d(TAG + " TAG", tag);
        }
    }

    public static void cancelAll(Context context, String tag) {
        if (tag != null) {
            HttpUtil.getInstance(context).getRequestQueue().cancelAll(tag);
        }
    }
}
