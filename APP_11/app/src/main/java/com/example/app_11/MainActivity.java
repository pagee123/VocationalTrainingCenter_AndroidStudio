package com.example.app_11;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private RadioGroup radioGroupFamily,radioGroupText;
    private RadioButton radioButtonText1,radioButtonText2,radioButtonFamily1,radioButtonFamily2;
    private String data;
    private CheckBox checkboxFamily1,checkboxFamily2;
    private Button buttonFamily,buttonAnother;
    private boolean familyflag;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        radioGroupFamily = (RadioGroup) findViewById(R.id.radioGroup_family);
        radioGroupText = (RadioGroup) findViewById(R.id.radiogroup_text);
        radioButtonText1 = (RadioButton) findViewById(R.id.radioButton_text1);
        radioButtonText2 = (RadioButton) findViewById(R.id.radioButton_text2);
        radioButtonFamily1 = (RadioButton) findViewById(R.id.radioButton_family1);
        radioButtonFamily2 = (RadioButton) findViewById(R.id.radioButton_family2);


        radioGroupFamily.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.radioButton_family1:
                        if(familyflag) {
                            radioButtonText1.setText("It is smart family.");
                            radioButtonText2.setText("It is lazy.");
                        }else{
                            radioButtonText1.setText("It is friran.");
                            radioButtonText2.setText("It is gate.");
                        }
                        break;
                    case R.id.radioButton_family2:
                        if(familyflag) {
                            radioButtonText1.setText("It is cute family.");
                            radioButtonText2.setText("It is fat.");
                        }else{
                            radioButtonText1.setText("It is friren.");
                            radioButtonText2.setText("It is gate.");
                        }
                        break;
                }
            }
        });

        radioGroupText.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.radioButton_text1:
                        data =radioButtonText1.getText().toString();
                        Log.d("main","data text1="+data);
                        break;
                    case R.id.radioButton_text2:
                        data =radioButtonText2.getText().toString();
                        Log.d("main","data text2="+data);
                        break;
                }
            }
        });

        checkboxFamily1 = (CheckBox) findViewById(R.id.checkBox_family1);
        checkboxFamily2 = (CheckBox) findViewById(R.id.checkBox_family2);

        checkboxFamily1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Toast.makeText(MainActivity.this, checkboxFamily1.getText().toString()+":"+isChecked, Toast.LENGTH_SHORT).show();
            }
        });

        checkboxFamily2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(familyflag) {
                    if (isChecked)
                        checkboxFamily2.setText("Cute Family 2");
                    else
                        checkboxFamily2.setText("Family 2");
                }else{
                    if (isChecked)
                        checkboxFamily2.setText("Cute Another 2");
                    else
                        checkboxFamily2.setText("Another 2");
                }
            }
        });
        familyflag = true;
        buttonFamily = (Button) findViewById(R.id.button_family);
        buttonAnother = (Button) findViewById(R.id.button_another);

        buttonFamily.setOnClickListener(new MyButton());
        buttonAnother.setOnClickListener(new MyButton());

    }



    private class MyButton implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.button_family:
                    familyflag = true;
                    radioButtonFamily1.setText("Family 1");
                    radioButtonFamily2.setText("Family 2");
                    radioButtonFamily1.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0,R.drawable.icon1,0);
                    radioButtonFamily2.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0,R.drawable.icon2,0);
                    checkboxFamily1.setText("Family 1");
                    checkboxFamily2.setText("Family 2");
                    checkboxFamily1.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0,R.drawable.icon1,0);
                    checkboxFamily2.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0,R.drawable.icon2,0);
                    radioButtonText1.setText("It is smart Family");
                    radioButtonText2.setText("It is lazy");
                    break;
                case R.id.button_another:
                    familyflag = false;
                    radioButtonFamily1.setText("Another 1");
                    radioButtonFamily2.setText("Another 2");
                    radioButtonFamily1.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0,R.drawable.frilan,0);
                    radioButtonFamily2.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0,R.drawable.gate,0);
                    checkboxFamily1.setText("Another 1");
                    checkboxFamily2.setText("Another 2");
                    checkboxFamily1.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0,R.drawable.frilan,0);
                    checkboxFamily2.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0,R.drawable.gate,0);
                    radioButtonText1.setText("It is animate");
                    radioButtonText2.setText("It is lazy");
                    break;
            }
        }
    }
}