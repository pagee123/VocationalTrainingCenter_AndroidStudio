package com.app.myapp;

import android.app.Dialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Button mBtnLoginDlg;
    private TextView mTxtResult;
    private Dialog mDlgLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mBtnLoginDlg = findViewById(R.id.btnLoginDlg);
        mTxtResult = findViewById(R.id.txtResult);

        mBtnLoginDlg.setOnClickListener(btnLoginDlgOnClick);
    }

    private View.OnClickListener btnLoginDlgOnClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            mTxtResult.setText("");

            mDlgLogin = new Dialog(MainActivity.this);
            mDlgLogin.setCancelable(false);
            mDlgLogin.setContentView(R.layout.dlg_login);
            Button loginBtnOK = mDlgLogin.findViewById(R.id.btnOK);
            Button loginBtnCancel = mDlgLogin.findViewById(R.id.btnCancel);
            loginBtnOK.setOnClickListener(loginDlgBtnOKOnClick);
            loginBtnCancel.setOnClickListener(loginDlgBtnCancelOnClick);
            mDlgLogin.show();
        }
    };

    private View.OnClickListener loginDlgBtnOKOnClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            EditText edtUserName = mDlgLogin.findViewById(R.id.edtUserName);
            EditText edtPassword = mDlgLogin.findViewById(R.id.edtPassword);

            mTxtResult.setText("輸入的使用者名稱：" + edtUserName.getText().toString() +
                    "，密碼：" + edtPassword.getText().toString());
            mDlgLogin.dismiss();
        }
    };

    private View.OnClickListener loginDlgBtnCancelOnClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            mTxtResult.setText("你按下取消按鈕");
            mDlgLogin.dismiss();
        }
    };
}
