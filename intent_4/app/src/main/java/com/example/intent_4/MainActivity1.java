package com.example.intent_4;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity1 extends AppCompatActivity {

    private TextView textViewAct1;
    private Intent intent;
    private EditText editTextInput;
    private Button buttonBack;
    private final int ACT1Code=100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main1);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });;
        setTitle("Act - 1");
        
        textViewAct1 = (TextView) findViewById(R.id.textView_act1);
        textViewAct1.setText("");
        intent = getIntent();
        String data1 = intent.getStringExtra("data1");
        textViewAct1.setText(data1);

        editTextInput = (EditText) findViewById(R.id.editText_act1);
        buttonBack = (Button) findViewById(R.id.button_act1);

        buttonBack.setOnClickListener(new View.OnClickListener() {
            private String data;

            @Override
            public void onClick(View v) {
                if(editTextInput.length()==0){
                    data = "No data 1";
                } else {
                    data = editTextInput.getText().toString();
                }

                Intent backIntent = new Intent();
                backIntent.putExtra("name","Act-1");
                backIntent.putExtra("act1",data);
                setResult(ACT1Code,backIntent);
                finish();
            }
        });


    }
}