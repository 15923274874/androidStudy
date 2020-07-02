package com.example.groudview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.GridView;

public class MainActivity extends AppCompatActivity {

    private int[] pictures= new int[]{
            R.drawable.hua,
            R.drawable.niao,
            R.drawable.qiche,
            R.drawable.qingwa,
            R.drawable.wenzi,
            R.drawable.xiaoxin,
            R.drawable.yezhi,
    };
    private GridView mGridView;
    private Myadapter myadapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mGridView = findViewById(R.id.gridView);
        myadapter = new Myadapter(this,pictures);
        mGridView.setAdapter(myadapter);
    }
}
