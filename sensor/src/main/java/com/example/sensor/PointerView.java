package com.example.sensor;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class PointerView extends View implements SensorEventListener {
    private SensorManager mSensorManager;//传感器管理器
    private Bitmap pointer = null;
    private float[] mAllValue;

    public PointerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mSensorManager= (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);//获取传感器管理器
        //注册传感器
        mSensorManager.registerListener(this,mSensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD),mSensorManager.SENSOR_DELAY_GAME);
        pointer = BitmapFactory.decodeResource(super.getResources(),R.drawable.zhinanzheng2);//指定绘制的图
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        //传感器值发生变化
        if(sensorEvent.sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD){
            float[] value = sensorEvent.values;//获取磁场管理器的值
            mAllValue = value;
            super.postInvalidate();//刷新界面
        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {
         //传感器进度发生变化
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //根据磁场强度绘制
        if (mAllValue != null){
            float x = mAllValue[0];
            float y = mAllValue[1];
            try {
                canvas.restore();//重置对象
            }catch (Exception e){
                e.printStackTrace();
            }
            canvas.translate(super.getWidth() / 2, super.getHeight() / 2);//设置旋转中心点
            if (y == 0 && x > 0){
                canvas.rotate(90);//选择90度
            }else if ( y == 0 && x < 0){
                canvas.rotate(270);
            }else {
                if(y >= 0){
                    canvas.rotate((float) (Math.tanh(x / y) * 90));
                }else {
                    canvas.rotate((float) (Math.tanh(x / y) * 90) + 180);
                }
            }
            canvas.drawBitmap(this.pointer,-this.pointer.getWidth() / 2,-this.pointer.getHeight() / 2,new Paint()); // 绘制指针

        }
    }
}
