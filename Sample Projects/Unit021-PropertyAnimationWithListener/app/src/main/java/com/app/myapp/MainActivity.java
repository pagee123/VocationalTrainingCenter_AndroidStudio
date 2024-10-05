package com.app.myapp;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.TypedValue;
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
    private Button mBtnScale, mBtnShift, mBtnChangeColor;

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

        // 設定三個新按鈕
        mBtnScale = findViewById(R.id.btnScale);
        mBtnShift = findViewById(R.id.btnShift);
        mBtnChangeColor = findViewById(R.id.btnChangeColor);

        mBtnScale.setOnClickListener(btnScaleOnClick);
        mBtnShift.setOnClickListener(btnShiftOnClick);
        mBtnChangeColor.setOnClickListener(btnChangeColorOnClick);
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

    private View.OnClickListener btnScaleOnClick = new View.OnClickListener() {
        public void onClick(View v) {
            ValueAnimator animTxtScale = ValueAnimator.ofInt(0, 35);
            animTxtScale.setDuration(4000);
            animTxtScale.setRepeatCount(1);
            animTxtScale.setRepeatMode(ObjectAnimator.REVERSE);
            animTxtScale.setInterpolator(new LinearInterpolator());
            animTxtScale.addUpdateListener(
                    new ValueAnimator.AnimatorUpdateListener(){
                @Override
                public void onAnimationUpdate(ValueAnimator valueAnimator) {
                    int val = (Integer)valueAnimator.getAnimatedValue();
                    mTxtDemo.setTextSize(TypedValue.COMPLEX_UNIT_SP, 15+val);
                }
            });
            animTxtScale.start();
        }
    };

    private View.OnClickListener btnShiftOnClick = new View.OnClickListener() {
        public void onClick(View v) {
            float x, xEnd1, xEnd2;

            x = mTxtDemo.getX();
            xEnd1 = 0;
            xEnd2 = mLinLay.getWidth() - mTxtDemo.getWidth();

            ObjectAnimator animTxtMove1 =
                    ObjectAnimator.ofFloat(mTxtDemo, "x", x, xEnd1);
            animTxtMove1.setDuration(2000);
            animTxtMove1.setInterpolator(new AccelerateDecelerateInterpolator());

            ObjectAnimator animTxtMove2 =
                    ObjectAnimator.ofFloat(mTxtDemo, "x", xEnd1, xEnd2);
            animTxtMove2.setDuration(3000);
            animTxtMove2.setInterpolator(new  AccelerateDecelerateInterpolator());

            ObjectAnimator animTxtMove3 =
                    ObjectAnimator.ofFloat(mTxtDemo, "x", xEnd2, x);
            animTxtMove3.setDuration(2000);
            animTxtMove3.setInterpolator(new  AccelerateDecelerateInterpolator());

            AnimatorSet animTxtMove = new AnimatorSet();
            animTxtMove.playSequentially(animTxtMove1, animTxtMove2, animTxtMove3);
            animTxtMove.start();
        }
    };

    private View.OnClickListener btnChangeColorOnClick =
            new View.OnClickListener() {
        public void onClick(View v) {
            // 讓紅色的強度從0增加到255
            ValueAnimator animRedColor = ValueAnimator.ofInt(0, 255);
            animRedColor.setDuration(3000);
            animRedColor.setInterpolator(new LinearInterpolator());
            animRedColor.start();
            animRedColor.addUpdateListener(
                    new ValueAnimator.AnimatorUpdateListener(){
                @Override
                public void onAnimationUpdate(ValueAnimator valueAnimator) {
                    int val = (Integer)valueAnimator.getAnimatedValue();
                    mTxtDemo.setTextColor(Color.rgb(val, 0, 0));
                }
            });
        }
    };
}
