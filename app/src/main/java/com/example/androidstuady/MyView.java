package com.example.androidstuady;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class MyView extends View {

    private float drowX;
    private float drowY;

    public float getDrowX() {
        return drowX;
    }

    public void setDrowX(float drowX) {
        this.drowX = drowX;
    }

    public float getDrowY() {
        return drowY;
    }

    public void setDrowY(float drowY) {
        this.drowY = drowY;
    }

    public MyView(Context context) {
        super(context,null);
    }

    public MyView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs,0);
    }

    public MyView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        drowX = 200;
        drowY = 200;

    }

    @Override
    protected void onDraw(Canvas canvas) {
        Paint paint = new Paint();
        Bitmap bitmap = BitmapFactory.decodeResource(this.getResources(),R.mipmap.renwu);
        canvas.drawBitmap(bitmap,drowX,drowY,paint);
        if(bitmap.isRecycled()){
            bitmap.recycle();
        }
    }

}
