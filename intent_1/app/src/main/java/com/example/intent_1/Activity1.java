package com.example.intent_1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Activity1 extends AppCompatActivity {

    private Button buttonBack,buttonTo;
    private TextView textViewData;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_1);

        setTitle("ACT-1");

        Log.d("main","onCreate-ACT1");

        buttonBack = (Button) findViewById(R.id.button_act1Back);
        buttonTo = (Button) findViewById(R.id.button_act1ToAct4);
        textViewData = (TextView) findViewById(R.id.textView_act1Data);

        textViewData.setText("");

        intent = getIntent();
        String data1 = intent.getStringExtra("input1");
        int data2 = intent.getIntExtra("input2",0);
        textViewData.setText("input1 : "+data1+"\n");
        textViewData.append("input2 : "+data2);



        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        buttonTo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String data = textViewData.getText().toString();

                Intent intent = new Intent(Activity1.this, Activity4.class);
                intent.putExtra("data",data);
                startActivity(intent);
            }
        });

    }
    @Override
    protected void onStart() {
        super.onStart();
        Log.d("main","onStart-ACT1");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("main","onStop-ACT1");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("main","onDestroy-ACT1");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("main","onPause-ACT1");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("main","onResume-ACT1");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("main","onRestart-ACT1");
    }
}