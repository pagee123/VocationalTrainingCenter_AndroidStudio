package com.example.app_8;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText editTextName,editTextDate,editTextIput;
    private RatingBar ratingBarId;
    private Button buttonCancel,buttonOk;
    private TextView textViewResult;

    public void Clear(){
            editTextName.setText("");
            editTextDate.setText("");
            editTextIput.setText("");
            ratingBarId.setRating(3);
            textViewResult.setText("");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextName = (EditText) findViewById(R.id.editText_name);
        editTextDate = (EditText) findViewById(R.id.editText_date);
        editTextIput = (EditText) findViewById(R.id.editText_input);
        ratingBarId = (RatingBar) findViewById(R.id.ratingBar_id);
        buttonCancel = (Button) findViewById(R.id.button_cancel);
        buttonOk = (Button) findViewById(R.id.button_ok);
        textViewResult = (TextView) findViewById(R.id.textView_result);
        textViewResult.setText("");


        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Clear();
            }
        });

        buttonOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(editTextName.length() == 0 || editTextDate.length() == 0) {
                    Toast.makeText(MainActivity.this, "Please Input Name & Date", Toast.LENGTH_SHORT).show();
                    Clear();
                }else {
                    String name = editTextName.getText().toString();
                    String date = editTextDate.getText().toString();
                    float star = ratingBarId.getRating();
                    String content = editTextIput.getText().toString();
                    textViewResult.setText("Name : " + name + "\n");
                    textViewResult.append("Date : " + date + "\n");
                    textViewResult.append("Star : " + star + "\n");
                    textViewResult.append("Content : " + content + "\n");
                }
            }
        });


    }
}