package com.example.app_3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Button buttonPic1,buttonPic2,buttonPic3;
    private ImageView imageViewPic;
    private TextView textViewResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonPic1 = (Button) findViewById(R.id.button_pic1);
        buttonPic2 = (Button) findViewById(R.id.button_pic2);
        buttonPic3 = (Button) findViewById(R.id.button_pic3);
        
        imageViewPic = (ImageView) findViewById(R.id.imageView_id);
        textViewResult = (TextView) findViewById(R.id.textView_result);

        buttonPic1.setOnClickListener(new MyButton());
        buttonPic2.setOnClickListener(new MyButton());
        buttonPic3.setOnClickListener(new MyButton());
    }

    private class MyButton implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.button_pic1:
                    Log.d("main","Button1 trigger!");
                    imageViewPic.setImageResource(R.drawable.ania_ha);
                    textViewResult.setText("安妮雅不削");
                    break;
                case R.id.button_pic2:
                    Log.d("main","Button2 trigger!");
                    imageViewPic.setImageResource(R.drawable.ania_see);
                    textViewResult.setText("安妮雅可愛");
                    break;
                case R.id.button_pic3:
                    Log.d("main","Button3 trigger!");
                    imageViewPic.setImageResource(R.drawable.ania_shock);
                    textViewResult.setText("安妮雅震驚");
                    break;
            }
        }
    }
}