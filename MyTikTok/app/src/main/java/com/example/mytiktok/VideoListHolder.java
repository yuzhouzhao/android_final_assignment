package com.example.mytiktok;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;


public class VideoListHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private MyImageView imageView;
    private TextView nickName;
    private TextView likeCount;

    private OnItemClickListener mListener;


    public VideoListHolder(@NonNull View itemView, OnItemClickListener listener) {
        super(itemView);
        itemView.setOnClickListener(this);
        imageView = itemView.findViewById(R.id.image);
        nickName = itemView.findViewById(R.id.text_nickname);
        likeCount = itemView.findViewById(R.id.text_likecount);

        mListener=listener;
    }

    public void bind(final Video video) {
        // 显示封面图片
        imageView.setImageURL(video.thumbnails);
        Log.d("123", video.thumbnails);
        // 显示用户名
        nickName.setText(video.nickName);
        // 显示点赞数
        likeCount.setText("点赞数：" + video.likeCount);
    }


    public static Bitmap getHttpBitmap(String url) {
        URL myFileURL;
        Bitmap bitmap = null;

        try {
            myFileURL = new URL(url);
            //获得连接
            HttpURLConnection conn = (HttpURLConnection) myFileURL.openConnection();
            //设置超时时间为6000毫秒，conn.setConnectionTiem(0);表示没有时间限制
            conn.setConnectTimeout(6000);
            //连接设置获得数据流
            conn.setDoInput(true);
            //不使用缓存
            conn.setUseCaches(false);
            //这句可有可无，没有影响
            //conn.connect();
            //得到数据流
            InputStream is = conn.getInputStream();
            //解析得到图片
            bitmap = BitmapFactory.decodeStream(is);
            //关闭数据流
            is.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bitmap;
    }

    @Override
    public void onClick(View v) {
        mListener.onItemClick(v,getAdapterPosition());
    }
}
