package com.example.groudview;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

public class Myadapter extends BaseAdapter {

    private Context mContext;
    private int pictures[];

    public Myadapter(Context mContext,int pictures[]) {
        this.mContext = mContext;
        this.pictures = pictures;

    }

    @Override
    public int getCount() {
        return pictures.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ImageView imageView;
        if(view != null){
            imageView = (ImageView) view;
        }else {
            imageView = new ImageView(mContext);
            imageView.setLayoutParams(new GridView.LayoutParams(150,150));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        }
        Log.i("pictures.index",String.valueOf(pictures[i]));
        imageView.setImageResource(pictures[i]);
        return imageView;
    }
}
