package com.maidouvr.net;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.ParseError;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;

/**
 * Created by xi.chen01 on 2016/11/9.
 * Project:Maidouvr
 * Company:Lodestone
 * Email:Xi.chen01@lodestonemc.com
 */

public class ErrorHelper {

    public static ErrorInfo getErrorInfo(VolleyError error) {
        if (error instanceof TimeoutError) {
            return new ErrorInfo(ErrorInfo.GENERAL_CODE, "网络不太给力", "网络请求超时");
        } else if (isNetworkProblem(error)) {
            return new ErrorInfo(ErrorInfo.GENERAL_CODE, "网络不太给力", "网络服务器错误");
        } else if (isServerProblem(error)) {
            return new ErrorInfo(ErrorInfo.GENERAL_CODE, "出错了", "服务器异常");
        } else if (error instanceof ParseError) {
            return new ErrorInfo(ErrorInfo.GENERAL_CODE, "出错了", "返回体实体解析异常");
        } else {
            return new ErrorInfo(ErrorInfo.GENERAL_CODE, "出错了", "未知异常");
        }
    }

    private static boolean isNetworkProblem(VolleyError error) {
        return (error instanceof NetworkError);
    }

    private static boolean isServerProblem(VolleyError error) {
        return (error instanceof AuthFailureError || error instanceof ServerError);
    }
}
