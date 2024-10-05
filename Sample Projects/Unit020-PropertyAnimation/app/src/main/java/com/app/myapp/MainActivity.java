package com.app.myapp;

import android.animation.AnimatorInflater;
import android.animation.ObjectAnimator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.BounceInterpolator;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private LinearLayout mLinLay;
    private TextView mTxtDemo;
    private Button mBtnDrop,
            mBtnTransparent,
            mBtnRotate;
    private float y, yEnd;
    private boolean mIsFallingFirst = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mLinLay = findViewById(R.id.linLay);
        mTxtDemo = findViewById(R.id.txtDemo);
        mBtnDrop = findViewById(R.id.btnDrop);
        mBtnTransparent = findViewById(R.id.btnTransparent);
        mBtnRotate = findViewById(R.id.btnRotate);

        mBtnDrop.setOnClickListener(btnDropOnClick);
        mBtnTransparent.setOnClickListener(btnTransparentOnClick);
        mBtnRotate.setOnClickListener(btnRotateOnClick);
    }

    private View.OnClickListener btnRotateOnClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            ObjectAnimator animTxtRotate =
                    ObjectAnimator.ofFloat(mTxtDemo, "rotation", 0, 360);
            animTxtRotate.setDuration(3000);
            animTxtRotate.setRepeatCount(1);
            animTxtRotate.setRepeatMode(ObjectAnimator.REVERSE);
            animTxtRotate.setInterpolator(
                    new AccelerateDecelerateInterpolator());
            animTxtRotate.start();
        }
    };

    private View.OnClickListener btnTransparentOnClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            ObjectAnimator animTxtAlpha =
                    ObjectAnimator.ofFloat(mTxtDemo, "alpha", 1, 0);
            animTxtAlpha.setDuration(2000);
            animTxtAlpha.setRepeatCount(1);
            animTxtAlpha.setRepeatMode(ObjectAnimator.REVERSE);
            animTxtAlpha.setInterpolator(new LinearInterpolator());
            animTxtAlpha.start();
        }
    };

    private View.OnClickListener btnDropOnClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (mIsFallingFirst) {
                // 計算掉落的y座標
                y = mTxtDemo.getY();
                yEnd = mLinLay.getHeight() -
                        mTxtDemo.getHeight();

                mIsFallingFirst = false;
            }

            ObjectAnimator animTxtFalling =
                    ObjectAnimator.ofFloat(mTxtDemo, "y", y, yEnd);
            animTxtFalling.setDuration(2000);
            animTxtFalling.setRepeatCount(ObjectAnimator.INFINITE);
            animTxtFalling.setInterpolator(new BounceInterpolator());
            animTxtFalling.start();
        }
    };
}
