package com.example.media;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.PixelFormat;
import android.hardware.Camera;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class SurfaceView2Activity extends Activity {

    private Button mTackPhoto;
    private Button mPreview;
    private SurfaceView mSurfaceView;
    private boolean isPreview = false;//是否为预览

    //定义摄像头对象
    private Camera mCamera;
    private SurfaceHolder mSurfaceHolder;

   final Camera.PictureCallback jpeg = new Camera.PictureCallback() {
        @Override
        public void onPictureTaken(byte[] bytes, Camera camera) {
            //保存照片
            Bitmap bitmap = BitmapFactory.decodeByteArray(bytes,0,bytes.length);//根据拍照数据创建位图
            camera.stopPreview();//停止预览
            isPreview = false;

            File appDir = new File(Environment.getExternalStorageDirectory(),"/DCIM/Camera/");
            if(!appDir.exists()){
                appDir.mkdir();
            }

            String fileName = System.currentTimeMillis() +".jpg";//根据当前时间设置名称
            File file  = new File(appDir,fileName);

            try {
                FileOutputStream fileOutputStream = new FileOutputStream(file);
                bitmap.compress(Bitmap.CompressFormat.JPEG,100,fileOutputStream);
                fileOutputStream.flush();
                fileOutputStream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

            //插入系统图库
                try {
                    MediaStore.Images.Media.insertImage(SurfaceView2Activity.this.getContentResolver(),file.getAbsolutePath(),fileName,null);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            SurfaceView2Activity.this.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.parse("file://"+"")));
            Toast.makeText(SurfaceView2Activity.this, "保存至"+file, Toast.LENGTH_SHORT).show();
            resetCamera();//重新预览
        }
    };

    private void resetCamera() {
        if(!isPreview){
            mCamera.startPreview();
            isPreview  = true;
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.surface_view_activity2);
        if(!android.os.Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
            Toast.makeText(this, "请按照sd卡", Toast.LENGTH_SHORT).show();
        }
        if(!this.hasCamera()){
            Toast.makeText(this, "该设备不支持摄像", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(this, "该设备支持摄像", Toast.LENGTH_SHORT).show();
        }



        mTackPhoto = findViewById(R.id.tackPhoto);
        mPreview = findViewById(R.id.preview);
        mSurfaceView = findViewById(R.id.surfaceView2);
        mPreview.setOnClickListener(mOnClickListener);
        mTackPhoto.setOnClickListener(mOnClickListener);
        //打开摄像头并预览

        mSurfaceHolder = mSurfaceView.getHolder();
        mSurfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);//自己不维护缓冲

    }

   private View.OnClickListener mOnClickListener = new View.OnClickListener() {
       @Override
       public void onClick(View view) {
           switch (view.getId()){
               case R.id.preview:
                   if (!isPreview){
                       mCamera = Camera.open();//打开摄像头
                       isPreview = true;
                   }
                   try {
                       mCamera.setPreviewDisplay(mSurfaceHolder);
                       Camera.Parameters parameters = mCamera.getParameters();//获取摄像头参数
                       parameters.setPictureFormat(PixelFormat.JPEG);//设置图片类型
                       parameters.set("jpeg-quality",80);//设置图片质量
                       mCamera.setParameters(parameters);//设置摄像头参数
                       mCamera.startPreview();//开始预览
                       mCamera.autoFocus(null);//自动对焦
                   } catch (IOException e) {
                       e.printStackTrace();
                   }
                   break;

                   //实现拍照
               case R.id.tackPhoto:
                    if (mCamera != null){
                        mCamera.takePicture(null,null,jpeg);
                    }
                   break;
           }
       }
   };

    @Override
    protected void onPause() {
        super.onPause();
        if(mCamera != null){
            mCamera.stopPreview();//停止预览
        }
        mCamera.release();//释放资源
    }

    //查看是否支持摄像头
    private boolean hasCamera(){
        boolean hasCamera = false;
        PackageManager pm=SurfaceView2Activity.this.getPackageManager();
        hasCamera=pm.hasSystemFeature(PackageManager.FEATURE_CAMERA)||pm.hasSystemFeature(PackageManager.FEATURE_CAMERA_FRONT)||
                Build.VERSION.SDK_INT<Build.VERSION_CODES.GINGERBREAD||Camera.getNumberOfCameras()>0;
        return hasCamera;
    }
}
