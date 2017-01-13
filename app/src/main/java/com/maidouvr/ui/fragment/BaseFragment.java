package com.maidouvr.ui.fragment;

import android.app.DialogFragment;
import android.app.Fragment;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v13.app.FragmentCompat;
import android.support.v4.content.ContextCompat;
import android.view.View;

import com.maidouvr.ui.activity.BaseActivity;
import com.maidouvr.ui.fragment.dialog.PermissionWarmDialogFragment;

/**
 * Created by xi.chen01 on 2016/11/8.
 * Project:Maidouvr
 * Company:Lodestone
 * Email:Xi.chen01@lodestonemc.com
 */

public abstract class BaseFragment extends Fragment implements View.OnClickListener {
    private static final int PERMISSION_REQUEST = 1002;

    public Context context;
    public String tag;

    private int requestCode;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        BaseActivity activity = (BaseActivity) getActivity();
        context = activity;
        tag = activity.tag;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case PERMISSION_REQUEST:
                for (int i = 0; i < permissions.length; i++) {
                    if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                        if (!FragmentCompat.shouldShowRequestPermissionRationale(this, permissions[i])) {
                            showPermissionWarmDialog();
                        }
                        return;
                    }
                }
                this.onRequestPermissionSuccess(this.requestCode);
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    //权限被拒绝且不在询问时对话框
    private void showPermissionWarmDialog() {
        DialogFragment dialogFragment = new PermissionWarmDialogFragment();
        dialogFragment.show(getFragmentManager(), "");
    }

    //申请权限
    public void requestPermission(int requestCode, @NonNull String[] permissions) {
        this.requestCode = requestCode;
        for (String permission : permissions) {
            if (ContextCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                FragmentCompat.requestPermissions(this, permissions, PERMISSION_REQUEST);
                return;
            }
        }

        this.onRequestPermissionSuccess(this.requestCode);
    }

    //申请权限成功回调
    public void onRequestPermissionSuccess(int requestCode) {
    }
}
