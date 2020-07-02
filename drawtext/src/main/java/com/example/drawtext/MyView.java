package com.example.drawtext;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
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
//        //绘制文字
        Paint paint = new Paint();
//        paint.setColor(0xFF000000);
//        paint.setAntiAlias(true);
//        paint.setTextAlign(Paint.Align.LEFT);//左对齐
//        paint.setTextSize(30);
//        canvas.drawText("测试绘制文字",175,160,paint);
//        canvas.drawText("测试绘制文字2",375,660,paint);

        paint.setAntiAlias(true);
        paint.setColor(0xFF0000FF);
        paint.setTextSize(20);
        paint.setStyle(Paint.Style.STROKE);//设置描边
        Path path = new Path();
        path.addCircle(150,100,50,Path.Direction.CW);//创建圆形路径
//        canvas.drawPath(path,paint);//绘制路径
        canvas.drawTextOnPath("这是绕路径的文本",path,0,0,paint);
    }
}
