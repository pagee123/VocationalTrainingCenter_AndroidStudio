package com.app.game;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class GameResultActivity extends AppCompatActivity {

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

        int iCountSet = bundle.getInt("COUNT_SET");
        int iCountPlayerWin = bundle.getInt("COUNT_PLAYER_WIN");
        int iCountComWin = bundle.getInt("COUNT_COM_WIN");
        int iCountDraw = bundle.getInt("COUNT_DRAW");

        mEdtCountSet.setText(Integer.toString(iCountSet));
        mEdtCountPlayerWin.setText(Integer.toString(iCountPlayerWin));
        mEdtCountComWin.setText(Integer.toString(iCountComWin));
        mEdtCountDraw.setText(Integer.toString(iCountDraw));
    }
}
