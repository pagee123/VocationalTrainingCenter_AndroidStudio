package com.app.myapp;

import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private MyBroadcastReceiver mMyReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnRegReceiver = findViewById(R.id.btnRegReceiver);
        btnRegReceiver.setOnClickListener(btnRegReceiverOnClick);

        Button btnUnregReceiver = findViewById(R.id.btnUnregReceiver);
        btnUnregReceiver.setOnClickListener(btnUnregReceiverOnClick);

        Button btnSendBroadcast = findViewById(R.id.btnSendBroadcast);
        btnSendBroadcast.setOnClickListener(btnSendBroadcastOnClick);
    }

    private View.OnClickListener btnRegReceiverOnClick =
            new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            IntentFilter itFilter = new IntentFilter("com.app.myapp.MY_BROADCAST");
            mMyReceiver = new MyBroadcastReceiver();
            registerReceiver(mMyReceiver, itFilter);
        }
    };

    private View.OnClickListener btnUnregReceiverOnClick =
            new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            unregisterReceiver(mMyReceiver);
        }
    };

    private View.OnClickListener btnSendBroadcastOnClick =
            new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent it = new Intent("com.app.myapp.MY_BROADCAST");
            it.putExtra("SENDER", "MainActivity");
            sendBroadcast(it);
        }
    };
}
