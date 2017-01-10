package com.maidouvr.net;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.maidouvr.utils.GsonUtil;
import com.maidouvr.utils.LogUtil;

import java.io.UnsupportedEncodingException;
import java.util.Map;

/**
 * Created by xi.chen01 on 2016/11/9.
 * Project:Maidouvr
 * Company:Lodestone
 * Email:Xi.chen01@lodestonemc.com
 */

public class GsonRequest<T> extends Request<T> {
    private static final String TAG = "Network Request";
    private static final int SOCKET_TIMEOUT = 20 * 1000;

    private final Gson gson = GsonUtil.getGson();
    private final Class<T> clazz;
    private final Map<String, String> headers;
    private final Map<String, String> parameters;
    private final Response.Listener<T> listener;

    public GsonRequest(int method, String url, Class<T> clazz, Map<String, String> headers, Map<String, String> parameters, final ResponseCallback<T> callback) {
        super(method, url, callback);
        this.clazz = clazz;
        this.headers = headers;
        this.listener = callback;
        this.parameters = parameters;
        setSocketTimeout(SOCKET_TIMEOUT);
        LogUtil.d(TAG + " URL", url);
    }

    // 设置请求延迟
    private void setSocketTimeout(int timeout) {
        setRetryPolicy(new DefaultRetryPolicy(timeout, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
    }

    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {
        LogUtil.d(TAG + " HEADERS", headers != null ? headers.toString() : "NULL");
        return headers != null ? headers : super.getHeaders();
    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        LogUtil.d(TAG + " PARAMS", parameters != null ? parameters.toString() : "NULL");
        return parameters != null ? parameters : super.getParams();
    }

    @Override
    protected void deliverResponse(T response) {
        listener.onResponse(response);
    }

    @Override
    protected Response<T> parseNetworkResponse(NetworkResponse response) {
        try {
            String json = new String(response.data, "UTF-8");
            LogUtil.d(TAG + " RESPONSE", json);
            return Response.success(gson.fromJson(json, clazz), HttpHeaderParser.parseCacheHeaders(response));
        } catch (UnsupportedEncodingException e) {
            return Response.error(new ParseError(e));
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
            return Response.error(new ParseError(e));
        }
    }


}
