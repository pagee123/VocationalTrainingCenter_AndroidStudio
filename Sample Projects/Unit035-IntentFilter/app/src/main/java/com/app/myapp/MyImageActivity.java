package com.app.myapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MyImageActivity extends AppCompatActivity {

    private TextView mTxtResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_image);

        mTxtResult = findViewById(R.id.txtResult);
        showResult();
    }

    private void showResult() {
        Intent it = getIntent();
        String sAct = it.getAction();
        String sScheme = it.getScheme();
        String sType = it.getType();

        if (sScheme.equals("https") && sAct.equals("android.intent.action.VIEW")) {
            String s = "收到Intent物件\n開啟網頁" + it.getData().toString();
            mTxtResult.setText(s);
        } else if (sType.equals("image/*") && sAct.equals("android.intent.action.VIEW")) {
            String s = "收到Intent物件\n開啟影像檔" + it.getData().toString();
            mTxtResult.setText(s);
        } else if (sType.equals("image/*") && sAct.equals("android.intent.action.EDIT")) {
            String s = "收到Intent物件\n編輯影像檔" + it.getData().toString();
            mTxtResult.setText(s);
        }
    }
}
