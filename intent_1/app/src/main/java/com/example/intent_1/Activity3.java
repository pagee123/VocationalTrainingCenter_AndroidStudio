package com.example.intent_1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

public class Activity3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_3);

        setTitle("ACT-3");
        Log.d("main","onCreate-ACT3");
    }
    @Override
    protected void onStart() {
        super.onStart();
        Log.d("main","onStart-ACT3");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("main","onStop-ACT3");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("main","onDestroy-ACT3");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("main","onPause-ACT3");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("main","onResume-ACT3");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("main","onRestart-ACT3");
    }
}