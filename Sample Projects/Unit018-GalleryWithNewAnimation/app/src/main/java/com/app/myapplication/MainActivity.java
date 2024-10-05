package com.app.myapplication;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.ViewSwitcher;

public class MainActivity extends AppCompatActivity implements ViewSwitcher.ViewFactory {

    private GridView mGridView;
    private ImageSwitcher mImgSwitcher;

    // 這個縮圖陣列是App專案的影像資源ID
    Integer[] miThumbImgArr = {
            R.drawable.img01th, R.drawable.img02th, R.drawable.img03th,
            R.drawable.img04th, R.drawable.img05th, R.drawable.img06th,
            R.drawable.img07th, R.drawable.img08th};

    // 這個圖片陣列是App專案的影像資源ID
    private Integer[] miImgArr = {
            R.drawable.img01, R.drawable.img02, R.drawable.img03,
            R.drawable.img04, R.drawable.img05, R.drawable.img06,
            R.drawable.img07, R.drawable.img08};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 建立一個ImageAdapter型態的物件
        ImageAdapter imgAdap = new ImageAdapter(getApplicationContext(), miThumbImgArr);

        mGridView = findViewById(R.id.gridView);
        mGridView.setAdapter(imgAdap);

        // 設定GridView物件的OnItemClickListener
        mGridView.setOnItemClickListener(gridViewOnItemClick);

        mImgSwitcher = findViewById(R.id.imgSwitcher);

        mImgSwitcher.setFactory(this);	// 主程式類別必須implements ViewSwitcher.ViewFactory
    }

    private AdapterView.OnItemClickListener gridViewOnItemClick =
            new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(
                AdapterView<?> adapterView, View view, int i, long l) {
            int r = (int)(Math.random()*3 + 1);
            switch (r) {
                case 1:
                    mImgSwitcher.setInAnimation(AnimationUtils.loadAnimation(
                            getApplicationContext(), R.anim.alpha_in));
                    mImgSwitcher.setOutAnimation(AnimationUtils.loadAnimation(
                            getApplicationContext(), R.anim.alpha_out));
                    break;
                case 2:
                    mImgSwitcher.setInAnimation(AnimationUtils.loadAnimation(
                            getApplicationContext(), R.anim.trans_in));
                    mImgSwitcher.setOutAnimation(AnimationUtils.loadAnimation(
                            getApplicationContext(), R.anim.trans_out));
                    break;
                case 3:
                    mImgSwitcher.setInAnimation(AnimationUtils.loadAnimation(
                            getApplicationContext(), R.anim.scale_rotate_in));
                    mImgSwitcher.setOutAnimation(AnimationUtils.loadAnimation(
                            getApplicationContext(), R.anim.scale_rotate_out));
                    break;
            }

            mImgSwitcher.setImageResource(miImgArr[i]);
        }
    };

    @Override
    public View makeView() {
        ImageView v = new ImageView(this);
        v.setBackgroundColor(0xFF000000);
        v.setScaleType(ImageView.ScaleType.FIT_CENTER);
        v.setLayoutParams(new ImageSwitcher.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));
        v.setBackgroundColor(Color.WHITE);
        return v;
    }
}
