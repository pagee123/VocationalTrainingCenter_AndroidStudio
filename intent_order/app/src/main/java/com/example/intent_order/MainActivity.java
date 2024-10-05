package com.example.intent_order;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.nio.charset.StandardCharsets;

public class MainActivity extends AppCompatActivity {

    private TextView textViewMain;
    private Button buttonMenu,buttonRegister;
    private Intent intent;
    private final int OrderCode=100;
    private ActivityResultLauncher<Intent> getResult;
    private String name,email;
    private boolean registerFlag;

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



        textViewMain = (TextView) findViewById(R.id.textView_main);
        textViewMain.setText("");

        buttonMenu = (Button)findViewById(R.id.button_menu);
        buttonRegister = (Button)findViewById(R.id.button_register);

        registerFlag = false;

        SharedPreferences sharePre = getSharedPreferences("register",MODE_PRIVATE);

        name = sharePre.getString("name","noname");

        if(name.equals("noname")){
            buttonRegister.setText("Register");
            registerFlag = true;
        }

        getResult = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult o) {
                int code = o.getResultCode();
                Log.d("main","code="+code);
                if(code == OrderCode){
                    intent = o.getData();
                    String data = intent.getStringExtra("order");
                    textViewMain.setText(data);

                }
            }
        });

        buttonMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(MainActivity.this,OrderActivity.class);
                //startActivity(intent);
                getResult.launch(intent);

            }
        });

        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(registerFlag) {
                    intent = new Intent(MainActivity.this, RegisterActivity.class);
                    startActivity(intent);
                }else {
                    email = sharePre.getString("email","noemail");
                    Dialog myBuilder = new Dialog(MainActivity.this);
                    myBuilder.setContentView(R.layout.dialog_layout);
                    EditText editTextName = (EditText) myBuilder.findViewById(R.id.editText_dlgName);
                    EditText editTextEmail = (EditText) myBuilder.findViewById(R.id.editText_dlgEmail);
                    Button buttonCancel = (Button) myBuilder.findViewById(R.id.button_dlgCancel);
                    Button buttonOK = (Button) myBuilder.findViewById(R.id.button_dlgOK);
                    Button buttonregister = (Button) myBuilder.findViewById(R.id.button_registerdia);

                    buttonOK.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if(editTextName.length() == 0 || editTextEmail.length() == 0){
                                Toast.makeText(MainActivity.this,"No input data",Toast.LENGTH_SHORT).show();
                            }else{
                                String nameDialog = editTextName.getText().toString();
                                String emailDialog = editTextEmail.getText().toString();
                                Log.d("main","name="+nameDialog+", email="+emailDialog);
                                if(name.equals(nameDialog) && email.equals(emailDialog)){
                                    textViewMain.setText("Login OK");
                                }else{
                                    Toast.makeText(MainActivity.this,"login fail",Toast.LENGTH_SHORT).show();
                                    textViewMain.setText("");
                                }
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

                    buttonregister.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            intent = new Intent(MainActivity.this, RegisterActivity.class);
                            startActivity(intent);
                            myBuilder.dismiss();
                        }
                    });

                    myBuilder.show();
                }
            }
        });

    }// onCreate()
}