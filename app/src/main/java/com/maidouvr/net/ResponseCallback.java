package com.maidouvr.net;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.maidouvr.model.response.ResponseBase;

/**
 * Created by xi.chen01 on 2016/11/9.
 * Project:Maidouvr
 * Company:Lodestone
 * Email:Xi.chen01@lodestonemc.com
 */

public abstract class ResponseCallback<T> implements Response.ErrorListener, Response.Listener<T> {

    @Override
    public void onErrorResponse(VolleyError volleyError) {
        ErrorInfo errorInfo = ErrorHelper.getErrorInfo(volleyError);
        onRequestFailed(errorInfo);
    }

    @Override
    public void onResponse(T result) {
        if (result == null) {
            onRequestFailed(new ErrorInfo(ErrorInfo.GENERAL_CODE, "出错了", "返回实体为空"));
            return;
        }

        if (result instanceof ResponseBase) {
            onRequestSuccess(result);
        } else {
            onRequestFailed(new ErrorInfo(ErrorInfo.GENERAL_CODE, "出错了", "请求不支持"));
        }
    }

    public abstract void onRequestSuccess(T result);

    public abstract void onRequestFailed(ErrorInfo error);
}
