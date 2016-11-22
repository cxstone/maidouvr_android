package com.maidouvr.net;

import com.maidouvr.utils.LogUtil;

/**
 * Created by xi.chen01 on 2016/11/8.
 * Project:Maidouvr
 * Company:Lodestone
 * Email:Xi.chen01@lodestonemc.com
 */

public class ErrorInfo {
    private static final String TAG = "Network Error";

    //没有数据返回
    public static final int GENERAL_CODE = 0;

    public int code;
    public String message;
    public String reason;

    public ErrorInfo(int code, String message, String reason) {
        this.code = code;
        this.message = message;
        this.reason = reason;
        LogUtil.d(TAG, this.toString());
    }

    @Override
    public String toString() {
        return "[code:" + this.code + ", message:" + this.message + ", reason:" + this.reason + "]";
    }
}
