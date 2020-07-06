package com.example.sensor;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class SpiritylevalView extends View implements SensorEventListener {
    private Bitmap mBitmap;
    float [] acceler = new float[3];//加速度值
    float[] magnet = new float[3];//磁场
    private int MAX_ANGILE = 30;//最大倾斜角度
    private int bubbleX,bubbleY;//球的角度

    public SpiritylevalView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        SensorManager sensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
        sensorManager.registerListener(this,sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD),SensorManager.SENSOR_DELAY_GAME);//磁场
        sensorManager.registerListener(this,sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),SensorManager.SENSOR_DELAY_GAME);//加速度
        mBitmap = BitmapFactory.decodeResource(getResources(),R.drawable.boll);//加载球
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {//值改变
        if (sensorEvent.sensor.getType() == Sensor.TYPE_ACCELEROMETER){
            acceler = sensorEvent.values.clone();
        }else if (sensorEvent.sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD){
            magnet = sensorEvent.values.clone();
        }
        float[] R = new float[9];//保存旋转数据
        float[] values = new float[3];//保存方向
        SensorManager.getRotationMatrix(R,null,acceler,magnet);//获得一个包含旋转矩阵的数组
        SensorManager.getOrientation(R,values);//获取方向值
        //获取x，y旋转角度
        float xAngle = (float) Math.toDegrees(values[1]);
        float yAngle = (float) Math.toDegrees(values[2]);
        getPosition(xAngle,yAngle);
        super.postInvalidate();//刷新界面
    }

    /**
     * 获取小球位置坐标
     * @param xAngle
     * @param yAngle
     */
    private void getPosition(float xAngle, float yAngle) {
        int x = (super.getWidth() - mBitmap.getWidth()) / 2;
        int y = (super.getHeight() - mBitmap.getHeight()) / 2;

        //控制x坐标
        if (Math.abs(yAngle) <= MAX_ANGILE){
            int deltaX = (int)((super.getWidth() - mBitmap.getWidth()) / 2 * yAngle / MAX_ANGILE);
            x -= deltaX;
        }else if (yAngle > MAX_ANGILE){
            x = 0;
        }else {
            x = super.getWidth() - mBitmap.getWidth();
        }


        //控制坐标
        if (Math.abs(yAngle) <= MAX_ANGILE){
            int deltaY = (int)((super.getHeight() - mBitmap.getHeight()) / 2 * xAngle / MAX_ANGILE);
            y += deltaY;
        }else if (xAngle > MAX_ANGILE){
            y = super.getHeight() - mBitmap.getHeight();
        }else {
            y = 0;
        }

        bubbleX = x;
        bubbleY = y;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(mBitmap,bubbleX,bubbleY,new Paint());//绘制球

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {//精度改变

    }
}
