package com.example.mytiktok;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mytiktok.Player.VideoPlayer;
import com.example.mytiktok.Player.VideoPlayerListener;

import tv.danmaku.ijk.media.player.IjkMediaPlayer;

public class VideoActivity extends AppCompatActivity {
    private VideoPlayer videoPlayer;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);

        videoPlayer = findViewById(R.id.videoView);

        //加载native库
        try {
            IjkMediaPlayer.loadLibrariesOnce(null);
            IjkMediaPlayer.native_profileBegin("libijkplayer.so");
        } catch (Exception e) {
            this.finish();
        }
        videoPlayer.setListener(new VideoPlayerListener());

        Intent intent = getIntent();
        String path = intent.getStringExtra("feedurl");
        videoPlayer.setVideoPath(path);
        Log.d("123", path);

    }

    @Override
    protected void onStop() {
        super.onStop();
        if (videoPlayer.isPlaying()) {
            videoPlayer.stop();
        }
        IjkMediaPlayer.native_profileEnd();
    }
}