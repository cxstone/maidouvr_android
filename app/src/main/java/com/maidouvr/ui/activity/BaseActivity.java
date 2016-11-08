package com.maidouvr.ui.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by xi.chen01 on 2016/11/8.
 * Project:Maidouvr
 * Company:Lodestone
 * Email:Xi.chen01@lodestonemc.com
 */

public abstract class BaseActivity extends AppCompatActivity implements View.OnClickListener {
    public Context context;
    public String tag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        tag = this.getClass().getSimpleName();
    }

    @Override
    protected void onStop() {
//        HttpUtils.cancelAll(context, tag);
        super.onStop();
    }

    @Override
    protected void onDestroy() {
//        ToastUtils.cancel();
        super.onDestroy();
    }
}
