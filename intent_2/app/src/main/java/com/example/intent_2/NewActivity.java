package com.example.intent_2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class NewActivity extends AppCompatActivity {

    private Intent intent;
    private ConstraintLayout layoutId;
    private Button buttonBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new);

        intent = getIntent();
        String title = intent.getStringExtra("title");
        setTitle(title);

        int picId = intent.getIntExtra("pic",R.drawable.frilan);

        layoutId = (ConstraintLayout) findViewById(R.id.layout_id);
        layoutId.setBackgroundResource(picId);

        buttonBack = (Button) findViewById(R.id.button_newBack);

        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}