package com.example.app_4;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private ImageButton imgbuttonPic1,imgbuttonPic2,imgbuttonPic3;
    private ImageView ImageViewPic;
    private TextView textViewData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imgbuttonPic1 = (ImageButton) findViewById(R.id.imageButton_pic1);
        imgbuttonPic2 = (ImageButton) findViewById(R.id.imageButton_pic2);
        imgbuttonPic3 = (ImageButton) findViewById(R.id.imageButton_pic3);
        
        ImageViewPic = (ImageView) findViewById(R.id.imageView_id);
        textViewData = (TextView) findViewById(R.id.textView_data);

        imgbuttonPic1.setOnClickListener(new MyButton());
        imgbuttonPic2.setOnClickListener(new MyButton());
        imgbuttonPic3.setOnClickListener(new MyButton());
    }


    private class MyButton implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.imageButton_pic1:
                    Log.d("main","Button1 Trigger");
                    ImageViewPic.setImageResource(R.drawable.ania_ha);
                    textViewData.setText("安妮雅不削");
                    break;
                case R.id.imageButton_pic2:
                    Log.d("main","Button2 Trigger");
                    ImageViewPic.setImageResource(R.drawable.ania_see);
                    textViewData.setText("安妮雅可愛");
                    break;
                case R.id.imageButton_pic3:
                    Log.d("main","Button3 Trigger");
                    ImageViewPic.setImageResource(R.drawable.ania_shock);
                    textViewData.setText("安妮雅震驚");
                    break;
            }
        }
    }
}