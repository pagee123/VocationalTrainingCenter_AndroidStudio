package com.example.menu_2;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {


    private TextView textViewName;
    private ImageView imageViewPic;
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
        textViewName.setText("");

        imageViewPic = (ImageView)findViewById(R.id.imageView_pic);
        
        picFlag = true;
        colorFlag = true;
    }//oncread()

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.setup,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.ChangePicture:
                if(picFlag){
                    imageViewPic.setImageResource(R.drawable.gate_r);
                    picFlag=false;
                }else{
                    imageViewPic.setImageResource(R.drawable.frilan_r);
                    picFlag=true;
                }
                break;
            case R.id.ChangeColor:
                if(colorFlag){
                    textViewName.setTextColor(0xFF03A9F4);
                    colorFlag=false;
                }else{
                    textViewName.setTextColor(0xFF4CAF50);
                    colorFlag=true;
                }
                break;
            case R.id.Text1:
                textViewName.setText("すずめの戸締まり");
                break;
            case R.id.Text2:
                textViewName.setText("葬送のフリーレン");
                break;
            case R.id.gate:
                textViewName.setText("すずめの戸締まり");
                imageViewPic.setImageResource(R.drawable.gate_r);
                break;
            case R.id.frilan:
                textViewName.setText("葬送のフリーレン");
                imageViewPic.setImageResource(R.drawable.frilan_r);
                break;
            case R.id.BlackTea:
                textViewName.setText("BlackTea");
                imageViewPic.setImageResource(R.drawable.blacktea);
                break;
            case R.id.Cola:
                textViewName.setText("Cola");
                imageViewPic.setImageResource(R.drawable.cola_1);
                break;
            case R.id.About:
                showDialog();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
    private void showDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Menu about");
        builder.setIcon(android.R.drawable.ic_dialog_info);
        builder.setMessage("This is Menu test");
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.show();
    }
}