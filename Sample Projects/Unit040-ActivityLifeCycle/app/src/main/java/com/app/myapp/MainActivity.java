package com.app.myapp;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final String LOG_TAG = "ActivityLifeCycle";

    final static private int LAUNCH_GAME = 0;
    private TextView mTxtResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(LOG_TAG, "MainActivity.onCreate()");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnLaunchGame = findViewById(R.id.btnLaunchGame);
        btnLaunchGame.setOnClickListener(btnLaunchGameOnClick);

        mTxtResult = findViewById(R.id.txtResult);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode != LAUNCH_GAME)
            return;

        if (resultCode == RESULT_OK) {
            Bundle bundle = data.getExtras();

            int iCountSet = bundle.getInt("COUNT_SET");
            int iCountPlayerWin = bundle.getInt("COUNT_PLAYER_WIN");
            int iCountComWin = bundle.getInt("COUNT_COM_WIN");
            int iCountDraw = bundle.getInt("COUNT_DRAW");

            String s = "總共玩了" + iCountSet +
                    "局, 贏了" + iCountPlayerWin +
                    "局, 輸了" + iCountComWin +
                    "局, 平手" + iCountDraw + "局";
            mTxtResult.setText(s);
        }
    }

    private View.OnClickListener btnLaunchGameOnClick =
            new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent it = new Intent();
            it.setClass(MainActivity.this, GameActivity.class);
            startActivityForResult(it, LAUNCH_GAME);
        }
    };

    @Override
    protected void onStart() {
        Log.d(LOG_TAG, "MainActivity.onStart()");
        super.onStart();
    }

    @Override
    protected void onStop() {
        Log.d(LOG_TAG, "MainActivity.onStop()");
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        Log.d(LOG_TAG, "MainActivity.onDestroy()");
        super.onDestroy();
    }

    @Override
    protected void onPause() {
        Log.d(LOG_TAG, "MainActivity.onPause()");
        super.onPause();
    }

    @Override
    protected void onResume() {
        Log.d(LOG_TAG, "MainActivity.onResume()");
        super.onResume();
    }

    @Override
    protected void onRestart() {
        Log.d(LOG_TAG, "MainActivity.onRestart()");
        super.onRestart();
    }
}
