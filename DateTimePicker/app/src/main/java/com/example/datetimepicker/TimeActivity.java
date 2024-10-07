package com.example.datetimepicker;

import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Calendar;

public class TimeActivity extends AppCompatActivity {

    private TextView textViewTime;
    private TimePicker timePicker;
    private Calendar now;
    private Button buttonTime;
    private TimePickerDialog timeDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_time);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        textViewTime = (TextView)findViewById(R.id.textView_time);
        textViewTime.setText("");
        timePicker = (TimePicker)findViewById(R.id.timePicker_id);
        timePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                textViewTime.setText("Time is :"+hourOfDay+":"+minute);
            }
        });
        now = Calendar.getInstance();
        buttonTime = (Button)findViewById(R.id.button_time);
        buttonTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timeDialog = new TimePickerDialog(TimeActivity.this,new MyTime(),now.get(Calendar.HOUR_OF_DAY),
                now.get(Calendar.MINUTE),false);
                timeDialog.show();
            }
        });
    }


    private class MyTime implements TimePickerDialog.OnTimeSetListener {
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            textViewTime.setText("Dialog time : "+hourOfDay+" : "+minute);
        }
    }
}