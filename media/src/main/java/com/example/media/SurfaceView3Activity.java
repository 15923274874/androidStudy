package com.example.media;

import android.app.Activity;
import android.hardware.Camera;
import android.media.MediaRecorder;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import java.io.File;
import java.io.IOException;

public class SurfaceView3Activity extends Activity {

    public static final int FENBIANLV_X = 1920;
    public static final int FENBIANLV_Y = 1080;
    private Button mStop;
    private Button mRecord;
    private SurfaceView mSurfaceView;
    private File mVoidFile;
    private MediaRecorder mMediaRecorder;
    private Camera mCamera;
    private boolean mIsRecord = false;//记录录像状态
    private LinearLayout mLinearLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.surface_view_activity3);
        if(!android.os.Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
            Toast.makeText(this, "请按照sd卡", Toast.LENGTH_SHORT).show();
        }


        mRecord = findViewById(R.id.record);
        mStop = findViewById(R.id.stop);
        mLinearLayout = findViewById(R.id.linearLayout3);
        mLinearLayout.setOnClickListener(mOnClickListener);
        mRecord.setOnClickListener(mOnClickListener);
        mStop.setOnClickListener(mOnClickListener);

        //开始摄像头参数设置
        mSurfaceView = findViewById(R.id.surfaceView3);
        mSurfaceView.getHolder().setFixedSize(FENBIANLV_X, FENBIANLV_Y);//设置分辨率


    }


    private View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @RequiresApi(api = Build.VERSION_CODES.O)
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.record:
                    record();
                    break;
                case R.id.stop:
                    //停止录制并提示保存位置
                    if (mIsRecord){
                        mMediaRecorder.stop();
                    }
                    mMediaRecorder.release();
                    Toast.makeText(SurfaceView3Activity.this, "视频保存在"+mVoidFile, Toast.LENGTH_SHORT).show();
                    break;
                case R.id.linearLayout3:
                    Toast.makeText(SurfaceView3Activity.this, "自动对焦", Toast.LENGTH_SHORT).show();
                    mCamera.autoFocus(null);//自动对焦
                    break;
            }
        }
    };

    //录制视频
    @RequiresApi(api = Build.VERSION_CODES.O)
    private void record(){
        File filePath = new File(Environment.getExternalStorageDirectory(),"/MyVideo/");
        if (!filePath.exists()){
            filePath.mkdir();//创建对象
        }
        String fileName = "video.mp4";//视频文件的名称
        mVoidFile = new File(filePath,fileName);
        mMediaRecorder = new MediaRecorder();
        mCamera.setDisplayOrientation(90);//调整摄像头角度
        mCamera.unlock();//摄像头解锁
        mMediaRecorder.setCamera(mCamera);
        mMediaRecorder.reset();
        mMediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);//设置麦克风
        mMediaRecorder.setVideoSource(MediaRecorder.VideoSource.CAMERA);//使用摄像头获取图像
        mMediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);//设置视频输出格式为mp4
        mMediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.DEFAULT);//设置音频编码格式
        mMediaRecorder.setVideoEncoder(MediaRecorder.VideoEncoder.MPEG_4_SP);//设置视频编码格式
        mMediaRecorder.setVideoEncodingBitRate(FENBIANLV_X*FENBIANLV_Y);//设置清晰度
        mMediaRecorder.setVideoSize(FENBIANLV_X,FENBIANLV_Y);//设置视频尺寸
        mMediaRecorder.setVideoFrameRate(10);//设置每秒10帧
        mMediaRecorder.setOutputFile(mVoidFile.getAbsoluteFile());//设置视频输出绝对路径
        mMediaRecorder.setPreviewDisplay(mSurfaceView.getHolder().getSurface());//设置使用的surfaceView
        mMediaRecorder.setOrientationHint(90);//设置播放视频的角度
        try {
            mMediaRecorder.prepare();//准备录像
            mMediaRecorder.start();//开始录制
            Toast.makeText(this, "开始录制", Toast.LENGTH_SHORT).show();
            mIsRecord = true;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    //当Activity获取焦点时
    @Override
    protected void onResume() {
        mCamera = Camera.open();//开启摄像头
        super.onResume();
    }

    //当Activity失去焦点时
    @Override
    protected void onPause() {
        super.onPause();
        mCamera.stopPreview();//停止预览
        mCamera.release();//释放资源
    }
}
