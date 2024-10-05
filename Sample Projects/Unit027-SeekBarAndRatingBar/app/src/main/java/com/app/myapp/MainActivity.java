package com.app.myapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RatingBar;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private RatingBar mRatingBar;
    private SeekBar mSeekBar;
    private TextView mTxtSeekBarProgress,
            mTxtRatingBarValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRatingBar = findViewById(R.id.ratingBar);
        mSeekBar = findViewById(R.id.seekBar);
        mTxtSeekBarProgress = findViewById(R.id.txtSeekBarProgress);
        mTxtRatingBarValue = findViewById(R.id.txtRatingBarValue);

        mSeekBar.setOnSeekBarChangeListener(seekBarOnChange);
        mRatingBar.setOnRatingBarChangeListener(ratingBarOnChange);
    }

    private SeekBar.OnSeekBarChangeListener seekBarOnChange =
            new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
            String s = getString(R.string.seek_bar_progress);
            mTxtSeekBarProgress.setText(s + String.valueOf(i));
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {
        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {
        }
    };

    private RatingBar.OnRatingBarChangeListener ratingBarOnChange =
            new RatingBar.OnRatingBarChangeListener() {
        @Override
        public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
            String s = getString(R.string.rating_bar_value);
            mTxtRatingBarValue.setText(s + String.valueOf(v));
        }
    };
}
