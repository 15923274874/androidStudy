package com.example.draw;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class MyView extends View {


    public MyView(Context context) {
        super(context,null);
    }

    public MyView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs,0);
    }

    public MyView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint = new Paint();


//        paint.setColor(0xFFFF6600);
//        paint.setStyle(Paint.Style.FILL);
//        canvas.drawRect(10,10,280,150,paint);

        paint.setAntiAlias(true);//抗锯齿开启
        paint.setColor(0xFFA4C739);

        //画头
        RectF rectF = new RectF(40,40,250,250);//定义外廓矩形
        rectF.offset(90,20);
        canvas.drawArc(rectF,-10,-160,false,paint);

        //绘制眼睛
        paint.setColor(0xFFFFFFFF);
        canvas.drawCircle(200,100,10,paint);
        canvas.drawCircle(260,100,10,paint);

        //画天线
        paint.setColor(0xFFA4C739);
        paint.setStrokeWidth(6);
        canvas.drawLine(140,40,170,100,paint);
        canvas.drawLine(340,40,300,100,paint);

        //绘制身体
        paint.setColor(0xFFA4C739);
        paint.setStrokeWidth(1);
        canvas.drawRect(130,170,340,200,paint);
        RectF rectF_body = new RectF(130,180,340,360);//定义外廓矩形
        canvas.drawRoundRect(rectF_body,10,10,paint);

        //绘制胳膊
        RectF rectF_left = new RectF(85,195,120,345);//定义外廓矩形
        canvas.drawRoundRect(rectF_left,20,20,paint);

        RectF rectF_right = new RectF(350,195,385,345);//定义外廓矩形
        canvas.drawRoundRect(rectF_right,20,20,paint);

        //绘制脚
        RectF rectF_foot_left = new RectF(160,340,195,430);//定义外廓矩形
        canvas.drawRoundRect(rectF_foot_left,20,20,paint);

        RectF rectF_foot_right = new RectF(275,340,310,430);//定义外廓矩形
        canvas.drawRoundRect(rectF_foot_right,20,20,paint);
    }
}
