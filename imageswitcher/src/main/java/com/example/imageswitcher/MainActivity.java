package com.example.imageswitcher;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.ViewSwitcher;

public class MainActivity extends AppCompatActivity {

    private ImageSwitcher mImageSwitcher;
    private int[] arrayPicture = new int[]{
            R.drawable.qingwa,
            R.drawable.niao,
            R.drawable.yezhi,
    };
    private int mIndex;
    private float touchDownX;
    private float touchUpX;

    private View.OnTouchListener mOnTouchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            if(motionEvent.getAction() == MotionEvent.ACTION_DOWN){
                MainActivity.this.touchDownX = motionEvent.getX();
                return true;
            }else if(motionEvent.getAction() == MotionEvent.ACTION_UP){
                MainActivity.this.touchUpX = motionEvent.getX();

                if(touchDownX - touchUpX > 100){
                    mIndex = mIndex == 0? 2 : mIndex - 1;
                    mImageSwitcher.setInAnimation(AnimationUtils.loadAnimation(MainActivity.this,android.R.anim.slide_out_right));
                    mImageSwitcher.setOutAnimation(AnimationUtils.loadAnimation(MainActivity.this,android.R.anim.slide_in_left));
                    mImageSwitcher.setImageResource(arrayPicture[mIndex]);
                }
                if(touchDownX - touchUpX < 100){
                    mIndex = mIndex == 2? 0 : mIndex + 1;
                    mImageSwitcher.setInAnimation(AnimationUtils.loadAnimation(MainActivity.this,android.R.anim.slide_in_left));
                    mImageSwitcher.setOutAnimation(AnimationUtils.loadAnimation(MainActivity.this,android.R.anim.slide_out_right));
                    mImageSwitcher.setImageResource(arrayPicture[mIndex]);
                }

                return true;
            }
            return false;
        }

    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mImageSwitcher = findViewById(R.id.imageSwitcher);
        mImageSwitcher.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                ImageView imageView = new ImageView(MainActivity.this);
                imageView.setImageResource(arrayPicture[mIndex]);
                return imageView;
            }
        });
        mImageSwitcher.setOnTouchListener(mOnTouchListener);
    }
}
