package com.example.intent_3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class Activity_display extends AppCompatActivity {

    private ImageView imageViewPic;
    private TextView textViewData;
    private Button buttonBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);

        imageViewPic = (ImageView) findViewById(R.id.imageView_id);
        textViewData = (TextView) findViewById(R.id.textView_data);
        buttonBack = (Button) findViewById(R.id.button_back);

        Intent intent = getIntent();

        String title = intent.getStringExtra("city");
        setTitle(title);

        int image = intent.getIntExtra("image",R.drawable.taipei);
        imageViewPic.setImageResource(image);

        int data = intent.getIntExtra("info",R.string.taipei_info);
        textViewData.setText(getResources().getString(data));

        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}