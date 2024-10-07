package com.example.http_2;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private WebView webView;
    private WebSettings webset;
    private EditText editTextIpAddr;
    private Button buttonLink;
    private String ipAddr;

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

        webView = (WebView) findViewById(R.id.webView_id);
        webset = webView.getSettings();
        webset.setJavaScriptEnabled(true);
        webset.setSupportZoom(true);
        webset.setBuiltInZoomControls(true);

        editTextIpAddr = (EditText)findViewById(R.id.editText_ipAddr);
        buttonLink = (Button) findViewById(R.id.button_link);
        
        buttonLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(editTextIpAddr.length()==0){
                    ipAddr = "https://www.google.com.tw";
                    editTextIpAddr.setText(ipAddr);
                }else{
                    ipAddr = editTextIpAddr.getText().toString();
                }
                webView.setWebChromeClient(new WebChromeClient());
                webView.loadUrl(ipAddr);
            }
        });

    }
}