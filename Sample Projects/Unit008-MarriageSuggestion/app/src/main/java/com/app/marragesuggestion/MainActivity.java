package com.app.marragesuggestion;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

//    private EditText mEdtGender, mEdtAge;
    private TextView mTxtResult;
    private Button mBtnDo;

    private RadioGroup mRadGrpGender, mRadGrpAge;
    private RadioButton mRadBtnAge1, mRadBtnAge2, mRadBtnAge3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        mEdtGender = findViewById(R.id.edtGender);
//        mEdtAge = findViewById(R.id.edtAge);
        mTxtResult = findViewById(R.id.txtResult);
        mBtnDo = findViewById(R.id.btnDo);

        mBtnDo.setOnClickListener(btnDoOnClick);

        mRadGrpGender = findViewById(R.id.radGrpGender);
        mRadGrpAge = findViewById(R.id.radGrpAge);
        mRadBtnAge1 = findViewById(R.id.radBtnAge1);
        mRadBtnAge2 = findViewById(R.id.radBtnAge2);
        mRadBtnAge3 = findViewById(R.id.radBtnAge3);
        mRadGrpGender.setOnCheckedChangeListener(radGrpGenderOnCheckedChange);
    }

    private View.OnClickListener btnDoOnClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            // 取得使用者輸入的性別和年齡
//            String strSex = mEdtGender.getText().toString();
//            int iAge = Integer.parseInt(mEdtAge.getText().toString());

            // 從字串資源取出預先定義好的suggestion字串
            String strSug = getString(R.string.suggestion);

            switch (mRadGrpAge.getCheckedRadioButtonId()) {
                case R.id.radBtnAge1:
                    strSug += getString(R.string.not_hurry);
                    break;
                case R.id.radBtnAge2:
                    strSug += getString(R.string.find_couple);
                    break;
                case R.id.radBtnAge3:
                    strSug += getString(R.string.get_married);
                    break;
            }

            // 把結果顯示在mTxtResult元件中
            mTxtResult.setText(strSug);
        }
    };

    private RadioGroup.OnCheckedChangeListener radGrpGenderOnCheckedChange = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup radioGroup, int i) {
            if (i == R.id.radBtnMale) {
                mRadBtnAge1.setText(getString(R.string.radbtn_male_age1));
                mRadBtnAge2.setText(getString(R.string.radbtn_male_age2));
                mRadBtnAge3.setText(getString(R.string.radbtn_male_age3));
            } else {
                mRadBtnAge1.setText(getString(R.string.radbtn_female_age1));
                mRadBtnAge2.setText(getString(R.string.radbtn_female_age2));
                mRadBtnAge3.setText(getString(R.string.radbtn_female_age3));
            }
        }
    };
}
