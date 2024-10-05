package com.example.menu_1;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private TextView textViewName;
    private ImageView imageViewPic;
    private final int ChangePicture = 10;
    private final int ChangeColor = 20;
    private final int ChangeText = 30;
    private final int Text1 = 31;
    private final int Text2 = 32;
    private boolean picFlag;
    private boolean colorFlag;


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
    textViewName = (TextView)findViewById(R.id.textView_name);
    textViewName.setText(getString(R.string.text1));

    imageViewPic = (ImageView)findViewById(R.id.imageView_pic);
    picFlag = true;
    colorFlag = true;

    }//onCreated()

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(1,ChangePicture,Menu.NONE,"Change Picture");
        menu.add(1,ChangeColor,Menu.NONE,"Change Color");
        SubMenu subMenu = menu.addSubMenu(1,ChangeText,Menu.NONE,"Change Text");
        subMenu.add(1,Text1,Menu.NONE,"Text1");
        subMenu.add(1,Text2,Menu.NONE,"Text2");
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case ChangePicture:
                if(picFlag){
                    imageViewPic.setImageResource(R.drawable.frilan);
                    picFlag=false;
                }else{
                    imageViewPic.setImageResource(R.drawable.gate);
                    picFlag=true;
                }
                break;
            case ChangeColor:
                if(colorFlag){
                    textViewName.setTextColor(getColor(R.color.color_2));
                    colorFlag=false;
                }else{
                    textViewName.setTextColor(getColor(R.color.color_1));
                    colorFlag=true;
                }
                break;
            case Text1:
                textViewName.setText(getString(R.string.text1));
                break;
            case Text2:
                textViewName.setText(getString(R.string.text2));
                break;

        }
        return super.onContextItemSelected(item);
    }
}