package com.maidouvr.ui.activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.maidouvr.R;
import com.maidouvr.net.HttpUtil;
import com.maidouvr.utils.ToastUtil;

/**
 * Created by xi.chen01 on 2016/11/8.
 * Project:Maidouvr
 * Company:Lodestone
 * Email:Xi.chen01@lodestonemc.com
 */

public abstract class BaseActivity extends AppCompatActivity implements View.OnClickListener {
    private static final int PERMISSION_REQUEST = 1000;
    public Context context;
    public String tag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        tag = this.getClass().getSimpleName();
    }

    @Override
    public void onClick(View v) {
    }

    @Override
    protected void onStop() {
        HttpUtil.cancelAll(context, tag);
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        ToastUtil.cancel();
        super.onDestroy();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case PERMISSION_REQUEST:
                for (int i = 0; i < permissions.length; i++) {
                    if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                        if (!ActivityCompat.shouldShowRequestPermissionRationale(this, permissions[i])) {
                            showPermissionWarmDialog();
                        }
                        return;
                    }
                }
                this.onRequestPermissionSuccess();
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    //权限被拒绝且不在询问时对话框
    private void showPermissionWarmDialog() {
        new AlertDialog.Builder(context, R.style.DialogStyle)
                .setMessage("警告：缺少使用该功能的必要权限")
                .setPositiveButton("前去设置", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent("android.settings.APPLICATION_DETAILS_SETTINGS");
                        intent.setData(Uri.fromParts("package", context.getPackageName(), null));
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        context.startActivity(intent);
                    }
                })
                .setNegativeButton("退出", null).show();
    }

    //申请权限
    public void requestPermission(String[] permissions) {
        if (permissions == null || permissions.length == 0) {
            this.onRequestPermissionSuccess();
            return;
        }

        for (String permission : permissions) {
            if (ContextCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(BaseActivity.this, permissions, PERMISSION_REQUEST);
                return;
            }
        }

        this.onRequestPermissionSuccess();
    }

    //申请权限成功回调
    public void onRequestPermissionSuccess() {
    }
}
