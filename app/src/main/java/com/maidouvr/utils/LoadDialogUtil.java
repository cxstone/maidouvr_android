package com.maidouvr.utils;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.AnimationDrawable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.maidouvr.R;

/**
 * Created by xi.chen01 on 2016/11/23.
 * Project:maidouvr_android
 */

public class LoadDialogUtil {

    public static Dialog createLoadingDialog(Context context) {
        return createLoadingDialog(context, R.string.load_normal);
    }

    public static Dialog createLoadingDialog(Context context, int resId) {
        return createLoadingDialog(context, context.getString(resId));
    }

    public static Dialog createLoadingDialog(Context context, String tips) {
        final Dialog loadingDialog = new Dialog(context, R.style.LoadingDialogStyle);
        loadingDialog.setCanceledOnTouchOutside(false);

        //加载布局
        final View view = LayoutInflater.from(context).inflate(R.layout.dialog_loading, new LinearLayout(context));
        loadingDialog.setContentView(view);

        //设置图片与文字属性
        final ImageView spaceshipImage = (ImageView) view.findViewById(R.id.img);
        final TextView tipTextView = (TextView) view.findViewById(R.id.tv_tips);
        spaceshipImage.setBackgroundResource(R.drawable.background_loading);
        tipTextView.setText(TextUtils.isEmpty(tips) ? "" : tips);

        //设置图片动画
        final AnimationDrawable frameAnimation = (AnimationDrawable) spaceshipImage.getBackground();
        frameAnimation.start();

        loadingDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                spaceshipImage.setImageDrawable(null);
            }
        });

        return loadingDialog;
    }
}
