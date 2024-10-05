package com.example.intent_1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private Button buttonAct1,buttonAct2,buttonAct3;
    private EditText editTextInput1,editTextInput2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonAct1 = (Button) findViewById(R.id.button_mainAct1);
        buttonAct2 = (Button) findViewById(R.id.button_mainAct2);
        buttonAct3 = (Button) findViewById(R.id.button_mainAct3);
        editTextInput1 = (EditText) findViewById(R.id.editText_mainInput1);
        editTextInput2 = (EditText) findViewById(R.id.editText_mainInput2);

        buttonAct1.setOnClickListener(new View.OnClickListener() {
            private int input2;
            private String input1;

            @Override
            public void onClick(View v) {

                if(editTextInput1.length()==0){
                    input1 = "No text";
                }else{
                    input1 = editTextInput1.getText().toString();
                }

                if(editTextInput2.length()==0){
                    input2 = 0;
                }else {
                    input2 = Integer.parseInt(editTextInput2.getText().toString());
                }
                Intent intentAct1 = new Intent(MainActivity.this,Activity1.class);
                intentAct1.putExtra("input1",input1);
                intentAct1.putExtra("input2",input2);
                startActivity(intentAct1);
            }
        });
        buttonAct2.setOnClickListener(new View.OnClickListener() {
            private int input2;
            private String input1;

            @Override
            public void onClick(View v) {
                if(editTextInput1.length()==0){
                    input1 = "No data";
                }else{
                    input1 = editTextInput1.getText().toString();
                }

                if(editTextInput2.length()==0){
                    input2 = 100;
                }else {
                    input2 = Integer.parseInt(editTextInput2.getText().toString());
                }
                Intent intentAct2 = new Intent(MainActivity.this,Activity2.class);
                intentAct2.putExtra("input1",input1);
                intentAct2.putExtra("input2",input2);
                intentAct2.putExtra("select",0);
                startActivity(intentAct2);
            }
        });
        buttonAct3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentAct3 = new Intent(MainActivity.this,Activity3.class);
                startActivity(intentAct3);
            }
        });
        Log.d("main","onCreate-M");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("main","onStart-M");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("main","onStop-M");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("main","onDestroy-M");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("main","onPause-M");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("main","onResume-M");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("main","onRestart-M");
    }
}