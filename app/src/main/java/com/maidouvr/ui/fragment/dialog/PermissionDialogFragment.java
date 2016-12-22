package com.maidouvr.ui.fragment.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

/**
 * Created by xi.chen01 on 2016/12/20.
 * Project:maidouvr_android
 */

public class PermissionDialogFragment extends DialogFragment {
    private PermissionDialogListener listener;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        String message = null;
        Bundle bundle = getArguments();
        if (bundle != null) {
            message = getArguments().getString("message");
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("权限申请")
                .setMessage(message)
                .setPositiveButton("下一步", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        listener.onDialogPositiveClick(PermissionDialogFragment.this);
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User cancelled the dialog
                    }
                });
        return builder.create();
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            listener = (PermissionDialogListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement PermissionDialogListener");
        }

    }

    public interface PermissionDialogListener {
        void onDialogPositiveClick(DialogFragment dialog);
    }
}
