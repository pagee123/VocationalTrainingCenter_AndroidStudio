package com.app.game;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView mTxtResult;
    private ImageButton mImgBtnScissors, mImgBtnStone, mImgBtnPaper;
    private ImageView mImgViewCom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mImgViewCom = findViewById(R.id.imgViewCom);
        mTxtResult = findViewById(R.id.txtResult);
        mImgBtnScissors = findViewById(R.id.imgBtnScissors);
        mImgBtnStone = findViewById(R.id.imgBtnStone);
        mImgBtnPaper = findViewById(R.id.imgBtnPaper);

        // 設定剪刀、石頭、布三個按鈕的Click Listener
        mImgBtnScissors.setOnClickListener(imgBtnScissorsOnClick);
        mImgBtnStone.setOnClickListener(imgBtnStoneOnClick);
        mImgBtnPaper.setOnClickListener(imgBtnPaperOnClick);
    }

    private View.OnClickListener imgBtnScissorsOnClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            // 電腦亂數出拳.
            int iComPlay = (int)(Math.random()*3 + 1);

            // 1:剪刀, 2:石頭, 3:布
            switch (iComPlay) {
                case 1:   // 平手
                    mImgViewCom.setImageResource(R.drawable.scissors);
                    mTxtResult.setText(getString(R.string.draw));
                    break;
                case 2:   // 玩家輸
                    mImgViewCom.setImageResource(R.drawable.stone);
                    mTxtResult.setText(getString(R.string.lose));
                    break;
                case 3:   // 玩家贏
                    mImgViewCom.setImageResource(R.drawable.paper);
                    mTxtResult.setText(getString(R.string.win));
                    break;
            }
        }
    };

    private View.OnClickListener imgBtnStoneOnClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            // 電腦亂數出拳.
            int iComPlay = (int)(Math.random()*3 + 1);

            // 1:剪刀, 2:石頭, 3:布
            switch (iComPlay) {
                case 1:   // 玩家贏
                    mImgViewCom.setImageResource(R.drawable.scissors);
                    mTxtResult.setText(getString(R.string.win));
                    break;
                case 2:   // 平手
                    mImgViewCom.setImageResource(R.drawable.stone);
                    mTxtResult.setText(getString(R.string.draw));
                    break;
                case 3:   // 玩家輸
                    mImgViewCom.setImageResource(R.drawable.paper);
                    mTxtResult.setText(getString(R.string.lose));
                    break;
            }
        }
    };

    private View.OnClickListener imgBtnPaperOnClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            // 電腦亂數出拳.
            int iComPlay = (int)(Math.random()*3 + 1);

            // 1:剪刀, 2:石頭, 3:布
            switch (iComPlay) {
                case 1:   // 玩家輸
                    mImgViewCom.setImageResource(R.drawable.scissors);
                    mTxtResult.setText(getString(R.string.lose));
                    break;
                case 2:   // 玩家贏
                    mImgViewCom.setImageResource(R.drawable.stone);
                    mTxtResult.setText(getString(R.string.win));
                    break;
                case 3:   // 平手
                    mImgViewCom.setImageResource(R.drawable.paper);
                    mTxtResult.setText(getString(R.string.draw));
                    break;
            }
        }
    };
}
