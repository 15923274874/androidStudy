package com.example.media;

import android.app.Activity;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.io.IOException;

public class SurfaceViewActivity extends Activity {

    private Button mStart;
    private Button mPause;
    private Button mStop;
    private MediaPlayer mMediaPlayer;
    private SurfaceHolder mSurfaceHolder;
    private SurfaceView mSurfaceView;
    private boolean noPlay = true;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.surface_view_activity);
        mStart = findViewById(R.id.surfaceViewStart);
        mPause = findViewById(R.id.surfaceViewPause);
        mStop = findViewById(R.id.surfaceViewStop);
        mStart.setOnClickListener(mOnClickListener);
        mPause.setOnClickListener(mOnClickListener);
        mStop.setOnClickListener(mOnClickListener);

        mSurfaceView = findViewById(R.id.surfaceView);
        mSurfaceHolder = mSurfaceView.getHolder();
        mMediaPlayer = new MediaPlayer();
        mMediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        mMediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                Toast.makeText(SurfaceViewActivity.this, "视频播放完毕", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
             switch (view.getId()){
                 case R.id.surfaceViewStart:
                     if(noPlay){
                         play();
                         noPlay = false;
                     }else {
                         mMediaPlayer.start();
                     }

                     break;
                 case R.id.surfaceViewPause:
                     if(mMediaPlayer.isPlaying()){
                         mMediaPlayer.pause();
                     }
                     break;
                 case R.id.surfaceViewStop:
                     if(mMediaPlayer.isPlaying()){
                         mMediaPlayer.stop();
                         noPlay = true;
                     }
                     break;
             }
        }
    };
    private void play(){
        String url = "https://vdept.bdstatic.com/39676a395359487138585571484e6766/427542396b797758/5fc483f448e6b26eee878103dd089a5a23adc94baaadf944e7f8f02ebb1a9a2ffcd27f1854268f0ba7ea2fcf66fcf5456b9c8e3f35a306f02c349c89a3f734f1.mp4?auth_key=1593751585-0-0-ecdc20c9023e510e88246ae3637d267a";
        mMediaPlayer.reset();
        mMediaPlayer.setDisplay(mSurfaceHolder);
        try {
            mMediaPlayer.setDataSource(SurfaceViewActivity.this, Uri.parse(url));
            mMediaPlayer.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }
        mMediaPlayer.start();
    }

    @Override
    protected void onDestroy() {
        if(mMediaPlayer != null){
            if(mMediaPlayer.isPlaying()){
                mMediaPlayer.stop();
            }
            mMediaPlayer.release();//释放资源
        }
        super.onDestroy();
    }
}
