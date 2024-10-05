package com.example.ex_3;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class CityActivity extends AppCompatActivity {

    private int city_code;
    private ImageView imgViewCity;
    private TextView textViewCity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_city);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        Intent intent = getIntent();
        imgViewCity = (ImageView)findViewById(R.id.imageView_city);
        textViewCity = (TextView)findViewById(R.id.textView_cityinfo);
        textViewCity.setText("");

        city_code = intent.getIntExtra("city",0);

        switch(city_code){
            case 0:
                setTitle(R.string.taipei);
                imgViewCity.setImageResource(R.drawable.taipei);
                textViewCity.setText(R.string.taipei_info);
                break;
            case 1:
                setTitle(R.string.newtaipei);
                imgViewCity.setImageResource(R.drawable.newpei);
                textViewCity.setText(R.string.newtaipei_info);
                break;
            case 2:
                setTitle(R.string.taoyua);
                imgViewCity.setImageResource(R.drawable.tauyuan);
                textViewCity.setText(R.string.taoyua_info);
                break;
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}