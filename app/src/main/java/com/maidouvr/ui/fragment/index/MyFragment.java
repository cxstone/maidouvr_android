package com.maidouvr.ui.fragment.index;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.maidouvr.R;
import com.maidouvr.adapter.MyAdapter;
import com.maidouvr.model.local.PhotoEntry;
import com.maidouvr.ui.fragment.BaseFragment;
import com.maidouvr.utils.LogUtil;
import com.maidouvr.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xi.chen01 on 2016/11/8.
 * Project:Maidouvr
 * Company:Lodestone
 * Email:Xi.chen01@lodestonemc.com
 */

public class MyFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener, MyAdapter.MyListener {
    private final int IMAGE_NUMBER = 4;

    //    private SwipeRefreshLayout refreshLayout;
    private MyAdapter adapter;
    private List<PhotoEntry> datas = new ArrayList<>();

    static {
        // 加载动态库
        System.loadLibrary("Fzair");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my, container, false);


//        LogUtil.d("NDK", Keys);


//        initView(view);
        return view;
    }

    private void initView(View view) {
//        refreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.refresh_layout);
        RecyclerView rvList = (RecyclerView) view.findViewById(R.id.rv_list);

//        refreshLayout.setOnRefreshListener(this);
        rvList.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
        rvList.addItemDecoration(new DividerItemDecoration(context, LinearLayoutManager.VERTICAL));
        datas.add(new PhotoEntry(PhotoEntry.TYPE_ADD));
        adapter = new MyAdapter(context, datas);
        adapter.setMyListener(this);


        rvList.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onRefresh() {
//        new MyAsyncTask().execute();
    }

    @Override
    public void add() {
        if (datas.size() == IMAGE_NUMBER) {
            ToastUtil.show(context, String.format("最多只能添加%1$s张图片", (IMAGE_NUMBER - 1)));
            return;
        }
        datas.remove((datas.size() - 1));
        datas.add(new PhotoEntry(PhotoEntry.TYPE_PHOTO, "http://img0.imgtn.bdimg.com/it/u=3858061328,2698026962&fm=23&gp=0.jpg"));
        datas.add(new PhotoEntry(PhotoEntry.TYPE_ADD));
        adapter.notifyDataSetChanged();
    }

    @Override
    public void delete(int position) {
        datas.remove(position);
        adapter.notifyDataSetChanged();
    }


}
