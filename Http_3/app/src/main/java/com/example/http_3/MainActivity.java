package com.example.http_3;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextClock;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    private EditText editTextIpAddr;
    private TextView textViewData;
    private Switch switchLed1,switchLed2,switchLed3,switchLed4,switchLed5;
    private static boolean led1Flag=false;
    private static boolean led2Flag=false;
    private static boolean led3Flag=false;
    private static boolean led4Flag=false;
    private static boolean led5Flag=false;
    private Button buttonLink;
    private boolean addrFlag=false;
    private String ipAddr;
    private StringBuilder serverAddr;
    private URL url;
    private HttpURLConnection conn;
    private int code;
    private String ledParam;
    private String data;


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

        editTextIpAddr =(EditText) findViewById(R.id.editText_ipaddr);
        textViewData = (TextView) findViewById(R.id.textView_data);
        textViewData.setText("Receive data:\n");

        switchLed1 = (Switch)findViewById(R.id.switch_led1);
        switchLed2 = (Switch)findViewById(R.id.switch_led2);
        switchLed3 = (Switch)findViewById(R.id.switch_led3);
        switchLed4 = (Switch)findViewById(R.id.switch_led4);
        switchLed5 = (Switch)findViewById(R.id.switch_led5);

        switchLed1.setChecked(led1Flag);
        switchLed2.setChecked(led2Flag);
        switchLed3.setChecked(led3Flag);
        switchLed4.setChecked(led4Flag);
        switchLed5.setChecked(led5Flag);

        buttonLink = (Button)findViewById(R.id.button_link);
        buttonLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(editTextIpAddr.length()==0){
                    Toast.makeText(MainActivity.this,"Please input ip address",Toast.LENGTH_SHORT).show();
                    addrFlag = false;
                }else{
                    ipAddr = editTextIpAddr.getText().toString();
                    new HttpGetData().start();
                }
            }
        });
        switchLed1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                led1Flag = isChecked;
                if(isChecked){
                    ledParam = "led=1&state=on";
                }else{
                    ledParam = "led=1&state=off";
                }
                if(addrFlag){
                    new HttpPostData().start() ;
                }else{
                    Toast.makeText(MainActivity.this,"ip address connect fail",Toast.LENGTH_SHORT).show();
                }
            }
        });
        switchLed2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                led2Flag = isChecked;
                if(isChecked){
                    ledParam = "led=2&state=on";
                }else{
                    ledParam = "led=2&state=off";
                }
                if(addrFlag){
                    new HttpPostData().start() ;
                }else{
                    Toast.makeText(MainActivity.this,"ip address connect fail",Toast.LENGTH_SHORT).show();
                }
            }
        });
        switchLed3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                led1Flag = isChecked;
                if(isChecked){
                    ledParam = "led=3&state=on";
                }else{
                    ledParam = "led=3&state=off";
                }
                if(addrFlag){
                    new HttpPostData().start() ;
                }else{
                    Toast.makeText(MainActivity.this,"ip address connect fail",Toast.LENGTH_SHORT).show();
                }
            }
        });
        switchLed4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                led1Flag = isChecked;
                if(isChecked){
                    ledParam = "led=4&state=on";
                }else{
                    ledParam = "led=4&state=off";
                }
                if(addrFlag){
                    new HttpPostData().start() ;
                }else{
                    Toast.makeText(MainActivity.this,"ip address connect fail",Toast.LENGTH_SHORT).show();
                }
            }
        });
        switchLed5.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                led1Flag = isChecked;
                if(isChecked){
                    ledParam = "led=5&state=on";
                }else{
                    ledParam = "led=5&state=off";
                }
                if(addrFlag){
                    new HttpPostData().start() ;
                }else{
                    Toast.makeText(MainActivity.this,"ip address connect fail",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private class HttpGetData extends Thread{
        private InputStream inputStream;
        private InputStreamReader reader;

        @Override
        public void run() {
            super.run();
            serverAddr = new StringBuilder();
            serverAddr.append("http://"+ipAddr+"/home");
            Log.d("main","serverAddr="+serverAddr);

            try {
                url = new URL(serverAddr.toString());
                conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");
                code = conn.getResponseCode();
                Log.d("main","code="+code);

            } catch (MalformedURLException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            if(code==HttpURLConnection.HTTP_OK){
                addrFlag = true;
                try {
                    inputStream = conn.getInputStream();
                    reader = new InputStreamReader(inputStream);
                    char[] buffer = new char[500];
                    int count = reader.read(buffer);
                    Log.d("main","count="+count);
                    data = String.valueOf(buffer);
                    Log.d("main","data="+data);
                    inputStream.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        textViewData.setText(data);
                    }
                });
            }
        }
    }

    private class HttpPostData extends Thread{
        private OutputStream outputStream;
        private OutputStreamWriter writer;
        private InputStream inputStream;

        @Override
        public void run() {
            super.run();
            serverAddr = new StringBuilder();
            serverAddr.append("http://"+ipAddr+"/home/led");
            try {
                url = new URL(serverAddr.toString());
                conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("POST");

            } catch (MalformedURLException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            try {
                outputStream = conn.getOutputStream();
                writer = new OutputStreamWriter(outputStream);
                writer.write(ledParam);
                writer.flush();
                outputStream.close();
                code = conn.getResponseCode();
                Log.d("main","code="+code);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            if(code==HttpURLConnection.HTTP_OK){
                try {
                    inputStream = conn.getInputStream();
                    InputStreamReader reader = new InputStreamReader(inputStream);
                    char[] buffer = new char[100];
                    reader.read(buffer);
                    data = String.valueOf(buffer);
                    inputStream.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        textViewData.setText(data);
                    }
                });

            }

        }
    }
}