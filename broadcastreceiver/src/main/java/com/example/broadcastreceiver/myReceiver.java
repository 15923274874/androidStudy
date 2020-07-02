package com.example.broadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class myReceiver extends BroadcastReceiver {

    public static final String ACTION_1 = "lds";
    public static final String ACTION_2 = "mayun";

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(ACTION_1)){
            Toast.makeText(context, "收到"+ACTION_1+"广播", Toast.LENGTH_SHORT).show();
        }
        if (intent.getAction().equals(ACTION_2)){
            Toast.makeText(context, "收到"+ACTION_2+"广播", Toast.LENGTH_SHORT).show();
        }
    }
}
