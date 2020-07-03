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
        String url = "https://vdept.bdstatic.com/696143476744416263666d3861624756/" +
                "4159756e71356552/eb1e98bc7e9718fc96eec29cbc3135a1154860ff7f13e1ecb6a9745f53e" +
                "698bda44ad6eb220ad792591774af76536810.mp4?auth_key=1593767024-0-0-e0eeb0169cb84213ba21e5ed5227fd5b";
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
