package com.example.mytiktok.ui;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mytiktok.R;
import com.example.mytiktok.Video;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;


public class ViewHolder extends RecyclerView.ViewHolder {

    private ImageView imageView;
    private TextView nickName;
    private TextView likeCount;

    public ViewHolder(@NonNull View itemView) {
        super(itemView);

        imageView = itemView.findViewById(R.id.image);
        nickName = itemView.findViewById(R.id.text_nickname);
        likeCount = itemView.findViewById(R.id.text_likecount);
    }

    public void bind(final Video video) {
        // 显示封面图片
        Bitmap bitmap = getHttpBitmap(video.avatar);
        imageView.setImageBitmap(bitmap);

        nickName.setText(video.nickName);

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

}
