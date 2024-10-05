package com.example.intent_camera;

import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    private EditText editTextFilename;
    private ImageButton imgButtonCamera;
    private ImageView imageViewPic;
    private ActivityResultLauncher<Intent> getResult;
    private String filename;
    private File file;
    private Uri uri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        editTextFilename = (EditText)findViewById(R.id.editText_filename);
        imgButtonCamera = (ImageButton) findViewById(R.id.imageButton_camera);
        imageViewPic = (ImageView)findViewById(R.id.imageView_pic);
        
        getResult = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult o) {
                        int code = o.getResultCode();
                        Log.d("main","code="+code);
                        if(code == MainActivity.RESULT_OK){
                            Uri imguri = Uri.parse(file.getAbsolutePath());
                            Log.d("main","imguri="+imguri);
                            imageViewPic.setImageResource(0);
                            imageViewPic.setImageURI(imguri);
                        }
                    }
                });
        imgButtonCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(editTextFilename.length()==0){
                    filename = "image1.png";
                }else{
                    filename = editTextFilename.getText().toString();
                }
                String filePath = getFilesDir().getAbsolutePath()+ File.separator+filename;
                Log.d("main","filepath="+filePath);
                file = new File(filePath);

                uri = FileProvider.getUriForFile(MainActivity.this,
                        getApplicationContext().getPackageName(),file);
                Log.d("main","uri="+uri);

                String action = MediaStore.ACTION_IMAGE_CAPTURE;
                Intent intent = new Intent(action);
                intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION|Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
                intent.putExtra(MediaStore.EXTRA_OUTPUT,uri);
                getResult.launch(intent);
            }
        });

    }
}