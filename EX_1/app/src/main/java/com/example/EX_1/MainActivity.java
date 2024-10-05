package com.example.EX_1;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private EditText editTextEmail,editTextName,editTextOther;
    private RadioGroup radiogroupGender;
    private CheckBox checkboxSport,checkboxReading,checkboxPainting,checkboxOther;
    private Button buttonCancel,buttonOk;
    private TextView textviewResult;

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

        editTextName = (EditText) findViewById(R.id.editText_name);
        editTextEmail = (EditText) findViewById(R.id.editText_email);
        editTextOther = (EditText) findViewById(R.id.editText_other);

        radiogroupGender = (RadioGroup) findViewById(R.id.radiogroup_gender);

        checkboxSport = (CheckBox) findViewById(R.id.checkBox_sport);
        checkboxReading = (CheckBox) findViewById(R.id.checkBox_reading);
        checkboxPainting = (CheckBox) findViewById(R.id.checkBox_painting);
        checkboxOther = (CheckBox) findViewById(R.id.checkBox_other);

        buttonCancel = (Button) findViewById(R.id.button_cancel);
        buttonOk = (Button) findViewById(R.id.button_ok);

        textviewResult = (TextView) findViewById(R.id.textView_result);

        buttonCancel.setOnClickListener(new Mybutton());
        buttonOk.setOnClickListener(new Mybutton());

    }

    private class Mybutton implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            switch(view.getId()){
                case R.id.button_cancel:
                    clearChose();
                    break;
                case R.id.button_ok :
                    check2Result();
                    break;

            }

        }
    }

    private void check2Result() {
        if(editTextName.length()==0 || editTextEmail.length()==0 || radiogroupGender.getCheckedRadioButtonId() == -1){
            Toast.makeText(MainActivity.this, "Please input Name & Email & Gender", Toast.LENGTH_SHORT).show();
            textviewResult.setText("");
        }else{
            textviewResult.setText("Name : " + editTextName.getText() + "\n");
            textviewResult.append("Email : " + editTextEmail.getText() + "\n");
            switch(radiogroupGender.getCheckedRadioButtonId()){
                case R.id.radioButton_male:
                    textviewResult.append("Gender : 男生\n");
                    break;
                case R.id.radioButton_Female:
                    textviewResult.append("Gender : 女生\n");
                    break;
            }
            textviewResult.append("興趣 : \n");
            if(checkboxSport.isChecked()){
                textviewResult.append("Sport ");
            }
            if(checkboxReading.isChecked()){
                textviewResult.append("Reading ");
            }
            if(checkboxPainting.isChecked()){
                textviewResult.append("Painting ");
            }
            if(checkboxOther.isChecked()){
                if(editTextOther.length() == 0){
                    Toast.makeText(MainActivity.this, "Please input Other content", Toast.LENGTH_SHORT).show();
                }else {
                    textviewResult.append(editTextOther.getText());
                }
            }

        }

    }

    private void clearChose() {
        editTextName.setText("");
        editTextEmail.setText("");
        editTextOther.setText("");
        textviewResult.setText("");
        radiogroupGender.clearCheck();
        checkboxSport.setChecked(false);
        checkboxPainting.setChecked(false);
        checkboxReading.setChecked(false);
        checkboxOther.setChecked(false);
    }
}