package com.maidouvr.net;

import android.content.Context;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.maidouvr.model.response.hybris.HybrisBase;
import com.maidouvr.model.response.hybris.HybrisState;
import com.maidouvr.model.response.php.PHPBase;
import com.maidouvr.model.response.php.PHPState;

/**
 * Created by xi.chen01 on 2016/11/9.
 * Project:Maidouvr
 * Company:Lodestone
 * Email:Xi.chen01@lodestonemc.com
 */

public abstract class ResponseCallback<T> implements Response.ErrorListener, Response.Listener<T> {
    private Context context;

    @Override
    public void onErrorResponse(VolleyError volleyError) {
        ErrorInfo errorInfo = ErrorHelper.getErrorInfo(volleyError);
        onRequestFailed(errorInfo);
    }

    @Override
    public void onResponse(T result) {
        if (result == null) {
            onRequestFailed(new ErrorInfo(ErrorInfo.GENERAL_CODE, "出错了", "接口返回实体为空"));
            return;
        }

        if (result instanceof HybrisBase) {
            HybrisState state = ((HybrisBase) result).state;
            if (state != null) {
                if (state.code == 200) {
                    onRequestSuccess(result);
                } else if (state.code == ErrorInfo.TOKEN_UNAVAILABLE_CODE) {
                    onRequestFailed(new ErrorInfo(state.code, state.msg, state.msg));
                    goToLogin();
                } else {
                    onRequestFailed(new ErrorInfo(state.code, state.msg, state.msg));
                }
            } else {
                onRequestFailed(new ErrorInfo(ErrorInfo.GENERAL_CODE, "出错了", "返回体中未定义错误信息"));
            }
        } else if (result instanceof PHPBase) {
            PHPState state = ((PHPBase) result).state;
            if (state != null) {
                if (state.code == 200) {
                    onRequestSuccess(result);
                } else {
                    onRequestFailed(new ErrorInfo(state.code, state.msg, state.msg));
                }
            } else {
                onRequestFailed(new ErrorInfo(ErrorInfo.GENERAL_CODE, "出错了", "返回体中未定义错误信息"));
            }
        } else {
            onRequestFailed(new ErrorInfo(ErrorInfo.GENERAL_CODE, "出错了", "接口不支持"));
        }
    }

    public void setContext(Context context) {
        this.context = context;
    }


    private void goToLogin() {
        if (context != null) {
//            context.startActivity(new Intent(context, LoginActivity.class));
        }
    }

    public abstract void onRequestSuccess(T result);

    public abstract void onRequestFailed(ErrorInfo error);
}
