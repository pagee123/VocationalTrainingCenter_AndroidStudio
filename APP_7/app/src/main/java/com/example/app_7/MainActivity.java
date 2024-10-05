package com.example.app_7;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText editTextName,editTextId,editTextMath,editTextEng;
    private Button buttonCancel,buttonOk;
    private TextView textViewResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextName = (EditText) findViewById(R.id.editText_name);
        editTextId = (EditText) findViewById(R.id.editText_id);
        editTextMath = (EditText) findViewById(R.id.editText_math);
        editTextEng = (EditText) findViewById(R.id.editText_eng);
        
        buttonCancel = (Button) findViewById(R.id.button_cancel);
        buttonOk = (Button) findViewById(R.id.button_ok);
        
        textViewResult = (TextView) findViewById(R.id.textView_result);

        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editTextName.setText("");
                editTextId.setText("");
                editTextMath.setText("");
                editTextEng.setText("");
                textViewResult.setText("");
            }
        });

        buttonOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(editTextName.length() == 0 || editTextId.length() == 0){
                    Toast.makeText(MainActivity.this,"Please input name & id",Toast.LENGTH_SHORT).show();
                }else {
                    String name = editTextName.getText().toString();
                    String id = editTextId.getText().toString();
                    textViewResult.setText("Name : "+name+"\n");
                    textViewResult.append("ID : "+id+"\n");
                }
                if(editTextEng.length() == 0 && editTextMath.length() == 0){
                    Toast.makeText(MainActivity.this, "Please input Score", Toast.LENGTH_SHORT).show();
                }else{
                    String math = editTextMath.getText().toString();
                    String eng = editTextEng.getText().toString();
                    if((Integer.parseInt(math)>100 || Integer.parseInt(math)<0) || (Integer.parseInt(eng)>100 || Integer.parseInt(eng)<0)){
                        Toast.makeText(MainActivity.this, "Please input Score(0~100)", Toast.LENGTH_SHORT).show();
                    }else {
                        int sum = Integer.parseInt(math) + Integer.parseInt(eng);
                        textViewResult.append("Math Score : " + math + "\n");
                        textViewResult.append("English Score : " + eng + "\n");
                        textViewResult.append("Total Score : " + sum + "\n");
                    }
                }
            }
        });


    }
}