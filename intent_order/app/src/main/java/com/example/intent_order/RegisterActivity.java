package com.example.intent_order;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class RegisterActivity extends AppCompatActivity {

    private EditText editTextName,editTextEmail,editTextPhone,editTextHeight,editTextWeight;
    private TextView textViewResult;
    private Button buttonCancel,buttonOk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        setTitle("BMI");

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        editTextName = (EditText) findViewById(R.id.editText_name);
        editTextEmail = (EditText) findViewById(R.id.editText_email);
        editTextPhone = (EditText) findViewById(R.id.editText_phone);
        editTextHeight = (EditText) findViewById(R.id.editText_height);
        editTextWeight = (EditText) findViewById(R.id.editText_weight);
        textViewResult = (TextView) findViewById(R.id.textView_result);
        textViewResult.setText("");
        buttonCancel = (Button) findViewById(R.id.button_cancel);
        buttonOk = (Button) findViewById(R.id.button_ok);

        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clear();
            }
        });

        buttonOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setValue();
            }
        });

    }
    public void clear(){
        editTextName.setText("");
        editTextWeight.setText("");
        editTextEmail.setText("");
        editTextHeight.setText("");
        editTextPhone.setText("");
        textViewResult.setText("");
        textViewResult.setCompoundDrawablesWithIntrinsicBounds(0,0,0,0);
    }
    public void setValue(){
        if(editTextName.length()==0 || editTextEmail.length()==0 || editTextPhone.length()==0){
            Toast.makeText(RegisterActivity.this, "Please input data", Toast.LENGTH_SHORT).show();
            textViewResult.setText("");
        }else {
            textViewResult.setText("Name:"+editTextName.getText() + "\n");
            textViewResult.append("Phone:"+editTextPhone.getText() + "\n");
            textViewResult.append("Email:"+editTextEmail.getText() + "\n");

            String name = editTextName.getText().toString();
            String email = editTextEmail.getText().toString();
            String phone = editTextPhone.getText().toString();

            SharedPreferences sharePre = getSharedPreferences("register",MODE_PRIVATE);
            sharePre.edit().putString("name",name).putString("email",email).putString("phone",phone).commit();
        }

        if(editTextWeight.length()==0 || editTextHeight.length() == 0){
            Toast.makeText(RegisterActivity.this, "Please input data", Toast.LENGTH_SHORT).show();
            textViewResult.setText("");
        }else {
            String height = editTextHeight.getText().toString();
            String weight = editTextWeight.getText().toString();
            Log.d("main","height="+Double.parseDouble(height)/100+"m");
            Log.d("main","weight="+Integer.parseInt(weight));
            float heightf = Float.parseFloat(height);
            float weightf = Float.parseFloat(weight);
            float Bmi = weightf*100*100/(heightf*heightf);
            Log.d("main","BMI="+Bmi);
            NumberFormat nf = new DecimalFormat("##.00");
            textViewResult.append("BMI:"+ nf.format(Bmi) + "\n");
            checkBMI(Bmi);
        }
    }

    private void checkBMI(float bmi) {
        if(bmi <18.5){
            textViewResult.append("過輕");
            textViewResult.setCompoundDrawablesWithIntrinsicBounds(0,0,R.drawable.fat_3,0);
        }else if(bmi <25){
            textViewResult.append("正常");
            textViewResult.setCompoundDrawablesWithIntrinsicBounds(0,0,R.drawable.fat_4,0);
        }else if(bmi <30){
            textViewResult.append("過重");
            textViewResult.setCompoundDrawablesWithIntrinsicBounds(0,0,R.drawable.fat_2,0);
        }else{
            textViewResult.append("肥胖");
            textViewResult.setCompoundDrawablesWithIntrinsicBounds(0,0,R.drawable.fat_1,0);
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);
    }
}