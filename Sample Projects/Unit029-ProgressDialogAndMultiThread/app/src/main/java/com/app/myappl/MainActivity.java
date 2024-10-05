package com.app.myappl;

import android.app.ProgressDialog;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.lang.ref.WeakReference;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    private Button mBtnStartProgDlg;

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

        mBtnStartProgDlg = findViewById(R.id.btnStartProgDlg);
        mBtnStartProgDlg.setOnClickListener(btnStartProgDlgOnClick);
    }

    private View.OnClickListener btnStartProgDlgOnClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            // 建立和設定ProgressDialog
            final ProgressDialog progDlg = new ProgressDialog(MainActivity.this);
            progDlg.setTitle("執行中");
            progDlg.setIcon(android.R.drawable.ic_dialog_info);
            progDlg.setCancelable(false);
            progDlg.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            progDlg.setMax(100);
            progDlg.show();

            Runnable r = new Runnable() {
                @Override
                public void run() {
                    Calendar begin = Calendar.getInstance();

                    do {
                        Calendar now = Calendar.getInstance();
                        final int timeDiff = 60 * (now.get(Calendar.MINUTE) - begin.get(Calendar.MINUTE)) +
                                now.get(Calendar.SECOND) - begin.get(Calendar.SECOND);

                        if (timeDiff * 2 > 100) {
                            mHandler.post(new Runnable() {
                                public void run() {
                                    progDlg.setProgress(100);
                                }
                            });

                            break;
                        }

                        mHandler.post(new Runnable() {
                            public void run() {
                                progDlg.setProgress(timeDiff * 2);
                            }
                        });

                        if (timeDiff * 4 < 100)
                            mHandler.post(new Runnable() {
                                public void run() {
                                    progDlg.setSecondaryProgress(timeDiff * 4);
                                }
                            });
                        else
                            mHandler.post(new Runnable() {
                                public void run() {
                                    progDlg.setSecondaryProgress(100);
                                }
                            });
                    } while (true);

                    // 結束ProgressDialog
                    progDlg.dismiss();
                }
            };

            Thread t = new Thread(r);
            t.start();
        }
    };
}
