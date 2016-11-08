package com.maidouvr.ui.fragment;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.View;

import com.maidouvr.ui.activity.BaseActivity;

/**
 * Created by xi.chen01 on 2016/11/8.
 * Project:Maidouvr
 * Company:Lodestone
 * Email:Xi.chen01@lodestonemc.com
 */

public abstract class BaseFragment extends Fragment implements View.OnClickListener {
    public Context context;
    public String tag;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        BaseActivity activity = (BaseActivity) getActivity();
        context = activity;
        tag = activity.tag;
    }
}
