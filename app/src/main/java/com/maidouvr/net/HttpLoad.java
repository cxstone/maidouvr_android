package com.maidouvr.net;

import android.content.Context;

import com.android.volley.Request;
import com.maidouvr.api.APIManager;
import com.maidouvr.model.response.hybris.HybrisBase;

import java.util.HashMap;

/**
 * 网络请求API封装类
 * Created by xi.chen01 on 2016/11/8.
 * Project:Maidouvr
 * Company:Lodestone
 * Email:Xi.chen01@lodestonemc.com
 */

public class HttpLoad {

    public static abstract class UserModule {
        //用户登录
        public static void login(Context context,
                                 String tag,
                                 String account,
                                 String password,
                                 ResponseCallback<HybrisBase> callback) {

            final HashMap<String, String> params = new HashMap<>();
            params.put("channel", "yxtapp");
            params.put("username", account);
            params.put("password", password);

            GsonRequest<HybrisBase> request = new GsonRequest<>(Request.Method.POST, APIManager.User.API_LOGIN(context), HybrisBase.class, null, params, callback);
            HttpUtil.getInstance(context).request(tag, request);
        }
    }


}
