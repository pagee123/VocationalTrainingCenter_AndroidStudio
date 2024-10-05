package com.app.myapp;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 設定顯示AlertDialog的按鈕
        Button btnAlertDlg = findViewById(R.id.btnAlertDlg);
        btnAlertDlg.setOnClickListener(btnAlertDlgOnClick);

        // 設定顯示Toast訊息的按鈕
        Button btnToastMsg = findViewById(R.id.btnToastMsg);
        btnToastMsg.setOnClickListener(btnToastMsgOnClick);
    }

    private View.OnClickListener btnAlertDlgOnClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            AlertDialog.Builder altDlgBuilder = new AlertDialog.Builder(MainActivity.this);
            altDlgBuilder.setTitle("AlertDialog");
            altDlgBuilder.setMessage("這是AlertDialog對話盒");
            altDlgBuilder.setIcon(android.R.drawable.ic_dialog_info);
            altDlgBuilder.setCancelable(false);

            // 設定AlertDialog對話盒的按鈕
            altDlgBuilder.setPositiveButton("是",
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            // 用Toast訊息顯示按下的按鈕
                            Toast.makeText(MainActivity.this, "你按下「是」按鈕", Toast.LENGTH_LONG)
                                    .show();
                        }
                    });
            altDlgBuilder.setNegativeButton("否",
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            // 用Toast訊息顯示按下的按鈕
                            Toast.makeText(MainActivity.this, "你按下「否」按鈕", Toast.LENGTH_LONG)
                                    .show();
                        }
                    });
            altDlgBuilder.setNeutralButton("取消",
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            // 用Toast訊息顯示按下的按鈕
                            Toast.makeText(MainActivity.this, "你按下「取消」按鈕", Toast.LENGTH_LONG)
                                    .show();
                        }
                    });

            altDlgBuilder.show();

        }
    };

    private View.OnClickListener btnToastMsgOnClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Toast.makeText(MainActivity.this, "這是Toast訊息", Toast.LENGTH_LONG).show();
        }
    };
}
