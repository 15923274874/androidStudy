package com.example.store;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class SharedActivity extends Activity {

    private Button mSaveBtn;
    private EditText mUserNameText;
    private EditText mPassWordText;
    private SharedPreferences mSharedPreferences;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shared_activity);
        mSaveBtn = findViewById(R.id.save);
        mUserNameText = findViewById(R.id.userName);
        mPassWordText = findViewById(R.id.password);
        mSaveBtn.setOnClickListener(mOnClickListener);
        //获取Shared对象
        //设置保存的文件名和权限，当前权限为本应用可用
        mSharedPreferences = getSharedPreferences("lds",MODE_PRIVATE);
        String userNameInShared = mSharedPreferences.getString("userName",null);//获取账号
        String passWordInShared = mSharedPreferences.getString("passWord",null);
        if (userNameInShared != null && passWordInShared != null){
            if(userNameInShared.equals("ldsTest") && passWordInShared.equals("123456")){
                Toast.makeText(this, "自动登录验证成功", Toast.LENGTH_SHORT).show();
                mUserNameText.setText(userNameInShared);
                mPassWordText.setText(passWordInShared);
            }
        }

    }

    private View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.save:
                    String inUserName = mUserNameText.getText().toString();
                    String inPassWord = mPassWordText.getText().toString();
                    SharedPreferences.Editor editor = mSharedPreferences.edit();
                    if("ldsTest".equals(inUserName) && "123456".equals(inPassWord)){
                        //存储数据
                        editor.putString("userName",inUserName);
                        editor.putString("passWord",inPassWord);
                        editor.commit();//提交信息
                        Toast.makeText(SharedActivity.this, "验证成功,密码已保存", Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(SharedActivity.this, "账号密码错误", Toast.LENGTH_SHORT).show();
                    }
                    break;
            }
        }
    };
}
