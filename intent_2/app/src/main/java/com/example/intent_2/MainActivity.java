package com.example.intent_2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {

    private ImageButton imgButton1,imgButton2,imgButton3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imgButton1 = (ImageButton) findViewById(R.id.imageButton1);
        imgButton2 = (ImageButton) findViewById(R.id.imageButton2);
        imgButton3 = (ImageButton) findViewById(R.id.imageButton3);

        imgButton1.setOnClickListener(new MyButton());
        imgButton2.setOnClickListener(new MyButton());
        imgButton3.setOnClickListener(new MyButton());

//        imgButton1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent1 = new Intent(MainActivity.this, Activity1.class);
//                startActivity(intent1);
//            }
//        });
//        imgButton2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent2 = new Intent(MainActivity.this, Activity2.class);
//                startActivity(intent2);
//            }
//        });
//        imgButton3.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent3 = new Intent(MainActivity.this, Activity3.class);
//                startActivity(intent3);
//            }
//        });
    }

    private class MyButton implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(MainActivity.this , NewActivity.class);
            switch (v.getId()){
                case R.id.imageButton1:
                    intent.putExtra("title","芙莉蓮");
                    intent.putExtra("pic",R.drawable.frilan);
                    break;
                case R.id.imageButton2:
                    intent.putExtra("title","鈴芽之旅");
                    intent.putExtra("pic",R.drawable.gate);
                    break;
                case R.id.imageButton3:
                    intent.putExtra("title","拉格納");
                    intent.putExtra("pic",R.drawable.sliver);
                    break;
            }
            startActivity(intent);
        }
    }
}