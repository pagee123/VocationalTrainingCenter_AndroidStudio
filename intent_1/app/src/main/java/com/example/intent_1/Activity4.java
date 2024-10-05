package com.example.intent_1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Activity4 extends AppCompatActivity {

    private Button buttonBack,buttonToAct2;
    private TextView textViewData;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_4);

        setTitle("ACT-4");

        buttonBack = (Button) findViewById(R.id.button_act4back);
        buttonToAct2 = (Button) findViewById(R.id.button_act4Goact2);
        textViewData = (TextView) findViewById(R.id.textView_act4Data);

        textViewData.setText("");
        intent = getIntent();
        String data = intent.getStringExtra("data");
        textViewData.setText(data);

        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        buttonToAct2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String data = textViewData.getText().toString();
                Intent intent = new Intent(Activity4.this, Activity2.class);
                intent.putExtra("data",data);
                intent.putExtra("select",1);
                startActivity(intent);
            }
        });

    }
}