package com.maidouvr.ui.fragment.index;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.maidouvr.R;
import com.maidouvr.model.response.test.CategoryResponse;
import com.maidouvr.net.ErrorInfo;
import com.maidouvr.net.HttpLoad;
import com.maidouvr.net.ImageLoad;
import com.maidouvr.net.ResponseCallback;
import com.maidouvr.ui.fragment.BaseFragment;

/**
 * Created by xi.chen01 on 2016/11/8.
 * Project:Maidouvr
 * Company:Lodestone
 * Email:Xi.chen01@lodestonemc.com
 */

public class MyFragment extends BaseFragment {
    private ImageView ivTest;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        ivTest = (ImageView) view.findViewById(R.id.iv_test);
        ImageLoad.loadImg(context, ivTest, "https://ss0.bdstatic.com/5aV1bjqh_Q23odCf/static/superman/img/logo/bd_logo1_31bdc765.png");

        HttpLoad.TestModule.getCategory(context, tag, new ResponseCallback<CategoryResponse>() {
            @Override
            public void onRequestSuccess(CategoryResponse result) {

            }

            @Override
            public void onRequestFailed(ErrorInfo error) {

            }
        });
    }

    @Override
    public void onClick(View v) {

    }
}
