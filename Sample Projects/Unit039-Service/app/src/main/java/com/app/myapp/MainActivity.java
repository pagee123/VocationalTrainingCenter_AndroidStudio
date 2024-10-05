package com.app.myapp;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private final String LOG_TAG = "ServiceDemo";

    private MyService mMyService= null;

    private ServiceConnection mServiceConn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            Log.d(LOG_TAG, "onServiceConnected() " +
                    componentName.getClassName());
            mMyService = ((MyService.LocalBinder) iBinder).getService();
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            Log.d(LOG_TAG, "onServiceDisconnected()" +
                    componentName.getClassName());
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 取得Button元件
        Button btnStartService = findViewById(R.id.btnStartService);
        Button btnStopService = findViewById(R.id.btnStopService);
        Button btnBindService = findViewById(R.id.btnBindService);
        Button btnUnbindService = findViewById(R.id.btnUnbindService);
        Button btnCallServiceMethod = findViewById(R.id.btnCallServiceMethod);

        // 設定Button的OnClickListener
        btnStartService.setOnClickListener(btnStartServiceOnClick);
        btnStopService.setOnClickListener(btnStopServiceOnClick);
        btnBindService.setOnClickListener(btnBindServiceOnClick);
        btnUnbindService.setOnClickListener(btnUnbinderviceOnClick);
        btnCallServiceMethod.setOnClickListener(btnCallServiceMethodOnClick);
    }

    private View.OnClickListener btnStartServiceOnClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            mMyService = null;
            Intent it = new Intent(MainActivity.this, MyService.class);
            startService(it);
        }
    };

    private View.OnClickListener btnStopServiceOnClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            mMyService = null;
            Intent it = new Intent(MainActivity.this, MyService.class);
            stopService(it);
        }
    };

    private View.OnClickListener btnBindServiceOnClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            mMyService = null;
            Intent it = new Intent(MainActivity.this, MyService.class);
            bindService(it, mServiceConn, BIND_AUTO_CREATE);
        }
    };

    private View.OnClickListener btnUnbinderviceOnClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            mMyService = null;
            unbindService(mServiceConn);
        }
    };

    private View.OnClickListener btnCallServiceMethodOnClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (mMyService != null)
                mMyService.myMethod();
        }
    };
}
