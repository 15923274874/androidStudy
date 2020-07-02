package com.example.actionbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button show;
    private Button hidden;


    private View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.show:
                    actionBar.show();
                    break;
                case R.id.hidden:
                    actionBar.hide();
                    break;
            }
        }
    };
    private ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        actionBar = getSupportActionBar();
        show = findViewById(R.id.show);
        hidden = findViewById(R.id.hidden);
        show.setOnClickListener(mOnClickListener);
        hidden.setOnClickListener(mOnClickListener);
    }


    //解析菜单文件
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    //菜单点击事件
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.my:
                Toast.makeText(this, "点击了我的", Toast.LENGTH_SHORT).show();
                break;
            case R.id.love:
                Toast.makeText(this, "点击了爱心", Toast.LENGTH_SHORT).show();
                break;
            case R.id.settings:
                Toast.makeText(this, "点击了设置", Toast.LENGTH_SHORT).show();
                break;
            case R.id.about:
                Toast.makeText(this, "点击了关于我们", Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onContextItemSelected(item);
    }
}
