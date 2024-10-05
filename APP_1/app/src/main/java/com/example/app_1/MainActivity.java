package com.example.app_1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView textViewText1,textViewText2;
    private Button buttonChange,buttonText2;

    private boolean textFlag,text2Flag;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewText1 = (TextView) findViewById(R.id.textView2_Text1);
        textViewText2 = (TextView) findViewById(R.id.textView_text2);
        buttonChange = (Button) findViewById(R.id.button_change);
        buttonText2 = (Button) findViewById(R.id.button2_text2);

        textFlag = true;
        text2Flag = true;

        buttonChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(textFlag) {
                    textViewText1.setText("Hello World!!!!");
                    textViewText2.setText("Good Morning");
                    textFlag = false;
                }else{
                    textViewText2.setText("Hello World!!!!");
                    textViewText1.setText("Good Morning");
                    textFlag = true;
                }
            }
        });

        buttonText2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(text2Flag){
                    text2Flag=false;
                    textViewText1.setText("1234567890");
                } else {
                    text2Flag=true;
                    textViewText1.setText("Hello World!!!!!");
                }
            }
        });
    }
}