package com.example.app_10;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity {

    private TextInputEditText textInputPassword;
    private Button buttonId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        textInputPassword = (TextInputEditText) findViewById(R.id.textInput_id);
        buttonId = (Button) findViewById(R.id.button_ok);

        buttonId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Password="+textInputPassword.getText(), Toast.LENGTH_SHORT).show();
            }
        });

        
    }
}