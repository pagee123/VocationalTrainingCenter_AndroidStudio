package com.app.marragesuggestion;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText mEdtGender, mEdtAge;
    private TextView mTxtResult;
    private Button mBtnDo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mEdtGender = findViewById(R.id.edtGender);
        mEdtAge = findViewById(R.id.edtAge);
        mTxtResult = findViewById(R.id.txtResult);
        mBtnDo = findViewById(R.id.btnDo);

        mBtnDo.setOnClickListener(btnDoOnClick);
    }

    private View.OnClickListener btnDoOnClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            // 取得使用者輸入的性別和年齡
            String strSex = mEdtGender.getText().toString();
            int iAge = Integer.parseInt(mEdtAge.getText().toString());

            // 從字串資源取出預先定義好的suggestion字串
            String strSug = getString(R.string.suggestion);

            // 比對輸入的性別是否是male
            if (strSex.equals(getString(R.string.male))) {
                if (iAge < 28)
                    strSug += getString(R.string.not_hurry);
                else if (iAge > 33)
                    strSug += getString(R.string.get_married);
                else
                    strSug += getString(R.string.find_couple);
            } else {
                if (iAge < 25)
                    strSug += getString(R.string.not_hurry);
                else if (iAge > 30)
                    strSug += getString(R.string.get_married);
                else
                    strSug += getString(R.string.find_couple);
            }

            // 把結果顯示在mTxtResult元件中
            mTxtResult.setText(strSug);
        }
    };
}
