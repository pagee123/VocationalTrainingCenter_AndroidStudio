package com.app.myapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class MyBroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String sender = intent.getStringExtra("SENDER");
        Toast.makeText(context, "MyBroadcastReceiver收到" + sender + "的廣播訊息",
                Toast.LENGTH_LONG).show();
    }
}
