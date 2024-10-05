package com.app.game;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class GameResultActivity extends AppCompatActivity {

    private int iCountSet,
            iCountPlayerWin,
            iCountComWin,
            iCountDraw;

    private EditText mEdtCountSet,
            mEdtCountPlayerWin,
            mEdtCountComWin,
            mEdtCountDraw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_result);

        mEdtCountSet = findViewById(R.id.edtCountSet);
        mEdtCountPlayerWin = findViewById(R.id.edtCountPlayerWin);
        mEdtCountComWin = findViewById(R.id.edtCountComWin);
        mEdtCountDraw = findViewById(R.id.edtCountDraw);

        Button btnBackToGame = findViewById(R.id.btnBackToGame);
        btnBackToGame.setOnClickListener(btnBackToGameOnClick);

        Button btnSaveResult = findViewById(R.id.btnSaveResult);
        btnSaveResult.setOnClickListener(btnSaveResultOnClick);

        Button btnLoadResult = findViewById(R.id.btnLoadResult);
        btnLoadResult.setOnClickListener(btnLoadResultOnClick);

        Button btnClearResult = findViewById(R.id.btnClearResult);
        btnClearResult.setOnClickListener(btnClearResultOnClick);

        showResult();
    }

    private View.OnClickListener btnBackToGameOnClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            finish();
        }
    };

    private void showResult() {
        // 從 Bundle 物件中取出資料
        Bundle bundle = getIntent().getExtras();

        iCountSet = bundle.getInt("COUNT_SET");
        iCountPlayerWin = bundle.getInt("COUNT_PLAYER_WIN");
        iCountComWin = bundle.getInt("COUNT_COM_WIN");
        iCountDraw = bundle.getInt("COUNT_DRAW");

        mEdtCountSet.setText(Integer.toString(iCountSet));
        mEdtCountPlayerWin.setText(Integer.toString(iCountPlayerWin));
        mEdtCountComWin.setText(Integer.toString(iCountComWin));
        mEdtCountDraw.setText(Integer.toString(iCountDraw));
    }

    private View.OnClickListener btnSaveResultOnClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            SharedPreferences sp =
                    getSharedPreferences("GAME_RESULT", 0);

            sp.edit()
                    .putInt("COUNT_SET", iCountSet)
                    .putInt("COUNT_PLAYER_WIN", iCountPlayerWin)
                    .putInt("COUNT_COM_WIN", iCountComWin)
                    .putInt("COUNT_DRAW", iCountDraw)
                    .commit();

            Toast.makeText(GameResultActivity.this, "儲存完成", Toast.LENGTH_LONG)
                    .show();
        }
    };

    private View.OnClickListener btnLoadResultOnClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            SharedPreferences sp =
                    getSharedPreferences("GAME_RESULT", 0);

            iCountSet = sp.getInt("COUNT_SET", 0);
            iCountPlayerWin = sp.getInt("COUNT_PLAYER_WIN", 0);
            iCountComWin = sp.getInt("COUNT_COM_WIN", 0);
            iCountDraw = sp.getInt("COUNT_DRAW", 0);

            mEdtCountSet.setText(Integer.toString(iCountSet));
            mEdtCountPlayerWin.setText(Integer.toString(iCountPlayerWin));
            mEdtCountComWin.setText(Integer.toString(iCountComWin));
            mEdtCountDraw.setText(Integer.toString(iCountDraw));

            Toast.makeText(GameResultActivity.this, "載入完成", Toast.LENGTH_LONG)
                    .show();
        }
    };

    private View.OnClickListener btnClearResultOnClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            SharedPreferences sp =
                    getSharedPreferences("GAME_RESULT", 0);

            sp.edit().clear().commit();

            mEdtCountSet.setText("0");
            mEdtCountPlayerWin.setText("0");
            mEdtCountComWin.setText("0");
            mEdtCountDraw.setText("0");

            Toast.makeText(GameResultActivity.this, "清除完成", Toast.LENGTH_LONG)
                    .show();
        }
    };
}
