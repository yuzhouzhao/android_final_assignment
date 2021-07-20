package com.example.mytiktok;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mytiktok.R;
import com.example.mytiktok.Video;
import com.example.mytiktok.ViewHolder;

import java.util.ArrayList;
import java.util.List;

/*
 * 利用RecyclerView显示视频列表 (一页显示多个item)
 */

public class ListAdapter extends RecyclerView.Adapter<ViewHolder> {

    private final List<Video> videoList = new ArrayList<>();

    public void refresh(List<Video> newVideos) {
        videoList.clear();
        if (newVideos != null) {
            videoList.addAll(newVideos);
        }
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.video_item, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull  ViewHolder holder, int position) {
        holder.bind(videoList.get(position));
    }

    @Override
    public int getItemCount() {
        return videoList.size();
    }

    public void setData(List<Video> videoLi) {
        for (int i = 0; i < videoLi.size(); i++) {
            videoList.add(videoLi.get(i));
        }
    }
}

