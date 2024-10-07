package com.example.datetimepicker;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    private TextView textViewDate;
    private DatePicker datePicker;
    private Calendar now;
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

        textViewDate = (TextView)findViewById(R.id.textView_date);
        textViewDate.setText("");

        now = Calendar.getInstance();
        textViewDate.setText("Current date :"+now.get(Calendar.YEAR)+"/"+(now.get(Calendar.MONTH)+1)+"/"+now.get(Calendar.DAY_OF_MONTH));

        datePicker = (DatePicker)findViewById(R.id.datePicker_id);
        datePicker.setOnDateChangedListener(new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                textViewDate.setText("Select Date : "+year+"/"+(monthOfYear+1)+"/"+dayOfMonth);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.setup,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case R.id.menu_time:
                intent = new Intent(MainActivity.this,TimeActivity.class);
                startActivity(intent);
                break;
            case R.id.menu_date:
                DatePickerDialog dateDialog = new DatePickerDialog(MainActivity.this,new MyDate(),
                        now.get(Calendar.YEAR),now.get(Calendar.MONTH),now.get(Calendar.DAY_OF_MONTH));
                dateDialog.show();
                break;

        }

        return super.onOptionsItemSelected(item);
    }

    private class MyDate implements DatePickerDialog.OnDateSetListener {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            textViewDate.setText("Select Date : "+year+"/"+(month+1)+"/"+dayOfMonth);
        }
    }
}