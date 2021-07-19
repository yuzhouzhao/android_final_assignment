package com.example.mytiktok;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {
    // https://beiyou.bytedance.com/api/invoke/video/invoke/video
    @GET("api/invoke/video/invoke/video")
    Call<VideoResponse> getVideos();
}
