package com.maidouvr.utils;

import android.content.Context;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.maidouvr.R;
import com.maidouvr.net.ErrorInfo;

/**
 * Created by xi.chen01 on 2016/11/8.
 * Project:Maidouvr
 * Company:Lodestone
 * Email:Xi.chen01@lodestonemc.com
 */

public class ToastUtil {
    private static Toast mToast = null;

    public static void show(final Context context, final ErrorInfo errorInfo) {
        if (errorInfo == null) {
            return;
        }
        show(context, errorInfo.message);
    }

    public static void show(final Context context, final int resId) {
        show(context, context.getString(resId));
    }

    public static void show(final Context context, final String message) {
        if (TextUtils.isEmpty(message)) {
            return;
        }
        if (mToast == null) {
            final LinearLayout view = new LinearLayout(context);
            LayoutInflater.from(context).inflate(R.layout.toast, view);
            mToast = new Toast(context);
            mToast.setGravity(Gravity.CENTER, 0, 0);
            mToast.setDuration(Toast.LENGTH_SHORT);
            mToast.setView(view);
        }
        TextView textView = (TextView) mToast.getView().findViewById(R.id.toast_text);
        textView.setText(message);
        mToast.show();
    }

    public static void cancel() {
        if (mToast != null) {
            mToast.cancel();
        }
    }
}
