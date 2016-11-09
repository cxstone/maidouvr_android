package com.maidouvr.net;

import android.content.Context;

import com.android.volley.Request;
import com.maidouvr.model.response.test.CategoryResponse;
import com.maidouvr.utils.ConstantUtil;

import java.util.HashMap;

/**
 * 网络请求API封装类
 * Created by xi.chen01 on 2016/11/8.
 * Project:Maidouvr
 * Company:Lodestone
 * Email:Xi.chen01@lodestonemc.com
 */

public class HttpLoad {

    public static abstract class TestModule {
        public static void getCategory(Context context, String tag, ResponseCallback<CategoryResponse> callback) {
            final HashMap<String, String> params = new HashMap<>();
            params.put("id", "1");
            params.put("page", "1");
            params.put("count", "1");

            GsonRequest<CategoryResponse> request = new GsonRequest<>(Request.Method.POST, ConstantUtil.API.Test.GET_CATEGORY, CategoryResponse.class, null, params, callback);
            HttpUtil.getInstance(context).request(tag, request);
        }
    }

}
