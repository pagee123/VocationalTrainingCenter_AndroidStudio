package com.example.intent_5;

import android.app.SearchManager;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private EditText editTextData;
    private ImageButton imgButtonPhone,imgButtonEmail,imgButtonHttp,imgButtonSearch;
    private String data;
    private Uri uri;
    private String action;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        editTextData = (EditText) findViewById(R.id.editText_data);
        imgButtonPhone = (ImageButton) findViewById(R.id.imageButton_phone);
        imgButtonEmail = (ImageButton) findViewById(R.id.imageButton_email);
        imgButtonHttp = (ImageButton) findViewById(R.id.imageButton_http);
        imgButtonSearch = (ImageButton) findViewById(R.id.imageButton_search);

        imgButtonPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(editTextData.length()==0){
                    data="123456";
                }else{
                    data = editTextData.getText().toString();
                }
                uri = Uri.parse("tel:"+data);
                action = Intent.ACTION_DIAL;
                intent = new Intent(action,uri);
                startActivity(intent);
            }
        });

        imgButtonEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(editTextData.length()==0){
                    data = "aaa@gmail.com";
                }else{
                    data = editTextData.getText().toString();
                }
                uri = Uri.parse("mailto:"+data);
                action = Intent.ACTION_SENDTO;
                intent = new Intent(action,uri);
                startActivity(intent);
            }
        });

        imgButtonHttp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(editTextData.length()==0){
                    data = "tw.yahoo.com";
                }else{
                    data = editTextData.getText().toString();
                }
                uri = Uri.parse("https:"+data);
                action = Intent.ACTION_VIEW;
                intent = new Intent(action,uri);
                startActivity(intent);
            }
        });

        imgButtonSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(editTextData.length()==0){
                    data = "udn";
                }else{
                    data = editTextData.getText().toString();
                }
                action = Intent.ACTION_WEB_SEARCH;
                intent = new Intent(action);
                intent.putExtra(SearchManager.QUERY,data);
                startActivity(intent);
            }
        });
    }
}