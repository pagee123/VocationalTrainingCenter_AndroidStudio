package com.example.intent_3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {

    private ImageButton imgButtonTaipei,imgButtonNewTaipei,imgButtonTaoyua,imgButtonHsinchu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imgButtonTaipei = (ImageButton) findViewById(R.id.imageButton_taipei);
        imgButtonNewTaipei = (ImageButton) findViewById(R.id.imageButton_newtaipei);
        imgButtonTaoyua = (ImageButton) findViewById(R.id.imageButton_taoyua);
        imgButtonHsinchu = (ImageButton) findViewById(R.id.imageButton_hsinchu);

        imgButtonTaipei.setOnClickListener(new MyButton());
        imgButtonNewTaipei.setOnClickListener(new MyButton());
        imgButtonTaoyua.setOnClickListener(new MyButton());
        imgButtonHsinchu.setOnClickListener(new MyButton());
    }

    private class MyButton implements View.OnClickListener {
        private String city;

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(MainActivity.this,Activity_display.class);
            switch (v.getId()){
                case R.id.imageButton_taipei:
                    city = getResources().getString(R.string.taipei);
                    intent.putExtra("city",city);
                    intent.putExtra("image",R.drawable.taipei);
                    intent.putExtra("info",R.string.taipei_info);
                    startActivity(intent);
                    break;

                case R.id.imageButton_newtaipei:
                    city = getResources().getString(R.string.newtaipei);
                    intent.putExtra("city",city);
                    intent.putExtra("image",R.drawable.newpei);
                    intent.putExtra("info",R.string.newtaipei_info);
                    startActivity(intent);
                    break;
                case R.id.imageButton_taoyua:
                    city = getResources().getString(R.string.taipei);
                    intent.putExtra("city",city);
                    intent.putExtra("image",R.drawable.tauyuan);
                    intent.putExtra("info",R.string.newtaipei_info);
                    startActivity(intent);
                    break;
                case R.id.imageButton_hsinchu:
                    city = getResources().getString(R.string.hsinchu);
                    intent.putExtra("city",city);
                    intent.putExtra("image",R.drawable.xingju);
                    intent.putExtra("info",R.string.hsinchu_info);
                    startActivity(intent);
                    break;
            }
        }
    }
}