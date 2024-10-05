package com.example.menu_4;

import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private ConstraintLayout layout;
    private ImageView imageViewPic;
    private int resetColor;
    private int color;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        layout = (ConstraintLayout)findViewById(R.id.main);
        imageViewPic = (ImageView) findViewById(R.id.imageView_pic);
        registerForContextMenu(layout);
        registerForContextMenu(imageViewPic);
        ColorDrawable layoutColor = (ColorDrawable) layout.getBackground();
        resetColor = layoutColor.getColor();


    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        switch (v.getId()){
            case R.id.main:
                if(menu.size() == 0) {
                    getMenuInflater().inflate(R.menu.menu1,menu);
                }
                break;
            case R.id.imageView_pic:
                getMenuInflater().inflate(R.menu.menu2,menu);
                break;
        }
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case R.id.menu1_blue:
                color = getColor(R.color.blue);
                layout.setBackgroundColor(color);
                break;
            case R.id.menu1_green:
                color = getColor(R.color.green);
                layout.setBackgroundColor(color);
                break;
            case R.id.menu1_reset:
                color = getColor(R.color.black);
                layout.setBackgroundColor(color);
                break;
            case R.id.menu2_Anime1:
                imageViewPic.setImageResource(R.drawable.frilan_r);
                break;
            case R.id.menu2_Anime2:
                imageViewPic.setImageResource(R.drawable.gate_r);
                break;

        }
        return super.onContextItemSelected(item);
    }
}