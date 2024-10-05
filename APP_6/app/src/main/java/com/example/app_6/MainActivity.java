package com.example.app_6;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView textViewResult;
    private ImageButton imgButtonScissor,imgButtonRock,imgButtonPapper;
    private ImageView imageViewComputer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewResult = (TextView) findViewById(R.id.textView_result);
        textViewResult.setText("");
        imageViewComputer = (ImageView) findViewById(R.id.imageView4);
        imageViewComputer.setVisibility(View.INVISIBLE);

        imgButtonScissor = (ImageButton) findViewById(R.id.imageButton_scissor);
        imgButtonRock = (ImageButton) findViewById(R.id.imageButton_rock);
        imgButtonPapper = (ImageButton) findViewById(R.id.imageButton_papper);

        imgButtonScissor.setOnClickListener(new MyClass());
        imgButtonRock.setOnClickListener(new MyClass());
        imgButtonPapper.setOnClickListener(new MyClass());


    }
    private class MyClass implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            int number = (int) ((Math.random() * 3) + 1);
            imageViewComputer.setVisibility(View.VISIBLE);
            Log.d("main","number:"+number);
            switch (v.getId()){
                case R.id.imageButton_scissor:
                    Log.d("main","scissor");
                    switch (number){
                        case 1:
                            imageViewComputer.setImageResource(R.drawable.scissors);
                            textViewResult.setText(getString(R.string.drow));
                            break;
                        case 2:
                            imageViewComputer.setImageResource(R.drawable.rock);
                            textViewResult.setText(getString(R.string.lose));
                            break;
                        case 3:
                            imageViewComputer.setImageResource(R.drawable.paper);
                            textViewResult.setText(getString(R.string.win));
                            break;
                    }
                    break;

                case R.id.imageButton_rock:
                    Log.d("main","rock");
                    switch (number){
                        case 1:
                            imageViewComputer.setImageResource(R.drawable.scissors);
                            textViewResult.setText(getString(R.string.win));
                            break;
                        case 2:
                            imageViewComputer.setImageResource(R.drawable.rock);
                            textViewResult.setText(getString(R.string.drow));
                            break;
                        case 3:
                            imageViewComputer.setImageResource(R.drawable.paper);
                            textViewResult.setText(getString(R.string.lose));
                            break;
                    }
                    break;
                case R.id.imageButton_papper:
                    Log.d("main","papper");
                    switch (number){
                        case 1:
                            imageViewComputer.setImageResource(R.drawable.scissors);
                            textViewResult.setText(getString(R.string.lose));
                            break;
                        case 2:
                            imageViewComputer.setImageResource(R.drawable.rock);
                            textViewResult.setText(getString(R.string.win));
                            break;
                        case 3:
                            imageViewComputer.setImageResource(R.drawable.paper);
                            textViewResult.setText(getString(R.string.drow));
                            break;
                    }
                    break;
            }
        }
    }

}