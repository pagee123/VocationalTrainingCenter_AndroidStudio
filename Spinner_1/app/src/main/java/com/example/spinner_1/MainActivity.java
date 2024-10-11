package com.example.spinner_1;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.NumberPicker;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private TextView textViewData;
    private Spinner spinner1;
    private Spinner spinner2;
    private ArrayAdapter<CharSequence> spinnerAdapter;
    private NumberPicker numberPicker1,numberPicker2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        textViewData = (TextView)findViewById(R.id.textView_data);
        textViewData.setText("");
        spinner1 = (Spinner)findViewById(R.id.spinner_id);
        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String item = (String) parent.getItemAtPosition(position);
                textViewData.setText("spinner 1:"+item);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinner2 = (Spinner)findViewById(R.id.spinner_id2);
        spinnerAdapter = ArrayAdapter.createFromResource(MainActivity.this,R.array.country, android.R.layout.simple_spinner_item);

        spinnerAdapter.setDropDownViewResource(R.layout.simple_dropdown_item_1line);
        spinner2.setAdapter(spinnerAdapter);
        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String item = (String) parent.getItemAtPosition(position);
                textViewData.setText("spinner 2 :"+item);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        numberPicker1 = (NumberPicker)findViewById(R.id.number_picker1);
        numberPicker1.setMinValue(1);
        numberPicker1.setMaxValue(20);
        numberPicker1.setValue(5);
        numberPicker1.setWrapSelectorWheel(false);
        numberPicker1.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                textViewData.setText("value = "+newVal);
            }
        });
        numberPicker2 = (NumberPicker)findViewById(R.id.number_picker2);
        String[] country = getResources().getStringArray(R.array.country);
        numberPicker2.setMinValue(0);
        numberPicker2.setMaxValue(country.length-1);
        numberPicker2.setDisplayedValues(country);
        numberPicker2.setValue(2);
        numberPicker2.setWrapSelectorWheel(true);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            numberPicker2.setTextSize(70);
            numberPicker2.setTextColor(0xFF00a000);
        }
        numberPicker2.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                textViewData.setText("country = "+country[newVal]);
            }
        });



    }
}