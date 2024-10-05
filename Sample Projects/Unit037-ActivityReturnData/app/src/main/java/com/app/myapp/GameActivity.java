package com.app.myapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class GameActivity extends AppCompatActivity {

    private TextView mTxtResult;
    private ImageButton mImgBtnScissors, mImgBtnStone, mImgBtnPaper;
    private ImageView mImgViewCom;

    private int miCountSet = 0,
            miCountPlayerWin = 0,
            miCountComWin = 0,
            miCountDraw = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        mImgViewCom = findViewById(R.id.imgViewCom);
        mTxtResult = findViewById(R.id.txtResult);
        mImgBtnScissors = findViewById(R.id.imgBtnScissors);
        mImgBtnStone = findViewById(R.id.imgBtnStone);
        mImgBtnPaper = findViewById(R.id.imgBtnPaper);

        // 設定剪刀、石頭、布三個按鈕的Click Listener
        mImgBtnScissors.setOnClickListener(imgBtnScissorsOnClick);
        mImgBtnStone.setOnClickListener(imgBtnStoneOnClick);
        mImgBtnPaper.setOnClickListener(imgBtnPaperOnClick);

        Button btnCloseGame = findViewById(R.id.btnCloseGame);
        btnCloseGame.setOnClickListener(btnCloseGameOnClick);
    }

    private View.OnClickListener imgBtnScissorsOnClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            // 電腦亂數出拳.
            int iComPlay = (int)(Math.random()*3 + 1);

            miCountSet++;   // 累計總局數

            // 1:剪刀, 2:石頭, 3:布
            switch (iComPlay) {
                case 1:   // 平手
                    mImgViewCom.setImageResource(R.drawable.scissors);
                    mTxtResult.setText(getString(R.string.draw));
                    miCountDraw++;
                    break;
                case 2:   // 玩家輸
                    mImgViewCom.setImageResource(R.drawable.stone);
                    mTxtResult.setText(getString(R.string.lose));
                    miCountComWin++;
                    break;
                case 3:   // 玩家贏
                    mImgViewCom.setImageResource(R.drawable.paper);
                    mTxtResult.setText(getString(R.string.win));
                    miCountPlayerWin++;
                    break;
            }
        }
    };

    private View.OnClickListener imgBtnStoneOnClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            // 電腦亂數出拳.
            int iComPlay = (int)(Math.random()*3 + 1);

            miCountSet++;   // 累計總局數

            // 1:剪刀, 2:石頭, 3:布
            switch (iComPlay) {
                case 1:   // 玩家贏
                    mImgViewCom.setImageResource(R.drawable.scissors);
                    mTxtResult.setText(getString(R.string.win));
                    miCountPlayerWin++;
                    break;
                case 2:   // 平手
                    mImgViewCom.setImageResource(R.drawable.stone);
                    mTxtResult.setText(getString(R.string.draw));
                    miCountDraw++;
                    break;
                case 3:   // 玩家輸
                    mImgViewCom.setImageResource(R.drawable.paper);
                    mTxtResult.setText(getString(R.string.lose));
                    miCountComWin++;
                    break;
            }
        }
    };

    private View.OnClickListener imgBtnPaperOnClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            // 電腦亂數出拳.
            int iComPlay = (int)(Math.random()*3 + 1);

            miCountSet++;   // 累計總局數

            // 1:剪刀, 2:石頭, 3:布
            switch (iComPlay) {
                case 1:   // 玩家輸
                    mImgViewCom.setImageResource(R.drawable.scissors);
                    mTxtResult.setText(getString(R.string.lose));
                    miCountComWin++;
                    break;
                case 2:   // 玩家贏
                    mImgViewCom.setImageResource(R.drawable.stone);
                    mTxtResult.setText(getString(R.string.win));
                    miCountPlayerWin++;
                    break;
                case 3:   // 平手
                    mImgViewCom.setImageResource(R.drawable.paper);
                    mTxtResult.setText(getString(R.string.draw));
                    miCountDraw++;
                    break;
            }
        }
    };

    private View.OnClickListener btnCloseGameOnClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent it = new Intent();

            Bundle bundle = new Bundle();
            bundle.putInt("COUNT_SET", miCountSet);
            bundle.putInt("COUNT_PLAYER_WIN", miCountPlayerWin);
            bundle.putInt("COUNT_COM_WIN", miCountComWin);
            bundle.putInt("COUNT_DRAW", miCountDraw);
            it.putExtras(bundle);

            setResult(RESULT_OK, it);
            finish();
        }
    };
}
