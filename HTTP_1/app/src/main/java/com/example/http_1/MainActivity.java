package com.example.http_1;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    private EditText editTextField1,editTextField2;
    private TextView textViewResult;
    private Button buttonGet,buttonPost,buttonReset,buttonData,buttonStatus;

    private String field1Data,field2Data;
    private String webAddress="https://api.thingspeak.com/";
    private String getApiKey="update?api_key=0FXU7FP592G6D80E";
    private String field1 = "&field1=";
    private String field2 = "&field2=";
    private String postApiKey="api_key=0FXU7FP592G6D80E";
    private String postCmd="update";
    private StringBuilder thingSpeakUrl;
    private URL url;
    private HttpURLConnection conn;
    private int code;
    private InputStream inputStream;
    private OutputStream outputStream;

    private String getField1Data="channels/2681900/fields/1/last.json?api_key=Z3F1W8Q1AM3ER3NT";
    private String getField2Data="channels/2681900/fields/2/last.json?api_key=Z3F1W8Q1AM3ER3NT";
    private String getStatusData="https://api.thingspeak.com/channels/2681900/status.json?api_key=Z3F1W8Q1AM3ER3NT";



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

        editTextField1 = (EditText)findViewById(R.id.editText_Field1);
        editTextField2 = (EditText)findViewById(R.id.editText_Field2);
        textViewResult = (TextView)findViewById(R.id.textView_result);
        textViewResult.setText("");

        buttonGet = (Button)findViewById(R.id.button_setget);
        buttonPost = (Button)findViewById(R.id.button_setpost);
        buttonReset = (Button)findViewById(R.id.button_reset);
        buttonData = (Button)findViewById(R.id.button_getDate);
        buttonStatus = (Button)findViewById(R.id.button_getStatus);

        buttonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textViewResult.setText("");
                editTextField1.setText("");
                editTextField2.setText("");
            }
        });

        buttonData.setOnClickListener(new View.OnClickListener() {
            private CharSequence[] fieldname={"Field 1","Field 2"};
            private int fieldnum;


            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Please select Field number");
                fieldnum = 0;
                builder.setSingleChoiceItems(fieldname, 0, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        fieldnum = which;
                    }
                });
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Log.d("main","fieldNum="+fieldnum);
                        thingSpeakUrl = new StringBuilder();
                        thingSpeakUrl.append(webAddress);
                        if(fieldnum==0){
                            thingSpeakUrl.append(getField1Data);
                        }else if (fieldnum==1){
                            thingSpeakUrl.append(getField2Data);
                        }
                        Log.d("main","thingSpeakUrl="+thingSpeakUrl);
                        new HttpDataField().start();
                        dialog.dismiss();
                    }
                });
                builder.show();
                        
            }
        });

        buttonStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                thingSpeakUrl = new StringBuilder();
                thingSpeakUrl.append(getStatusData);
                Log.d("main","thingspeakurl="+thingSpeakUrl);
                new HttpStatusData().start();
            }
        });

        buttonGet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(editTextField1.length()==0 || editTextField2.length()==0 ){
                    Toast.makeText(MainActivity.this,"Please input data",Toast.LENGTH_SHORT).show();
                } else {
                    field1Data = editTextField1.getText().toString();
                    field2Data = editTextField2.getText().toString();
                    new httpSetData().start();
                }
            }
        });

        buttonPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(editTextField1.length()==0 || editTextField2.length()==0 ){
                    Toast.makeText(MainActivity.this,"Please input data",Toast.LENGTH_SHORT).show();
                } else {
                    field1Data = editTextField1.getText().toString();
                    field2Data = editTextField2.getText().toString();
                    new HttpPostData().start();
                }
            }
        });
    }//onCreate()


    private class httpSetData extends Thread {
        @Override
        public void run() {
            super.run();
            thingSpeakUrl = new StringBuilder();
            thingSpeakUrl.append(webAddress);
            thingSpeakUrl.append(getApiKey+field1+field1Data+field2+field2Data);
            Log.d("main","thinkSpeak="+thingSpeakUrl);
            try {
                url = new URL(thingSpeakUrl.toString());
                conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");
                code = conn.getResponseCode();
                Log.d("main","code="+code);
            }catch (MalformedURLException e){
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            if(code==HttpURLConnection.HTTP_OK){
                try{
                    inputStream = conn.getInputStream();
                }catch (IOException e){
                    throw new RuntimeException(e);
                }
            }

            InputStreamReader reader = new InputStreamReader(inputStream);
            char[] buffer = new char[10];
            int count = 0;
            try{
                count = reader.read(buffer);
            }catch (IOException e){
                throw new RuntimeException(e);
            }
            Log.d("main","count="+count);
            String data = String.valueOf(buffer);
            try{
                inputStream.close();
            }catch (IOException e){
                throw new RuntimeException(e);
            }

            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    textViewResult.setText("Write number : "+data);
                }
            });
        }
    }

    private class HttpPostData extends Thread {
        @Override
        public void run() {
            super.run();
            thingSpeakUrl = new StringBuilder();
            thingSpeakUrl.append(webAddress);
            thingSpeakUrl.append(postCmd);
            Log.d("main","thingSpeakUrl="+thingSpeakUrl);
            try{
                url = new URL(thingSpeakUrl.toString());
                conn = (HttpURLConnection) url.openConnection();
                conn.setDoOutput(true);
                conn.setRequestMethod("POST");
            }catch (MalformedURLException e){
                throw new RuntimeException(e);
            } catch (ProtocolException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            try {
                outputStream = conn.getOutputStream();
                OutputStreamWriter writer = new OutputStreamWriter(outputStream);
                String param = postApiKey+field1+field1Data+field2+field2Data;
                Log.d("main","param="+param);
                writer.write(param);
                writer.flush();
                outputStream.close();

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            try {
                code = conn.getResponseCode();
                Log.d("main","code="+code);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            if(code==HttpURLConnection.HTTP_OK){
                try{
                    inputStream = conn.getInputStream();
                }catch (IOException e){
                    throw new RuntimeException(e);
                }
            }

            InputStreamReader reader = new InputStreamReader(inputStream);
            char[] buffer = new char[10];
            int count = 0;
            try{
                count = reader.read(buffer);
            }catch (IOException e){
                throw new RuntimeException(e);
            }
            Log.d("main","count="+count);
            String data = String.valueOf(buffer);
            try{
                inputStream.close();
            }catch (IOException e){
                throw new RuntimeException(e);
            }

            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    textViewResult.setText("Write number : "+data);
                }
            });

        }
    }
    private class HttpDataField extends Thread {
        private String data;
        @Override
        public void run() {
            super.run();
            try {
                url = new URL(thingSpeakUrl.toString());
                conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");
                code = conn.getResponseCode();
                Log.d("main","code="+code);
            } catch (MalformedURLException e) {
                throw new RuntimeException(e);
            } catch (ProtocolException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            if(code == HttpURLConnection.HTTP_OK){
                try {
                    inputStream = conn.getInputStream();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                InputStreamReader reader = new InputStreamReader(inputStream);
                BufferedReader stringReader = new BufferedReader(reader);
                try {
                    data = stringReader.readLine();
                    inputStream.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                runOnUiThread(new Runnable() {
                    JSONObject jsonObj;
                    @Override
                    public void run() {
                        textViewResult.setText(data+"\n\n");

                        try {
                            jsonObj = new JSONObject(data);
                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }

                        JSONArray jkey = jsonObj.names();
                        Log.d("main","jkey="+jkey);
                        StringBuilder dataJson = new StringBuilder();
                        for(int i=0;i<jkey.length();i++){
                            String key = jkey.optString(i);
                            Log.d("main","key="+key);
                            try {
                                String value = jsonObj.getString(key);
                                dataJson.append("key"+key+" , ");
                                dataJson.append("value="+value+"\n");
                            } catch (JSONException e) {
                                throw new RuntimeException(e);
                            }
                        }
                        textViewResult.append(dataJson.toString());
                    }
                });


            }
        }
    }

    private class HttpStatusData extends Thread{
        private String data;
        @Override
        public void run() {
            super.run();
            try {
                url = new URL(thingSpeakUrl.toString());
                conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");
                code = conn.getResponseCode();
                Log.d("main","code="+code);
            } catch (MalformedURLException e) {
                throw new RuntimeException(e);
            } catch (ProtocolException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            if(code == HttpURLConnection.HTTP_OK) {
                try {
                    inputStream = conn.getInputStream();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                InputStreamReader reader = new InputStreamReader(inputStream);
                BufferedReader stringReader = new BufferedReader(reader);
                try {
                    data = stringReader.readLine();
                    inputStream.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                runOnUiThread(new Runnable() {


                    @Override
                    public void run() {
                        textViewResult.setText(data + "\n\n");
                    }
                });
            }
        }
    }
}