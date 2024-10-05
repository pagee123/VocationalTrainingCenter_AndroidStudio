package com.app.myapp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    private final int REQUEST_PERMISSION_FOR_WRITE_EXTERNAL_STORAGE = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnOpenWebPage = findViewById(R.id.btnOpenWebPage);
        Button btnPlayMP3 = findViewById(R.id.btnPlayMP3);
        Button btnShowImage = findViewById(R.id.btnShowImage);

        btnOpenWebPage.setOnClickListener(btnOpenWebPageOnClick);
        btnPlayMP3.setOnClickListener(btnPlayMP3OnClick);
        btnShowImage.setOnClickListener(btnShowImageOnClick);

        askForWriteExternalStoragePermission();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        // 檢查收到的權限要求編號是否和我們送出的相同
        if (requestCode == REQUEST_PERMISSION_FOR_WRITE_EXTERNAL_STORAGE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(MainActivity.this, "取得 WRITE_EXTERNAL_STORAGE 權限",
                        Toast.LENGTH_SHORT)
                        .show();
                return;
            }
        }

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    private View.OnClickListener btnOpenWebPageOnClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Uri uri = Uri.parse("https://www.google.com");
            Intent it = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(it);
        }
    };

    private View.OnClickListener btnPlayMP3OnClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            // 建立Intent物件，設定是要檢視，也就是開啟檔案
            Intent it = new Intent(Intent.ACTION_VIEW);

            // 準備好檔案路徑，然後建立一個File物件
            String sFilePath = Environment.getExternalStorageDirectory().getPath() + File.separator + "song.mp3";
            File file = new File(sFilePath);
            Log.d("main","failepath="+sFilePath);

            // 利用FileProvider把File物件包成一個URI物件
            Uri uri = FileProvider.getUriForFile(MainActivity.this, BuildConfig.APPLICATION_ID, file);
            Log.d("main","uri="+uri);

            // 把URI物件傳給Intent，設定它的型態，然後開放讀取權限
            it.setDataAndType(uri, "audio/*");
            it.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);

            // 送出Intent物件
            startActivity(it);
        }
    };

    private View.OnClickListener btnShowImageOnClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            // 建立Intent物件，設定是要檢視，也就是開啟檔案
            Intent it = new Intent(Intent.ACTION_VIEW);

            // 準備好檔案路徑，然後建立一個File物件
            String sFilePath = Environment.getExternalStorageDirectory().getPath() + File.separator + "image.png";
            File file = new File(sFilePath);

            // 利用FileProvider把File物件包成一個URI物件
            Uri uri = FileProvider.getUriForFile(MainActivity.this, BuildConfig.APPLICATION_ID, file);

            // 把URI物件傳給Intent，設定它的型態，然後開放讀取權限
            it.setDataAndType(uri, "image/*");
            it.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);

            // 送出Intent物件
            startActivity(it);
        }
    };

    private void askForWriteExternalStoragePermission() {
        if (ContextCompat.checkSelfPermission(MainActivity.this,
                android.Manifest.permission.WRITE_EXTERNAL_STORAGE) !=
                PackageManager.PERMISSION_GRANTED) {
            // 這項功能尚未取得使用者的同意
            // 開始執行徵詢使用者的流程
            if (ActivityCompat.shouldShowRequestPermissionRationale(
                    MainActivity.this,
                    android.Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                AlertDialog.Builder altDlgBuilder =
                        new AlertDialog.Builder(MainActivity.this);
                altDlgBuilder.setTitle("提示");
                altDlgBuilder.setMessage("App需要讀寫SD卡中的資料。");
                altDlgBuilder.setIcon(android.R.drawable.ic_dialog_info);
                altDlgBuilder.setCancelable(false);
                altDlgBuilder.setPositiveButton("確定",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                // 顯示詢問使用者是否同意功能權限的對話盒
                                // 使用者答覆後會執行onRequestPermissionsResult()
                                ActivityCompat.requestPermissions(MainActivity.this,
                                        new String[]{
                                                android.Manifest.permission.WRITE_EXTERNAL_STORAGE},
                                        REQUEST_PERMISSION_FOR_WRITE_EXTERNAL_STORAGE);
                            }
                        });
                altDlgBuilder.show();

                return;
            } else {
                // 顯示詢問使用者是否同意功能權限的對話盒
                // 使用者答覆後會執行onRequestPermissionsResult()
                ActivityCompat.requestPermissions(MainActivity.this,
                        new String[]{
                                android.Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        REQUEST_PERMISSION_FOR_WRITE_EXTERNAL_STORAGE);

                return;
            }
        }
    }
}
