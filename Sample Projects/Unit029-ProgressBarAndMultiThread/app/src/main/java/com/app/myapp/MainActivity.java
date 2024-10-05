package com.app.myapp;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ProgressBar;

import java.lang.ref.WeakReference;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    private ProgressBar mProgressBar2;

    // 建立一個靜態Handler類別
    private static class StaticHandler extends Handler {
        private final WeakReference<MainActivity> mActivity;

        public StaticHandler(MainActivity activity) {
            mActivity = new WeakReference<MainActivity>(activity);
        }
    }

    // 建立靜態Handler的物件以避免出現記憶體漏洞
    public final StaticHandler mHandler = new StaticHandler(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mProgressBar2 = findViewById(R.id.progressBar2);

        Runnable r = new Runnable() {
            @Override
            public void run() {
                // 開始執行的時間
                Calendar begin = Calendar.getInstance();

                do {
                    Calendar now = Calendar.getInstance();

                    // 計算現在時間和開始時間的秒差
                    final int timeDiff =
                            60 * (now.get(Calendar.MINUTE) - begin.get(Calendar.MINUTE)) +
                            now.get(Calendar.SECOND) - begin.get(Calendar.SECOND);

                    // 判斷是否已經完成，如果完成，設定進度為100，然後結束
                    if (timeDiff * 2 > 100) {
                        mHandler.post(new Runnable() {
                            public void run() {
                                mProgressBar2.setProgress(100);
                            }
                        });

                        break;
                    }

                    // 依照秒差更新進度
                    mHandler.post(new Runnable() {
                        public void run() {
                            mProgressBar2.setProgress(timeDiff * 2);
                        }
                    });

                    // 更新第二進度
                    if (timeDiff * 4 < 100) {
                        mHandler.post(new Runnable() {
                            public void run() {
                                mProgressBar2.setSecondaryProgress(timeDiff * 4);
                            }
                        });
                    } else {
                        mHandler.post(new Runnable() {
                            public void run() {
                                mProgressBar2.setSecondaryProgress(100);
                            }
                        });
                    }
                } while (true);
            }
        };

        Thread t = new Thread(r);

        t.start();
    }
}
