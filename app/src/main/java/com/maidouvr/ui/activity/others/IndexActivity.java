package com.maidouvr.ui.activity.others;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;

import com.maidouvr.R;
import com.maidouvr.ui.activity.BaseActivity;
import com.maidouvr.ui.fragment.index.MyFragment;

public class IndexActivity extends BaseActivity {
    public static final int TAB_MY = 0;

    private MyFragment myFragment;

    private FragmentManager fragmentManager;
    private int position = TAB_MY;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);
        initView();
    }

    private void initView() {
        fragmentManager = getFragmentManager();

        setTabSelection(position);
    }

    @Override
    public void onClick(View v) {

    }

    //切换Fragment
    private void setTabSelection(int position) {
        this.position = position;
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        // 先隐藏掉所有的Fragment，以防止有多个Fragment显示在界面上的情况
        hideFragments(transaction);
        switch (position) {
            case TAB_MY:
                if (myFragment == null) {
                    myFragment = new MyFragment();
                    transaction.add(R.id.fragment_container, myFragment);
                } else {
                    transaction.show(myFragment);
                }
                break;
        }
        transaction.commitAllowingStateLoss();
    }

    //隐藏所有Fragment
    private void hideFragments(FragmentTransaction transaction) {
        if (myFragment != null) {
            transaction.hide(myFragment);
        }
    }
}
