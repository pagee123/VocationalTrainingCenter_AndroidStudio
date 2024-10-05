package com.example.intent_1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Activity2 extends AppCompatActivity {

    private Button buttoBack;
    private TextView textViewData;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);

        setTitle("ACT-2");

        Log.d("main","onCreate-ACT2");

        buttoBack = (Button) findViewById(R.id.button_act2Back);
        textViewData = (TextView) findViewById(R.id.textView_act2Data);

        textViewData.setText("");
        intent = getIntent();
        switch (intent.getIntExtra("select",0)){
            case 0:
                String data1 = intent.getStringExtra("input1");
                int data2 = intent.getIntExtra("input2",100);
                textViewData.setText("input1 : "+data1+"\n");
                textViewData.append("input2 : "+data2);
                break;
            case 1 :
                String data = intent.getStringExtra("data");
                textViewData.setText(data);
                break;
        }
        buttoBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    @Override
    protected void onStart() {
        super.onStart();
        Log.d("main","onStart-ACT2");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("main","onStop-ACT2");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("main","onDestroy-ACT2");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("main","onPause-ACT2");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("main","onResume-ACT2");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("main","onRestart-ACT2");
    }
}