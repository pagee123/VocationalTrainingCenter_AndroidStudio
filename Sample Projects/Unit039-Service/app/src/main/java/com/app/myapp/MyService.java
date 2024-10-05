package com.app.myapp;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

public class MyService extends Service {

    private final String LOG_TAG = "ServiceDemo";

    public class LocalBinder extends Binder {
        MyService getService() {
            return  MyService.this;
        }
    }

    private LocalBinder mLocalBinder = new LocalBinder();

    public void myMethod() {
        Log.d(LOG_TAG, "myMethod()");
    }

    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.d(LOG_TAG, "onBind()");
        return mLocalBinder;
    }

    @Override
    public void onCreate() {
        Log.d(LOG_TAG, "onCreate()");
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(LOG_TAG, "onStartCommand()");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        Log.d(LOG_TAG, "onDestroy()");
        super.onDestroy();
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.d(LOG_TAG, "onUnbind()");
        return super.onUnbind(intent);
    }
}
