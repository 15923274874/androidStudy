package com.example.media;

import android.app.Activity;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.annotation.Nullable;

public class VideoViewActivity extends Activity {

    private VideoView mVideoView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.video_view_activity);
        mVideoView = findViewById(R.id.videoView);
        String url = "https://vdept.bdstatic.com/39676a395359487138585571484e6766/427542396b797758/5fc483f448e6b26eee878103dd089a5a23adc94baaadf944e7f8f02ebb1a9a2ffcd27f1854268f0ba7ea2fcf66fcf5456b9c8e3f35a306f02c349c89a3f734f1.mp4?auth_key=1593751585-0-0-ecdc20c9023e510e88246ae3637d267a";
        Uri uri = Uri.parse(url);
        //加载播放视频
        mVideoView.setVideoURI(uri);//指定播放视频


        //控制视频播放

        MediaController mediaController = new MediaController(VideoViewActivity.this);//创建对象
        mVideoView.setMediaController(mediaController);//关联
        mVideoView.requestFocus();//获得焦点
        mVideoView.start();
        mVideoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                Toast.makeText(VideoViewActivity.this, "视频播放完毕", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
