package com.example.app_2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView tittle, counter,result;
    private Button trigger,reset;

    int cnt = 0;
    String cnt_s = cnt+"";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tittle = (TextView) findViewById(R.id._sayHi);
        counter = (TextView) findViewById(R.id.textView_Count);
        result = (TextView) findViewById(R.id.textView2_result);
        trigger = (Button) findViewById(R.id.button_trigger);
        reset = (Button) findViewById(R.id.button_reset);

        trigger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cnt++;
                cnt_s = cnt+"";
                if(cnt == 10) {
                    result.setVisibility(result.VISIBLE);
                    result.setText("Congraduration!!!! to 10");
                }else{
                    result.setVisibility(result.INVISIBLE);
                    result.setText("");
                }
                counter.setText(cnt_s);
            }
        });

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cnt = 0;
                cnt_s = cnt + "";
                    result.setVisibility(result.INVISIBLE);
                counter.setText(cnt_s);
            }
        });
    }
}


