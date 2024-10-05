package com.app.myapp;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    // 宣告FirstFrament和SecondFragment物件
    private FirstFragment mFirstFrag;
    private SecondFragment mSecondFrag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 取得二個Button元件，並設定它們的Listener
        Button btnShowFrag1 = findViewById(R.id.btnShowFrag1);
        btnShowFrag1.setOnClickListener(btnShowFrag1OnClick);
        Button btnShowFrag2 = findViewById(R.id.btnShowFrag2);
        btnShowFrag2.setOnClickListener(btnShowFrag2OnClick);

        // 建立Fragment物件，然後把它們放到FrameLayout裏頭
        mFirstFrag = new FirstFragment();
        mSecondFrag = new SecondFragment();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.frameLay, mFirstFrag, "First Fragment")
                .add(R.id.frameLay, mSecondFrag, "Second Fragment")
                .hide(mSecondFrag)
                .commit();
    }

    // 第一個Button的Listener
    private View.OnClickListener btnShowFrag1OnClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            // 顯示FirstFragment，隱藏SecondFragment
            getSupportFragmentManager().beginTransaction()
                    .show(mFirstFrag)
                    .hide(mSecondFrag)
                    .commit();
        }
    };

    // 第二個Button的Listener
    private View.OnClickListener btnShowFrag2OnClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            // 顯示SecondFragment，隱藏FirstFragment
            getSupportFragmentManager().beginTransaction()
                    .show(mSecondFrag)
                    .hide(mFirstFrag)
                    .commit();
        }
    };
}
