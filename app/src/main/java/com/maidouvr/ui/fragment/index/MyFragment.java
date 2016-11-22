package com.maidouvr.ui.fragment.index;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.maidouvr.R;
import com.maidouvr.adapter.LoadMoreAdapter;
import com.maidouvr.model.response.test.CategoryResponse;
import com.maidouvr.net.ErrorInfo;
import com.maidouvr.net.HttpLoad;
import com.maidouvr.net.ResponseCallback;
import com.maidouvr.ui.fragment.BaseFragment;
import com.maidouvr.utils.LogUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xi.chen01 on 2016/11/8.
 * Project:Maidouvr
 * Company:Lodestone
 * Email:Xi.chen01@lodestonemc.com
 */

public class MyFragment extends BaseFragment implements LoadMoreAdapter.LoadMoreListener {
    private RecyclerView rvList;
    private LoadMoreAdapter adapter;
    private List<String> datas = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        rvList = (RecyclerView) view.findViewById(R.id.rv_list);
        rvList.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
        createDatas();
        adapter = new LoadMoreAdapter(context, datas);
        adapter.setLoadMoreListener(this);
        rvList.setAdapter(adapter);


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

    private void createDatas() {
        for (int i = 0; i < 20; i++) {
            datas.add(i + "");
        }
    }

    @Override
    public void loadMore() {
        LogUtil.d("Load", "---------------");
    }
}
