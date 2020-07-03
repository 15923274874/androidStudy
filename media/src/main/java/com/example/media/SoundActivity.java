package com.example.media;

import android.app.Activity;
import android.media.AudioAttributes;
import android.media.SoundPool;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import java.util.HashMap;

public class SoundActivity extends Activity {
    private String[] items = new String[]{"铃声1","铃声2","铃声3","铃声4"};
    private ListView mListView;
    private SoundPool mSoundPool;
    private HashMap<Integer, Integer> mHashMap;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sound_activity);
        mListView = findViewById(R.id.soundListView);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(SoundActivity.this,
                android.R.layout.simple_list_item_1, items);
        mListView.setAdapter(adapter);
        mListView.setOnItemClickListener(mOnItemClickListener);

        //创建对象设置属性
        AudioAttributes audioAttributes = new AudioAttributes.Builder()
                .setUsage(AudioAttributes.USAGE_MEDIA)//设置使用场景
                .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)//设置音效类型
                .build();
        mSoundPool = new SoundPool.Builder()
                .setAudioAttributes(audioAttributes)//设置音效池
                .setMaxStreams(10)//设置最多容纳10个音频流
                .build();
        //将播放的音频保存HashMap中
        mHashMap = new HashMap<Integer, Integer>();
        mHashMap.put(0, mSoundPool.load(this,R.raw.lingsheng1,1));
        mHashMap.put(1, mSoundPool.load(this,R.raw.lingsheng2,1));
        mHashMap.put(2, mSoundPool.load(this,R.raw.lingsheng3,1));
        mHashMap.put(3, mSoundPool.load(this,R.raw.lingsheng4,1));
    }

    private AdapterView.OnItemClickListener mOnItemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            mSoundPool.play(mHashMap.get(i),1,1,0,0,1);//播放音频
        }
    };
}
