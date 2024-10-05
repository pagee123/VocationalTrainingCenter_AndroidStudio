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

public class MainActivity2 extends AppCompatActivity {

    private TextView textViewData;
    private Button buttonBack;
    private EditText editViewData;
    private final int ACT2Code = 200;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main2);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        setTitle("Act-2");

        textViewData = (TextView) findViewById(R.id.textView_act2);
        textViewData.setText("");
        Intent intent = getIntent();
        String data2 = intent.getStringExtra("data2");
        textViewData.setText(data2);
        editViewData = (EditText)findViewById(R.id.editText_act2);


        buttonBack = (Button) findViewById(R.id.button_act2);

        buttonBack.setOnClickListener(new View.OnClickListener() {
            private String data;

            @Override
            public void onClick(View v) {
                if(editViewData.length()==0){
                    data = "No data 2";
                }else{
                    data = editViewData.getText().toString();
                }
                Intent backIntent = new Intent();
                backIntent.putExtra("name","ACT 2");
                backIntent.putExtra("act2",data);
                setResult(ACT2Code,backIntent);
                finish();
            }
        });
    }
}