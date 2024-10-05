package com.app.myapp;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class MarriSugFragment extends Fragment {

    private EditText mEdtGender, mEdtAge;
    private TextView mTxtResult;
    private Button mBtnDo;
    private String msGender;

    public MarriSugFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_marri_sug, container, false);

        mEdtAge = v.findViewById(R.id.edtAge);
        mTxtResult = v.findViewById(R.id.txtResult);
        mBtnDo = v.findViewById(R.id.btnDo);

        mBtnDo.setOnClickListener(btnDoOnClick);

        Spinner spnGender = v.findViewById(R.id.spnGender);
        spnGender.setOnItemSelectedListener(spnGenderOnItemSelected);

        return v;
    }

    private View.OnClickListener btnDoOnClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            // 取得使用者輸入的性別和年齡
//            String strSex = mEdtGender.getText().toString();
            int iAge = Integer.parseInt(mEdtAge.getText().toString());

            // 從字串資源取出預先定義好的suggestion字串
            String strSug = getString(R.string.suggestion);

            // 比對輸入的性別是否是male
//            if (strSex.equals(getString(R.string.male))) {
            if (msGender.equals(getString(R.string.male))) {
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

    private AdapterView.OnItemSelectedListener spnGenderOnItemSelected = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            // 參數i是使用者點選的項目編號，0是第一項，1是第二項，依此類推
            switch (i) {   // 根據使用者選擇的項目設定msGender
                case 0:
                    msGender = getString(R.string.male);
                    break;
                case 1:
                    msGender = "";
                    break;
            }
        }

        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {

        }
    };

}
