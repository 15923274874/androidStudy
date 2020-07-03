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
        String url = "https://vdept.bdstatic.com/696143476744416263666d3861624756/" +
                "4159756e71356552/eb1e98bc7e9718fc96eec29cbc3135a1154860ff7f13e1ecb6a9745f53e698bda44ad6eb220ad79259" +
                "1774af76536810.mp4?auth_key=1593767024-0-0-e0eeb0169cb84213ba21e5ed5227fd5b";
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
