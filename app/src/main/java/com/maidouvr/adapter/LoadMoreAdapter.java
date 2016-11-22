package com.maidouvr.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.maidouvr.R;
import com.maidouvr.utils.LogUtil;

import java.util.List;

/**
 * Created by xi.chen01 on 2016/11/22.
 * Project:maidouvr_android
 */

public class LoadMoreAdapter extends RecyclerView.Adapter<LoadMoreAdapter.LoadMoreViewHolder> {
    private Context context;
    private List<String> datas;
    private LoadMoreListener loadMoreListener;

    public LoadMoreAdapter(Context context, List<String> datas) {
        this.context = context;
        this.datas = datas;
    }

    public void setLoadMoreListener(LoadMoreListener loadMoreListener) {
        this.loadMoreListener = loadMoreListener;
    }

    @Override
    public LoadMoreViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new LoadMoreViewHolder(LayoutInflater.from(context).inflate(R.layout.adapter_item, parent, false));
    }

    @Override
    public void onBindViewHolder(LoadMoreViewHolder holder, int position) {
        holder.tvName.setText(datas.get(position));
        LogUtil.d("POSITION", position + "");
        if (loadMoreListener != null && position == datas.size() - 1) {
            loadMoreListener.loadMore();
        }
    }

    @Override
    public int getItemCount() {
        return datas == null ? 0 : datas.size();
    }

    class LoadMoreViewHolder extends RecyclerView.ViewHolder {
        TextView tvName;

        LoadMoreViewHolder(View itemView) {
            super(itemView);
            tvName = (TextView) itemView.findViewById(R.id.tv_name);
        }
    }

    public interface LoadMoreListener {
        void loadMore();
    }
}
