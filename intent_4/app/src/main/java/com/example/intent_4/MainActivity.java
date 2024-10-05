package com.example.intent_4;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private Button buttonAct1,butonAct2;
    private EditText editTextInputAct1,editTextAct2;
    private TextView textViewData,textViewData2;
    private ActivityResultLauncher<Intent> getResult;
    private final int ACT1Code=100;
    private final int ACT2Code=200;
    private RadioGroup radioGroupAnime;
    private RadioButton rButtonAnime3,rButtonAnime1,rButtonAnime2;

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

        buttonAct1 = (Button) findViewById(R.id.button_mainAct1);
        editTextInputAct1 = (EditText) findViewById(R.id.editText_mainAct1);
        textViewData = (TextView) findViewById(R.id.textView_main);
        textViewData.setText("");
        textViewData2 = (TextView) findViewById(R.id.textView_main2);
        textViewData2.setText("");

        getResult = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult o) {
                        int code = o.getResultCode();
                        Log.d("main","code="+code);
                        if(code == ACT1Code){
                            Intent backIntent = o.getData();
                            String name = backIntent.getStringExtra("name");
                            String act1 = backIntent.getStringExtra("act1");
                            textViewData.setText(name+" : "+act1+"\n");
                        } else if (code == ACT2Code){
                            Intent backIntent = o.getData();
                            String name = backIntent.getStringExtra("name");
                            String act2 = backIntent.getStringExtra("act2");
                            textViewData2.setText(name+" : "+act2+"\n");
                        }
                    }
                });

        buttonAct1.setOnClickListener(new View.OnClickListener() {
            private String data1;

            @Override
            public void onClick(View v) {
                if (editTextInputAct1.length() == 0) {
                    data1 = "No Data 1";
                } else {
                    data1 = editTextInputAct1.getText().toString();
                }

                Intent intent = new Intent(MainActivity.this, MainActivity1.class);
                intent.putExtra("data1",data1);
                getResult.launch(intent);

            }
        });

        editTextAct2 = (EditText) findViewById(R.id.editText_mainact2);
        butonAct2 = (Button) findViewById(R.id.button_mainact2);

        butonAct2.setOnClickListener(new View.OnClickListener() {
            private String data2;

            @Override
            public void onClick(View v) {
                if(editTextAct2.length() == 0){
                    data2 = "No data 2";
                }else{
                    data2 = editTextAct2.getText().toString();
                }
                Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                intent.putExtra("data2",data2);
                getResult.launch(intent);
            }
        });

        radioGroupAnime = (RadioGroup)findViewById(R.id.radioGroup_Anime);
        rButtonAnime1 = (RadioButton)findViewById(R.id.radioButton_Anime1);
        rButtonAnime2 = (RadioButton)findViewById(R.id.radioButton_Anime3);
        rButtonAnime3 = (RadioButton)findViewById(R.id.radioButton_Anime2);

        radioGroupAnime.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                Intent intent = new Intent(MainActivity.this,AnimeActivity.class);
                switch (checkedId){
                    case R.id.radioButton_Anime1:
                        intent.putExtra("name",rButtonAnime1.getText().toString());
                        intent.putExtra("img",R.drawable.frilan);
                        break;
                    case R.id.radioButton_Anime2:
                        intent.putExtra("name",rButtonAnime2.getText().toString());
                        intent.putExtra("img",R.drawable.gate);
                        break;
                    case R.id.radioButton_Anime3:
                        intent.putExtra("name",rButtonAnime3.getText().toString());
                        intent.putExtra("img",R.drawable.sliver);
                        break;
                }
                startActivity(intent);
            }
        });




    }//  onCreate()
}