package com.example.ex_3;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private ImageView imageViewTaipei,imageViewNewtaipei,imageViewTaoyuan;
    private AlertDialog.Builder builder;
    private int foodnum;
    private CharSequence[] foodname_taipei={"大鵬壽司","肉多多西門店","天母藏王鍋物"};
    private CharSequence[] foodname_newtaipei={"酉時暮光","逸茶酒室","淂藝洋行"};
    private CharSequence[] foodname_taoyuan={"饗厚牛排","ABV 日式居酒館","永芯茶檔 茶餐廳"};
    private TextView textViewResult;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        imageViewTaipei = (ImageView)findViewById(R.id.imageView_taipei);
        imageViewNewtaipei = (ImageView)findViewById(R.id.imageView_newtaipei);
        imageViewTaoyuan = (ImageView)findViewById(R.id.imageView_taoyuan);
        textViewResult = (TextView)findViewById(R.id.textView_data);
        textViewResult.setText("");
        registerForContextMenu(imageViewTaipei);
        registerForContextMenu(imageViewNewtaipei);
        registerForContextMenu(imageViewTaoyuan);


    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        switch (v.getId()){
            case R.id.imageView_taipei:
//                getMenuInflater().inflate(R.menu.menu_taipei,menu);
                builder = new AlertDialog.Builder(MainActivity.this);
                foodnum = 0;
                builder.setSingleChoiceItems(foodname_taipei, 0, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        foodnum = which;
                    }
                });
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        textViewResult.setText("台北:"+foodname_taipei[foodnum]);
                        dialog.dismiss();
                    }
                });
                builder.show();
                break;
            case R.id.imageView_newtaipei:
//                getMenuInflater().inflate(R.menu.menu_newtaipei,menu);
                builder = new AlertDialog.Builder(MainActivity.this);
                foodnum = 0;
                builder.setSingleChoiceItems(foodname_newtaipei, 0, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        foodnum = which;
                    }
                });
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        textViewResult.setText("新北:"+foodname_newtaipei[foodnum]);
                        dialog.dismiss();
                    }
                });
                builder.show();
                break;
            case R.id.imageView_taoyuan:
//                getMenuInflater().inflate(R.menu.menu_taoyuan,menu);
                builder = new AlertDialog.Builder(MainActivity.this);
                foodnum = 0;
                builder.setSingleChoiceItems(foodname_taoyuan, 0, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        foodnum = which;
                    }
                });
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        textViewResult.setText("桃園:"+foodname_taoyuan[foodnum]);
                        dialog.dismiss();
                    }
                });
                builder.show();
                break;

        }
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.menu_city,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.taipei:
                intent = new Intent(MainActivity.this, CityActivity.class);
                intent.putExtra("city",0);
                startActivity(intent);
//                getResult.launch(intent);
                break;
            case R.id.newtaipei:
                intent = new Intent(MainActivity.this, CityActivity.class);
                intent.putExtra("city",1);
                startActivity(intent);
//                getResult.launch(intent);
                break;
            case R.id.taoyuan:
                intent = new Intent(MainActivity.this, CityActivity.class);
                intent.putExtra("city",2);
                startActivity(intent);
//                getResult.launch(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}