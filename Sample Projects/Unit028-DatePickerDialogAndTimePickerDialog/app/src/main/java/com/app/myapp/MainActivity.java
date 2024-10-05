package com.app.myapp;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    private TextView mTxtResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 設定顯示DatePickerDialog的按鈕
        Button btnDateDlg = findViewById(R.id.btnDateDlg);
        btnDateDlg.setOnClickListener(btnDateDlgOnClick);

        // 設定顯示TimePickerDialog的按鈕
        Button btnTimeDlg = findViewById(R.id.btnTimeDlg);
        btnTimeDlg.setOnClickListener(btnTimeDlgOnClick);

        // 用來顯示使用者設定的日期和時間
        mTxtResult = findViewById(R.id.txtResult);
    }

    private View.OnClickListener btnDateDlgOnClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            // 以下是建立並顯示DatePickerDialog的程式碼
            Calendar now = Calendar.getInstance();

            DatePickerDialog datePickerDlg = new DatePickerDialog(MainActivity.this,
                    datePickerDlgOnDateSet,
                    now.get(Calendar.YEAR),
                    now.get(Calendar.MONTH),
                    now.get(Calendar.DAY_OF_MONTH));

            datePickerDlg.setTitle("選擇日期");
            datePickerDlg.setMessage("請選擇適合您的日期");
            datePickerDlg.setIcon(android.R.drawable.ic_dialog_info);
            datePickerDlg.setCancelable(false);
            datePickerDlg.show();
        }
    };

    private View.OnClickListener btnTimeDlgOnClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            // 以下是建立並顯示TimePickerDialog的程式碼
            Calendar now = Calendar.getInstance();

            TimePickerDialog timePickerDlg = new TimePickerDialog(MainActivity.this,
                    timePickerDlgOnTimeSet,
                    now.get(Calendar.HOUR_OF_DAY),
                    now.get(Calendar.MINUTE),
                    true);

            timePickerDlg.setTitle("選擇時間");
            timePickerDlg.setMessage("請選擇適合您的時間");
            timePickerDlg.setIcon(android.R.drawable.ic_dialog_info);
            timePickerDlg.setCancelable(false);
            timePickerDlg.show();
        }
    };

    private DatePickerDialog.OnDateSetListener datePickerDlgOnDateSet = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
            // 按下DatePickerDialog的OK按鈕後執行
            mTxtResult.setText("您選擇的日期是" +
                    String.valueOf(i) + "年" +
                    String.valueOf(i1 + 1) + "月" +
                    String.valueOf(i2) + "日");
        }
    };

    private TimePickerDialog.OnTimeSetListener timePickerDlgOnTimeSet = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker timePicker, int i, int i1) {
            // 按下TimePickerDialog的OK按鈕後執行
            mTxtResult.setText("您選擇的時間是" +
                    String.valueOf(i) + "時" +
                    String.valueOf(i1) + "分");
        }
    };
}
