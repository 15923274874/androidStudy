package com.example.alphaanima;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ViewFlipper;

public class MainActivity extends AppCompatActivity implements GestureDetector.OnGestureListener {

    private ViewFlipper mViewFlipper;
    GestureDetector mGestureDetector;//手势检测器
    final int distance = 50;//手势动作最小距离
    final int index = 0;

    private int[] images = new int[]{
            R.drawable.hua,
            R.drawable.niao,
            R.drawable.qiche,
            R.drawable.qingwa,
            R.drawable.yezhi
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        final ImageView imageView = findViewById(R.id.imageView);
//        imageView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Log.i("test","点击了图片");
//                Animation animation  = AnimationUtils.loadAnimation(MainActivity.this,R.anim.alpha);//透明的
//                Animation animation  = AnimationUtils.loadAnimation(MainActivity.this,R.anim.rotate);//旋转
//                Animation animation  = AnimationUtils.loadAnimation(MainActivity.this,R.anim.scale);//缩放
//                Animation animation  = AnimationUtils.loadAnimation(MainActivity.this,R.anim.translate);//平移
//                imageView.startAnimation(animation);
//            }
//        });

        mGestureDetector = new GestureDetector(this,this);
        mViewFlipper = findViewById(R.id.flipper);
        for (int i = 0; i < images.length; i++){
            ImageView imageView = new ImageView(this);
            imageView.setImageResource(images[i]);
            mViewFlipper.addView(imageView);
        }
        //定义初始化数组
        Animation[] animations = new Animation[2];
        animations[0] = AnimationUtils.loadAnimation(this,R.anim.anim_alpha_in);
        animations[1] = AnimationUtils.loadAnimation(this,R.anim.anim_alpha_out);

        //指定切换动画
        mViewFlipper.setInAnimation(animations[0]);
        mViewFlipper.setOutAnimation(animations[1]);
    }

    @Override
    public boolean onDown(MotionEvent motionEvent) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent motionEvent) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent motionEvent) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent motionEvent) {

    }

    @Override
    public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {

        //从右向左
        if (motionEvent.getX() - motionEvent1.getX() > distance){
            mViewFlipper.showPrevious();
            return true;
        }
        if (motionEvent.getX() - motionEvent1.getX() < distance){
            mViewFlipper.showNext();
            return true;
        }
        return false;
    }

    //将触摸事件交给mGestureDetector处理
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return mGestureDetector.onTouchEvent(event);
    }
}
