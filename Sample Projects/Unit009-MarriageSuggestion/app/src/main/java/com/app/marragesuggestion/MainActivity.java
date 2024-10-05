package com.app.marragesuggestion;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView mTxtResult;
    private Button mBtnDo;

    private RadioGroup mRadGrpGender, mRadGrpAge;

    private NumberPicker mNumPickerAge;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mNumPickerAge = findViewById(R.id.numPickerAge);
        mNumPickerAge.setMinValue(0);
        mNumPickerAge.setMaxValue(200);
        mNumPickerAge.setValue(25);

        mTxtResult = findViewById(R.id.txtResult);
        mBtnDo = findViewById(R.id.btnDo);

        mBtnDo.setOnClickListener(btnDoOnClick);

        mRadGrpGender = findViewById(R.id.radGrpGender);
    }

    private View.OnClickListener btnDoOnClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            // 從字串資源取出預先定義好的suggestion字串
            String strSug = getString(R.string.suggestion);

            // 取得年齡
            int age = mNumPickerAge.getValue();

            // 檢查性別
            switch (mRadGrpGender.getCheckedRadioButtonId()) {
                case R.id.radBtnMale:   // 男生
                    if (age < 28)
                        strSug += getString(R.string.not_hurry);
                    else if (age > 33)
                        strSug += getString(R.string.get_married);
                    else
                        strSug += getString(R.string.find_couple);

                    break;
                case R.id.radBtnFemale:   // 女生
                    if (age < 25)
                        strSug += getString(R.string.not_hurry);
                    else if (age > 30)
                        strSug += getString(R.string.get_married);
                    else
                        strSug += getString(R.string.find_couple);

                    break;
            }

            // 把結果顯示在mTxtResult元件中
            mTxtResult.setText(strSug);
        }
    };
}
