package com.example.stringresource;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText editText;


    /**
     * 文本框改变时
     */
    private TextWatcher mTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
             if(editText.length() > 0){
                 mLoginBtn.setEnabled(true);
             }else {
                 mLoginBtn.setEnabled(false);
             }
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    };
    private Button mLoginBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = findViewById(R.id.editText);
        editText.addTextChangedListener(mTextWatcher);
        mLoginBtn = findViewById(R.id.login_btn);

        //为组件绑定上下文菜单
        registerForContextMenu(editText);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = new MenuInflater(MainActivity.this);
        menuInflater.inflate(R.menu.test_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    /**
     * 系统菜单设置
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.settings:
                Toast.makeText(this, "选择了设置", Toast.LENGTH_SHORT).show();
                break;
            case R.id.about:
                Toast.makeText(this, "选择了关于我们", Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * 上下文菜单设置
     * @param menu
     * @param v
     * @param menuInfo
     */
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        MenuInflater menuInflater = new MenuInflater(MainActivity.this);
        menuInflater.inflate(R.menu.touch_menu,menu);
    }

    /**
     * 上下文菜单选择
     * @param item
     * @return
     */
    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_title:
                Toast.makeText(this, "选择了第一个", Toast.LENGTH_SHORT).show();
                break;
            case R.id.menu_title2:
                Toast.makeText(this, "选择了第二个", Toast.LENGTH_SHORT).show();
                break;
            case R.id.menu_title3:
                Toast.makeText(this, "选择了第三个", Toast.LENGTH_SHORT).show();
                break;
            case R.id.menu_title4:
                Toast.makeText(this, "选择了第四个", Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onContextItemSelected(item);
    }
}
