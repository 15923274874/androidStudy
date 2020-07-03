package com.example.media;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private MediaPlayer mMediaPlayer;
    private Button mediaStart;
    private Button mediaPause;
    private Button mediaStop;

    private Button soundStart;

    private Button videoView;
    private Button surfaceView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mediaStart = findViewById(R.id.mediaStart);
        mediaPause = findViewById(R.id.mediaPause);
        mediaStop = findViewById(R.id.mediaStop);
        soundStart = findViewById(R.id.soundStart);

        videoView = findViewById(R.id.videoView);
        surfaceView = findViewById(R.id.surfaceView);
        mMediaPlayer = MediaPlayer.create(MainActivity.this,R.raw.test);

        mediaStart.setOnClickListener(mOnClickListener);
        mediaPause.setOnClickListener(mOnClickListener);
        mediaStop.setOnClickListener(mOnClickListener);
        surfaceView.setOnClickListener(mOnClickListener);


        soundStart.setOnClickListener(mOnClickListener);
        videoView.setOnClickListener(mOnClickListener);
    }

    private View.OnClickListener mOnClickListener = new View.OnClickListener() {

        private Intent mIntent;

        @Override
            public void onClick(View view) {
            switch (view.getId()){
                case R.id.mediaStart:
                    //创建media对象并装载音频
                    mMediaPlayer.start();
                    break;
                case R.id.mediaPause:
                    mMediaPlayer.pause();
                    break;
                case R.id.mediaStop:
                     mMediaPlayer.stop();
                    break;

                case R.id.soundStart:
                    mIntent = new Intent(MainActivity.this,SoundActivity.class);
                    startActivity(mIntent);
                    break;
                case R.id.videoView:
                    mIntent = new Intent(MainActivity.this,VideoViewActivity.class);
                    startActivity(mIntent);
                    break;
                case R.id.surfaceView:
                    mIntent = new Intent(MainActivity.this,SurfaceViewActivity.class);
                    startActivity(mIntent);
                    break;
            }
        }
    };

    @Override
    protected void onDestroy() {
        if(mMediaPlayer.isPlaying()){//如果播放暂停
            mMediaPlayer.stop();
        }
        mMediaPlayer.release();//释放资源
        super.onDestroy();
    }
}
