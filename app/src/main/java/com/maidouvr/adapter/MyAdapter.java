package com.maidouvr.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.maidouvr.R;
import com.maidouvr.model.local.PhotoEntry;
import com.maidouvr.net.ImageLoad;

import java.util.List;

/**
 * Created by xi.chen01 on 2016/12/6.
 * Project:maidouvr_android
 */

public class MyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private List<PhotoEntry> datas;
    private MyListener listener;

    public MyAdapter(Context context, List<PhotoEntry> datas) {
        this.context = context;
        this.datas = datas;
    }

    public void setMyListener(MyListener listener) {
        this.listener = listener;
    }

    @Override
    public int getItemViewType(int position) {
        return datas.get(position).type;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder = null;
        if (viewType == PhotoEntry.TYPE_ADD) {
            viewHolder = new MyAddViewHolder(LayoutInflater.from(context).inflate(R.layout.adapter_my_add, parent, false));
        } else if (viewType == PhotoEntry.TYPE_PHOTO) {
            viewHolder = new MyPhotoViewHolder(LayoutInflater.from(context).inflate(R.layout.adapter_my_photo, parent, false));
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        PhotoEntry item = datas.get(position);

        if (holder instanceof MyAddViewHolder) {
            MyAddViewHolder viewHolder = (MyAddViewHolder) holder;
            viewHolder.btnAdd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (listener != null) {
                        listener.add();
                    }
                }
            });

        } else if (holder instanceof MyPhotoViewHolder) {
            MyPhotoViewHolder viewHolder = (MyPhotoViewHolder) holder;
            ImageLoad.loadImg(context, viewHolder.ivIcon, item.url);
            viewHolder.btnDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (listener != null) {
                        listener.delete(position);
                    }
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return datas == null ? 0 : datas.size();
    }

    private class MyAddViewHolder extends RecyclerView.ViewHolder {
        Button btnAdd;

        MyAddViewHolder(View itemView) {
            super(itemView);
            btnAdd = (Button) itemView.findViewById(R.id.btn_add);
        }
    }

    private class MyPhotoViewHolder extends RecyclerView.ViewHolder {
        ImageView ivIcon;
        Button btnDelete;

        MyPhotoViewHolder(View itemView) {
            super(itemView);
            ivIcon = (ImageView) itemView.findViewById(R.id.iv_icon);
            btnDelete = (Button) itemView.findViewById(R.id.btn_delete);
        }
    }

    public interface MyListener {
        void add();

        void delete(int position);
    }

}
