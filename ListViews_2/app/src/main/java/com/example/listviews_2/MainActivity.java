package com.example.listviews_2;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private ListView listViewData;
    private String[] name,version;
    private List<Map<String, String>> listData;
    private ListAdapter adapter;


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
        listViewData = (ListView)findViewById(R.id.listView_id);

        name = getResources().getStringArray(R.array.android_name);
        version = getResources().getStringArray(R.array.version);

        listData = new ArrayList< Map<String,String> > ();

        for(int i =0;i< name.length;i++){
            Map<String, String> data =new HashMap<>();
            data.put("name",name[i]);
            data.put("version",version[i]);
            listData.add(data);
        }

        Log.d("main","listData="+listData);
        adapter = new SimpleAdapter(MainActivity.this,listData,R.layout.item_layout,new String[]{"name","version"},
                new int[]{R.id.textView_itemName,R.id.textView_itemNo});
        listViewData.setAdapter(adapter);

        listViewData.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Map<String,String> item = (Map<String, String>) parent.getItemAtPosition(position);
                Toast.makeText(MainActivity.this,"select:"+item.get("name")+":"+item.get("version"),Toast.LENGTH_SHORT).show();
            }
        });
    }
}