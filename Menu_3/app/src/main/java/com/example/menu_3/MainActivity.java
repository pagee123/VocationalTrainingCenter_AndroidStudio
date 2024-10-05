package com.example.menu_3;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private ImageView imageViewAnime1,imageViewAnime2,imageViewAnime3;
    private AlertDialog.Builder builder;
    private int picnum;
    private CharSequence[] picName={"Anime1","Anime2","Anime3"};
    private boolean[] picCheck={true,false,false};

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

        imageViewAnime1 = (ImageView) findViewById(R.id.imageView_anime1);
        imageViewAnime2 = (ImageView) findViewById(R.id.imageView_anime2);
        imageViewAnime3 = (ImageView) findViewById(R.id.imageView_anime3);
        imageViewAnime1.setVisibility(ImageView.INVISIBLE);
        imageViewAnime2.setVisibility(ImageView.INVISIBLE);
        imageViewAnime3.setVisibility(ImageView.INVISIBLE);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.setup,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.radio_dialog:
                builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Please select on picture");
                picnum = 0;
                builder.setSingleChoiceItems(picName, 0, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        picnum = which;
                    }
                });
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if(picnum == 0){
                            imageViewAnime1.setVisibility(ImageView.VISIBLE);
                            imageViewAnime2.setVisibility(ImageView.INVISIBLE);
                            imageViewAnime3.setVisibility(ImageView.INVISIBLE);
                        }else if(picnum == 1){
                            imageViewAnime1.setVisibility(ImageView.INVISIBLE);
                            imageViewAnime2.setVisibility(ImageView.VISIBLE);
                            imageViewAnime3.setVisibility(ImageView.INVISIBLE);
                        }else if(picnum == 2){
                            imageViewAnime1.setVisibility(ImageView.INVISIBLE);
                            imageViewAnime2.setVisibility(ImageView.INVISIBLE);
                            imageViewAnime3.setVisibility(ImageView.VISIBLE);
                        }
                        dialog.dismiss();
                    }
                });
                builder.show();
                break;
            case R.id.check_dialog:
                builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Please Check Picture");
                builder.setMultiChoiceItems(picName, picCheck, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                        picCheck[which] = isChecked;
                    }
                });
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if(picCheck[0]){
                            imageViewAnime1.setVisibility(ImageView.VISIBLE);
                        }else{
                            imageViewAnime1.setVisibility(ImageView.INVISIBLE);
                        }
                        if(picCheck[1]){
                            imageViewAnime2.setVisibility(ImageView.VISIBLE);
                        }else{
                            imageViewAnime2.setVisibility(ImageView.INVISIBLE);
                        }
                        if(picCheck[2]){
                            imageViewAnime3.setVisibility(ImageView.VISIBLE);
                        }else{
                            imageViewAnime3.setVisibility(ImageView.INVISIBLE);
                        }
                        dialog.dismiss();
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        for(int i = 0;i<picCheck.length;i++){
                            picCheck[i]=false;
                        }
                        dialog.dismiss();
                    }
                });
                builder.show();
                break;
            case R.id.entry_dialog:
                Dialog myBuilder = new Dialog(MainActivity.this);
                myBuilder.setContentView(R.layout.dialog_layout);
                EditText editTextName = (EditText) myBuilder.findViewById(R.id.editText_dlgName);
                EditText editTextEmail = (EditText) myBuilder.findViewById(R.id.editText_dlgEmail);
                Button buttonCancel = (Button) myBuilder.findViewById(R.id.button_dlgCancel);
                Button buttonOK = (Button) myBuilder.findViewById(R.id.button_dlgOK);
                buttonOK.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(editTextName.length() == 0 || editTextEmail.length() == 0){
                            Toast.makeText(MainActivity.this,"No input data",Toast.LENGTH_SHORT).show();
                        }else{
                            String name = editTextName.getText().toString();
                            String email = editTextEmail.getText().toString();
                            Log.d("main","name="+name+", email="+email);
                        }

                        myBuilder.dismiss();
                    }
                });
                buttonCancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        myBuilder.dismiss();
                    }
                });
                myBuilder.show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}