package com.app.myapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class MainActivity extends AppCompatActivity {

    private static final String FILE_NAME = "data.txt";

    private EditText mEdtIn;
    private TextView mTxtFileContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mEdtIn = findViewById(R.id.edtIn);
        mTxtFileContent = findViewById(R.id.txtFileContent);

        Button btnWrite = findViewById(R.id.btnWrite);
        Button btnRead = findViewById(R.id.btnRead);
        Button btnClear = findViewById(R.id.btnClear);

        btnWrite.setOnClickListener(btnWriteOnClick);
        btnRead.setOnClickListener(btnReadOnClick);
        btnClear.setOnClickListener(btnClearOnClick);
    }

    private View.OnClickListener btnWriteOnClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            FileOutputStream fileOut = null;
            BufferedOutputStream bufFileOut = null;

            try {
                fileOut = openFileOutput(FILE_NAME, MODE_APPEND);
                bufFileOut = new BufferedOutputStream(fileOut);
                bufFileOut.write(mEdtIn.getText().toString().getBytes());
                bufFileOut.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };

    private View.OnClickListener btnReadOnClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            FileInputStream fileIn = null;
            BufferedInputStream bufFileIn = null;

            try {
                fileIn = openFileInput(FILE_NAME);
                bufFileIn = new BufferedInputStream(fileIn);

                byte[] bufBytes = new byte[10];

                mTxtFileContent.setText("");

                do {
                    int c = bufFileIn.read(bufBytes);

                    if (c == -1)
                        break;
                    else
                        mTxtFileContent.append(new String(bufBytes), 0, c);
                } while (true);

                bufFileIn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };

    private View.OnClickListener btnClearOnClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            FileOutputStream fileOut = null;

            try {
                fileOut = openFileOutput(FILE_NAME, MODE_PRIVATE);
                fileOut.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };
}
